package kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity

import jakarta.persistence.*
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.Role

@Entity
@Table(name = "user")
open class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long?,
        var username : String,
        var email : String,
        var password : String,
        var studentNum : Int,
        var department : String,
        var phone_number : String,
        @Enumerated(value = EnumType.STRING)
        var role : Role
)
