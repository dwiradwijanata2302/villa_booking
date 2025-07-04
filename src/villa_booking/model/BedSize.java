package villabooking.model;

public enum BedSize {
    DOUBLE,
    QUEEN,
    KING;

    public static BedSize fromString(String value) {
        return switch (value.toLowerCase()) {
            case "double" ->
                DOUBLE;
            case "queen" ->
                QUEEN;
            case "king" ->
                KING;
            default ->
                throw new IllegalArgumentException("Invalid bed size: " + value);
        };
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
