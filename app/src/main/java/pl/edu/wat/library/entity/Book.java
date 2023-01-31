package pl.edu.wat.library.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class Book implements Serializable {

    private String id;
    private String title;
    private String description;
    private String author;
    private List<String> type;
    private String dateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public void setCreated(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getType() {
        return type;
    }

    public String getCreated() {
        //Calendar calendar = Calendar.getInstance();
        //return calendar.getTime();
        return dateTime;
    }


    public Book(String title,
                String description,
                String author,
                List<String> type,
                String dateTime) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.type = type;
        this.dateTime = dateTime;
    }

    public Book() {

    }
}