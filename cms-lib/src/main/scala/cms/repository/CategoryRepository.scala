package cms.repository

import cms.domain.Category
import org.springframework.data.jpa.repository.{JpaRepository, JpaSpecificationExecutor}

trait CategoryRepository extends JpaRepository[Category, Integer] with JpaSpecificationExecutor[Category] {
    def findByLevel(level: Int) : java.util.List[Category]
}
