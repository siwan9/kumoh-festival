package kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.UserResponse

data class BoothResponse(
        var name : String,
        var description : String,
        var location : Int,
        var is_reservation : Boolean,
        var user : UserResponse
)
