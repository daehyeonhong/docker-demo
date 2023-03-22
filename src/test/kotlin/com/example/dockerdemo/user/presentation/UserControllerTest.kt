package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.AcceptanceTest
import com.example.dockerdemo.user.UserRequestVo
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpStatus
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.cli.CliDocumentation
import org.springframework.restdocs.http.HttpDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation

@AutoConfigureMockMvc
@AutoConfigureRestDocs
class UserControllerTest : AcceptanceTest() {
    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        RestAssured.port = port
        this.spec =  RequestSpecBuilder()
            .addFilter(
                RestAssuredRestDocumentation.documentationConfiguration(restDocumentation)
                .snippets()
                .withDefaults(
                    CliDocumentation.curlRequest(),
                    HttpDocumentation.httpRequest(),
                    HttpDocumentation.httpResponse(),
                    PayloadDocumentation.requestBody(),
                    PayloadDocumentation.responseBody()
                ))
            .build()
    }
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
        assertThat(response.statusCode()).isNotEqualTo(HttpStatus.ACCEPTED.name)
        Assertions.assertAll(
            { assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()) },
            { assertThat(response.jsonPath().getInt("age")).isEqualTo(userRequest.age) },
            { assertThat(response.jsonPath().getString("name")).isEqualTo(userRequest.name) },
        )
    }
}
