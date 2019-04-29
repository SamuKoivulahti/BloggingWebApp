package tamk.tiko.BloggingWebApp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Blog data.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
@Entity
public class BlogPost {

    /**
     * Stores identifier.
     */
    @Id
    @GeneratedValue
    private int id;

    /**
     * Stores author name.
     */
    @Column
    private String name;

    /**
     * Stores title.
     */
    @Column
    private String title;

    /**
     * Stores content.
     */
    @Column
    private String content;

    /**
     * Stores comments.
     */
    @Column(length = 10000)
    private ArrayList<String> comments;

    public BlogPost() {
    }

    public BlogPost(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        comments = new ArrayList<>();
    }

    /**
     * Gets identifier.
     *
     * @return Identifier as integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets author name.
     *
     * @return Author name as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets author name.
     *
     * @param name Name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets title.
     *
     * @return Title as String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title Title value.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets content.
     *
     * @return Content as String.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets content.
     *
     * @param content Content value.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets comments.
     *
     * @return Comments as a ArrayList<String>.
     */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
     * Sets comments.
     *
     * @param comments Comments value.
     */
    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    /**
     * Adds a comment into comments.
     *
     * @param comment Comment value.
     */
    public void addComment(String comment) {
        comments.add(comment);
    }

    /**
     * Deletes a comment based on its id in the comments ArrayList.
     *
     * @param commentId id of the comment to be deleted.
     */
    public void deleteComment(int commentId) {
        comments.remove(commentId);
    }

    /**
     * Overrides default toString().
     *
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
