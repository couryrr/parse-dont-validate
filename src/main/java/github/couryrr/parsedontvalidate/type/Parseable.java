package github.couryrr.parsedontvalidate.type;

public interface Parseable<T> {
    static <T> T from(String value) {
        throw new UnsupportedOperationException("Must be implemented by subclasses");
    }

    String getValue();
}