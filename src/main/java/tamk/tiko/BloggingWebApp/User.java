package tamk.tiko.BloggingWebApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;

    @Column
    private String name;

    @Column
    private boolean admin;

    @Column
    private ArrayList<BlogPost> like;

    public User() {
    }

    public User(String name, boolean admin) {
        this.name = name;
        this.admin = admin;
        like = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public ArrayList<BlogPost> getLike() {
        return like;
    }

    public void setLike(ArrayList<BlogPost> like) {
        this.like = like;
    }

    public void addLike(BlogPost blogPost) {
        like.add(blogPost);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                '}';
    }
}
