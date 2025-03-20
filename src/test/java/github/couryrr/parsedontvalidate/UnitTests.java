package github.couryrr.parsedontvalidate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.couryrr.parsedontvalidate.type.Unit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class UnitTests {

    @Test
    void parseUnit() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = new ClassPathResource("address.json").getFile().toPath();
        String json = Files.readString(path);
        List<Map<String, Object>> addresses = objectMapper.readValue(json, new TypeReference<>() {
        });
        for (Map<String, Object> address : addresses) {
            var sut = (String) address.get("unit");
            Unit unit = Unit.from(sut);
            if (sut != null) {
                Assertions.assertThat(unit.getValue()).isEqualTo(sut);
            } else {
                Assertions.assertThat(unit.getValue()).isEmpty();

            }
        }
    }

    @Test
    void parseInvalidSpecialCharacter() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Unit.from("Suite! 3")).withMessageContaining("Invalid unit:");
    }

    @Test
    void parseInvalidMissingNumber() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Unit.from("# ")).withMessageContaining("Invalid unit:");
    }


}
