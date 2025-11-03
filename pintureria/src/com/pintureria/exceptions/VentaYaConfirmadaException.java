package com.pintureria.exceptions;

// MOD-018 (main): Excepción para venta ya confirmada
public class VentaYaConfirmadaException extends VentaException {
    private final String numeroVenta;
    
    public VentaYaConfirmadaException(String numeroVenta) {
        super(String.format("La venta %s ya ha sido confirmada y no puede ser modificada", numeroVenta));
        this.numeroVenta = numeroVenta;
    }
    
    public String getNumeroVenta() {
        return numeroVenta;
    }
}
