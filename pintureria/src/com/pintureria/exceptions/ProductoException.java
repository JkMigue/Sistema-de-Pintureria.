package com.pintureria.exceptions;

// MOD-011 (main): Excepción base para errores relacionados con productos
public class ProductoException extends Exception {
    public ProductoException(String mensaje) {
        super(mensaje);
    }
    
    public ProductoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
