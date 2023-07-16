package net.javaguides.todo.service;

import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(Long id);

    TodoDto completeTodo(Long id);

    TodoDto incompleteTodo(Long id);
}
