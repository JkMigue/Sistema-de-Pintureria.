# Sistema de Pinturería - Explicación para el Profesor

## Introducción

Este proyecto implementa un **Sistema de Gestión de Ventas para una Pinturería** que demuestra los conceptos fundamentales de la **Programación Orientada a Objetos (POO)** en Java. El sistema está diseñado para ser educativo, práctico y fácil de entender.

## Conceptos Implementados

### 1. **Herencia (Inheritance)**
- **Clase abstracta `Producto`**: Define la estructura común para todos los productos
- **Clase abstracta `Cliente`**: Define la estructura común para todos los clientes
- **Clases hijas**: `Pintura`, `Herramienta`, `Accesorio` (productos) y `ClienteMayorista`, `ClienteMinorista` (clientes)
- **Beneficio**: Reutilización de código y organización jerárquica

### 2. **Polimorfismo (Polymorphism)**
- **Métodos abstractos**: `calcularDescuento()` y `getInformacionEspecifica()` en `Producto`
- **Métodos abstractos**: `calcularDescuentoCliente()` y `getTipoCliente()` en `Cliente`
- **Sobreescritura**: Cada clase hija implementa los métodos de manera específica
- **Ejemplo**: Una pintura calcula descuentos diferentes a una herramienta

### 3. **Manejo de Excepciones (Exception Handling)**
- **Jerarquía de excepciones personalizadas**: `ValidacionException`, `StockInsuficienteException`, etc.
- **Validación robusta**: Todos los datos de entrada son validados
- **Mensajes descriptivos**: Errores claros y específicos para el usuario
- **Prevención de estados inválidos**: Validación en constructores y métodos

### 4. **Entrada Interactiva (Interactive Input)**
- **Scanner**: Entrada de datos desde consola
- **Menú interactivo**: Opciones para demostración automática o interactiva
- **Validación en tiempo real**: Validación de datos ingresados por el usuario
- **Formato profesional**: Números con 2 decimales, generación automática de códigos

## Estructura del Proyecto

```
src/com/pintureria/
├── app/
│   └── Main.java                    # Clase principal con menú interactivo
├── model/
│   ├── Producto.java               # Clase abstracta base
│   ├── Pintura.java                # Herencia de Producto
│   ├── Herramienta.java            # Herencia de Producto
│   ├── Accesorio.java              # Herencia de Producto
│   ├── Cliente.java                # Clase abstracta base
│   ├── ClienteMayorista.java       # Herencia de Cliente
│   ├── ClienteMinorista.java       # Herencia de Cliente
│   ├── Venta.java                  # Gestión de ventas
│   └── ItemVenta.java              # Items de venta
└── exceptions/
    ├── ValidacionException.java     # Excepción base de validación
    ├── StockInsuficienteException.java
    ├── VentaVaciaException.java
    ├── VentaYaConfirmadaException.java
    └── ... (otras excepciones)
```

## Funcionalidades del Sistema

### **Demostración Automática**
1. **Manejo de excepciones**: Demuestra validación de datos inválidos
2. **Creación de objetos**: Crea productos y clientes de diferentes tipos
3. **Cálculo de descuentos**: Aplica descuentos polimórficos
4. **Procesamiento de ventas**: Calcula totales con descuentos
5. **Métodos específicos**: Usa métodos únicos de cada clase hija
6. **Confirmación**: Actualiza el stock de productos
7. **Resumen**: Muestra información detallada de las ventas

### **Demostración Interactiva**
1. **Menú principal**: Selección entre demostración automática o interactiva
2. **Creación de cliente**: Entrada interactiva de datos del cliente
3. **Productos predefinidos**: Selección de productos disponibles
4. **Generación automática**: Número de venta generado automáticamente
5. **Selección de productos**: Elección de productos y cantidades
6. **Resumen con formato**: Precios y totales con 2 decimales
7. **Confirmación opcional**: El usuario decide confirmar la venta

## Ejemplos de Polimorfismo

### **Descuentos por Tipo de Producto**
- **Pintura**: 5% si compra 3+ unidades, 3% adicional para latex si compra 2+
- **Herramienta**: 10% si compra 5+ unidades, 5% adicional para reutilizables si compra 3+
- **Accesorio**: 2% base + 3% adicional si compra 10+ unidades

### **Descuentos por Tipo de Cliente**
- **ClienteMayorista**: 8% base + 3-5% adicional según volumen anual
- **ClienteMinorista**: 2% base + 3% si es frecuente + 1-2% según compras del año

## Validaciones Implementadas

- **CUIT**: Acepta formato con o sin guiones (11 dígitos)
- **DNI**: Entre 7 y 8 caracteres
- **Teléfono**: Formato válido
- **Precios**: No negativos, mayor a cero
- **Stock**: No negativo
- **Códigos**: Mínimo 3 caracteres
- **Descripciones**: Mínimo 5 caracteres

## Características Técnicas

### **Formato de Salida**
- **Precios**: Siempre con 2 decimales
- **Números de venta**: Generados automáticamente (V-XXXX)
- **Mensajes de error**: Descriptivos y específicos

### **Compilación y Ejecución**
```bash
# Compilar todo el proyecto
javac -d out src/com/pintureria/model/*.java src/com/pintureria/exceptions/*.java src/com/pintureria/app/*.java

# Ejecutar
java -cp out com.pintureria.app.Main
```

## Beneficios del Diseño

1. **Extensibilidad**: Fácil agregar nuevos tipos de productos o clientes
2. **Mantenibilidad**: Código organizado y fácil de mantener
3. **Reutilización**: Código común en clases padre
4. **Flexibilidad**: Comportamiento específico por tipo de objeto
5. **Escalabilidad**: Sistema preparado para crecer
6. **Educativo**: Demuestra claramente los conceptos de POO

## Valor Educativo

Este proyecto es ideal para enseñar:

- **Herencia**: Cómo las clases hijas heredan y extienden funcionalidad
- **Polimorfismo**: Cómo el mismo método se comporta diferente según el tipo de objeto
- **Abstracción**: Cómo las clases abstractas definen contratos
- **Encapsulación**: Cómo se protegen los datos con métodos de acceso
- **Manejo de excepciones**: Cómo manejar errores de manera elegante
- **Entrada de usuario**: Cómo interactuar con el usuario desde consola

## Casos de Uso Demostrados

1. **Creación de objetos**: Diferentes constructores con validación
2. **Cálculo de precios**: Algoritmos específicos por tipo de producto
3. **Gestión de stock**: Control de inventario con excepciones
4. **Procesamiento de ventas**: Flujo completo de una transacción
5. **Validación de datos**: Prevención de estados inválidos
6. **Interfaz de usuario**: Menú interactivo y entrada de datos

## Conclusión

Este sistema demuestra de manera práctica y clara los conceptos fundamentales de la Programación Orientada a Objetos. Es un excelente ejemplo para estudiantes porque:

- **Es realista**: Simula un sistema de ventas real
- **Es completo**: Cubre todos los conceptos principales de POO
- **Es interactivo**: Permite experimentar con diferentes escenarios
- **Es educativo**: Cada parte del código tiene un propósito didáctico
- **Es extensible**: Fácil de modificar y expandir

El proyecto está diseñado para ser presentado en clase, demostrando cómo los conceptos teóricos se aplican en la práctica, y cómo un buen diseño orientado a objetos hace que el código sea más mantenible, extensible y fácil de entender.
