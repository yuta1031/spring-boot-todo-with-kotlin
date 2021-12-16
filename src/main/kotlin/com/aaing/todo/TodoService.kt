package com.aaing.todo

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import de.huxhorn.sulky.ulid.ULID

@Service
class TodoService(
        private val todoRepository: TodoRepository,
        private val ulid: ULID
    ) {
    fun findAll(): List<TodoEntity> {
        return todoRepository.findAll()
    }

    fun findById(id: String): TodoEntity? {
        return todoRepository.findByIdOrNull(id)
    }

    fun findByName(name: String): TodoEntity? {
        return todoRepository.findByName(name)
    }

    fun create(name: String): TodoEntity {
        return todoRepository.save(
            TodoEntity(
                ulid.nextULID(),
                name
            )
        )
    }

    fun update(todoEntity: TodoEntity): TodoEntity {
        return todoRepository.save(todoEntity)
    }

    fun delete(todoEntity: TodoEntity) {
        todoRepository.delete(todoEntity)
    }
}