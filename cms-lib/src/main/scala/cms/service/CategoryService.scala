package cms.service

import cms.domain.Category
import cms.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.collection.JavaConverters._

@Service
class CategoryService {

    @Autowired
    var categoryRepository: CategoryRepository = null

    def categories() = categoryRepository.findByLevel(0)

    def category(categoryId: Integer) = categoryRepository.findOne(categoryId)

    def save(parentId: Integer, category: Category) = {
        if (parentId != null) {
            val parentCategory = this.category(parentId)
            if (parentCategory != null) {
                parentCategory.hasChildren = true
                category.parentCategory = parentCategory
                category.level = parentCategory.level + 1
            }
        }
        categoryRepository.save(category)
    }

    def remove(categoryId: Integer) {
        def removeCategory(category: Category) {
            if (category.hasChildren) {
                for (c <- category.childrenCategories().asScala) {
                    removeCategory(c)
                }
            }
            categoryRepository.delete(category)
        }
        removeCategory(category(categoryId))
    }
}
