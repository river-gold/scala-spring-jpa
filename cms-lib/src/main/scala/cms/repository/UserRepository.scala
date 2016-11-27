package cms.repository

import java.util.Optional

import cms.domain.User
import org.springframework.data.jpa.repository.{JpaRepository, JpaSpecificationExecutor}

trait UserRepository extends JpaRepository[User, Integer] with JpaSpecificationExecutor[User] {
    def findByUserId(userId: String) : Optional[User]
}
