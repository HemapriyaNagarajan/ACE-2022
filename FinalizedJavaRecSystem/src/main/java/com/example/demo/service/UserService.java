package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

	 
	@Autowired
	private UserRepository repo;
	public void addUser(User u)
	{
		repo.save(u);
	}
	
	public List<User> getAllUsers()
	{
		return repo.findAll();
	}
	
	public User getUserById(int id)
	{
		Optional<User> u = repo.findById(id);
		if(u.isPresent())
		{
			return u.get();
		}
		return null;
	}
	
	public void deleteUser(int id)
	{
		repo.deleteById(id);
	}
	
	public List<User> listAll(String keyword) 
	{
	        if (keyword!= null) {
	            return repo.findAll(keyword);
	        }
	        return repo.findAll();
	}
	
	public List<User> useFindByParam(String tech, int years, String est, int cgpa, int hsc)
	{
		return repo.findByParam(tech, years, est, cgpa, hsc);
	}
	
	
	public List<User> useFindByParamTwo(String tech1, String tech2, int years, String est, int cgpa, int hsc)
	{
		return repo.findByParamTwo(tech1, tech2, years, est, cgpa, hsc);
	}
	
	public List<User> useFindByParamThree(String tech1, String tech2, String tech3,  int years, String est, int cgpa, int hsc)
	{
		return repo.findByParamThree(tech1, tech2, tech3, years, est, cgpa, hsc);
	}
	
	//search by email
	public List<User> findByEmail(String email)
	{
		return repo.findByEmail(email);
	}

	
}
