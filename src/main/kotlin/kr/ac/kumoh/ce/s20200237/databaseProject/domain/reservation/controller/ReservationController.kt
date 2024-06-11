package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto.ReservationRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto.ReservationResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.service.ReservationService
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.Role
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SessionUser
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.global.BasicResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reservation")
class ReservationController(val reservationService: ReservationService) {
    @PostMapping("/{booth_id}")
    fun reservation(request : HttpServletRequest,
                    @PathVariable booth_id : Long,
                    @RequestBody reservationRequest: ReservationRequest) : ResponseEntity<BasicResponse>{
        try{
            val session : HttpSession = request.getSession(false) ?: throw RuntimeException("authentication fault")
            val user : SessionUser = session.getAttribute("user") as? SessionUser ?: throw RuntimeException("authentication fault")

            reservationService.reservation(booth_id, user.email, reservationRequest)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }
        return ResponseEntity.ok()
                .body(BasicResponse(200, "success", "reservation success!"))
    }

    @GetMapping("/{booth_id}")
    fun check(@PathVariable booth_id : Long) : ResponseEntity<out Any>{
        var reservationResponse : ReservationResponse
        try{
            reservationResponse = reservationService.check(booth_id)
        } catch (e : RuntimeException){
            return ResponseEntity.badRequest()
                    .body(BasicResponse(400, "error", e.message))
        }
        return ResponseEntity.ok().body(reservationResponse)
    }
}