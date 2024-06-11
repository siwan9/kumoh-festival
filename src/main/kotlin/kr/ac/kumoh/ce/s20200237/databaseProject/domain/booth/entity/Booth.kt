package kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity

import jakarta.persistence.*
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto.BoothType
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import java.time.LocalDate

@Entity
@Table(name = "booth")
open class Booth(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long?,
        var name : String,
        var description : String,
        var location : Int,

        @Enumerated(value = EnumType.STRING)
        var type : BoothType,

        var is_reservation : Boolean,

        @OneToOne
        @JoinColumn(name = "user_id")
        var user : User
){

}
// 엔티티는 final 못쓴다? -> 프록시 객체를 만들어야하므로
// 엔티티는 기본 생성자가 있어야한다? -> 직렬화
