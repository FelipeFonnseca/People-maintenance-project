package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.response.Responses;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value="API REST peoples")
@CrossOrigin(origins = "*")
public class UsersController {
	@Autowired
	private UserService userService;
	
	/**
	 * This function brings up the entire list registered in the bank.
	 * @return
	 */
	@GetMapping("/listAll")
	@ApiOperation(value ="this method returns a list of registered users")
	public ResponseEntity<Responses<List<Users>>> listAll(){
		return ResponseEntity.ok(new Responses<List<Users>>(this.userService.listAll()));
	}
	
	/**
	 * This function lists by id
	 * @param id
	 * @return
	 */
	@GetMapping("/listId/{id}")
	@ApiOperation(value ="this method returns registered users by Id")
	public ResponseEntity<Responses<Users>> listId(@PathVariable String id){
		return ResponseEntity.ok(new Responses<>(this.userService.listId(id)));
	}
	
	/**
	 * This function registers with a validation that does not allow the name to be null or empty
	 * @param user
	 * @param result
	 * @return
	 */
		
	@PostMapping("/register")
	@ApiOperation(value ="This method makes the first user registration")
	public ResponseEntity<Responses<Users>> register(@Validated @RequestBody Users user, BindingResult result){
		if (user.getGivenName() == null || user.getGivenName().isEmpty()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getObjectName()));
			return ResponseEntity.badRequest().body(new Responses<Users>(erros));
		}
		return ResponseEntity.ok(new Responses<Users>(this.userService.register(user)));
	}
	
	/**
	 * This function updates with a validation that does not allow the name to be null or empty.
	 * @param id
	 * @param user
	 * @param result
	 * @return
	 */
	@PutMapping("/update/{id}")
	@ApiOperation(value ="This method updates any user data when their id is passed")
	public ResponseEntity<Responses<Users>> update(@PathVariable String id,@Validated @RequestBody Users user, BindingResult result){
		if (user.getGivenName() == null || user.getGivenName().isEmpty()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getObjectName()));
			return ResponseEntity.badRequest().body(new Responses<Users>(erros));
		}
		user.setId(id);
		return ResponseEntity.ok(new Responses<Users>(this.userService.update(id, user)));
	}
	
	/**
	 * delete the registered user by id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/remove/{id}")
	@ApiOperation(value ="This method remove users by ID")
	public ResponseEntity<Responses<Integer>> remove(@PathVariable(name="id") String id){
		this.userService.remove(id);
		return ResponseEntity.ok(new Responses<Integer>(1));
	}
	
}
