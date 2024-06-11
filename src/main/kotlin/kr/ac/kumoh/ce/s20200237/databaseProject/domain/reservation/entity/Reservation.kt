package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.entity

import jakarta.persistence.*
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import java.time.LocalDateTime

@Entity
@Table(name = "reservation")
open class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @ManyToOne
        @JoinColumn(name = "user_id")
        var user: User,

        @ManyToOne
        @JoinColumn(name = "booth_id")
        var booth: Booth,

        var num: Int,

        var date: LocalDateTime,

        var created_at: LocalDateTime
)
