package kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.service.PostService
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SessionUser
import kr.ac.kumoh.ce.s20200237.databaseProject.global.BasicResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/post")
class PostController(val postService: PostService) {
    @PostMapping()
    fun post(request : HttpServletRequest,
             @RequestBody postRequest: PostRequest) : ResponseEntity<BasicResponse>{
        try{
            val session : HttpSession = request.getSession(false) ?: throw RuntimeException("authentication fault")
            val user : SessionUser = session.getAttribute("user") as? SessionUser ?: throw RuntimeException("authentication fault")

            postService.post(postRequest, user)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }
        return ResponseEntity.ok()
                .body(BasicResponse(200, "success", "post success!"))
    }

    @GetMapping("/{post_id}")
    fun check(@PathVariable post_id : Long) : ResponseEntity<out Any>{
        var postResponse : PostResponse
        try{
            postResponse = postService.check(post_id)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }
        return ResponseEntity.ok()
                .body(postResponse)
    }
}