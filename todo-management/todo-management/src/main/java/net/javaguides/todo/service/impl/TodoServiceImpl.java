package net.javaguides.todo.service.impl;

import lombok.AllArgsConstructor;
import lombok.Setter;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Setter
@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {


    private TodoRepository todoRepository;

    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto to jpa entity
        /*Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted()); */

        Todo todo = modelMapper.map(todoDto, Todo.class); //using model mapper to map dto object to jpa entity

        Todo savedTodo=todoRepository.save(todo);

        //convert back to todoDto

        TodoDto savedTodoDto = modelMapper.map(savedTodo,TodoDto.class);
        /*savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setCompleted(savedTodo.isCompleted());
       */


        return savedTodoDto;


    }

    @Override
    public TodoDto getTodo(Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("todo not found with id" + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> retrievedTods = todoRepository.findAll();
        List<TodoDto> retrievedTodoDto = new ArrayList<>();
        for(Todo todo: retrievedTods){
            retrievedTodoDto.add(modelMapper.map(todo, TodoDto.class));
        }
        return retrievedTodoDto;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo =todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("todo not found with id" + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("todo not found with id" +id));
        todoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("todo not found with id" + id));
        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto incompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("todo not found with id" + id));
        todo.setCompleted(false);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }


}
