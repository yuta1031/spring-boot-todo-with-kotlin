package com.aaing.todo

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TodoDto(

    @field:NotEmpty
    @field:Size(min = 1,max = 100)
    val name: String?
)