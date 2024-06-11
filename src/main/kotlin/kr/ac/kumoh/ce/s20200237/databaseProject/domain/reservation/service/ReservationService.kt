package kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.service

import jakarta.transaction.Transactional
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto.ReservationInfo
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto.ReservationRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.dto.ReservationResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.entity.Reservation
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.reservation.repository.ReservationRepository
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.repository.UserRepository
import kr.ac.kumoh.ce.s20200237.databaseProject.post.repository.BoothRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(val reservationRepository: ReservationRepository,
                         val userRepository: UserRepository,
                         val boothRepository: BoothRepository) {
    @Transactional
    fun reservation(booth_id : Long,
                    email : String,
                    reservationRequest : ReservationRequest){
        val booth : Booth = boothRepository.findById(booth_id)
                .orElseThrow{ RuntimeException("booth not found") }
        val user : User = userRepository.findByEmail(email)
                .orElseThrow{RuntimeException("user not found")}
        reservationRepository.save(Reservation(null, user, booth,
                reservationRequest.num, reservationRequest.date, LocalDateTime.now()))
    }

    @Transactional
    fun check(booth_id : Long) : ReservationResponse{
        boothRepository.findById(booth_id)
                .orElseThrow{ RuntimeException("booth not found") }
        val reservationList : List<Reservation> = reservationRepository.findByBooth_Id(booth_id)

        var reservationResponse = ReservationResponse()
        for (i : Int in 1..reservationList.size){
            reservationResponse.reservaion_list
                    .add(ReservationInfo.from(reservationList[i-1]))
        }
        return reservationResponse
    }
}