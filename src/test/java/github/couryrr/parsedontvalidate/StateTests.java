package github.couryrr.parsedontvalidate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.couryrr.parsedontvalidate.type.State;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

class StateTests {

    @Test
    void parseState() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Path path = new ClassPathResource("address.json").getFile().toPath();
        String json = Files.readString(path);
        List<Map<String, Object>> addresses = objectMapper.readValue(json, new TypeReference<>() {});
        for (Map<String, Object> address : addresses) {
                var sut = (String) address.get("state");
                State state = State.from(sut);
                Assertions.assertThat(state.getValue()).isEqualTo(sut);
        }
    }

    @Test
    void parseInvalidAbbreviation() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> State.from("NG")).withMessageContaining("Invalid state:");
    }

    @Test
    void parseInvalidName() {
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> State.from("New Bork")).withMessageContaining("Invalid state:");
    }

}
