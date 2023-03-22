package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.AcceptanceTest
import com.example.dockerdemo.user.UserRequestVo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpStatus

@AutoConfigureMockMvc
@AutoConfigureRestDocs
class UserControllerTest : AcceptanceTest() {
    @Test
    fun findByUserWithUserId(): Unit {
        val userRequest: UserRequestVo = UserSteps.userReqeustGenerate()
        val response = UserSteps.createUserRequestGenerate(userRequest)
        Assertions.assertAll(
            { assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()) },
            { assertThat(response.jsonPath().getInt("age")).isEqualTo(userRequest.age) },
            { assertThat(response.jsonPath().getString("name")).isEqualTo(userRequest.name) },
        )
    }    @Test
    fun findByUserWithUserId2(): Unit {
        val userRequest: UserRequestVo = UserSteps.userReqeustGenerate()
        val response = UserSteps.createUserRequestGenerate(userRequest)
        Assertions.assertAll(
            { assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()) },
            { assertThat(response.jsonPath().getInt("age")).isEqualTo(userRequest.age) },
            { assertThat(response.jsonPath().getString("name")).isEqualTo(userRequest.name) },
        )
    }
}
