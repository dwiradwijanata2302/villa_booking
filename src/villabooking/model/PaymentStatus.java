package villabooking.model;

public enum PaymentStatus {
    WAITING,
    SUCCESS,
    FAILED;

    public static PaymentStatus fromString(String value) {
        return switch (value.toLowerCase()) {
            case "waiting" ->
                    WAITING;
            case "success" ->
                    SUCCESS;
            case "failed" ->
                    FAILED;
            default ->
                    throw new IllegalArgumentException("Invalid payment status: " + value);
        };
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
