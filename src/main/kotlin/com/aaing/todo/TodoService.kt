package com.aaing.todo

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
        private val todoRepository: TodoRepository
    ) {
    fun findAll(): List<TodoEntity> {
        return todoRepository.findAll()
    }

    fun findById(id: Int): TodoEntity? {
        return todoRepository.findByIdOrNull(id)
    }

    fun findByName(name: String): TodoEntity? {
        return todoRepository.findByName(name)
    }
}