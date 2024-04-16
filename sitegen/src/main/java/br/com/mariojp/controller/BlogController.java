
package br.com.mariojp.controller;

import br.com.mariojp.model.Blog;
import br.com.mariojp.model.Post;
import br.com.mariojp.parsers.HTMLGenerator;
import br.com.mariojp.view.BlogView;

import java.util.ArrayList;
import java.util.List;

public class BlogController {

    private Blog blog;


    public BlogController() {
    }

    public Blog getBlog() {
        return blog;
    }

    public ArrayList<Post> getPosts(){
        return this.blog.getPosts();
    }

    public void addPost(String title, String content, String template) {
        blog.addPost(new Post(title, content, template));
    }

    public void editPost(int index, String title, String content) {
        if (index >= 0 && index < blog.getPosts().size()) {
            Post post = blog.getPosts().get(index);
            post.setTitle(title);
            post.setContent(content);
        }
    }

    public void deletePost(int index) {
        blog.deletePost(index);
    }


    public void genetateSite(){
        new HTMLGenerator(blog).generateBlog();
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }


}
