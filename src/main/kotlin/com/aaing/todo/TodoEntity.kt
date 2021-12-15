package com.aaing.todo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "kotlin_todo")
data class TodoEntity(
    @Column
    @Id
    val id: Int,
    @Column
    val name: String
)