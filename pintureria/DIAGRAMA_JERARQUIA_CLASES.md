# Diagrama de Jerarquía de Clases - Sistema de Pinturería

## Descripción del Sistema

Este sistema implementa un sistema de ventas para una pinturería que demuestra los conceptos de **Herencia**, **Polimorfismo**, **Manejo de Excepciones** y **Entrada Interactiva** en Java. El sistema incluye una demostración automática y una demostración interactiva con Scanner para entrada de usuario.

## Jerarquía de Clases

### 1. Jerarquía de Productos

```
                    Producto (abstracta)
                    ├── codigo: String
                    ├── descripcion: String
                    ├── precioUnitario: BigDecimal
                    ├── stock: int
                    │
                    ├── + calcularDescuento(int): BigDecimal (abstracto)
                    ├── + getInformacionEspecifica(): String (abstracto)
                    ├── + getPrecioFinal(int): BigDecimal
                    └── + disminuirStock(int): void
                    │
                    ├── Pintura
                    │   ├── tipo: String (latex, esmalte, acrílico)
                    │   ├── color: String
                    │   ├── litros: double
                    │   │
                    │   ├── + calcularDescuento(int): BigDecimal
                    │   ├── + getInformacionEspecifica(): String
                    │   └── + esPinturaExterior(): boolean
                    │
                    ├── Herramienta
                    │   ├── categoria: String (brocha, rodillo, espátula)
                    │   ├── material: String
                    │   ├── esReutilizable: boolean
                    │   │
                    │   ├── + calcularDescuento(int): BigDecimal
                    │   ├── + getInformacionEspecifica(): String
                    │   └── + requiereMantenimiento(): boolean
                    │
                    └── Accesorio
                        ├── tipo: String (cinta, papel, plástico)
                        ├── unidad: String (metros, rollos, unidades)
                        ├── medida: double
                        │
                        ├── + calcularDescuento(int): BigDecimal
                        ├── + getInformacionEspecifica(): String
                        └── + esConsumible(): boolean
```

### 2. Jerarquía de Clientes

```
                    Cliente (abstracta)
                    ├── dni: String
                    ├── nombre: String
                    ├── telefono: String
                    │
                    ├── + calcularDescuentoCliente(BigDecimal): BigDecimal (abstracto)
                    ├── + getTipoCliente(): String (abstracto)
                    └── + getMontoFinal(BigDecimal): BigDecimal
                    │
                    ├── ClienteMayorista
                    │   ├── razonSocial: String
                    │   ├── cuit: String
                    │   ├── volumenCompraAnual: int
                    │   │
                    │   ├── + calcularDescuentoCliente(BigDecimal): BigDecimal
                    │   ├── + getTipoCliente(): String
                    │   ├── + tieneDescuentoEspecial(): boolean
                    │   └── + getPorcentajeDescuentoTotal(): double
                    │
                    └── ClienteMinorista
                        ├── esFrecuente: boolean
                        ├── comprasUltimoAno: int
                        │
                        ├── + calcularDescuentoCliente(BigDecimal): BigDecimal
                        ├── + getTipoCliente(): String
                        ├── + tieneDescuentoFrecuente(): boolean
                        └── + getPorcentajeDescuentoTotal(): double
```

### 3. Clases de Soporte

```
                    ItemVenta
                    ├── producto: Producto
                    ├── cantidad: int
                    │
                    ├── + getSubtotal(): BigDecimal
                    ├── + getPrecioFinal(): BigDecimal
                    └── + getDescuentoProducto(): BigDecimal

                    Venta
                    ├── numero: String
                    ├── cliente: Cliente
                    ├── fechaHora: LocalDateTime
                    ├── items: List<ItemVenta>
                    ├── confirmada: boolean
                    │
                    ├── + getSubtotal(): BigDecimal
                    ├── + getTotalConDescuentosProductos(): BigDecimal
                    ├── + getTotal(): BigDecimal
                    ├── + getDescuentoTotalProductos(): BigDecimal
                    ├── + getDescuentoCliente(): BigDecimal
                    ├── + confirmar(): void
                    └── + isConfirmada(): boolean
```

### 4. Jerarquía de Excepciones

```
                    Exception
                    │
                    ├── ProductoException
                    │   ├── StockInsuficienteException
                    │   └── ProductoNoEncontradoException
                    │
                    ├── ClienteException
                    │   └── ClienteNoEncontradoException
                    │
                    ├── VentaException
                    │   ├── VentaVaciaException
                    │   └── VentaYaConfirmadaException
                    │
                    └── ValidacionException
```

### 5. Clase Principal

```
                    Main
                    ├── scanner: Scanner (static)
                    │
                    ├── + main(String[]): void
                    ├── + mostrarMenu(): void
                    ├── + ejecutarDemostracionAutomatica(): void
                    ├── + ejecutarDemostracionInteractiva(): void
                    ├── + crearClienteInteractivo(): Cliente
                    ├── + crearVentaInteractiva(...): Venta
                    └── + mostrarResumenVenta(Venta): void
```

## Conceptos Demostrados

### 1. Herencia
- **Producto** es la clase padre abstracta para todos los tipos de productos
- **Cliente** es la clase padre abstracta para todos los tipos de clientes
- Las clases hijas heredan atributos y métodos de las clases padre
- Las clases hijas pueden agregar atributos y métodos específicos

