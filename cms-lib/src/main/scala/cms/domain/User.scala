package cms.domain

import java.util
import java.util.Date
import javax.persistence._

import org.springframework.data.annotation.{CreatedDate, LastModifiedDate}
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(uniqueConstraints = Array(new UniqueConstraint(columnNames = Array("USER_ID"))))
@EntityListeners(value = Array(classOf[AuditingEntityListener]))
class User extends UserDetails with ScalaObject {
    @Id
    @GeneratedValue
    var id: Integer = null

    var userId: String = null

    var password: String = null

    @Transient
    var password2: String = null

    var userName: String = null

    var auth: String = null

    var salt: String = null

    @CreatedDate
    var createDate: Date = null

    @LastModifiedDate
    var updateDate: Date = null

    @Transient
    var authorities: util.Collection[_ <: GrantedAuthority] = null

    override def isEnabled: Boolean = true

    override def isAccountNonExpired: Boolean = true

    override def isCredentialsNonExpired: Boolean = true

    override def getAuthorities: util.Collection[_ <: GrantedAuthority] = authorities

    override def isAccountNonLocked: Boolean = true

    override def getUsername = userName

    override def getPassword = password
}
