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
import co.edu.icesi.ci.thymeval.model.CompleteInfoValidation;
import co.edu.icesi.ci.thymeval.model.CredentialsInfoValidation;
import co.edu.icesi.ci.thymeval.model.PersonalInfoValidation;
import co.edu.icesi.ci.thymeval.model.UserApp;
import co.edu.icesi.ci.thymeval.service.UserServiceImpl;
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

	@GetMapping("/login")
	public String login(Model model) {

		return "/login";
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return "/login";
	}

	@GetMapping("/users/addUser1V")
	public String addUser(Model model) {
		model.addAttribute("user", new UserApp());
		return "users/add-user-first-validation";
	}

	@PostMapping("/users/addUser1V")
	public String saveUserV1(@Validated(CredentialsInfoValidation.class) @ModelAttribute UserApp user,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {

		if (action.equals("Cancel")) {
			return "redirect:/users/";
		} else {
			if (bindingResult.hasErrors()) {

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
	public String saveUser(@Validated(PersonalInfoValidation.class) @ModelAttribute UserApp user,
			BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {

		if (action.equals("Cancel")) {

			userService.delete(user);
			return "redirect:/users/";
		} else {
			if (bindingResult.hasErrors()) {
				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());
				return "/users/add-user-second-validation";
			}

			userService.saveUserSecondValidation(user);
			return "redirect:/users/";
		}

	}

	@GetMapping("/users/del/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		UserApp user = userService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userService.delete(user);
		model.addAttribute("users", userService.findAll());
		return "users/index";
	}

	@GetMapping("/users/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Optional<UserApp> user = userService.findById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id:" + id);
		model.addAttribute("user", user.get());
		model.addAttribute("genders", userService.getGenders());
		model.addAttribute("types", userService.getTypes());

		return "users/update-user";
	}

	@PostMapping("/users/edit/{id}")
	public String updateUser(@Validated(CompleteInfoValidation.class) @ModelAttribute UserApp user,
			BindingResult bindingResult, Model model, @PathVariable("id") long id,
			@RequestParam(value = "action", required = true) String action) {

		if (action != null && !action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {

				model.addAttribute("genders", userService.getGenders());
				model.addAttribute("types", userService.getTypes());

				return "users/update-user";
			}

			if (user.getPassword().isEmpty()) {

				user.setPassword(userService.findById(id).get().getPassword());
			}

			userService.save(user);
		}
		return "redirect:/users/";
	}
}
