package kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto

data class SignUpRequest(
        var username : String,
        var email : String,
        var password : String,
        var student_num : Int,
        var department : String,
        var phone_number : String,
)
