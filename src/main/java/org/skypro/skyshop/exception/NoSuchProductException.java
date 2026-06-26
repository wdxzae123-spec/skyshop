package org.skypro.skyshop.exception;

/** добавили пакет ошибки
 *
 */
public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        super(message);
    }
}
