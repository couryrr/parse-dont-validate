package github.couryrr.parsedontvalidate.type;

import java.util.regex.Pattern;

public class City implements Parseable<Zip> {
    private static final Pattern CITY_PATTERN = Pattern.compile(
            "^[A-Za-z]+(?:[.\\-\\s][A-Za-z]+)*$",
            Pattern.CASE_INSENSITIVE
    );

    private final String value;

    private City(String city) {
        this.value = city;
    }

    public static City from(String city) {
        if (city == null || !CITY_PATTERN.matcher(city).matches()) {
            throw new IllegalArgumentException("Invalid city: " + city);
        }
        return new City(city);
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
