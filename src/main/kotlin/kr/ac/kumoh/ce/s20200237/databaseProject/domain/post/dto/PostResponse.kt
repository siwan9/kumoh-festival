package kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.entity.Post
import java.time.LocalDateTime

data class PostResponse(
        var title : String,
        var content : String,
        var type : PostType,
        var created_at : LocalDateTime,
        var username : String,
        var booth_name : String
){
    companion object{
        fun from(post : Post) : PostResponse{
            return PostResponse(post.title, post.content, post.type,
                    post.created_at, post.user.username, post.booth.name)
        }
    }
}
