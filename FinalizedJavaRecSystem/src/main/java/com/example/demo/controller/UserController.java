package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Email;
import com.example.demo.service.MailService;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.itextpdf.text.DocumentException;

@Controller
public class UserController 
{
	@Autowired
	private UserService service;
	@Autowired
	private MailService notificationService;
	@Autowired
	private UserRepository repo;
	@Autowired
	private Email user;
	
	@GetMapping("/")
	public String home()
	{
		return "homePage";
	}
	
	@GetMapping("/aboutUs")
	public String aboutUs()
	{
		return "aboutUs";
	}
	
	@GetMapping("/addDetails")
	public String addUserDetails()
	{
		return "addUserDetails";
	}
	
	@PostMapping("/register")
	public String UserRegister(@ModelAttribute User u, HttpSession session)
	{
		
		service.addUser(u);
		session.setAttribute("msg", "We have received your application...Stay tuned !!! ");
		session.setAttribute("id",u.getId());
		return "addedUserResult";
	}
	
	@GetMapping("/showAll")
	public String showAll(Model m)
	{
		List<User> user = service.getAllUsers();
		m.addAttribute("user",user);
		return "showAllUsers";
	}
	
	@GetMapping("/editUser/{id}")
	public String edit(@PathVariable int id, Model m)
	{
		User u = service.getUserById(id);
		m.addAttribute("user",u);
		return "editUser";
		
	}
	
	@GetMapping("/viewUser/{id}")
	public String view(@PathVariable int id, Model m)
	{
		User u = service.getUserById(id);
		m.addAttribute("user",u);
		return "viewUser";
		
	}
	
	
	@PostMapping("/update")
	public String updateUser(@ModelAttribute User u, HttpSession session)
	{
		service.addUser(u);
		session.setAttribute("msg", "Your Details updated successfully");
		return "userDashboard";
	}
	
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable int id, HttpSession session)
	{
		service.deleteUser(id);
		session.setAttribute("msg", "Your application has been deleted ");
		return "deletedUserResult";
		
	}
	

	
	
	@GetMapping("/searchUser")
	public String showUser()
	{
		return "searchUser";
	}
	
	@GetMapping("/viewSearchUser/{id}")
	public String viewSearch(@PathVariable int id, Model m)
	{
		User u = service.getUserById(id);
		m.addAttribute("user",u);
		return "searchResult";
		
	}
//	@PostMapping("/searchUser")
//	public String showUser(@RequestParam(value="id") int id,  Model m)
//	{
//		User u = service.getUserById(id);
//		m.addAttribute("user",u);
//		return "searchResult";
//	}
	
	@PostMapping("/mailUser/{id}")
	public String mailUser(@PathVariable int id)
	{
		User u = service.getUserById(id);
		user.setEmailAddress(u.getEmail());  //Receiver's email address
		try {
			notificationService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "adminDashboard";
	}
	
	@PostMapping("/mailUserAttachment/{id}/{firstName}/{lastName}")
	public String mailUserAttachment(@PathVariable int id, @PathVariable String firstName,@PathVariable String lastName) throws MessagingException, FileNotFoundException, DocumentException
	{
		User u = service.getUserById(id);
		user.setEmailAddress(u.getEmail()); //Receiver's email address
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		try {
			notificationService.sendEmailWithAttachment(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "adminDashboard";
	}
	
	
	
	//searching
	@RequestMapping("/search")
    public String viewHomePage(Model model,
    		@Param("keyword") String keyword) {
        List<User> listUser = service.listAll(keyword);
        model.addAttribute("user",listUser);
        model.addAttribute("keyword", keyword);
		return "showAllUsers";

    }
	
	
	
	@PostMapping("/searchUser")
	public String paramSearch(Model m, @RequestParam(required = false, name="tech") String tech, 
			@RequestParam(required=false, defaultValue="0", name="experience") int years,
			@RequestParam(required = false, name="establishment") String est,
			@RequestParam(required = false, defaultValue="0", name="cgpa") int cgpa,
			@RequestParam(required = false, defaultValue="0", name="hsc") int hsc)
		{
		List<User> listUser= new ArrayList<>();
		String[] techArray = tech.split(",");
		if(techArray.length==1)
		{
			listUser = service.useFindByParam(techArray[0], years, est, cgpa, hsc);
		}
		else if(techArray.length==2)
		{
			listUser = service.useFindByParamTwo(techArray[0], techArray[1], years, est, cgpa, hsc);
		}
		else if(techArray.length==3)
		{
			listUser = service.useFindByParamThree(techArray[0], techArray[1], techArray[2], years, est, cgpa, hsc);
		}		
		m.addAttribute("user", listUser);
		return "showSearchUsers";
		}

	
	//email search for single user
	@PostMapping("/searchEmail")
	public String searchViaEmail(Model m , @RequestParam("email") String email, HttpSession session)
	{
		List<User> users = service.findByEmail(email);
		if(users.size()==0)
		{
			session.setAttribute("msg", "Oops, create an application soon !");
			m.addAttribute("user",users);
			return "singleUserApplication";
		}
		else
		{
			session.setAttribute("msg", "List of your applications ");
			m.addAttribute("user",users);
			return "singleUserApplication";
		}
	}
	

	//login part
	@RequestMapping("/welcome")
	public ModelAndView defaultHome() {
		return new ModelAndView("login");
	}

//	@RequestMapping("/home")
//	public ModelAndView showhome() {
//		return new ModelAndView("home");
//	}

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("index");
	}

	@RequestMapping("/userDashboard")
	public ModelAndView userDashboard() {
		return new ModelAndView("userDashboard");
	}

	@RequestMapping("/adminDashboard")
	public ModelAndView admindashboard() {
		return new ModelAndView("adminDashboard");
	}
	
	
}











