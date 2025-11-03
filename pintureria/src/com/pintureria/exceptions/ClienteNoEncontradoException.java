package com.pintureria.exceptions;

// MOD-015 (main): Excepción para cliente no encontrado
public class ClienteNoEncontradoException extends ClienteException {
    private final String dni;
    
    public ClienteNoEncontradoException(String dni) {
        super(String.format("Cliente con DNI %s no encontrado", dni));
        this.dni = dni;
    }
    
    public String getDni() {
        return dni;
    }
}
