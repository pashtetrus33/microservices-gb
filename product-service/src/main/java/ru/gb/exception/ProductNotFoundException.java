package ru.gb.exception;

public class ProductNotFoundException extends RuntimeException{
    /**
     * Конструктор родительского класса.
     *
     * @param message сообщение об ошибке
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

}
