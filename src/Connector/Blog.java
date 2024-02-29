package Connector;

public class Blog {
    private Long id;

    private User user;

    private String title;

    private String content;

    private String postDate;

    public Blog(){}

    public Blog(Long id, User user, String title, String content, String postDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
