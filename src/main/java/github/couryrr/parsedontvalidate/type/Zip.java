package github.couryrr.parsedontvalidate.type;

import java.util.regex.Pattern;

public class Zip implements Parseable<Zip> {
    private static final Pattern ZIP_PATTERN = Pattern.compile(
            "^\\d{5}(-\\d{4})?$"
    );

    private final String value;

    private Zip(String zip) {
        this.value = zip;
    }

    public static Zip from(String zip) {
        if (zip == null || !ZIP_PATTERN.matcher(zip).matches()) {
            throw new IllegalArgumentException("Invalid zip: " + zip);
        }
        return new Zip(zip);
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
