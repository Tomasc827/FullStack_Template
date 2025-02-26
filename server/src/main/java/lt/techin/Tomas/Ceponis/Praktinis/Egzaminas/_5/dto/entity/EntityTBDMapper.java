package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity;

import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.EntityTBD;

public class EntityTBDMapper {

    public static EntityTBD toEntity(EntityTBDRequestDTO dto) {
        EntityTBD entityTBD = new EntityTBD();
        entityTBD.setName(dto.name().toLowerCase());
        entityTBD.setImageURL(dto.imageURL());
        return entityTBD;
    }

    public static EntityTBDResponseDTO toDTO(EntityTBD entityTBD) {
        return new EntityTBDResponseDTO(entityTBD.getId(),
                entityTBD.getName(),
                entityTBD.getImageURL());
    }
}
