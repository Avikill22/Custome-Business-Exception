package com.backend.blogBackend.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.blogBackend.Entity.User;
import com.backend.blogBackend.Exception.Custom.BusinessException;
import com.backend.blogBackend.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		try {
			List<User> userList =  this.userRepository.findAll();
			if(userList == null) {
				throw new BusinessException("604","List of User completely Empty");
			}
			return userList;
		}catch(Exception e){
			throw new BusinessException("603","Something went wrong on server end"+e.getMessage());
		}
	}

	public User getUserById(Long id) {
		try {
			User user = this.userRepository.findById(id).get();
			return user;
		}catch(IllegalArgumentException e) {
			throw new BusinessException("604","given empty Id is null"+e.getMessage());
		}catch(NoSuchElementException e) {
			throw new BusinessException("605","Given Employee Id does not exists in database.");
		}
	}

	public ResponseEntity<HttpStatus> deleteUser(Long id) {
		try {
			this.userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(IllegalArgumentException e) {
			throw new BusinessException("604","given employ Id is null"+e.getMessage());
		}catch(EmptyResultDataAccessException e) {
			throw new BusinessException("605","GIven Employee Id does not exists in database.");
		}
		
		
		
	}

	public User saveUser(User user) {
		try{
			if(user.getName() == null || user.getName().length() == 0) {
				throw new BusinessException("601","User name is not mentioned");
			}
			User createUser = this.userRepository.save(user);
			return createUser;
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","User entity is null"+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","Something went wrong on server end"+e.getMessage());
		}
		
	}

	public User updateUserInformation(User user) {
		try{
			if(user.getId() == null) {
				throw new BusinessException("610","User id is not mentioned");
			}
			User createUser = this.userRepository.save(user);
			return createUser;
		}catch(IllegalArgumentException e) {
			throw new BusinessException("602","User entity is null"+e.getMessage());
		}catch(Exception e) {
			throw new BusinessException("603","Something went wrong on server end"+e.getMessage());
		}
	}
	
	

}
