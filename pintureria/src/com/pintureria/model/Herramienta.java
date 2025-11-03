package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-003 (main): Clase Herramienta que hereda de Producto para demostrar herencia
// MOD-022 (main): Agregado manejo de excepciones personalizadas
public class Herramienta extends Producto {
    private final String categoria; // brocha, rodillo, espátula
    private final String material;
    private final boolean esReutilizable;

    public Herramienta(String codigo, String descripcion, BigDecimal precioUnitario, int stock,
                       String categoria, String material, boolean esReutilizable) throws ValidacionException {
        super(codigo, descripcion, precioUnitario, stock);
        validarCategoria(categoria);
        validarMaterial(material);
        
        this.categoria = categoria;
        this.material = material;
        this.esReutilizable = esReutilizable;
    }
    
    private void validarCategoria(String categoria) throws ValidacionException {
        if (categoria == null || categoria.isBlank()) {
            throw new ValidacionException("categoria", categoria, "La categoría no puede ser nula o vacía");
        }
        if (!categoria.equalsIgnoreCase("brocha") && !categoria.equalsIgnoreCase("rodillo") && 
            !categoria.equalsIgnoreCase("espátula")) {
            throw new ValidacionException("categoria", categoria, "La categoría debe ser: brocha, rodillo o espátula");
        }
    }
    
    private void validarMaterial(String material) throws ValidacionException {
        if (material == null || material.isBlank()) {
            throw new ValidacionException("material", material, "El material no puede ser nulo o vacío");
        }
    }

    public String getCategoria() { return categoria; }
    public String getMaterial() { return material; }
    public boolean esReutilizable() { return esReutilizable; }

    // Implementación polimórfica del método abstracto
    @Override
    public BigDecimal calcularDescuento(int cantidad) {
        BigDecimal subtotal = getPrecioUnitario().multiply(new BigDecimal(cantidad));
        
        // Descuento por compra múltiple de herramientas: 10% si compra más de 5 unidades
        if (cantidad >= 5) {
            return subtotal.multiply(new BigDecimal("0.10"));
        }
        
        // Descuento especial para herramientas reutilizables: 5% si compra más de 3
        if (esReutilizable && cantidad >= 3) {
            return subtotal.multiply(new BigDecimal("0.05"));
        }
        
        return BigDecimal.ZERO;
    }

    // Implementación polimórfica del método abstracto
    @Override
    public String getInformacionEspecifica() {
        return String.format("Categoría: %s, Material: %s, %s", 
                           categoria, material, esReutilizable ? "Reutilizable" : "Desechable");
    }

    // Método específico de Herramienta
    public boolean requiereMantenimiento() {
        return esReutilizable && ("brocha".equalsIgnoreCase(categoria) || 
                                 "rodillo".equalsIgnoreCase(categoria));
    }
}

