package com.backend.blogBackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blogBackend.Entity.User;
import com.backend.blogBackend.Exception.Custom.BusinessException;
import com.backend.blogBackend.Exception.Custom.ControllerException;
import com.backend.blogBackend.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<?> getallUsers(){
		try {
			return new ResponseEntity<List<User>>(this.userService.getAllUsers(),HttpStatus.OK);
		}catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorDescription());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<?> getUser(@PathVariable("userID") Long id){
		try {
			return new ResponseEntity<User>(this.userService.getUserById(id),HttpStatus.FOUND);
		}catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorDescription());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{userID}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("userID") Long id){
		return this.userService.deleteUser(id);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> saveUser(@RequestBody User user){
		try {
			return new ResponseEntity<>(this.userService.saveUser(user),HttpStatus.CREATED);
		}catch(BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorDescription());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		return new ResponseEntity<>(this.userService.updateUserInformation(user),HttpStatus.ACCEPTED);
	}
}
