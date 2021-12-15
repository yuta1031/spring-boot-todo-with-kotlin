package com.aaing.todo

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping("/healthcheck")
    @ResponseBody
    fun healthCheck(model: Model): ResponseEntity<String> {
        return ResponseEntity.ok("hello world")
    }

    @GetMapping("/")
    fun index(model: Model): String {
        val todoList = todoService.findAll()
        model.addAttribute("todoList", todoList)
        return "index"
    }

    @GetMapping("/create")
    fun createView(model: Model): String {
        return "create"
    }

    @PostMapping("/create")
    fun create(
        @ModelAttribute @Validated todoCreateDto: TodoCreateDto,
        bindingResult: BindingResult,
        model: Model
    ): String {
        if(bindingResult.hasErrors()) {
            // TODO viewにエラーメッセージを埋め込む
            model.addAttribute("errors",bindingResult.allErrors)
            return "create"
        }
        // TODO 新規作成処理
        val todoList = todoService.findAll()
        model.addAttribute("todoList", todoList)
        return "index"
    }
}