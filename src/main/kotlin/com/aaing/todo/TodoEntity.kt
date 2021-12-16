package com.aaing.todo

import javax.persistence.*

@Entity
@Table(name = "kotlin_todo")
data class TodoEntity(
    @Column
    @Id
    val id: String,
    @Column
    val name: String
)