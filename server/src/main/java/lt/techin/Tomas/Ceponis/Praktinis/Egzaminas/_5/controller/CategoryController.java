package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.controller;

import jakarta.validation.Valid;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.category.CategoryRequestDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.category.CategoryResponseDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.service.CategoryService;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.util.WebUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/entity/{entityID}/add-category")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<CategoryResponseDTO> addCategory(@Valid @RequestBody CategoryRequestDTO dto, @PathVariable Long entityID) {
        CategoryResponseDTO responseDTO = categoryService.addCategory(dto,entityID);
        URI location = WebUtil.createLocation("/{id}", responseDTO.id());
        return ResponseEntity.created(location).body(responseDTO);
    }
}
