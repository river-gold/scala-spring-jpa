package cms.service

import cms.domain.User
import cms.repository.UserRepository
import org.apache.commons.lang3.{RandomStringUtils, StringUtils}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.dao.SaltSource
import org.springframework.security.authentication.encoding.ShaPasswordEncoder
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.provisioning.UserDetailsManager
import kesti4j.core.Implicits._

class UserAuthService extends UserDetailsManager {

    @Autowired
    private val userRepository: UserRepository = null

    @Autowired
    private val saltSource: SaltSource = null

    @Autowired
    private val passwordEncoder: ShaPasswordEncoder = null

    private val codes = Array[Char]('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '\\', '|', '[', ']', '{', '}', ';', ':', '\'', '"', ',', '.', '<', '>', '/', '?',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

    override def loadUserByUsername(userId: String) = {
        val user = userRepository.findByUserId(userId)
        user.ifPresent((u: User) => u.authorities = AuthorityUtils.createAuthorityList(u.auth.split(","): _*))
        user.orElse(null)
    }

    override def deleteUser(userId: String) = userRepository.findByUserId(userId).ifPresent((u: User) => userRepository.delete(u))

    override def createUser(user: UserDetails) {
        val newUser = user.asInstanceOf[User]

        if (StringUtils.isBlank(newUser.userId)) {
            throw new RuntimeException("사용자 아이디가 없습니다.")
        }

        if (StringUtils.isBlank(newUser.userName)) {
            throw new RuntimeException("사용자 명이 없습니다.")
        }

        if (StringUtils.isBlank(newUser.password)) {
            throw new RuntimeException("비밀번호가 없습니다.")
        }

        if (!StringUtils.equals(newUser.password, newUser.password2)) {
            throw new RuntimeException("기존 비밀번호가 동일하지 않습니다.")
        }

        newUser.salt = RandomStringUtils.random(45, codes: _*)
        newUser.password = passwordEncoder.encodePassword(newUser.password, saltSource.getSalt(newUser))

        userRepository.save(newUser)
    }

    override def changePassword(oldPassword: String, newPassword: String) {
        if (StringUtils.isBlank(oldPassword)) {
            throw new RuntimeException("기존 비밀번호가 없습니다.")
        }

        if (StringUtils.isBlank(newPassword)) {
            throw new RuntimeException("새 비밀번호가 없습니다.")
        }

        if (StringUtils.equals(oldPassword, newPassword)) {
            throw new RuntimeException("기존 비밀번호와 동일합니다.")
        }

        val user = loadUserByUsername(SecurityContextHolder.getContext.getAuthentication.getName)
        val encodeOldPassword = passwordEncoder.encodePassword(oldPassword, saltSource.getSalt(user))

        if (!StringUtils.equals(user.password, encodeOldPassword)) {
            throw new RuntimeException("비밀번호가 틀렸습니다.")
        }

        user.salt = RandomStringUtils.random(45, codes: _*)
        user.password = passwordEncoder.encodePassword(newPassword, saltSource.getSalt(user))

        userRepository.save(user)

        SecurityContextHolder.clearContext()

    }

    override def userExists(username: String) = userRepository.findByUserId(username).isPresent

    override def updateUser(user: UserDetails) = userRepository.save(user.asInstanceOf[User])
}
