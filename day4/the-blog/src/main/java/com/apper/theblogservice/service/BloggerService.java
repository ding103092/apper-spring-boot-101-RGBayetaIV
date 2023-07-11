package com.apper.theblogservice.service;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.exception.EmailAlreadyRegisteredException;
import com.apper.theblogservice.exception.InvalidBloggerIdException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }

    public Blogger registerBlogger(String email, String name, String password) throws EmailAlreadyRegisteredException {

        // Make sure that email is not yet registered
        if (bloggerRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("Email is already registered");
        }

        // Save to repository for new blogger
        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    public Blog createBlog(String title, String body, String bloggerId) throws BloggerNotFoundException, InvalidBloggerIdException {
        if(bloggerId.equals("")) {
            throw new InvalidBloggerIdException("`blogger_id` cannot be empty");
        }

        if(!isBloggerRegistered(bloggerId)) {
            throw new BloggerNotFoundException("No registered blogger found with id: " + bloggerId);
        }

        // Save to repository for new blog
        Blog blog = new Blog();
        blog.setBloggerId(bloggerId);
        blog.setTitle(title);
        blog.setBody(body);

        return blogRepository.save(blog);
    }

    public Blogger getBlogger(String id) throws BloggerNotFoundException {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);

        return bloggerResult.orElseThrow(() -> new BloggerNotFoundException("No blogger found with id: " + id));
    }

    public Blog getBlog(String id) throws BlogNotFoundException {
        Optional<Blog> blogResult = blogRepository.findById(id);
        return blogResult.orElseThrow(() -> new BlogNotFoundException("No blog found with id: " + id));
    }

    public List<Blogger> getAllBloggers() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    public List<Blog> getAllBlogsByBloggerId(String bloggerId) throws BloggerNotFoundException {
        if (!isBloggerRegistered(bloggerId)) {
            throw new BloggerNotFoundException("No registered blogger found with id: " + bloggerId);
        }
        return blogRepository.findAllByBloggerId(bloggerId);
    }


    public Blog updateBlog(String title, String body, String blogId) throws BlogNotFoundException {
        if(!isBlockExists(blogId)) {
            throw new BlogNotFoundException("No such blog found with id: " + blogId);
        }
        Optional<Blog> selectedBlog = blogRepository.findById(blogId);
        selectedBlog.ifPresent(blog -> {
            blog.setTitle(title);
            blog.setBody(body);
            blog.setLastUpdate(LocalDateTime.now()); // Updates the time
            blogRepository.save(blog);
        });

        return selectedBlog.orElseThrow(() -> new BlogNotFoundException("No blog found with id: " + blogId));
    }


    /*==========================/
    *     Helper functions      *
    /==========================*/
    private boolean isBloggerRegistered(String bloggerId) {
        return bloggerRepository.existsById(bloggerId);
    }

    private boolean isBlockExists(String blogId) {
        return blogRepository.existsById(blogId);
    }


}