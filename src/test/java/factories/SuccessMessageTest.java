package factories;

import dto.SuccessMessageDTO;
import org.testng.annotations.Factory;
import tests.ValidatingSuccessMessageTests;
import utils.JsonTestDataReader;

import java.util.List;

public class SuccessMessageTest {

    @Factory
    public Object[] createInstances() {
        // NOTE: Make sure the path matches the actual resource folder name!
        String resourcePath = "testdata/successMessageTestData.json"; // <-- Update to match your directory!
        List<SuccessMessageDTO> testData = JsonTestDataReader.getsSuccessMessageData(resourcePath);

        if (testData == null || testData.isEmpty()) {
            throw new RuntimeException("No test data loaded from: " + resourcePath);
        }

        Object[] instances = testData.stream()
                .map(ValidatingSuccessMessageTests::new)
                .toArray();

        System.out.println("Factory generated " + instances.length + " test instances from " + resourcePath);

        return instances;
    }
}