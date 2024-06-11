package kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.repository

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String) : Optional<User>
    fun findByStudentNum(student_num : Int) : Optional<User>
}