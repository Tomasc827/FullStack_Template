package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.repository;

import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.EntityTBD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityTBDRepository extends JpaRepository<EntityTBD,Long> {
    Page<EntityTBD> findByNameContaining(String name, Pageable pageable);
}
