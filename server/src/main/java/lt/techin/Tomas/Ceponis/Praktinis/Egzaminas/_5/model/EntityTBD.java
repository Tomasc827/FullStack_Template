package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "entity")
public class EntityTBD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String imageURL;
    private LocalDateTime createdAt;

    public EntityTBD() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDateTime getCreateAt() {
        return createdAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createdAt = createAt;
    }
}
