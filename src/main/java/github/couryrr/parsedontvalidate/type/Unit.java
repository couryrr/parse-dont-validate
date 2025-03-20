package github.couryrr.parsedontvalidate.type;

import java.util.regex.Pattern;

public class Unit implements Parseable<Unit> {
    private static final Pattern UNIT_PATTERN = Pattern.compile(
            "^(?:[A-Za-z#]+\\s*)?\\d+[A-Za-z-]*$"
    );

    private final String value;

    private Unit(String unit) {
        this.value = unit;
    }

    public static Unit from(String unit) {
        if (unit != null && !unit.isBlank()) {
            if (!UNIT_PATTERN.matcher(unit).matches()) {
                throw new IllegalArgumentException("Invalid unit: " + unit);
            }
            return new Unit(unit);
        }
        return new Unit("");
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
