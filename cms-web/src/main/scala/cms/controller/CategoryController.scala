package cms.controller

import cms.domain.Category
import cms.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.{Propagation, Transactional}
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._

@Controller
class CategoryController {

    @Autowired
    var categoryService: CategoryService = null

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @RequestMapping(value = Array("/categories"), method = Array(RequestMethod.GET))
    def list(model: Model) = {
        model.addAttribute("categories", categoryService.categories())
        "categories"
    }

    @Transactional
    @RequestMapping(value = Array("/categories"), method = Array(RequestMethod.POST))
    def save(@RequestParam parentId: Integer, category: Category) = {
        categoryService.save(parentId, category)
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @RequestMapping(value = Array("/categories/{categoryId}"), method = Array(RequestMethod.GET))
    def get(@PathVariable categoryId: Integer) = categoryService.category(categoryId)

    @Transactional
    @RequestMapping(value = Array("/categories/{categoryId}"), method = Array(RequestMethod.DELETE))
    def remove(@PathVariable categoryId: Integer) = categoryService.remove(categoryId)

}