### 2. Polimorfismo
- **Métodos abstractos**: `calcularDescuento()` y `getInformacionEspecifica()` en Producto
- **Métodos abstractos**: `calcularDescuentoCliente()` y `getTipoCliente()` en Cliente
- **Sobreescritura**: Cada clase hija implementa los métodos abstractos de manera específica
- **Polimorfismo en tiempo de ejecución**: Los métodos se comportan diferente según el tipo real del objeto

### 3. Encapsulación
- Todos los atributos son privados
- Acceso controlado mediante métodos getter y setter
- Validación de datos en constructores y setters

### 4. Abstracción
- Clases abstractas que definen la interfaz común
- Métodos abstractos que deben ser implementados por las clases hijas
- Ocultación de detalles de implementación

### 5. Manejo de Excepciones
- **Excepciones personalizadas**: Jerarquía de excepciones específicas para el dominio
- **Validación robusta**: Validación de datos de entrada con mensajes descriptivos
- **Manejo de errores**: Captura y manejo apropiado de excepciones
- **Prevención de estados inválidos**: Validación en constructores y métodos

### 6. Entrada Interactiva
- **Scanner**: Entrada de datos desde consola
- **Menú interactivo**: Opciones para demostración automática o interactiva
- **Validación en tiempo real**: Validación de datos ingresados por el usuario
- **Formato de salida**: Números con 2 decimales para mejor presentación

## Ejemplos de Polimorfismo

### Descuentos por Tipo de Producto
- **Pintura**: 5% si compra 3+ unidades, 3% adicional para latex si compra 2+
- **Herramienta**: 10% si compra 5+ unidades, 5% adicional para reutilizables si compra 3+
- **Accesorio**: 2% base + 3% adicional si compra 10+ unidades

### Descuentos por Tipo de Cliente
- **ClienteMayorista**: 8% base + 3-5% adicional según volumen anual
- **ClienteMinorista**: 2% base + 3% si es frecuente + 1-2% según compras del año

## Flujo de la Aplicación

### Demostración Automática
1. **Manejo de excepciones**: Demuestra validación de datos inválidos
2. **Creación de objetos**: Se crean productos y clientes de diferentes tipos
3. **Cálculo de descuentos**: Los métodos polimórficos calculan descuentos específicos
4. **Procesamiento de ventas**: Se aplican descuentos de productos y clientes
5. **Métodos específicos**: Uso de métodos únicos de cada clase hija
6. **Confirmación**: Se actualiza el stock de productos
7. **Resumen**: Se muestra información detallada de las ventas

### Demostración Interactiva
1. **Menú principal**: Selección entre demostración automática o interactiva
2. **Creación de cliente**: Entrada interactiva de datos del cliente
3. **Productos predefinidos**: Selección de productos disponibles
4. **Generación automática**: Número de venta generado automáticamente
5. **Selección de productos**: Elección de productos y cantidades
6. **Resumen con formato**: Precios y totales con 2 decimales
7. **Confirmación opcional**: El usuario decide confirmar la venta

## Beneficios del Diseño

1. **Extensibilidad**: Fácil agregar nuevos tipos de productos o clientes
2. **Mantenibilidad**: Código organizado y fácil de mantener
3. **Reutilización**: Código común en clases padre
4. **Flexibilidad**: Comportamiento específico por tipo de objeto
5. **Escalabilidad**: Sistema preparado para crecer

## Compilación y Ejecución

```bash
# Compilar todo el proyecto
javac -d out src/com/pintureria/model/*.java src/com/pintureria/exceptions/*.java src/com/pintureria/app/*.java

# Ejecutar
java -cp out com.pintureria.app.Main
```

## Características del Sistema

### Validaciones Implementadas
- **CUIT**: Acepta formato con o sin guiones (11 dígitos)
- **DNI**: Entre 7 y 8 caracteres
- **Teléfono**: Formato válido
- **Precios**: No negativos, mayor a cero
- **Stock**: No negativo
- **Códigos**: Mínimo 3 caracteres
- **Descripciones**: Mínimo 5 caracteres

### Formato de Salida
- **Precios**: Siempre con 2 decimales
- **Números de venta**: Generados automáticamente (V-XXXX)
- **Mensajes de error**: Descriptivos y específicos

## Salida Esperada

### Demostración Automática
- Manejo de excepciones con datos inválidos
- Creación de diferentes tipos de productos y clientes
- Cálculo de descuentos polimórficos
- Aplicación de descuentos en ventas
- Uso de métodos específicos de cada clase
- Actualización de stock
- Resumen completo de las ventas

### Demostración Interactiva
- Menú de opciones
- Entrada de datos del cliente
- Lista de productos disponibles
- Generación automática de número de venta
- Selección de productos y cantidades
- Resumen con precios formateados (2 decimales)
- Confirmación opcional de venta

## Resumen de Conceptos

Este sistema demuestra claramente los conceptos de:
- **Herencia**: Jerarquías de clases con herencia de atributos y métodos
- **Polimorfismo**: Métodos abstractos y sobreescritura
- **Manejo de Excepciones**: Validación robusta y manejo de errores
- **Entrada Interactiva**: Uso de Scanner para entrada de usuario
- **Formato de Salida**: Presentación profesional de datos numéricos
- **Generación Automática**: Números de venta generados automáticamente

El sistema está diseñado para ser fácil de usar, educativo y demostrativo de los conceptos fundamentales de la programación orientada a objetos en Java.

