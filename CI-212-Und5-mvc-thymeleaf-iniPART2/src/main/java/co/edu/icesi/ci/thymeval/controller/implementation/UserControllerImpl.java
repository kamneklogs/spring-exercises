package co.edu.icesi.ci.thymeval.controller.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.icesi.ci.thymeval.controller.interfaces.UserController;
import co.edu.icesi.ci.thymeval.model.User;
import co.edu.icesi.ci.thymeval.model.add1;
import co.edu.icesi.ci.thymeval.model.add2;
import co.edu.icesi.ci.thymeval.model.editValidations;
import co.edu.icesi.ci.thymeval.service.UserServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserControllerImpl implements UserController {

	UserServiceImpl userService;

	@Autowired
	public UserControllerImpl(UserServiceImpl userService) {
		this.userService = userService;

	}

	@GetMapping("/users/")
	public String indexUser(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/users/addUser1V")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "users/add-user-first-validation";
	}

	@PostMapping("/users/addUser1V")
	public String saveUserV1(@Validated(add1.class) @ModelAttribute User user, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (action.equals("Cancel")) {
			return "redirect:/users/";
		} else {
			if (bindingResult.hasErrors()) {

				log.info("ERRROOORRRRRRRRRRRRRRRRRRRR");
				log.info(bindingResult.getErrorCount());
				log.info(bindingResult.getFieldError().getDefaultMessage());

				return "/users/add-user-first-validation";
			}
			user.setId(userService.save(user).getId());

			model.addAttribute("user", user);
			model.addAttribute("genders", userService.getGenders());
			model.addAttribute("types", userService.getTypes());

			return "/users/add-user-second-validation";
		}

	}

	@PostMapping("/users/save")
	public String saveUser(@Validated(add2.class) @ModelAttribute User user, BindingResult bindingResult, Model model,
			@RequestParam(value = "action", required = true) String action) {

		if (action.equals("Cancel")) {

			userService.delete(user);
			return "redirect:/users/";
		} else {

			log.info(user.toString());

			userService.saveUserSecondValidation(user);

			return "redirect:/users/";
		}

	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User user = userService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<User> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());

		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@Validated(editValidations.class) @ModelAttribute User user, BindingResult bindingResult,
			Model model, @PathVariable("id") long id, @RequestParam(value = "action", required = true) String action) {

		log.info(user.getUsername());

		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				log.info("ERRROOORRRRRRRRRRRRRRRRRRRR");
				log.info(bindingResult.getAllErrors().get(0).getDefaultMessage());
				model.addAttribute("user", userService.findById(id).get());
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());

				return "users/update-user";
			}

			if (user.getPassword().isEmpty()) {

				user.setPassword(userService.findById(id).get().getPassword());
				log.info(user.getPassword() + "     1111111111");
			}
			log.info(user.getPassword() + "      2222222222222");

			userService.save(user);
			model.addAttribute("users", userService.findAll());
		}
		return "redirect:/users/";
	}
}
