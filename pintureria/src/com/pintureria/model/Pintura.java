package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-002 (main): Clase Pintura que hereda de Producto para demostrar herencia
// MOD-021 (main): Agregado manejo de excepciones personalizadas
public class Pintura extends Producto {
    private final String tipo; // latex, esmalte, acrílico
    private final String color;
    private final double litros;

    public Pintura(String codigo, String descripcion, BigDecimal precioUnitario, int stock, 
                   String tipo, String color, double litros) throws ValidacionException {
        super(codigo, descripcion, precioUnitario, stock);
        validarTipo(tipo);
        validarColor(color);
        validarLitros(litros);
        
        this.tipo = tipo;
        this.color = color;
        this.litros = litros;
    }
    
    private void validarTipo(String tipo) throws ValidacionException {
        if (tipo == null || tipo.isBlank()) {
            throw new ValidacionException("tipo", tipo, "El tipo de pintura no puede ser nulo o vacío");
        }
        if (!tipo.equalsIgnoreCase("latex") && !tipo.equalsIgnoreCase("esmalte") && 
            !tipo.equalsIgnoreCase("acrílico")) {
            throw new ValidacionException("tipo", tipo, "El tipo debe ser: latex, esmalte o acrílico");
        }
    }
    
    private void validarColor(String color) throws ValidacionException {
        if (color == null || color.isBlank()) {
            throw new ValidacionException("color", color, "El color no puede ser nulo o vacío");
        }
    }
    
    private void validarLitros(double litros) throws ValidacionException {
        if (litros <= 0) {
            throw new ValidacionException("litros", String.valueOf(litros), "Los litros deben ser mayores a cero");
        }
        if (litros > 20) {
            throw new ValidacionException("litros", String.valueOf(litros), "Los litros no pueden ser mayores a 20");
        }
    }

    public String getTipo() { return tipo; }
    public String getColor() { return color; }
    public double getLitros() { return litros; }

    // Implementación polimórfica del método abstracto
    @Override
    public BigDecimal calcularDescuento(int cantidad) {
        BigDecimal subtotal = getPrecioUnitario().multiply(new BigDecimal(cantidad));
        
        // Descuento por volumen: 5% si compra más de 3 unidades
        if (cantidad >= 3) {
            return subtotal.multiply(new BigDecimal("0.05"));
        }
        
        // Descuento especial para latex: 3% si compra más de 2 unidades
        if ("latex".equalsIgnoreCase(tipo) && cantidad >= 2) {
            return subtotal.multiply(new BigDecimal("0.03"));
        }
        
        return BigDecimal.ZERO;
    }

    // Implementación polimórfica del método abstracto
    @Override
    public String getInformacionEspecifica() {
        return String.format("Tipo: %s, Color: %s, %.1fL", tipo, color, litros);
    }

    // Método específico de Pintura
    public boolean esPinturaExterior() {
        return "esmalte".equalsIgnoreCase(tipo) || "acrílico".equalsIgnoreCase(tipo);
    }
}

