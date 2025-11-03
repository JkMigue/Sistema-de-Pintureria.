package com.pintureria.model;

import com.pintureria.exceptions.StockInsuficienteException;
import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-001 (main): Convertida a clase abstracta para implementar herencia y polimorfismo
// MOD-020 (main): Agregado manejo de excepciones personalizadas
public abstract class Producto {
    private final String codigo;
    private String descripcion;
    private BigDecimal precioUnitario;
    private int stock;

    public Producto(String codigo, String descripcion, BigDecimal precioUnitario, int stock) throws ValidacionException {
        validarCodigo(codigo);
        validarPrecio(precioUnitario);
        validarStock(stock);
        validarDescripcion(descripcion);
        
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }
    
    private void validarCodigo(String codigo) throws ValidacionException {
        if (codigo == null || codigo.isBlank()) {
            throw new ValidacionException("codigo", codigo, "El código no puede ser nulo o vacío");
        }
        if (codigo.length() < 3) {
            throw new ValidacionException("codigo", codigo, "El código debe tener al menos 3 caracteres");
        }
    }
    
    private void validarPrecio(BigDecimal precio) throws ValidacionException {
        if (precio == null) {
            throw new ValidacionException("precio", "null", "El precio no puede ser nulo");
        }
        if (precio.signum() < 0) {
            throw new ValidacionException("precio", precio.toString(), "El precio no puede ser negativo");
        }
        if (precio.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidacionException("precio", precio.toString(), "El precio debe ser mayor a cero");
        }
    }
    
    private void validarStock(int stock) throws ValidacionException {
        if (stock < 0) {
            throw new ValidacionException("stock", String.valueOf(stock), "El stock no puede ser negativo");
        }
    }
    
    private void validarDescripcion(String descripcion) throws ValidacionException {
        if (descripcion == null || descripcion.isBlank()) {
            throw new ValidacionException("descripcion", descripcion, "La descripción no puede ser nula o vacía");
        }
        if (descripcion.length() < 5) {
            throw new ValidacionException("descripcion", descripcion, "La descripción debe tener al menos 5 caracteres");
        }
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) throws ValidacionException { 
        validarDescripcion(descripcion);
        this.descripcion = descripcion; 
    }
    
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    
    public void setPrecioUnitario(BigDecimal precioUnitario) throws ValidacionException {
        validarPrecio(precioUnitario);
        this.precioUnitario = precioUnitario;
    }
    
    public int getStock() { return stock; }
    
    public void setStock(int stock) throws ValidacionException {
        validarStock(stock);
        this.stock = stock;
    }

    public void disminuirStock(int cantidad) throws StockInsuficienteException, ValidacionException {
        if (cantidad <= 0) {
            throw new ValidacionException("cantidad", String.valueOf(cantidad), "La cantidad debe ser mayor a cero");
        }
        if (cantidad > stock) {
            throw new StockInsuficienteException(codigo, stock, cantidad);
        }
        stock -= cantidad;
    }

    // Método abstracto para calcular descuento específico de cada tipo de producto
    public abstract BigDecimal calcularDescuento(int cantidad);
    
    // Método abstracto para obtener información específica del producto
    public abstract String getInformacionEspecifica();
    
    // Método polimórfico para calcular precio final con descuento
    public BigDecimal getPrecioFinal(int cantidad) {
        BigDecimal subtotal = precioUnitario.multiply(new BigDecimal(cantidad));
        BigDecimal descuento = calcularDescuento(cantidad);
        return subtotal.subtract(descuento);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", stock=" + stock +
                ", info=" + getInformacionEspecifica() +
                '}';
    }
}
