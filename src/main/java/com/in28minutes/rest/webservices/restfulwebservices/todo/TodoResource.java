package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TodoResource {

	@Autowired
	private TodoHardCodedService todoService;

	@GetMapping("/users/{username}/todos")
	public List<Todo> getTodos(@PathVariable String username) {
		return todoService.findAll();

	}

	@GetMapping("/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		return todoService.findById(id);
	}

	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo todo) {
		Todo todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK); //returns status of OK when successful
	}

	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @RequestBody Todo todo) {
		Todo todoCreated = todoService.save(todo);

		//takes the current request path and appends the id of newly created todo and creates resource
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todoCreated.getId()).toUri();
		
		//returns status of created and the URL of the created resource 
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {

		Todo todo = todoService.deleteById(id);
		if (todo != null) {
			return ResponseEntity.noContent().build(); //returns no content when successful
		}

		return ResponseEntity.notFound().build(); //returns not found when failing
	}
}
