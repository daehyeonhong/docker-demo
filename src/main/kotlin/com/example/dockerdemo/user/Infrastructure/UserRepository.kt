package com.example.dockerdemo.user.Infrastructure

import com.example.dockerdemo.user.domain.UserEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, UUID>
