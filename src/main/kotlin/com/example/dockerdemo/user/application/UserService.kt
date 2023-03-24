package com.example.dockerdemo.user.application

import com.example.dockerdemo.user.Infrastructure.UserRepository
import com.example.dockerdemo.user.UserRequestVo
import com.example.dockerdemo.user.domain.UserEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun save(userRequestVo: UserRequestVo): UserEntity {
        return this.userRepository.save(UserEntity(userRequestVo.name, userRequestVo.age))
    }

}
