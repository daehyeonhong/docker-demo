package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.user.UserRequestVo
import com.example.dockerdemo.user.UserResponseVo
import com.example.dockerdemo.user.application.UserService
import com.example.dockerdemo.user.domain.UserEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/users"])
class UserController(
    private val userService: UserService
) {
    @PostMapping
    fun registerUser(@RequestBody userRequestVo: UserRequestVo): ResponseEntity<UserResponseVo> {
        val user: UserEntity = this.userService.save(userRequestVo)
        return ResponseEntity( UserResponseVo( user.name, user.age), HttpStatus.CREATED)
    }
}
