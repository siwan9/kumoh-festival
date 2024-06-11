package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto

data class ReservationResponse(
        var reservaion_list : MutableList<ReservationInfo> = mutableListOf()
)
