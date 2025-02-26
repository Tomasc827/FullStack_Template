package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.controller;

import jakarta.validation.Valid;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity.EntityTBDRequestDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity.EntityTBDResponseDTO;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.service.EntityTBDService;
import lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.util.WebUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/entity")
public class EntityTBDController {

    private final EntityTBDService entityTBDService;

    public EntityTBDController(EntityTBDService entityTBDService) {
        this.entityTBDService = entityTBDService;
    }

    @PostMapping
    public ResponseEntity<EntityTBDResponseDTO> createEntity(@Valid @RequestBody EntityTBDRequestDTO dto) {
        EntityTBDResponseDTO responseDTO = entityTBDService.createEntity(dto);

        URI location = WebUtil.createLocation("/{id}",responseDTO.id());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<EntityTBDResponseDTO>> getAllSortedPaged(@RequestParam(defaultValue = "desc") String direction,
                                                                        @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                        @RequestParam(defaultValue = "0") short page,
                                                                        @RequestParam(defaultValue = "8") byte size,
                                                                        @RequestParam(required = false) String name) {
        Pageable pageable = PageRequest.of(page,size,direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,sortBy);

        return ResponseEntity.ok().body(entityTBDService.getAllSortedPaged(pageable,name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityTBDResponseDTO> getOneEntity(@PathVariable long id) {
        return ResponseEntity.ok().body(entityTBDService.getOneEntity(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> deleteEntity(@PathVariable long id) {
        entityTBDService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<EntityTBDResponseDTO> updateEntity(@Valid @RequestBody EntityTBDRequestDTO dto,@PathVariable long id) {
        return ResponseEntity.ok().body(entityTBDService.updateEntity(dto, id));
    }

}
