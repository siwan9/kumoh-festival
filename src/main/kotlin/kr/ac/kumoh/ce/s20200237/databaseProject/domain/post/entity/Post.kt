package kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.entity

import jakarta.persistence.*
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto.PostType
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "post")
open class Post(
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?,

        @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User,

        @ManyToOne
    @JoinColumn(name = "booth_id")
    var booth : Booth,

        var title : String,

        var content : String,

        @Enumerated(value = EnumType.STRING)
        var type : PostType,

        var created_at : LocalDateTime,
)
