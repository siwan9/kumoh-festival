package kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User

data class SessionUser(
        var username: String,
        var email : String,
        var student_num : Int,
        var department : String,
        var phone_number : String,
        var role : Role
){
    companion object{
        fun from(user : User) : SessionUser{
            return SessionUser(user.username, user.email,
                    user.studentNum, user.department, user.phone_number, user.role)
        }
    }
}
