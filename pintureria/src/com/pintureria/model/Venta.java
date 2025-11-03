package com.pintureria.model;

import com.pintureria.exceptions.StockInsuficienteException;
import com.pintureria.exceptions.ValidacionException;
import com.pintureria.exceptions.VentaVaciaException;
import com.pintureria.exceptions.VentaYaConfirmadaException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// MOD-009 (main): Actualizada para usar métodos polimórficos de Cliente y Producto
// MOD-028 (main): Agregado manejo de excepciones personalizadas
public class Venta {
    private final String numero;
    private final Cliente cliente;
    private final LocalDateTime fechaHora;
    private final List<ItemVenta> items;
    private boolean confirmada;

    public Venta(String numero, Cliente cliente) throws ValidacionException {
        validarNumero(numero);
        validarCliente(cliente);
        
        this.numero = numero;
        this.cliente = cliente;
        this.fechaHora = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.confirmada = false;
    }
    
    private void validarNumero(String numero) throws ValidacionException {
        if (numero == null || numero.isBlank()) {
            throw new ValidacionException("numero", numero, "El número de venta no puede ser nulo o vacío");
        }
        if (!numero.matches("V-\\d{4}")) {
            throw new ValidacionException("numero", numero, "El número debe tener formato V-XXXX");
        }
    }
    
    private void validarCliente(Cliente cliente) throws ValidacionException {
        if (cliente == null) {
            throw new ValidacionException("cliente", "null", "El cliente no puede ser nulo");
        }
    }

    public void agregarItem(ItemVenta item) throws VentaYaConfirmadaException, ValidacionException {
        if (confirmada) {
            throw new VentaYaConfirmadaException(numero);
        }
        if (item == null) {
            throw new ValidacionException("item", "null", "El item no puede ser nulo");
        }
        items.add(item);
    }

    // Método que calcula el total sin descuentos
    public BigDecimal getSubtotal() throws VentaVaciaException {
        if (items.isEmpty()) {
            throw new VentaVaciaException(numero);
        }
        return items.stream()
                .map(ItemVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Método que calcula el total con descuentos de productos
    public BigDecimal getTotalConDescuentosProductos() throws VentaVaciaException {
        if (items.isEmpty()) {
            throw new VentaVaciaException(numero);
        }
        return items.stream()
                .map(ItemVenta::getPrecioFinal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Método que calcula el total final con descuentos de productos y cliente
    public BigDecimal getTotal() throws VentaVaciaException {
        BigDecimal totalProductos = getTotalConDescuentosProductos();
        return cliente.getMontoFinal(totalProductos);
    }
    
    // Método para obtener el descuento total de productos
    public BigDecimal getDescuentoTotalProductos() throws VentaVaciaException {
        if (items.isEmpty()) {
            throw new VentaVaciaException(numero);
        }
        return items.stream()
                .map(ItemVenta::getDescuentoProducto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // Método para obtener el descuento del cliente
    public BigDecimal getDescuentoCliente() throws VentaVaciaException {
        BigDecimal totalProductos = getTotalConDescuentosProductos();
        return cliente.calcularDescuentoCliente(totalProductos);
    }

    public void confirmar() throws VentaVaciaException, StockInsuficienteException, VentaYaConfirmadaException, ValidacionException {
        if (confirmada) {
            throw new VentaYaConfirmadaException(numero);
        }
        if (items.isEmpty()) {
            throw new VentaVaciaException(numero);
        }
        
        for (ItemVenta item : items) {
            item.getProducto().disminuirStock(item.getCantidad());
        }
        confirmada = true;
    }
    
    public boolean isConfirmada() {
        return confirmada;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public String getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        try {
            return "Venta{" +
                    "numero='" + numero + '\'' +
                    ", cliente=" + cliente +
                    ", fechaHora=" + fechaHora +
                    ", confirmada=" + confirmada +
                    ", subtotal=" + getSubtotal() +
                    ", descuentoProductos=" + getDescuentoTotalProductos() +
                    ", descuentoCliente=" + getDescuentoCliente() +
                    ", total=" + getTotal() +
                    '}';
        } catch (VentaVaciaException e) {
            return "Venta{" +
                    "numero='" + numero + '\'' +
                    ", cliente=" + cliente +
                    ", fechaHora=" + fechaHora +
                    ", confirmada=" + confirmada +
                    ", items=" + items.size() +
                    '}';
        }
    }
}
