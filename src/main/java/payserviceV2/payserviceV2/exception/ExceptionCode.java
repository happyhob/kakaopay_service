package payserviceV2.payserviceV2.exception;

public enum ExceptionCode {
    PAY_CANCEL("Payment has been cancelled"),
    PAY_FAILED("Payment failed");

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}