package exceptions;

public class ConnectionException extends RuntimeException {
    /**
     * кастомное исключение для исключений подключения
     *
     * @param message - сообщение об ошибке
     * @param cause   - исключение
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
