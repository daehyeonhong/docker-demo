package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.user.UserRequestVo
import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import java.util.*
import kotlin.random.Random
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

class UserSteps {
    companion object {
        fun createUserRequestGenerate(userRequest: UserRequestVo): ExtractableResponse<Response> =
            RestAssured.given().log().all()
                .body(userRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`()
                .post("/users")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract()

        fun userReqeustGenerate(): UserRequestVo = UserRequestVo(
            name = UUID.randomUUID().toString().split("-")[0],
            age = Random.nextInt(1, 101)
        )
    }
}
