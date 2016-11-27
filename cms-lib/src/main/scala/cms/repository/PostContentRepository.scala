package cms.repository

import cms.domain.PostContent
import org.springframework.data.jpa.repository.{JpaSpecificationExecutor, JpaRepository}
import org.springframework.stereotype.Repository

trait PostContentRepository extends JpaRepository[PostContent, Integer] with JpaSpecificationExecutor[PostContent]{
}
