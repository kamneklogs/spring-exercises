package co.edu.icesi.ci.thymeval.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.thymeval.controller.interfaces.UserController;
import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.service.UserServiceImpl;

@Controller
@RequestMapping("/users/")
public class UserControllerImpl implements UserController {

	UserServiceImpl userService;

	@Autowired
	public UserControllerImpl(UserServiceImpl userService) {
		this.userService = userService;
		;
	}

	@GetMapping("/add")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/add-user";
	}

	@GetMapping("/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@PostMapping("/add")
	public String saveUser(User user, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel"))
			userService.save(user);
		return "redirect:/users/";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());
		return "users/update-user";
	}

	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action, User user, Model model) {
		if (action != null && !action.equals("Cancel")) {
			userService.save(user);
			model.addAttribute("users", userService.findAll());
		}
		return "redirect:/users/";
	}
}
