package github.couryrr.parsedontvalidate.type;

import java.util.Map;

public class State implements Parseable<State> {
    public static final Map<String, String> STATE_ABBREVIATIONS = Map.ofEntries(
            Map.entry("Alabama", "AL"),
            Map.entry("Alaska", "AK"),
            Map.entry("Arizona", "AZ"),
            Map.entry("Arkansas", "AR"),
            Map.entry("California", "CA"),
            Map.entry("Colorado", "CO"),
            Map.entry("Connecticut", "CT"),
            Map.entry("Delaware", "DE"),
            Map.entry("Florida", "FL"),
            Map.entry("Georgia", "GA"),
            Map.entry("Hawaii", "HI"),
            Map.entry("Idaho", "ID"),
            Map.entry("Illinois", "IL"),
            Map.entry("Indiana", "IN"),
            Map.entry("Iowa", "IA"),
            Map.entry("Kansas", "KS"),
            Map.entry("Kentucky", "KY"),
            Map.entry("Louisiana", "LA"),
            Map.entry("Maine", "ME"),
            Map.entry("Maryland", "MD"),
            Map.entry("Massachusetts", "MA"),
            Map.entry("Michigan", "MI"),
            Map.entry("Minnesota", "MN"),
            Map.entry("Mississippi", "MS"),
            Map.entry("Missouri", "MO"),
            Map.entry("Montana", "MT"),
            Map.entry("Nebraska", "NE"),
            Map.entry("Nevada", "NV"),
            Map.entry("New Hampshire", "NH"),
            Map.entry("New Jersey", "NJ"),
            Map.entry("New Mexico", "NM"),
            Map.entry("New York", "NY"),
            Map.entry("North Carolina", "NC"),
            Map.entry("North Dakota", "ND"),
            Map.entry("Ohio", "OH"),
            Map.entry("Oklahoma", "OK"),
            Map.entry("Oregon", "OR"),
            Map.entry("Pennsylvania", "PA"),
            Map.entry("Rhode Island", "RI"),
            Map.entry("South Carolina", "SC"),
            Map.entry("South Dakota", "SD"),
            Map.entry("Tennessee", "TN"),
            Map.entry("Texas", "TX"),
            Map.entry("Utah", "UT"),
            Map.entry("Vermont", "VT"),
            Map.entry("Virginia", "VA"),
            Map.entry("Washington", "WA"),
            Map.entry("West Virginia", "WV"),
            Map.entry("Wisconsin", "WI"),
            Map.entry("Wyoming", "WY")
    );

    private final String value;

    private State(String state) {
        this.value = state;
    }

    public static State from(String state) {
        if (state == null || !(STATE_ABBREVIATIONS.containsKey(state) || STATE_ABBREVIATIONS.containsValue(state))) {
            throw new IllegalArgumentException("Invalid state: " + state);
        }
        return new State(state);
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
