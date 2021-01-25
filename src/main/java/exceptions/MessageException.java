package exceptions;

public class MessageException extends RuntimeException {
    /**
     * кастомное исключение для исключений отправки email
     *
     * @param message - сообщение об ошибке
     * @param cause   - исключение
     */
    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
