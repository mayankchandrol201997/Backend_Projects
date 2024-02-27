package Project.TicTacToe.Exception;

public class InvalidSymbolException extends RuntimeException{
    public InvalidSymbolException() {
    }

    public InvalidSymbolException(String message) {
        super(message);
    }

    public InvalidSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSymbolException(Throwable cause) {
        super(cause);
    }

    public InvalidSymbolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
