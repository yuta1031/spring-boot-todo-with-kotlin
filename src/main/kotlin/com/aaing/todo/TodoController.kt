package com.aaing.todo

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

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
        @ModelAttribute @Validated todoDto: TodoDto,
        bindingResult: BindingResult,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult.allErrors)
            return "create"
        }

        if(todoDto.name == null) {
            model.addAttribute("errors", listOf(ObjectError("name","invalid input")))
            return "create"
        }

        if(todoService.findByName(todoDto.name) != null) {
            model.addAttribute("errors", listOf(ObjectError("name","already used")))
            return "create"
        }

        todoService.create(todoDto.name)
        val todoList = todoService.findAll()
        model.addAttribute("todoList", todoList)
        redirectAttributes.addFlashAttribute("message","successfully created")
        return "redirect:/"
    }

    @GetMapping("/update/{id}")
    fun updateView(@PathVariable id: String,model: Model): String {
        model.addAttribute("id",id)
        return "update"
    }

    @PostMapping("update/{id}")
    fun update(
        @PathVariable id: String,
        @ModelAttribute @Validated todoDto: TodoDto,
        bindingResult: BindingResult,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult.allErrors)
            model.addAttribute("id",id)
            return "update"
        }

        if(todoDto.name == null) {
            model.addAttribute("errors", listOf(ObjectError("name","invalid input")))
            model.addAttribute("id",id)
            return "update"
        }

        if(todoService.findByName(todoDto.name) != null) {
            model.addAttribute("errors", listOf(ObjectError("name","already used")))
            model.addAttribute("id",id)
            return "update"
        }

        val todoEntity = todoService.findById(id)
        if(todoEntity == null) {
            model.addAttribute("errors", listOf(ObjectError("id","not found")))
            return "index"
        }

        todoService.update(
            TodoEntity(
                todoEntity.id,
                todoDto.name
            )
        )
        val todoList = todoService.findAll()
        model.addAttribute("todoList", todoList)
        redirectAttributes.addFlashAttribute("message","successfully updated")
        return "redirect:/"
    }

    @GetMapping("/delete/{id}")
    fun deleteView(
        @PathVariable id: String,
        model: Model
    ): String {
        model.addAttribute("id",id)
        return "delete"
    }

    @PostMapping("/delete/{id}")
    fun delete(
        @PathVariable id: String,
        model: Model,
        redirectAttributes: RedirectAttributes
    ): String {
        val todoEntity = todoService.findById(id)
        if(todoEntity == null) {
            model.addAttribute("errors", listOf(ObjectError("id","not found")))
            return "index"
        }
        todoService.delete(todoEntity)
        redirectAttributes.addFlashAttribute("message","successfully deleted")
        return "redirect:/"
    }
}