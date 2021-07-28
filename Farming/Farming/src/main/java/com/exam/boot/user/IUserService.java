package com.exam.boot.user;

import java.util.List;

public interface IUserService {
	
	
	public List<UserRegister> getAllUser();	// get all users

	public UserRegister insertUser(UserRegister userReg);

}
