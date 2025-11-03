package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-007 (main): Clase ClienteMinorista que hereda de Cliente para demostrar herencia
// MOD-026 (main): Agregado manejo de excepciones personalizadas
public class ClienteMinorista extends Cliente {
    private final boolean esFrecuente;
    private final int comprasUltimoAno;

    public ClienteMinorista(String dni, String nombre, String telefono, 
                           boolean esFrecuente, int comprasUltimoAno) throws ValidacionException {
        super(dni, nombre, telefono);
        validarComprasUltimoAno(comprasUltimoAno);
        
        this.esFrecuente = esFrecuente;
        this.comprasUltimoAno = comprasUltimoAno;
    }
    
    private void validarComprasUltimoAno(int compras) throws ValidacionException {
        if (compras < 0) {
            throw new ValidacionException("comprasUltimoAno", String.valueOf(compras), "Las compras del último año no pueden ser negativas");
        }
        if (compras > 365) {
            throw new ValidacionException("comprasUltimoAno", String.valueOf(compras), "Las compras del último año no pueden ser mayores a 365");
        }
    }

    public boolean esFrecuente() { return esFrecuente; }
    public int getComprasUltimoAno() { return comprasUltimoAno; }

    // Implementación polimórfica del método abstracto
    @Override
    public BigDecimal calcularDescuentoCliente(BigDecimal monto) {
        BigDecimal descuento = BigDecimal.ZERO;
        
        // Descuento base para minoristas: 2%
        descuento = monto.multiply(new BigDecimal("0.02"));
        
        // Descuento adicional para clientes frecuentes
        if (esFrecuente) {
            descuento = descuento.add(monto.multiply(new BigDecimal("0.03"))); // +3%
        }
        
        // Descuento por cantidad de compras en el último año
        if (comprasUltimoAno >= 10) {
            descuento = descuento.add(monto.multiply(new BigDecimal("0.02"))); // +2%
        } else if (comprasUltimoAno >= 5) {
            descuento = descuento.add(monto.multiply(new BigDecimal("0.01"))); // +1%
        }
        
        return descuento;
    }

    // Implementación polimórfica del método abstracto
    @Override
    public String getTipoCliente() {
        return "Minorista";
    }

    // Método específico de ClienteMinorista
    public boolean tieneDescuentoFrecuente() {
        return esFrecuente && comprasUltimoAno >= 5;
    }

    // Método específico para obtener descuento total
    public double getPorcentajeDescuentoTotal() {
        double descuento = 2.0; // base
        
        if (esFrecuente) {
            descuento += 3.0;
        }
        
        if (comprasUltimoAno >= 10) {
            descuento += 2.0;
        } else if (comprasUltimoAno >= 5) {
            descuento += 1.0;
        }
        
        return descuento;
    }
}

