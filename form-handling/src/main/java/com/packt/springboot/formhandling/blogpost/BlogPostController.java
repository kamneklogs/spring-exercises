package com.packt.springboot.formhandling.blogpost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/blogposts")
@Slf4j
public class BlogPostController {

    @GetMapping("new-multiple-values")
    public String renderFormViewForSeparateValues(Model model) {
        model.addAttribute("title", "Default Title");
        model.addAttribute("slug", "default-slug");
        model.addAttribute("content", "I always wanted to say...");
        return "blogposts/form-multiple-values";
    }

    // Insert createBlogPostFromMultipleValues() here
    @PostMapping("create-multiple-values")
    public ModelAndView createBlogPostFromMultipleValues(@RequestParam(name = "title") String title,
            @RequestParam(name = "slug") String slug, @RequestParam(name = "content") String content,
            @RequestParam(name = "visible", defaultValue = "visible") boolean visible) {
        BlogPost createdBlogPost = createBlogPost(title, slug, content, visible);
        return new ModelAndView("blogposts/show", "blogPost", createdBlogPost);
    }

    // Insert renderFormViewForBackingBean()
    @GetMapping("new-backing-bean")
    public ModelAndView renderFormViewForBackingBean() {
        CreateBlogPostCommand createBlogPostCommand = new CreateBlogPostCommand();
        createBlogPostCommand.setTitle("Default Title");
        return new ModelAndView("blogposts/form-backing-bean", "createBlogPostCommand", createBlogPostCommand);
    }

    // Insert createBlogPostFromBackingBean()

    @PostMapping("create-backing-bean")
    public ModelAndView createBlogPostFromBackingBean(@ModelAttribute CreateBlogPostCommand createBlogPostCommand) {
        BlogPost createdBlogPost = createBlogPost(createBlogPostCommand.getTitle(), createBlogPostCommand.getSlug(),
                createBlogPostCommand.getContent(), createBlogPostCommand.isVisible());
        return new ModelAndView("blogposts/show", "blogPost", createdBlogPost);
    }

    // Insert renderFormViewForValidatedBean()

    @GetMapping("new-validated-bean")
    public ModelAndView renderFormViewForValidatedBean() {
        CreateValidatedBlogPostCommand createValidatedBlogPostCommand = new CreateValidatedBlogPostCommand();
        createValidatedBlogPostCommand.setTitle("Default Title");
        return new ModelAndView("blogposts/form-validated-bean", "createValidatedBlogPostCommand",
                createValidatedBlogPostCommand);
    }

    // Insert createBlogPostFromValidatedBean()

    @PostMapping("create-validated-bean")
    public ModelAndView createBlogPostFromValidatedBean(
            @Validated @ModelAttribute CreateValidatedBlogPostCommand createValidatedBlogPostCommand,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("ERRROOORRRRRRRRRRRRRRRRRRRR");

            return new ModelAndView("blogposts/form-validated-bean", "createValidatedBlogPostCommand",
                    createValidatedBlogPostCommand);

        } // ...
        createValidatedBlogPostCommand.setCreated(LocalDateTime.now());
        log.info(createValidatedBlogPostCommand.getContent());

        return new ModelAndView("blogposts/show", "createValidatedBlogPostCommand", createValidatedBlogPostCommand);
    }

    private BlogPost createBlogPost(String title, String slug, String content, boolean visible) {
        BlogPost createdBlogPost = new BlogPost(LocalDateTime.now(), title, slug, content, visible);

        log.info("Created blog post {}", createdBlogPost);

        return createdBlogPost;
    }

}
