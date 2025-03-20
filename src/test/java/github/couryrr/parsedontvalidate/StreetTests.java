package github.couryrr.parsedontvalidate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.couryrr.parsedontvalidate.type.Street;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.List;

class StreetTests {

    @Test
    void parseStreet() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = new ClassPathResource("address.json").getFile().toPath();
        String json = Files.readString(path);
        List<Map<String, Object>> addresses = objectMapper.readValue(json, new TypeReference<>() {});
        for (Map<String, Object> address : addresses) {
                var sut = (String) address.get("street");
                Street street = Street.from(sut);
                Assertions.assertThat(street.getValue()).isEqualTo(sut);
        }
    }

    @Test
    void parseInvalidStreet() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Street.from("123 Main R")).withMessageContaining("Invalid street address:");
    }

    @Test
    void parseInvalidNumber() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Street.from("Main Street")).withMessageContaining("Invalid street address:");
    }

    @Test
    void parseInvalidName() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Street.from("123 Street")).withMessageContaining("Invalid street address:");
    }

}
