package github.couryrr.parsedontvalidate.type;

import java.util.regex.Pattern;

public class Street implements Parseable<Street> {
    private static final Pattern STREET_PATTERN = Pattern.compile(
            "^\\d+\\s+[A-Za-z0-9\\s]+\\s+(?:St|Street|Ave|Avenue|Blvd|Boulevard|Rd|Road|Dr|Drive|Ln|Lane|Ct|Court|Pl|Place|Way|Hwy|Highway|Pkwy|Parkway|Circle|Cir)$",
            Pattern.CASE_INSENSITIVE
    );

    private final String value;

    private Street(String street) {
        this.value = street;
    }

    public static Street from(String street) {
        if (street == null || !STREET_PATTERN.matcher(street).matches()) {
            throw new IllegalArgumentException("Invalid street address: " + street);
        }
        return new Street(street);
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
