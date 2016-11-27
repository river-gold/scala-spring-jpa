package cms.repository

import cms.domain.Post
import org.springframework.data.jpa.repository.{JpaRepository, JpaSpecificationExecutor}

trait PostRepository extends JpaRepository[Post, Integer] with JpaSpecificationExecutor[Post] {
}
