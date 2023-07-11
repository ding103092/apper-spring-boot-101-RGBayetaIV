package com.apper.theblogservice;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.exception.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BloggerApi {

    private final BloggerService bloggerService;

    @Autowired
    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping(path="/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse registerBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailAlreadyRegisteredException {
        // System.out.println(request);

        Blogger createdBlogger = bloggerService.registerBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());

        return response;
    }

    @PostMapping(path="/blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) throws BloggerNotFoundException {
        // System.out.println(request);

        Blog createdBlog = bloggerService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdate(createdBlog.getLastUpdate());

        return response;
    }

    @PutMapping(path="/blog/{blog_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CreateBlogResponse updateBlog(@PathVariable String blog_id, @RequestBody @Valid CreateBlogRequest request) throws BlogNotFoundException {

        Blog createdBlog = bloggerService.updateBlog(request.getTitle(), request.getBody(), blog_id.replace("/blog",""));

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getId());
        response.setBloggerId(createdBlog.getBloggerId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdate(createdBlog.getLastUpdate());

        return response;
    }

    @GetMapping(path="/blogger/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetBloggerResponse getBlogger(@PathVariable String id) throws BloggerNotFoundException {
        Blogger blogger = bloggerService.getBlogger(id);

        GetBloggerResponse bloggerDetails = new GetBloggerResponse();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }

    @GetMapping(path="/blog/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetBlogResponse getBlog(@PathVariable String id) throws BlogNotFoundException {
        Blog blog = bloggerService.getBlog(id);

        return getGetBlogResponse(blog);
    }

    @GetMapping(path="/blogger")
    @ResponseStatus(HttpStatus.OK)
    public List<GetBloggerResponse> getAllBloggers() throws BloggerNotFoundException {
        List<Blogger> bloggers = bloggerService.getAllBloggers();

        List<GetBloggerResponse> bloggerDetails = bloggers.stream()
                .map(blogger -> {
                    GetBloggerResponse response = new GetBloggerResponse();
                    response.setId(blogger.getId());
                    response.setName(blogger.getName());
                    response.setEmail(blogger.getEmail());
                    response.setDateRegistration(blogger.getCreatedAt());
                    return response;
                })
                .toList();

        if(bloggerDetails.isEmpty()) {
            throw new BloggerNotFoundException("No registered bloggers found");
        }

        return bloggerDetails;
    }

    @GetMapping(path="/blog")
    @ResponseStatus(HttpStatus.OK)
    public List<GetBlogResponse> getAllBlogs() throws BlogNotFoundException {
        List<Blog> blogs = bloggerService.getAllBlogs();

        List<GetBlogResponse> blogDetails = blogs.stream()
                .map(this::getGetBlogResponse)
                .toList();

        if(blogDetails.isEmpty()) {
            throw new BlogNotFoundException("No blogs found");
        }

        return blogDetails;
    }

    @GetMapping(path="/blog/blogger/{blogger_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetBlogResponse> getAllBlogsByBloggerId(@PathVariable String blogger_id) throws BloggerNotFoundException, BlogNotFoundException {
        List<Blog> blogs = bloggerService.getAllBlogsByBloggerId(blogger_id);

        List<GetBlogResponse> blogDetails = blogs.stream()
                .map(this::getGetBlogResponse)
                .toList();

        if(blogDetails.isEmpty()) {
            throw new BlogNotFoundException("No blogs found");
        }

        return blogDetails;
    }

    /*==========================/
    *     Helper functions      *
    /==========================*/
    private GetBlogResponse getGetBlogResponse(Blog blog) {
        GetBlogResponse response = new GetBlogResponse();
        response.setBloggerId(blog.getBloggerId());
        response.setTitle(blog.getTitle());
        response.setBody(blog.getBody());
        response.setCreatedAt(blog.getCreatedAt());
        response.setLastUpdated(blog.getLastUpdate());
        return response;
    }
}