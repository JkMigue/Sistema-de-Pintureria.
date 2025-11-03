package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-004 (main): Clase Accesorio que hereda de Producto para demostrar herencia
// MOD-023 (main): Agregado manejo de excepciones personalizadas
public class Accesorio extends Producto {
    private final String tipo; // cinta, papel, plástico
    private final String unidad; // metros, rollos, unidades
    private final double medida;

    public Accesorio(String codigo, String descripcion, BigDecimal precioUnitario, int stock,
                    String tipo, String unidad, double medida) throws ValidacionException {
        super(codigo, descripcion, precioUnitario, stock);
        validarTipo(tipo);
        validarUnidad(unidad);
        validarMedida(medida);
        
        this.tipo = tipo;
        this.unidad = unidad;
        this.medida = medida;
    }
    
    private void validarTipo(String tipo) throws ValidacionException {
        if (tipo == null || tipo.isBlank()) {
            throw new ValidacionException("tipo", tipo, "El tipo de accesorio no puede ser nulo o vacío");
        }
        if (!tipo.equalsIgnoreCase("cinta") && !tipo.equalsIgnoreCase("papel") && 
            !tipo.equalsIgnoreCase("plástico")) {
            throw new ValidacionException("tipo", tipo, "El tipo debe ser: cinta, papel o plástico");
        }
    }
    
    private void validarUnidad(String unidad) throws ValidacionException {
        if (unidad == null || unidad.isBlank()) {
            throw new ValidacionException("unidad", unidad, "La unidad no puede ser nula o vacía");
        }
        if (!unidad.equalsIgnoreCase("metros") && !unidad.equalsIgnoreCase("rollos") && 
            !unidad.equalsIgnoreCase("unidades")) {
            throw new ValidacionException("unidad", unidad, "La unidad debe ser: metros, rollos o unidades");
        }
    }
    
    private void validarMedida(double medida) throws ValidacionException {
        if (medida <= 0) {
            throw new ValidacionException("medida", String.valueOf(medida), "La medida debe ser mayor a cero");
        }
        if (medida > 1000) {
            throw new ValidacionException("medida", String.valueOf(medida), "La medida no puede ser mayor a 1000");
        }
    }

    public String getTipo() { return tipo; }
    public String getUnidad() { return unidad; }
    public double getMedida() { return medida; }

    // Implementación polimórfica del método abstracto
    @Override
    public BigDecimal calcularDescuento(int cantidad) {
        BigDecimal subtotal = getPrecioUnitario().multiply(new BigDecimal(cantidad));
        
        // Descuento fijo para accesorios: 2% siempre
        BigDecimal descuentoBase = subtotal.multiply(new BigDecimal("0.02"));
        
        // Descuento adicional por volumen: 3% más si compra más de 10 unidades
        if (cantidad >= 10) {
            descuentoBase = descuentoBase.add(subtotal.multiply(new BigDecimal("0.03")));
        }
        
        return descuentoBase;
    }

    // Implementación polimórfica del método abstracto
    @Override
    public String getInformacionEspecifica() {
        return String.format("Tipo: %s, %.1f %s", tipo, medida, unidad);
    }

    // Método específico de Accesorio
    public boolean esConsumible() {
        return "cinta".equalsIgnoreCase(tipo) || "papel".equalsIgnoreCase(tipo);
    }
}

