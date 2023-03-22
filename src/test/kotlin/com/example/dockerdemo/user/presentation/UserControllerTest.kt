package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.AcceptanceTest
import com.example.dockerdemo.user.UserRequestVo
import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import java.util.*
import kotlin.random.Random
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


class UserControllerTest : AcceptanceTest() {

    @Test
    fun findByUserWithUserId(): Unit {
        val userRequest: UserRequestVo = UserSteps.createUserRequestGenerate()
        val response = UserSteps.createMemberRequestGenerate(userRequest)

        Assertions.assertAll(
            { assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()) },
            { assertThat(response.jsonPath().getInt("age")).isEqualTo(userRequest.age) },
            { assertThat(response.jsonPath().getString("name")).isEqualTo(userRequest.name) },
        )
    }

    class UserSteps {
        companion object {
            fun createMemberRequestGenerate(userRequest: UserRequestVo): ExtractableResponse<Response> =
                RestAssured
                    .given().log().all()
                    .body(userRequest)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .`when`()
                    .post("/users")
                    .then().log().all()
                    .extract()

            fun createUserRequestGenerate(): UserRequestVo = UserRequestVo(
                name = UUID.randomUUID().toString().split("-")[0],
                age = Random.nextInt(1, 101)
            )
        }
    }
}
