package kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.repository

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface PostRepository : JpaRepository<Post, Long>{
    @Query("select distinct p from Post p " +
            "left outer join Booth b on p.booth.id = b.id " +
            "left outer join User u on p.user.id = u.id " +
            "where p.id = :id")
    override fun findById(id : Long) : Optional<Post>
}