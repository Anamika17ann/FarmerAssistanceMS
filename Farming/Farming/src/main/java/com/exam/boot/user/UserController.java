package com.exam.boot.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@Autowired
	private IUserService iUserService;
	
	@GetMapping("/getUsers")
	@ResponseBody
	public List<UserRegister> getUsers() {
		List<UserRegister> users = iUserService.getAllUser();
		return users;
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<Object> create(@RequestBody UserRegister userReg) {
		System.out.println(userReg.getUserEmail());
		System.out.println(userReg.getUserName());
		System.out.println(userReg.getUserPassword());
		System.out.println(userReg.getUserPass());
		System.out.println(userReg.getUserRole());
		System.out.println("controller ends");
		try {
			UserRegister inserted=iUserService.insertUser(userReg);
			return new ResponseEntity<>(inserted, HttpStatus.CREATED);//201
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}

}
