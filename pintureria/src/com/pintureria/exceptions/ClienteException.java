package com.pintureria.exceptions;

// MOD-014 (main): Excepción base para errores relacionados con clientes
public class ClienteException extends Exception {
    public ClienteException(String mensaje) {
        super(mensaje);
    }
    
    public ClienteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
