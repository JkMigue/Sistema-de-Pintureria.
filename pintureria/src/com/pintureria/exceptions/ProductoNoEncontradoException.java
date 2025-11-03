package com.pintureria.exceptions;

// MOD-013 (main): Excepción para producto no encontrado
public class ProductoNoEncontradoException extends ProductoException {
    private final String codigoProducto;
    
    public ProductoNoEncontradoException(String codigoProducto) {
        super(String.format("Producto con código %s no encontrado", codigoProducto));
        this.codigoProducto = codigoProducto;
    }
    
    public String getCodigoProducto() {
        return codigoProducto;
    }
}
