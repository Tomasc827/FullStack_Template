package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "entity_id")
    private EntityTBD entityTBD;

    public Category() {

    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityTBD getEntityTBD() {
        return entityTBD;
    }

    public void setEntityTBD(EntityTBD entityTBD) {
        this.entityTBD = entityTBD;
    }
}
