package tamk.tiko.BloggingWebApp;

public class NotFoundException extends IllegalArgumentException {
    private int id;
    private String message;

    public NotFoundException(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
