package tamk.tiko.BloggingWebApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * User data.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
@Entity
public class User {

    /**
     * Stores identifier.
     */
    @Id
    @GeneratedValue
    private int userId;

    /**
     * Stores user name.
     */
    @Column
    private String name;

    /**
     * Stores admin state.
     */
    @Column
    private boolean admin;

    /**
     * Stores password.
     */
    @Column
    private String pass;

    /**
     * Stores likes.
     */
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

    /**
     * Gets identifier.
     *
     * @return Identifier as integer.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets users name.
     *
     * @return Name as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the user.
     *
     * @param name Name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if user is admin.
     *
     * @return True if user is admin, false otherwise.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets user admin state.
     *
     * @param admin Admin state.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets password.
     *
     * @return Password as String.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Sets password.
     *
     * @param pass Password value.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Gets likes.
     *
     * @return Likes as ArrayList<Integer>.
     */
    public ArrayList<Integer> getLikes() {
        return likes;
    }

    /**
     * Sets likes.
     *
     * @param likes Likes value.
     */
    public void setLikes(ArrayList<Integer> likes) {
        this.likes = likes;
    }

    /**
     * Adds a like to likes ArrayList.
     *
     * @param id Identifier of liked blog post.
     */
    public void addLikes(Integer id) {
        likes.add(id);
    }

    /**
     * Delete a like from likes ArrayList.
     *
     * @param id Identifier of the liked blog post.
     */
    public void deleteLikes(Integer id) {
        for (int i = 0; i < likes.size(); i++) {
            if (likes.get(i).equals(id)) {
                likes.remove(i);
                break;
            }
        }
    }

    /**
     * Overrides default toString().
     *
     * @return String representation of this object.
     */
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
