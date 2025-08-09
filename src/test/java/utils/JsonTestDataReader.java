package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.SuccessMessageDTO;

import java.io.InputStream;
import java.util.List;

public class JsonTestDataReader {

    public static List<SuccessMessageDTO> getsSuccessMessageData(String jsonFilePath) {
        try (InputStream is = JsonTestDataReader.class.getClassLoader().getResourceAsStream(jsonFilePath)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<List<SuccessMessageDTO>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON test data", e);
        }
    }
}
