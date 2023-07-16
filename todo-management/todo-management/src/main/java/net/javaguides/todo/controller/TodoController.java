package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    //Build todo REST API

    @PreAuthorize("hasRole('ADMIN')") // This annotation is from method level security to ensure only for respective role acess.
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedTodoDto = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodoDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto>getTodo(@PathVariable Long id){
        TodoDto retrievedTodoDto = todoService.getTodo(id);
        return new ResponseEntity<>(retrievedTodoDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todo = todoService.getAllTodo();
        return new ResponseEntity<>(todo,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto>updateTodo(@RequestBody TodoDto todoDto,@PathVariable  Long id){
        TodoDto updatedDto = todoService.updateTodo(todoDto,id);
        return new ResponseEntity<>(updatedDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String>deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Element is deleted",HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')") // For giving multiple acess roles.
    @PatchMapping("{id}") // This annotation used for changing only partial attributes of a table.
    public ResponseEntity<TodoDto>completeTodo(@PathVariable Long id){
        TodoDto updatedTodoDto = todoService.completeTodo(id);
        return new ResponseEntity<>(updatedTodoDto,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto>incompleteTodo(@PathVariable Long id){
        TodoDto updatedTodoDto = todoService.incompleteTodo(id);
        return new ResponseEntity<>(updatedTodoDto,HttpStatus.OK);

    }

}

