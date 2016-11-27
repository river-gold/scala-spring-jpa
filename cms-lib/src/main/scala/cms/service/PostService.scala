package cms.service

import cms.domain.{Post, PostContent}
import cms.repository.{PostContentRepository, PostRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService {
    @Autowired
    var postRepository: PostRepository = null

    @Autowired
    var postContentRepository: PostContentRepository = null

    def remove(postId: Integer) = {
        postContentRepository.delete(postId)
        postRepository.delete(postId)

    }

    def post(postId: Integer) = postRepository.findOne(postId)

    def viewCountPlus(postId: Integer) = postRepository.findOne(postId).viewCount += 1

    def save(post: Post, postContent: PostContent) = {
        val savedPost = postRepository.save(post)
        postContent.id = savedPost.id
        savedPost.postContent = postContentRepository.save(postContent)
        savedPost
    }

    def posts(pageable: Pageable) = postRepository.findAll(pageable)


}
