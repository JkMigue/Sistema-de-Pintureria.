package com.pintureria.app;

import com.pintureria.exceptions.*;
import com.pintureria.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

// MOD-010 (main): Actualizado para demostrar herencia y polimorfismo
// MOD-029 (main): Agregado manejo de excepciones personalizadas
// MOD-030 (main): Agregado Scanner para entrada interactiva
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PINTURERÍA - DEMOSTRACIÓN INTERACTIVA ===\n");
        
        // Mostrar menú principal
        mostrarMenu();
        
        scanner.close();
    }
    
    private static void mostrarMenu() {
        System.out.println("=== MENÚ PRINCIPAL ===");
        System.out.println("1. Demostración automática (herencia, polimorfismo y excepciones)");
        System.out.println("2. Demostración interactiva (crear productos y clientes)");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción (1-3): ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        
        switch (opcion) {
            case 1:
                System.out.println("\n=== EJECUTANDO DEMOSTRACIÓN AUTOMÁTICA ===\n");
                ejecutarDemostracionAutomatica();
                break;
            case 2:
                System.out.println("\n=== EJECUTANDO DEMOSTRACIÓN INTERACTIVA ===\n");
                ejecutarDemostracionInteractiva();
                break;
            case 3:
                System.out.println("¡Hasta luego!");
                System.exit(0);
            default:
                System.out.println("Opción inválida. Ejecutando demostración automática...\n");
                ejecutarDemostracionAutomatica();
        }
    }
    
    private static void ejecutarDemostracionInteractiva() {
        System.out.println("=== DEMOSTRACIÓN INTERACTIVA ===\n");
        
        try {
            // Crear cliente interactivo
            Cliente cliente = crearClienteInteractivo();
            
            // Productos predefinidos
            Producto latex = new Pintura("LATEX-BLANCO-4L", "Pintura Latex Blanco 4L", 
                                       new BigDecimal("12999.90"), 10, "latex", "blanco", 4.0);
            Producto brocha = new Herramienta("BROCHA-4P", "Brocha de 4 pulgadas", 
                                            new BigDecimal("2500.00"), 20, "brocha", "cerda natural", true);
            Producto cinta = new Accesorio("CINTA-48MM", "Cinta de enmascarar 48mm", 
                                         new BigDecimal("800.00"), 50, "cinta", "metros", 50.0);
            
            System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
            System.out.println("1. " + latex.getDescripcion() + " - $" + String.format("%.2f", latex.getPrecioUnitario()));
            System.out.println("2. " + brocha.getDescripcion() + " - $" + String.format("%.2f", brocha.getPrecioUnitario()));
            System.out.println("3. " + cinta.getDescripcion() + " - $" + String.format("%.2f", cinta.getPrecioUnitario()));
            
            // Crear venta interactiva
            Venta venta = crearVentaInteractiva(cliente, latex, brocha, cinta);
            
            // Mostrar resumen
            mostrarResumenVenta(venta);
            
        } catch (Exception e) {
            System.out.println("Error en la demostración interactiva: " + e.getMessage());
        }
    }
    
    private static Cliente crearClienteInteractivo() throws ValidacionException {
        System.out.println("=== CREAR CLIENTE ===");
        
        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine();
        
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();
        
        System.out.print("¿Es cliente mayorista? (s/n): ");
        String esMayorista = scanner.nextLine().toLowerCase();
        
        if (esMayorista.equals("s")) {
            System.out.print("Ingrese razón social: ");
            String razonSocial = scanner.nextLine();
            
            System.out.print("Ingrese CUIT (11 dígitos, con o sin guiones): ");
            String cuit = scanner.nextLine();
            
            System.out.print("Ingrese volumen de compra anual: ");
            int volumen = scanner.nextInt();
            scanner.nextLine();
            
            return new ClienteMayorista(dni, nombre, telefono, razonSocial, cuit, volumen);
        } else {
            System.out.print("¿Es cliente frecuente? (s/n): ");
            boolean esFrecuente = scanner.nextLine().toLowerCase().equals("s");
            
            System.out.print("Ingrese compras del último año: ");
            int compras = scanner.nextInt();
            scanner.nextLine();
            
            return new ClienteMinorista(dni, nombre, telefono, esFrecuente, compras);
        }
    }
    
    
    private static Venta crearVentaInteractiva(Cliente cliente, Producto latex, Producto brocha, Producto cinta) throws ValidacionException, VentaYaConfirmadaException {
        System.out.println("\n=== CREAR VENTA ===");
        
        // Generar número de venta automáticamente
        String numero = "V-" + String.format("%04d", (int)(Math.random() * 9999) + 1);
        System.out.println("Número de venta generado: " + numero);
        
        Venta venta = new Venta(numero, cliente);
        
        System.out.print("¿Agregar " + latex.getDescripcion() + "? (s/n): ");
        if (scanner.nextLine().toLowerCase().equals("s")) {
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            venta.agregarItem(new ItemVenta(latex, cantidad));
        }
        
        System.out.print("¿Agregar " + brocha.getDescripcion() + "? (s/n): ");
        if (scanner.nextLine().toLowerCase().equals("s")) {
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            venta.agregarItem(new ItemVenta(brocha, cantidad));
        }
        
        System.out.print("¿Agregar " + cinta.getDescripcion() + "? (s/n): ");
        if (scanner.nextLine().toLowerCase().equals("s")) {
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            venta.agregarItem(new ItemVenta(cinta, cantidad));
        }
        
        return venta;
    }
    
    private static void mostrarResumenVenta(Venta venta) {
        try {
            System.out.println("\n=== RESUMEN DE VENTA ===");
            System.out.println("Cliente: " + venta.getCliente().getNombre());
            System.out.println("Número: " + venta.getNumero());
            System.out.println("Subtotal: $" + String.format("%.2f", venta.getSubtotal()));
            System.out.println("Descuento productos: $" + String.format("%.2f", venta.getDescuentoTotalProductos()));
            System.out.println("Descuento cliente: $" + String.format("%.2f", venta.getDescuentoCliente()));
            System.out.println("TOTAL: $" + String.format("%.2f", venta.getTotal()));
            
            System.out.print("\n¿Confirmar venta? (s/n): ");
            if (scanner.nextLine().toLowerCase().equals("s")) {
                venta.confirmar();
                System.out.println("✓ Venta confirmada exitosamente");
            } else {
                System.out.println("Venta no confirmada");
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar resumen: " + e.getMessage());
        }
    }
    
    private static void ejecutarDemostracionAutomatica() {
        System.out.println("=== DEMOSTRACIÓN AUTOMÁTICA ===\n");
        
        // === DEMOSTRACIÓN DE MANEJO DE EXCEPCIONES ===
        System.out.println("1. MANEJO DE EXCEPCIONES:");
        System.out.println("=========================");
        
        // Demostrar excepciones de validación
        try {
            System.out.println("Intentando crear producto con datos inválidos...");
            new Pintura("AB", "Test", new BigDecimal("-100"), -5, "tipo_invalido", "", -1);
        } catch (ValidacionException e) {
            System.out.println("✓ Excepción capturada: " + e.getMessage());
        }
        
        try {
            System.out.println("Intentando crear cliente con DNI inválido...");
            new ClienteMinorista("123", "A", "telefono_invalido", true, -1);
        } catch (ValidacionException e) {
            System.out.println("✓ Excepción capturada: " + e.getMessage());
        }
        
        try {
            System.out.println("Intentando crear venta con número inválido...");
            new Venta("INVALIDO", null);
        } catch (ValidacionException e) {
            System.out.println("✓ Excepción capturada: " + e.getMessage());
        }
        
        System.out.println();
        
        // === DEMOSTRACIÓN DE HERENCIA EN CLIENTES ===
        System.out.println("2. HERENCIA EN CLIENTES:");
        System.out.println("========================");
        
        // Crear clientes de diferentes tipos (polimorfismo)
        Cliente clienteMayorista = null;
        Cliente clienteMinorista = null;
        
        try {
            clienteMayorista = new ClienteMayorista("20123456", "Distribuidora ABC", "+54 11 4444-0000", 
                                                   "Distribuidora ABC S.A.", "20-12345678-9", 1200);
            clienteMinorista = new ClienteMinorista("40123123", "Ana Perez", "+54 11 5555-0000", 
                                                   true, 8);
            
            System.out.println("Cliente Mayorista: " + clienteMayorista);
            System.out.println("Cliente Minorista: " + clienteMinorista);
        } catch (ValidacionException e) {
            System.out.println("Error al crear clientes: " + e.getMessage());
        }
        
        System.out.println();
        
        // === DEMOSTRACIÓN DE HERENCIA EN PRODUCTOS ===
        System.out.println("3. HERENCIA EN PRODUCTOS:");
        System.out.println("=========================");
        
        // Crear productos de diferentes tipos (polimorfismo)
        Producto latex = null;
        Producto esmalte = null;
        Producto brocha = null;
        Producto cinta = null;
        
        try {
            latex = new Pintura("LATEX-BLANCO-4L", "Pintura Latex Blanco 4L", 
                               new BigDecimal("12999.90"), 10, "latex", "blanco", 4.0);
            esmalte = new Pintura("ESMALTE-NEGRO-1L", "Esmalte Sintético Negro 1L", 
                                 new BigDecimal("8999.50"), 5, "esmalte", "negro", 1.0);
            brocha = new Herramienta("BROCHA-4P", "Brocha de 4 pulgadas", 
                                   new BigDecimal("2500.00"), 20, "brocha", "cerda natural", true);
            cinta = new Accesorio("CINTA-48MM", "Cinta de enmascarar 48mm", 
                                new BigDecimal("800.00"), 50, "cinta", "metros", 50.0);
            
            System.out.println("Pintura Latex: " + latex);
            System.out.println("Pintura Esmalte: " + esmalte);
            System.out.println("Herramienta Brocha: " + brocha);
            System.out.println("Accesorio Cinta: " + cinta);
        } catch (ValidacionException e) {
            System.out.println("Error al crear productos: " + e.getMessage());
        }
        
        System.out.println();
        
        // === DEMOSTRACIÓN DE POLIMORFISMO EN DESCUENTOS ===
        System.out.println("4. POLIMORFISMO EN DESCUENTOS:");
        System.out.println("==============================");
        
        // Crear venta con cliente mayorista
        Venta ventaMayorista = null;
        Venta ventaMinorista = null;
        
        try {
            if (clienteMayorista != null && latex != null && brocha != null && cinta != null) {
                ventaMayorista = new Venta("V-0001", clienteMayorista);
                ventaMayorista.agregarItem(new ItemVenta(latex, 3)); // Descuento por volumen
                ventaMayorista.agregarItem(new ItemVenta(brocha, 6)); // Descuento por cantidad
                ventaMayorista.agregarItem(new ItemVenta(cinta, 12)); // Descuento fijo + volumen
                
                System.out.println("VENTA MAYORISTA:");
                System.out.println("Subtotal: $" + String.format("%.2f", ventaMayorista.getSubtotal()));
                System.out.println("Descuento productos: $" + String.format("%.2f", ventaMayorista.getDescuentoTotalProductos()));
                System.out.println("Descuento cliente: $" + String.format("%.2f", ventaMayorista.getDescuentoCliente()));
                System.out.println("TOTAL FINAL: $" + String.format("%.2f", ventaMayorista.getTotal()));
            }
        } catch (ValidacionException | VentaYaConfirmadaException | VentaVaciaException e) {
            System.out.println("Error en venta mayorista: " + e.getMessage());
        }
        
        System.out.println();
        
        try {
            if (clienteMinorista != null && esmalte != null && brocha != null) {
                // Crear venta con cliente minorista
                ventaMinorista = new Venta("V-0002", clienteMinorista);
                ventaMinorista.agregarItem(new ItemVenta(esmalte, 2)); // Sin descuento de producto
                ventaMinorista.agregarItem(new ItemVenta(brocha, 2)); // Sin descuento de producto
                
                System.out.println("VENTA MINORISTA:");
                System.out.println("Subtotal: $" + String.format("%.2f", ventaMinorista.getSubtotal()));
                System.out.println("Descuento productos: $" + String.format("%.2f", ventaMinorista.getDescuentoTotalProductos()));
                System.out.println("Descuento cliente: $" + String.format("%.2f", ventaMinorista.getDescuentoCliente()));
                System.out.println("TOTAL FINAL: $" + String.format("%.2f", ventaMinorista.getTotal()));
            }
        } catch (ValidacionException | VentaYaConfirmadaException | VentaVaciaException e) {
            System.out.println("Error en venta minorista: " + e.getMessage());
        }
        
        System.out.println();
        
        // === DEMOSTRACIÓN DE MÉTODOS ESPECÍFICOS ===
        System.out.println("5. MÉTODOS ESPECÍFICOS DE CLASES HIJAS:");
        System.out.println("=======================================");
        
        try {
            // Métodos específicos de Pintura
            if (latex != null) {
                Pintura pinturaLatex = (Pintura) latex;
                System.out.println("¿Es pintura exterior? " + pinturaLatex.esPinturaExterior());
            }
            
            // Métodos específicos de Herramienta
            if (brocha != null) {
                Herramienta herramientaBrocha = (Herramienta) brocha;
                System.out.println("¿Requiere mantenimiento? " + herramientaBrocha.requiereMantenimiento());
            }
            
            // Métodos específicos de Accesorio
            if (cinta != null) {
                Accesorio accesorioCinta = (Accesorio) cinta;
                System.out.println("¿Es consumible? " + accesorioCinta.esConsumible());
            }
            
            // Métodos específicos de ClienteMayorista
            if (clienteMayorista != null) {
                ClienteMayorista mayorista = (ClienteMayorista) clienteMayorista;
                System.out.println("¿Tiene descuento especial? " + mayorista.tieneDescuentoEspecial());
                System.out.println("Porcentaje descuento total: " + mayorista.getPorcentajeDescuentoTotal() + "%");
            }
            
            // Métodos específicos de ClienteMinorista
            if (clienteMinorista != null) {
                ClienteMinorista minorista = (ClienteMinorista) clienteMinorista;
                System.out.println("¿Tiene descuento frecuente? " + minorista.tieneDescuentoFrecuente());
                System.out.println("Porcentaje descuento total: " + minorista.getPorcentajeDescuentoTotal() + "%");
            }
        } catch (Exception e) {
            System.out.println("Error en métodos específicos: " + e.getMessage());
        }
        
        System.out.println();
        
        // === DEMOSTRACIÓN DE EXCEPCIONES EN VENTAS ===
        System.out.println("6. EXCEPCIONES EN VENTAS:");
        System.out.println("=========================");
        
        try {
            System.out.println("Intentando confirmar venta vacía...");
            Venta ventaVacia = new Venta("V-0003", clienteMinorista);
            ventaVacia.confirmar();
        } catch (VentaVaciaException e) {
            System.out.println("✓ Excepción capturada: " + e.getMessage());
        } catch (ValidacionException | StockInsuficienteException | VentaYaConfirmadaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            System.out.println("Intentando agregar item a venta confirmada...");
            if (ventaMayorista != null) {
                ventaMayorista.confirmar();
                ventaMayorista.agregarItem(new ItemVenta(latex, 1));
            }
        } catch (VentaYaConfirmadaException e) {
            System.out.println("✓ Excepción capturada: " + e.getMessage());
        } catch (ValidacionException | VentaVaciaException | StockInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            System.out.println("Intentando vender más stock del disponible...");
            Producto productoStockBajo = new Pintura("STOCK-BAJO", "Producto con poco stock", 
                                                    new BigDecimal("1000"), 2, "latex", "rojo", 1.0);
            Venta ventaStockInsuficiente = new Venta("V-0004", clienteMinorista);
            ventaStockInsuficiente.agregarItem(new ItemVenta(productoStockBajo, 5));
            ventaStockInsuficiente.confirmar();
        } catch (StockInsuficienteException e) {
            System.out.println("✓ Excepción capturada: " + e.getMessage());
            System.out.println("  - Producto: " + e.getCodigoProducto());
            System.out.println("  - Stock disponible: " + e.getStockDisponible());
            System.out.println("  - Cantidad solicitada: " + e.getCantidadSolicitada());
        } catch (ValidacionException | VentaVaciaException | VentaYaConfirmadaException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println();
        
        // === CONFIRMACIÓN DE VENTAS ===
        System.out.println("7. CONFIRMACIÓN DE VENTAS:");
        System.out.println("==========================");
        
        try {
            if (ventaMayorista != null && !ventaMayorista.isConfirmada()) {
                ventaMayorista.confirmar();
                System.out.println("✓ Venta mayorista confirmada");
            }
            if (ventaMinorista != null && !ventaMinorista.isConfirmada()) {
                ventaMinorista.confirmar();
                System.out.println("✓ Venta minorista confirmada");
            }
            
            if (latex != null) System.out.println("Stock restante latex: " + latex.getStock());
            if (esmalte != null) System.out.println("Stock restante esmalte: " + esmalte.getStock());
            if (brocha != null) System.out.println("Stock restante brocha: " + brocha.getStock());
            if (cinta != null) System.out.println("Stock restante cinta: " + cinta.getStock());
        } catch (VentaVaciaException | StockInsuficienteException | VentaYaConfirmadaException | ValidacionException e) {
            System.out.println("Error al confirmar ventas: " + e.getMessage());
        }
        
        System.out.println();
        
        // === RESUMEN DE VENTAS ===
        System.out.println("8. RESUMEN DE VENTAS:");
        System.out.println("=====================");
        if (ventaMayorista != null) System.out.println(ventaMayorista);
        if (ventaMinorista != null) System.out.println(ventaMinorista);
        
        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
}
