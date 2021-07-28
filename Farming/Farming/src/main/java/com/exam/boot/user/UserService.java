package com.exam.boot.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserRegister> getAllUser() {
		// TODO Auto-generated method stub
		List<UserRegister> users = userDao.findAll();
		return users;
		
	}

	@Override
	public UserRegister insertUser(UserRegister userReg) {
		// TODO Auto-generated method stub
		System.out.println(userReg.getUserEmail());
		System.out.println(userReg.getUserName());
		System.out.println(userReg.getUserPassword());
		System.out.println(userReg.getUserPass());
		System.out.println(userReg.getUserRole());
		return userDao.save(userReg);
		
	}


}
