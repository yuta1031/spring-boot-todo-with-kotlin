package com.aaing.todo

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class SampleController {
    @GetMapping("/")
    @ResponseBody
    fun index(model: Model): ResponseEntity<String> {
        return ResponseEntity.ok("hello world")
    }
}