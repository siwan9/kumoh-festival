package kr.ac.kumoh.ce.s20200237.databaseProject.post.repository

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface BoothRepository : JpaRepository<Booth, Long> {
    @Query("select distinct b from Booth b " +
            "left outer join User u on b.user.id = u.id " +
            "where b.id = :booth_id")
    override fun findById(booth_id : Long) : Optional<Booth>
}