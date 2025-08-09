package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllureReportGenerator {

    public static void generateReport() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "allure", "generate", "allure-results", "--clean", "-o", "allure-report"
        );
        Process process = pb.start();

// Optionally, consume and print the output safely
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Allure report generated successfully.");
        } else {
            System.out.println("Allure report generation failed with exit code: " + exitCode);
        }

    }
}
