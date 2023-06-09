package com.example.dockerdemo.user.presentation

import com.example.dockerdemo.user.UserRequestVo
import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import java.util.*
import kotlin.random.Random
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document

class UserSteps {
    companion object {
        fun userSignupRequestGenerate(
            spec: RequestSpecification,
            userRequest: UserRequestVo
        ): ExtractableResponse<Response> =
            RestAssured.given(spec).log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(userRequest).filter(
                    document(
                        "{class-name}/{method-name}",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        responseFields(
                            fieldWithPath("name").type(JsonFieldType.STRING).description("사용자 이름"),
                            fieldWithPath("age").type(JsonFieldType.NUMBER).description("사용자 나이"),
                        )
                    )
                )
                .`when`()
                .post("/users")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract()

        fun createUserSignupGenerate(): UserRequestVo = UserRequestVo(
            name = UUID.randomUUID().toString().split("-")[0],
            age = Random.nextInt(1, 101)
        )
    }
}
