package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.user.Infrastructure.UserRepository
import com.example.dockerdemo.user.UserRequestVo
import com.example.dockerdemo.user.application.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.*
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
@AutoConfigureRestDocs
class UserControllerTest(
) {
    @Autowired
    private lateinit var mockMvc: MockMvc
    private val objectMapper: ObjectMapper = ObjectMapper();
    private val APPLICATION_JSON: MediaType = MediaType.APPLICATION_JSON

    @MockBean
    private lateinit var userService: UserService
    @MockBean
    private lateinit var userRepository: UserRepository


    @Test
    fun findByUserWithUserId(): Unit {
        val uuid: UUID = UUID.randomUUID()
        val userRequest: UserRequestVo = UserRequestVo("GilDong-Hong", 32)
        val perform = mockMvc.perform(
            post("/users")
                .content(objectMapper.writeValueAsBytes(userRequest))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
        )

        perform.andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(userRequest.name))
            .andExpect(jsonPath("$.age").value(userRequest.age))
    }
}
