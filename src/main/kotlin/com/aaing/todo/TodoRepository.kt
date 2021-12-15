package com.aaing.todo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository: JpaRepository<TodoEntity,Int> {
    override fun findAll(): List<TodoEntity>
    fun findByName(name: String): TodoEntity?
}