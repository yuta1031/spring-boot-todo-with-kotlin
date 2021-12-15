package com.aaing.todo

import org.jetbrains.annotations.NotNull
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TodoCreateDto(

    @field:NotEmpty
    @field:Size(max = 100)
    val name: String?
)