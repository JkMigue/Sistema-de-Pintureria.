package com.pintureria.exceptions;

// MOD-017 (main): Excepción para venta sin items
public class VentaVaciaException extends VentaException {
    private final String numeroVenta;
    
    public VentaVaciaException(String numeroVenta) {
        super(String.format("La venta %s no tiene items para procesar", numeroVenta));
        this.numeroVenta = numeroVenta;
    }
    
    public String getNumeroVenta() {
        return numeroVenta;
    }
}
