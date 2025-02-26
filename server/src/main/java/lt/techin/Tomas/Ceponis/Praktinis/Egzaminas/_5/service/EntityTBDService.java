package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.service;


import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity.EntityTBDMapper;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity.EntityTBDRequestDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity.EntityTBDResponseDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.exception.NotFoundException;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.EntityTBD;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.repository.EntityTBDRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntityTBDService {
    private final EntityTBDRepository entityTBDRepository;

    public EntityTBDService(EntityTBDRepository entityTBDRepository) {
        this.entityTBDRepository = entityTBDRepository;
    }

    public EntityTBDResponseDTO createEntity(EntityTBDRequestDTO dto) {
        EntityTBD entityTBD = EntityTBDMapper.toEntity(dto);

        if (entityTBD.getImageURL() == null || entityTBD.getImageURL().isEmpty()) {
            entityTBD.setImageURL("https://thb-space-01.sgp1.cdn.digitaloceanspaces.com/thb-bucket-dev/attachment/arcfile_8ad469be-a86c-4788-902e-156cdd435881.jpg");
        }

        entityTBD.setCreateAt(LocalDateTime.now());

        entityTBDRepository.save(entityTBD);

        return EntityTBDMapper.toDTO(entityTBD);
    }
    public Page<EntityTBDResponseDTO> getAllSortedPaged(Pageable pageable,String name) {
        if (name == null || name.isEmpty()) {
            return entityTBDRepository.findAll(pageable).map(EntityTBDMapper::toDTO);
        }
        return entityTBDRepository.findByNameContaining(name,pageable).map(EntityTBDMapper::toDTO);

    }

    public void deleteEntity(long id) {
        entityTBDRepository.delete(entityTBDRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity " +
                "Not Found")));
    }

    public EntityTBDResponseDTO getOneEntity(long id) {
        return EntityTBDMapper.toDTO(entityTBDRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity" +
                " not found")));
    }

    public EntityTBDResponseDTO updateEntity(EntityTBDRequestDTO dto,long id) {
        EntityTBD entityTBD = entityTBDRepository.findById(id).orElseThrow(() -> new NotFoundException("Entity not " +
                "found"));
        entityTBD.setName(dto.name());
        entityTBD.setImageURL(dto.imageURL());
        entityTBDRepository.save(entityTBD);
        return EntityTBDMapper.toDTO(entityTBD);
    }


}
