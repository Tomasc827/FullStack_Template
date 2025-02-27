package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity;

import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.Category;

import java.util.List;

public record EntityTBDResponseDTO(long id, String name, String imageURL, List<Category> categories) {
}
