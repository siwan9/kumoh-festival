package kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.service

import jakarta.transaction.Transactional
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostType
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.entity.Post
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.repository.PostRepository
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.Role
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SessionUser
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.repository.UserRepository
import kr.ac.kumoh.ce.s20200237.databaseProject.post.repository.BoothRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PostService(val postRepository: PostRepository,
                  val userRepository: UserRepository,
                  var boothRepository: BoothRepository) {
    @Transactional
    fun post(postRequest: PostRequest, user : SessionUser){
        if (postRequest.type == PostType.announcement)
            if(user.role != Role.manager)
                throw RuntimeException("Authorization fault")

        var user : User = userRepository.findByEmail(user.email)
                .orElseThrow{RuntimeException("user not found")}
        var booth : Booth = boothRepository.findById(postRequest.booth_id)
                .orElseThrow{RuntimeException("booth not found")}

        postRepository.save(Post(null, user, booth, postRequest.title, postRequest.content, postRequest.type, LocalDateTime.now()))
    }

    @Transactional
    fun check(post_id : Long) : PostResponse{
        var post : Post = postRepository.findById(post_id)
                .orElseThrow{RuntimeException("post not found")}

        return PostResponse.from(post)
    }
}