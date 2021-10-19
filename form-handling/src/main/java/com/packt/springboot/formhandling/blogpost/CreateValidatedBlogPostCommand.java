package com.packt.springboot.formhandling.blogpost;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateValidatedBlogPostCommand {
    @NotBlank
    @Size(max = 140) // title is not allowed to be empty // or longer than 140 characters
    private String title;
    @Size(min = 3, max = 60) // slug must be between 3 and 60 characters long
    private String slug;

    @NotBlank // content must not be empty
    private String content;

    private boolean visible;

    private  LocalDateTime created;

}
