package com.pintureria.exceptions;

// MOD-019 (main): Excepción para errores de validación
public class ValidacionException extends Exception {
    private final String campo;
    private final String valor;
    
    public ValidacionException(String mensaje) {
        super(mensaje);
        this.campo = null;
        this.valor = null;
    }
    
    public ValidacionException(String campo, String valor, String mensaje) {
        super(String.format("Error de validación en campo '%s' con valor '%s': %s", campo, valor, mensaje));
        this.campo = campo;
        this.valor = valor;
    }
    
    public String getCampo() {
        return campo;
    }
    
    public String getValor() {
        return valor;
    }
}
