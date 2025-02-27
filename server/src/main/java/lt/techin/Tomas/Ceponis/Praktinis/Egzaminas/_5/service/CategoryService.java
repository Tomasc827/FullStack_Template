package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.service;

import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.category.CategoryMapper;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.category.CategoryRequestDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.category.CategoryResponseDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.exception.AlreadyExistsException;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.exception.NotFoundException;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.Category;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.model.EntityTBD;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.repository.CategoryRepository;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.repository.EntityTBDRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final EntityTBDRepository entityTBDRepository;

    public CategoryService(CategoryRepository categoryRepository, EntityTBDRepository entityTBDRepository) {
        this.categoryRepository = categoryRepository;
        this.entityTBDRepository = entityTBDRepository;
    }

    public CategoryResponseDTO addCategory(CategoryRequestDTO dto,Long entityId) {
        EntityTBD entityTBD = entityTBDRepository.findById(entityId).orElseThrow(() -> new NotFoundException("Entity not found"));

        if (categoryRepository.existsByNameAndEntityTBD(dto.name(),entityTBD)) {
            throw new AlreadyExistsException("Category '" + dto.name() + "' already exists");
        }
        Category category = new Category();
        category.setName(dto.name().toLowerCase());

        entityTBD.getCategories().add(category);
        category.setEntityTBD(entityTBD);

        categoryRepository.save(category);

        return CategoryMapper.toDTO(category);


    }
}
