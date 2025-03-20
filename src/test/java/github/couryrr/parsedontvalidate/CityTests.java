package github.couryrr.parsedontvalidate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.couryrr.parsedontvalidate.type.City;
import github.couryrr.parsedontvalidate.type.Zip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class CityTests {

    @Test
    void parseCity() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = new ClassPathResource("address.json").getFile().toPath();
        String json = Files.readString(path);
        List<Map<String, Object>> addresses = objectMapper.readValue(json, new TypeReference<>() {});
        for (Map<String, Object> address : addresses) {
                var sut = (String) address.get("city");
                City city = City.from(sut);
                Assertions.assertThat(city.getValue()).isEqualTo(sut);
        }
    }

    @Test
    void parseInvalidHasNumber() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> City.from("123City")).withMessageContaining("Invalid city:");
    }

    @Test
    void parseInvalidHasSpecialChar() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> City.from("City@Name")).withMessageContaining("Invalid city:");
    }
}
