package kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.dto

data class PostRequest(
        var title : String,
        var content : String,
        var type : PostType,
        var booth_id : Long
)
