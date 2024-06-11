package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.entity.Reservation
import java.time.LocalDateTime

data class ReservationInfo(
        var username : String,
        var phone_number : String,
        var num : Int,
        var date : LocalDateTime,
        var created_at : LocalDateTime
){
    companion object{
        fun from(reservation : Reservation) : ReservationInfo{
            return ReservationInfo(reservation.user.username,
                    reservation.user.phone_number, reservation.num,
                    reservation.date, reservation.created_at)
        }
    }
}
