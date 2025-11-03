package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-008 (main): Actualizada para usar métodos polimórficos de Producto
// MOD-027 (main): Agregado manejo de excepciones personalizadas
public class ItemVenta {
    private final Producto producto;
    private final int cantidad;

    public ItemVenta(Producto producto, int cantidad) throws ValidacionException {
        validarProducto(producto);
        validarCantidad(cantidad);
        
        this.producto = producto;
        this.cantidad = cantidad;
    }
    
    private void validarProducto(Producto producto) throws ValidacionException {
        if (producto == null) {
            throw new ValidacionException("producto", "null", "El producto no puede ser nulo");
        }
    }
    
    private void validarCantidad(int cantidad) throws ValidacionException {
        if (cantidad <= 0) {
            throw new ValidacionException("cantidad", String.valueOf(cantidad), "La cantidad debe ser mayor a cero");
        }
        if (cantidad > 1000) {
            throw new ValidacionException("cantidad", String.valueOf(cantidad), "La cantidad no puede ser mayor a 1000");
        }
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    // Método que usa polimorfismo para calcular subtotal con descuento de producto
    public BigDecimal getSubtotal() {
        return producto.getPrecioUnitario().multiply(new BigDecimal(cantidad));
    }
    
    // Método que usa polimorfismo para calcular precio final con descuento de producto
    public BigDecimal getPrecioFinal() {
        return producto.getPrecioFinal(cantidad);
    }
    
    // Método para obtener el descuento aplicado por el producto
    public BigDecimal getDescuentoProducto() {
        return producto.calcularDescuento(cantidad);
    }
}
