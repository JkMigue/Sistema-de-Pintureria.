package com.pintureria.exceptions;

// MOD-016 (main): Excepción base para errores relacionados con ventas
public class VentaException extends Exception {
    public VentaException(String mensaje) {
        super(mensaje);
    }
    
    public VentaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
