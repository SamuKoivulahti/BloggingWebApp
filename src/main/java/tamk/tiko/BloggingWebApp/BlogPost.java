package tamk.tiko.BloggingWebApp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private ArrayList<String> comments;

    public BlogPost() {
    }

    public BlogPost(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
        comments = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

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
