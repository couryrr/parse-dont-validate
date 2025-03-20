package github.couryrr.parsedontvalidate.type;

import github.couryrr.parsedontvalidate.dto.AddressDto;

import java.util.*;
import java.util.function.Function;

public class Address {
    private final Street street;
    private final Unit unit;
    private final City city;
    private final State state;
    private final Zip zip;

    private Address(Street street, City city, State state, Zip zip, Unit unit) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.unit = unit;
    }

    public static Address from(AddressDto addressDto) {
        List<String> errors = new ArrayList<>();

        var street = validateField(addressDto.street(), Street::from, errors);
        var city = validateField(addressDto.city(), City::from, errors);
        var state = validateField(addressDto.state(), State::from, errors);
        var zip = validateField(addressDto.zip(), Zip::from, errors);
        var unit = validateField(addressDto.unit(), Unit::from, errors);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.valueOf(errors));
        }

        return new Address(street.orElseThrow(), city.orElseThrow(), state.orElseThrow(), zip.orElseThrow(), unit.orElseThrow());
    }

    private static <T> Optional<T> validateField(String value, Function<String, T> parser, List<String> errors) {
        try {
            return Optional.of(parser.apply(value));
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
            return Optional.empty();
        }
    }

    public Street getStreet() {
        return street;
    }

    public Unit getUnit() {
        return unit;
    }

    public City getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public Zip getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "{", "}")
                .add("street='" + street.getValue() + "'")
                .add("city='" + city.getValue() + "'")
                .add("state='" + state.getValue() + "'")
                .add("zip='" + zip.getValue() + "'")
                .add("unit='" + (unit != null ? unit.getValue() : "N/A") + "'")
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zip, unit);
    }
}
