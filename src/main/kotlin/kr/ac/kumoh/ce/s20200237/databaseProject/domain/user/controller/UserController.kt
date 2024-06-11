package kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.LoginRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SessionUser
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SignUpRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.service.UserService
import kr.ac.kumoh.ce.s20200237.databaseProject.global.BasicResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
class UserController(val userService: UserService) {
    @PostMapping("/login")
    fun login(request : HttpServletRequest,
            @RequestBody loginRequest: LoginRequest) : ResponseEntity<BasicResponse>{
        var user : User
        try {
            request.getSession(false)?.let { throw RuntimeException("already login") }
                    ?: run{
                        user = userService.login(loginRequest)
                        request.getSession().setAttribute("user", SessionUser.from(user))
                    }
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest().body(
                    BasicResponse(400, "error", e.message))
        }

        return ResponseEntity.ok()
                .body(BasicResponse(200, "success", "login success!"))
    }

    @PostMapping("/logout")
    fun logout(request : HttpServletRequest) : ResponseEntity<BasicResponse>{
        try {
            var session : HttpSession = request.getSession(false) ?: throw RuntimeException("not login")
            session.invalidate()
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest().body(
                    BasicResponse(400, "error", e.message))
        }
        return ResponseEntity.ok()
                .body(BasicResponse(200, "success", "logout success!"))
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody signUpRequest: SignUpRequest) : ResponseEntity<BasicResponse>{
        try {
            userService.signUp(signUpRequest)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }

        return ResponseEntity.ok()
                .body(BasicResponse(200, "success", "singUp success!"))
    }
}