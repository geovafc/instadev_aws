package br.com.instadev.aws_instadev.model;

import javax.persistence.*;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"code"})
        }
)
@Entity
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 32, nullable = false)
    private String title;

    @Column(length = 120, nullable = false)
    private String description;

    private long likeCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

}
