package Project.TicTacToe.Exception;

public class InvalidPlayerException extends RuntimeException {
    public InvalidPlayerException() {
    }

    public InvalidPlayerException(String message) {
        super(message);
    }

    public InvalidPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlayerException(Throwable cause) {
        super(cause);
    }

    public InvalidPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
