package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.repository

import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface ReservationRepository : JpaRepository<Reservation, Long> {
    @Query("select distinct r from Reservation r " +
            "left outer join User u on r.user.id = u.id " +
            "where r.booth.id = :booth_id")
    fun findByBooth_Id(booth_id : Long) : List<Reservation>
}