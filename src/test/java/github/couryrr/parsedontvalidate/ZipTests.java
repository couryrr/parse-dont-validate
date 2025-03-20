package github.couryrr.parsedontvalidate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.couryrr.parsedontvalidate.type.Zip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class ZipTests {

    @Test
    void parseZip() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = new ClassPathResource("address.json").getFile().toPath();
        String json = Files.readString(path);
        List<Map<String, Object>> addresses = objectMapper.readValue(json, new TypeReference<>() {});
        for (Map<String, Object> address : addresses) {
                var sut = (String) address.get("zip");
                Zip zip = Zip.from(sut);
                Assertions.assertThat(zip.getValue()).isEqualTo(sut);
        }
    }

    @Test
    void parseInvalidTooShort() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Zip.from("1234")).withMessageContaining("Invalid zip:");
    }

    @Test
    void parseInvalidTooLong() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Zip.from("123456")).withMessageContaining("Invalid zip:");
    }

    @Test
    void parseInvalidHasAlpha() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Zip.from("1234b")).withMessageContaining("Invalid zip:");
    }

    @Test
    void parseInvalidHasJustHyphen() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Zip.from("12345-")).withMessageContaining("Invalid zip:");
    }

    @Test
    void parseInvalidHasHyphenTooShort() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Zip.from("12345-123")).withMessageContaining("Invalid zip:");
    }

    @Test
    void parseInvalidHasHyphenTooLong() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Zip.from("12345-12345")).withMessageContaining("Invalid zip:");
    }

}
