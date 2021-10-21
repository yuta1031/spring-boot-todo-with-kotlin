package com.aaing.todo

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TodoController {
    @GetMapping("/healthcheck")
    @ResponseBody
    fun healthCheck(model: Model): ResponseEntity<String> {
        return ResponseEntity.ok("hello world")
    }

    @GetMapping("/")
    fun index(model: Model): String {
        // TODO 仮置き固定
        model.addAttribute("list", listOf("item1","item2","item3"))
        return "index"
    }
}