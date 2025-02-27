package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.repository;

import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.Category;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.EntityTBD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByNameAndEntityTBD(String name, EntityTBD entityTBD);
}
