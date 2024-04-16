package br.com.mariojp.model;

import java.util.ArrayList;

public class Blog {
    private String siteName;
    private String baseUrl;
    private String email;
    private ArrayList<Post> posts;



    public Blog(String siteName, String baseUrl, String email) {
        this.siteName = siteName;
        this.baseUrl = baseUrl;
        this.email = email;
        this.posts = new ArrayList<>();
    }

    // Getters and setters
    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(int index) {
        if (index >= 0 && index < posts.size()) {
            posts.remove(index);
        }
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

