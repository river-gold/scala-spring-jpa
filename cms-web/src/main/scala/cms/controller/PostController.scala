package cms.controller

import cms.domain.{Post, PostContent}
import cms.repository.PostRepository
import cms.service.PostService
import cms.view.Posts
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.{Propagation, Transactional}
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._
import scala.xml.dtd.DocType

@Controller
class PostController {

    @Autowired
    var postService: PostService = null

    @Autowired
    var documentRepository: PostRepository = null


    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @RequestMapping(value = Array("/posts"), method = Array(RequestMethod.GET))
    def list(model: Model, pageable: Pageable) = {
        model.addAttribute("posts", postService.posts(pageable))
    }

    @Transactional
    @RequestMapping(value = Array("/posts"), method = Array(RequestMethod.POST))
    def save(post:Post, postContent: PostContent) = {
        postService.save(post, postContent)
    }

    @Transactional
    @RequestMapping(value = Array("/posts/{postId}"), method = Array(RequestMethod.GET))
    def get(@PathVariable postId : Integer) = {
        postService.viewCountPlus(postId)
        postService.post(postId)
        "posts"
    }

    @Transactional
    @RequestMapping(value = Array("/posts/{postId}"), method = Array(RequestMethod.DELETE))
    def remove(@PathVariable postId : Integer) = postService.remove(postId)
}
