package kr.ac.kumoh.ce.s20200237.databaseProject.post.service

import jakarta.transaction.Transactional
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto.BoothRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto.BoothResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.dto.BoothType
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.booth.entity.Booth
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.Role
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.UserResponse
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.repository.UserRepository
import kr.ac.kumoh.ce.s20200237.databaseProject.post.repository.BoothRepository
import org.springframework.stereotype.Service

@Service
class BoothService(val boothRepository: BoothRepository,
        val userRepository: UserRepository) {

    @Transactional
    fun post(boothRequest: BoothRequest) {
        val user : User = userRepository.findByEmail(boothRequest.email)
                .orElseThrow{throw RuntimeException("User not found")}
        boothRepository.save(Booth(null, boothRequest.name, boothRequest.description,
                boothRequest.location, boothRequest.type, true, user))
        user.role = Role.manager
    }

    @Transactional
    fun check(booth_type : BoothType, booth_id : Long) : BoothResponse {
        var booth : Booth = boothRepository.findByIdAndBoothType(booth_id, booth_type)
                .orElseThrow{throw RuntimeException("booth not found")}
        var user : User = booth.user
        var userResponse = UserResponse(user.username,
                user.phone_number, user.department)
        return BoothResponse(booth.name, booth.description,
                booth.location, booth.is_reservation, userResponse)
    }
}