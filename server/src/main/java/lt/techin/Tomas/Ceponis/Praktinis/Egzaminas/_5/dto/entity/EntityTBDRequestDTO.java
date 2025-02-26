package lt.techin.Tomas.Ceponis.Praktinis.Egzaminas._5.dto.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EntityTBDRequestDTO(@NotNull(message = "Name cannot be null")
                                  @Size(min = 3,max = 255, message = "Name must be from 3 to 255")
                                  @Pattern(regexp = "^[a-zA-Z0-9 !.,£$%^&*(){}:;'#`~_-]*$", message = "Name allows letter,numbers and following symbols !.,£$%^&*(){}:;'#`~_-")
                                  String name,
                                  @Pattern(
                                          regexp = "^(|https?:\\/\\/(?:[a-zA-Z0-9\\-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})+)$",
                                          message = "Must be a valid HTTP/HTTPS URL or empty"
                                  )
                                  String imageURL) {
}
