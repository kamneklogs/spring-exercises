package com.packt.springboot.formhandling.blogpost;

import lombok.Data;

@Data
public class CreateBlogPostCommand {
    private String title;
    private String slug;
    private String content;
    private boolean visible;
}
