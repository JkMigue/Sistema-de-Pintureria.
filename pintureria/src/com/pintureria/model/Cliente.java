package com.pintureria.model;

import com.pintureria.exceptions.ValidacionException;

import java.math.BigDecimal;

// MOD-005 (main): Convertida a clase abstracta para implementar herencia y polimorfismo
// MOD-024 (main): Agregado manejo de excepciones personalizadas
public abstract class Cliente {
    private final String dni;
    private String nombre;
    private String telefono;

    public Cliente(String dni, String nombre, String telefono) throws ValidacionException {
        validarDni(dni);
        validarNombre(nombre);
        validarTelefono(telefono);
        
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    private void validarDni(String dni) throws ValidacionException {
        if (dni == null || dni.isBlank()) {
            throw new ValidacionException("dni", dni, "El DNI no puede ser nulo o vacío");
        }
        if (dni.length() < 7 || dni.length() > 8) {
            throw new ValidacionException("dni", dni, "El DNI debe tener entre 7 y 8 caracteres");
        }
        if (!dni.matches("\\d+")) {
            throw new ValidacionException("dni", dni, "El DNI debe contener solo números");
        }
    }
    
    private void validarNombre(String nombre) throws ValidacionException {
        if (nombre == null || nombre.isBlank()) {
            throw new ValidacionException("nombre", nombre, "El nombre no puede ser nulo o vacío");
        }
        if (nombre.length() < 2) {
            throw new ValidacionException("nombre", nombre, "El nombre debe tener al menos 2 caracteres");
        }
        if (nombre.length() > 50) {
            throw new ValidacionException("nombre", nombre, "El nombre no puede tener más de 50 caracteres");
        }
    }
    
    private void validarTelefono(String telefono) throws ValidacionException {
        if (telefono == null || telefono.isBlank()) {
            throw new ValidacionException("telefono", telefono, "El teléfono no puede ser nulo o vacío");
        }
        if (!telefono.matches("\\+?\\d{2,3}\\s?\\d{4}\\s?-?\\d{4}")) {
            throw new ValidacionException("telefono", telefono, "El teléfono debe tener formato válido (ej: +54 11 4444-0000)");
        }
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) throws ValidacionException { 
        validarNombre(nombre);
        this.nombre = nombre; 
    }
    
    public String getTelefono() { return telefono; }
    
    public void setTelefono(String telefono) throws ValidacionException { 
        validarTelefono(telefono);
        this.telefono = telefono; 
    }

    // Método abstracto para calcular descuento según tipo de cliente
    public abstract BigDecimal calcularDescuentoCliente(BigDecimal monto);
    
    // Método abstracto para obtener información específica del cliente
    public abstract String getTipoCliente();
    
    // Método polimórfico para calcular monto final con descuento de cliente
    public BigDecimal getMontoFinal(BigDecimal monto) {
        BigDecimal descuento = calcularDescuentoCliente(monto);
        return monto.subtract(descuento);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo=" + getTipoCliente() +
                '}';
    }
}
