package pl.edu.wat.library.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookRequest {
    private String title;
    private String description;
    private String authorId;
    private List<String> type;

    public BookRequest(String title, String description, String authorId, LocalDateTime dateTime) {
        this.title = title;
        this.description = description;
        this.authorId = authorId;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthorId() {
        return authorId;
    }

    public List<String> getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    private LocalDateTime dateTime;
}
