package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-006 (main): Clase ClienteMayorista que hereda de Cliente para demostrar herencia
// MOD-025 (main): Agregado manejo de excepciones personalizadas
public class ClienteMayorista extends Cliente {
    private final String razonSocial;
    private final String cuit;
    private final int volumenCompraAnual; // en unidades

    public ClienteMayorista(String dni, String nombre, String telefono, 
                           String razonSocial, String cuit, int volumenCompraAnual) throws ValidacionException {
        super(dni, nombre, telefono);
        validarRazonSocial(razonSocial);
        validarCuit(cuit);
        validarVolumenCompra(volumenCompraAnual);
        
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.volumenCompraAnual = volumenCompraAnual;
    }
    
    private void validarRazonSocial(String razonSocial) throws ValidacionException {
        if (razonSocial == null || razonSocial.isBlank()) {
            throw new ValidacionException("razonSocial", razonSocial, "La razón social no puede ser nula o vacía");
        }
        if (razonSocial.length() < 5) {
            throw new ValidacionException("razonSocial", razonSocial, "La razón social debe tener al menos 5 caracteres");
        }
    }
    
    private void validarCuit(String cuit) throws ValidacionException {
        if (cuit == null || cuit.isBlank()) {
            throw new ValidacionException("cuit", cuit, "El CUIT no puede ser nulo o vacío");
        }
        // Remover guiones si los tiene
        String cuitSinGuiones = cuit.replace("-", "");
        if (!cuitSinGuiones.matches("\\d{11}")) {
            throw new ValidacionException("cuit", cuit, "El CUIT debe tener 11 dígitos (con o sin guiones)");
        }
    }
    
    private void validarVolumenCompra(int volumen) throws ValidacionException {
        if (volumen < 0) {
            throw new ValidacionException("volumenCompraAnual", String.valueOf(volumen), "El volumen de compra no puede ser negativo");
        }
        if (volumen > 100000) {
            throw new ValidacionException("volumenCompraAnual", String.valueOf(volumen), "El volumen de compra no puede ser mayor a 100000");
        }
    }

    public String getRazonSocial() { return razonSocial; }
    public String getCuit() { return cuit; }
    public int getVolumenCompraAnual() { return volumenCompraAnual; }

    // Implementación polimórfica del método abstracto
    @Override
    public BigDecimal calcularDescuentoCliente(BigDecimal monto) {
        BigDecimal descuento = BigDecimal.ZERO;
        
        // Descuento base para mayoristas: 8%
        descuento = monto.multiply(new BigDecimal("0.08"));
        
        // Descuento adicional por volumen anual
        if (volumenCompraAnual >= 1000) {
            descuento = descuento.add(monto.multiply(new BigDecimal("0.05"))); // +5%
        } else if (volumenCompraAnual >= 500) {
            descuento = descuento.add(monto.multiply(new BigDecimal("0.03"))); // +3%
        }
        
        return descuento;
    }

    // Implementación polimórfica del método abstracto
    @Override
    public String getTipoCliente() {
        return "Mayorista";
    }

    // Método específico de ClienteMayorista
    public boolean tieneDescuentoEspecial() {
        return volumenCompraAnual >= 1000;
    }

    // Método específico para obtener descuento total
    public double getPorcentajeDescuentoTotal() {
        double descuento = 8.0; // base
        
        if (volumenCompraAnual >= 1000) {
            descuento += 5.0;
        } else if (volumenCompraAnual >= 500) {
            descuento += 3.0;
        }
        
        return descuento;
    }
}

