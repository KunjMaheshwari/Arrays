class Solution{
    public static void swapping(int arr[]){
        for(int i=0;i<arr.length;i++){
            if(i % 2 == 0){
                if(arr[i] > arr[i+1]){
                    swap(arr, i, i+1);
                }
            }else{
                if(arr[i] < arr[i+1]){
                    swap(arr, i, i+1);
                }
            }
        }
    }

    public static void swap(int arr[], int start, int end){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }
}






import org.openqa.selenium.*;
import org.testng.Assert;
import java.util.*;

public class ScenarioVersionValidator {

    WebDriver driver;

    public ScenarioVersionValidator(WebDriver driver) {
        this.driver = driver;
    }

    // -------------------- MAIN METHOD --------------------
    public void validateFY25OctData() {
        // Define expected Scenario-Version pairs
        List<String[]> expectedPairs = Arrays.asList(
            new String[]{"OEP_Actual", "OEP_Working"},
            new String[]{"OEP_Forecast", "OEP_Working"},
            new String[]{"OEP_Forecast", "OEP_TPWorking"},
            new String[]{"Actual_IFRS", "OEP_Working"}
        );

        // Call helper method to perform validation
        boolean validationResult = validateScenarioVersionForPeriod("FY25", "Oct", expectedPairs);

        // Final assertion
        Assert.assertTrue(validationResult, "❌ Scenario-Version validation failed for FY25 Oct!");
        System.out.println("✅ All Scenario-Version combinations validated successfully for FY25 Oct.");
    }

    // -------------------- HELPER METHOD --------------------
    public boolean validateScenarioVersionForPeriod(String yearFilter, String periodFilter, List<String[]> expectedPairs) {
        List<String[]> foundPairs = new ArrayList<>();

        // Locate all rows in the table
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
        System.out.println("Total rows found: " + rows.size());

        for (WebElement row : rows) {
            try {
                String year = row.findElement(By.xpath(".//td[1]")).getText().trim();
                String period = row.findElement(By.xpath(".//td[2]")).getText().trim();
                String scenario = row.findElement(By.xpath(".//td[3]")).getText().trim();
                String version = row.findElement(By.xpath(".//td[4]")).getText().trim();

                // Only process rows matching given Year and Period
                if (year.equalsIgnoreCase(yearFilter) && period.equalsIgnoreCase(periodFilter)) {
                    foundPairs.add(new String[]{scenario, version});
                    System.out.println("Found -> " + year + " | " + period + " | " + scenario + " | " + version);
                }
            } catch (NoSuchElementException e) {
                // Skip invalid row structure
            }
        }

        boolean allValid = true;

        // Validate all found pairs
        for (String[] pair : foundPairs) {
            boolean matchFound = expectedPairs.stream()
                .anyMatch(expected -> expected[0].equals(pair[0]) && expected[1].equals(pair[1]));

            if (!matchFound) {
                allValid = false;
                System.err.println("❌ Unexpected combination: " + pair[0] + " - " + pair[1]);
            }
        }

        // Check for missing expected pairs
        for (String[] expected : expectedPairs) {
            boolean exists = foundPairs.stream()
                .anyMatch(pair -> pair[0].equals(expected[0]) && pair[1].equals(expected[1]));

            if (!exists) {
                allValid = false;
                System.err.println("❌ Missing expected combination: " + expected[0] + " - " + expected[1]);
            }
        }

        return allValid;
    }
}

