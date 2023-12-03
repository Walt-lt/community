package com.ltstudy.community.DTO;

public class GitHubUser {
    private  String name;
    private  long id;
    private  String bio;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public String toString() {
        return "GitHubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
