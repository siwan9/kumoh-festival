package kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.service

import jakarta.transaction.Transactional
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.LoginRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.Role
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.dto.SignUpRequest
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.entity.User
import kr.ac.kumoh.ce.s20200237.databaseProject.domain.user.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository,
                  val passwordEncoder : PasswordEncoder) {

    @Transactional
    fun login(loginRequest: LoginRequest) : User{
        val user : User = userRepository.findByEmail(loginRequest.email)
                .orElseThrow{throw RuntimeException("User Not found")}
        if (!passwordEncoder.matches(loginRequest.password, user.password))
            throw RuntimeException("password incorrect")

        return user
    }

    @Transactional
    fun signUp(signUpRequest: SignUpRequest) {
        if(userRepository.findByEmail(signUpRequest.email).isPresent
                || userRepository.findByStudentNum(signUpRequest.student_num).isPresent)
            throw RuntimeException("duplicate info")
        else{
            userRepository.save(User(null, signUpRequest.username,
                    signUpRequest.email, passwordEncoder.encode(signUpRequest.password),
                    signUpRequest.student_num, signUpRequest.department, signUpRequest.phone_number,
                    Role.user))
        }
    }
}