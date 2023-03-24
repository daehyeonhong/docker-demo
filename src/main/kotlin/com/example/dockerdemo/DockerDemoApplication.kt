package com.example.dockerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerDemoApplication

fun main(args: Array<String>) {
    runApplication<DockerDemoApplication>(*args)
}
