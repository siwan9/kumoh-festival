package kr.ac.kumoh.ce.s20200237.databaseProject.post.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto.BoothRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto.BoothResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.Role
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SessionUser
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.global.BasicResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.post.service.BoothService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/booth")
class BoothController(val boothService: BoothService) {
    @PostMapping
    fun post(request: HttpServletRequest,
             @RequestBody boothRequest: BoothRequest) : ResponseEntity<BasicResponse>{
        try {
            val session : HttpSession = request.getSession(false) ?: throw RuntimeException("authentication fault")
            val user : SessionUser = session.getAttribute("user") as? SessionUser ?: throw RuntimeException("authentication fault")
            if (user.role != Role.admin) throw RuntimeException("authorize fault")

            boothService.post(boothRequest)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }

        return ResponseEntity.ok()
                .body(BasicResponse(200, "success", "create booth success!"))
    }

    @GetMapping("/{booth_id}")
    fun check(@PathVariable booth_id : Long) : ResponseEntity<out Any>{
        var boothResponse : BoothResponse
        try{
            boothResponse = boothService.check(booth_id)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }
        return ResponseEntity.ok().body(boothResponse)
    }
}