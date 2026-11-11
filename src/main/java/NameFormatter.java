public class NameFormatter {

    // Private constructor
    private NameFormatter() {
    }

    // Format: First Last
    public static String format(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    // Format: Prefix First Middle,Last, Suffix
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

    // Format a full name into standard format: Last, First Middle
    public static String format(String fullName) {
        String[] parts = fullName.trim().split("\\s+");

        if (parts.length == 2) {
            return parts[1] + ", " + parts[0];
        } else if (parts.length == 3) {
            return parts[2] + ", " + parts[0] + " " + parts[1];
        }

        return fullName;
    }}
