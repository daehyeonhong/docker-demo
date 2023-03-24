package com.example.dockerdemo

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation
import org.springframework.restdocs.restassured.RestDocumentationFilter

@TestConfiguration
class RestDocsConfiguration {
    @Bean
    fun write(): RestDocumentationFilter {
        return RestAssuredRestDocumentation.document(
            "{class-name}/{method-name}",
            Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
            Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        )
    }
}
