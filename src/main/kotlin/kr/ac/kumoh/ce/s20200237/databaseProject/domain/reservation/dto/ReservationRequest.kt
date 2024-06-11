package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ReservationRequest(
        var num : Int,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        var date : LocalDateTime
)