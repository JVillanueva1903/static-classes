import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class NameFormatter {

    // Private constructor
    private NameFormatter() {
    }

    // Format: First Last
    public static String format(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    // Format: Prefix First Middle Last, Suffix
    public static String format(String prefix,
                                String firstName,
                                String middleName,
                                String lastName,
                                String suffix) {

        String result = "";

        if (prefix != null && !prefix.isEmpty()) {
            result += prefix + " ";
        }

        result += firstName + " ";

        if (middleName != null && !middleName.isEmpty()) {
            result += middleName + " ";
        }

        result += lastName;

        if (suffix != null && !suffix.isEmpty()) {
            result += ", " + suffix;
        }

        return result;
    }

    // Converts full name to: LastName, FirstName MiddleName
    public static String format(String fullName) {

        // Remove suffix if present
        String namePart = fullName;
        if (fullName.contains(",")) {
            namePart = fullName.substring(0, fullName.indexOf(",")).trim();
        }

        String[] parts = namePart.split("\\s+");

        String prefix = "";
        int start = 0;

        // Simple prefix detection
        if (parts[0].endsWith(".")) {
            prefix = parts[0];
            start = 1;
        }

        String firstName = parts[start];
        String middleName = "";
        String lastName;

        if (parts.length - start == 3) {
            middleName = parts[start + 1];
            lastName = parts[start + 2];
        } else {
            lastName = parts[start + 1];
        }

        if (!middleName.isEmpty()) {
            return lastName + ", " + firstName + " " + middleName;
        }

        return lastName + ", " + firstName;
    }
}
import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.*;

class NameFormatterTest {

    @Test
    public void testFirstLastFormat() {
        String result = NameFormatter.format("Mel", "Johnson");
        assertEquals("Mel Johnson", result);
    }

    @Test
    public void testFullFormatWithPrefixMiddleAndSuffix() {
        String result = NameFormatter.format(
                "Dr.",
                "Mel",
                "B",
                "Johnson",
                "PhD");

        assertEquals("Dr. Mel B Johnson, PhD", result);
    }

    @Test
    public void testFullNameWithPrefixMiddleAndSuffix() {
        String result = NameFormatter.format(
                "Dr. Mel B Johnson, PhD");

        assertEquals("Johnson, Mel B", result);
    }

    @Test
    public void testFullNameWithMiddleAndSuffix() {
        String result = NameFormatter.format(
                "Mel B Johnson, PhD");

        assertEquals("Johnson, Mel B", result);
    }

    @Test
    public void testFullNameWithoutMiddleName() {
        String result = NameFormatter.format(
                "Mel Johnson");

        assertEquals("Johnson, Mel", result);
    }

    @Test
    public void testAnotherName() {
        String result = NameFormatter.format(
                "Mrs. Sarah Jane Smith, MBA");

        assertEquals("Smith, Sarah Jane", result);
    }
}