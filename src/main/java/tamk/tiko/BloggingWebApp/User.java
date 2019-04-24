package tamk.tiko.BloggingWebApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

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
    private String pass;

    @Column
    private ArrayList<Integer> likes;

    public User() {
    }

    public User(String name, boolean admin, String pass) {
        this.name = name;
        this.admin = admin;
        this.pass = pass;
        likes = new ArrayList<>();
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public ArrayList<Integer> getLikes() {
        return likes;
    }

    public void setLikes(ArrayList<Integer> likes) {
        this.likes = likes;
    }

    public void addLikes(Integer id) {
        likes.add(id);
    }

    public void deleteLikes(Integer id) {
        for (int i = 0; i < likes.size(); i++) {
            if (likes.get(i).equals(id)) {
                likes.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                ", pass='" + pass + '\'' +
                ", likes=" + likes +
                '}';
    }
}
