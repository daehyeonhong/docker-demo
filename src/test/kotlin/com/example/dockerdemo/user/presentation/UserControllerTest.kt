package com.example.dockerdemo.user.presentation

import RestDocsTestBase
import com.example.dockerdemo.user.UserRequestVo
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@AutoConfigureMockMvc
@AutoConfigureRestDocs
class UserControllerTest(@LocalServerPort private val port: Int) : RestDocsTestBase(port) {
    @Test
    fun findByUserWithUserId(): Unit {
        val userRequest: UserRequestVo = UserSteps.createUserSignupGenerate()
        val response = UserSteps.userSignupRequestGenerate(spec, userRequest)

        Assertions.assertAll(
            { assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()) },
            { assertThat(response.jsonPath().getInt("age")).isEqualTo(userRequest.age) },
            { assertThat(response.jsonPath().getString("name")).isEqualTo(userRequest.name) },
        )
    }

    @Test
    fun findByUserWithUserId2(): Unit {
        val userRequest: UserRequestVo = UserSteps.createUserSignupGenerate()
        val response = UserSteps.userSignupRequestGenerate(spec, userRequest)
        assertThat(response.statusCode()).isNotEqualTo(HttpStatus.ACCEPTED.name)
        Assertions.assertAll(
            { assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()) },
            { assertThat(response.jsonPath().getInt("age")).isEqualTo(userRequest.age) },
            { assertThat(response.jsonPath().getString("name")).isEqualTo(userRequest.name) },
        )
    }
}
