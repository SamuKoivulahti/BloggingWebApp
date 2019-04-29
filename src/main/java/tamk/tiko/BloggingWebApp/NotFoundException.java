package tamk.tiko.BloggingWebApp;

/**
 * Custom exception for not found.
 *
 * @author Joni Alanko <joni.alanko@tuni.fi>
 *         Samu Koivulahti <samu.koivulahti@tuni.fi>
 * @version 20190429
 * @since   1.8
 */
public class NotFoundException extends IllegalArgumentException {
    /**
     * Stores target identifier.
     */
    private int id;

    /**
     * Stores target identifier.
     */
    private String name;

    /**
     * Stores exception message.
     */
    private String message;

    public NotFoundException(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public NotFoundException(String name, String message) {
        this.name = name;
        this.message = message;
    }

    /**
     * Gets target identifier.
     *
     * @return Target identifier as integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets target identifier.
     *
     * @return Target identifier as String.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets exception message.
     *
     * @return Exception message as String.
     */
    public String getMessage() {
        return message;
    }
}
