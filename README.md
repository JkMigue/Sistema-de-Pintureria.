# 🖥️ Sistema de Pinturería

**Miguel Alberto Torrico – Desarrollador de Software**  
[LinkedIn](https://www.linkedin.com/in/miguel-torrico-052524210) | [GitHub](https://github.com/JkMigue)

---

## 📘 Descripción del Proyecto

El **Sistema de Pinturería** es una aplicación desarrollada en **Java** que implementa los conceptos fundamentales de la **Programación Orientada a Objetos (POO)**.  
El proyecto está diseñado con fines educativos y demuestra el uso de:

- Herencia  
- Polimorfismo  
- Encapsulación  
- Abstracción  
- Manejo de Excepciones  
- Entrada Interactiva  

El sistema permite gestionar clientes, productos y ventas dentro de una pinturería, mostrando cómo aplicar buenas prácticas de diseño orientado a objetos.

---

## ⚙️ Funcionalidades Principales

- Gestión de **productos**: Pinturas, Herramientas y Accesorios.  
- Gestión de **clientes**: Mayoristas y Minoristas con descuentos personalizados.  
- Procesamiento de **ventas** con aplicación de descuentos y confirmación de stock.  
- **Validación** de datos robusta mediante excepciones personalizadas.  
- **Interfaz interactiva** por consola utilizando Scanner.  
- **Demostración automática** para validar comportamientos y excepciones.

---

## 🧩 Jerarquía de Clases

### Productos
- **Producto (abstracta)**  
  - Clases hijas: `Pintura`, `Herramienta`, `Accesorio`  
  - Métodos abstractos: `calcularDescuento()`, `getInformacionEspecifica()`  

### Clientes
- **Cliente (abstracta)**  
  - Clases hijas: `ClienteMayorista`, `ClienteMinorista`  
  - Métodos abstractos: `calcularDescuentoCliente()`, `getTipoCliente()`  

### Soporte
- `ItemVenta` – Representa un producto dentro de una venta.  
- `Venta` – Manejo de totales, descuentos y confirmación de ventas.  

### Excepciones
- Jerarquía de errores personalizada:  
  `ProductoException`, `ClienteException`, `VentaException`, `ValidacionException`.

---

## 🧠 Conceptos Demostrados

1. **Herencia**: Clases hijas que heredan atributos y métodos de clases abstractas.  
2. **Polimorfismo**: Métodos sobreescritos que se comportan diferente según la clase concreta.  
3. **Encapsulación**: Atributos privados y métodos de acceso controlado.  
4. **Abstracción**: Clases base que definen comportamientos obligatorios.  
5. **Manejo de Excepciones**: Validaciones de entrada y prevención de estados inválidos.  
6. **Interactividad**: Entrada de datos en tiempo real por consola.  

---

## 🔄 Flujo de la Aplicación

### Demostración Automática
1. Validación de datos y manejo de excepciones.  
2. Creación de diferentes tipos de productos y clientes.  
3. Cálculo y aplicación de descuentos polimórficos.  
4. Confirmación de ventas y actualización de stock.  
5. Generación de resumen detallado de ventas.

### Demostración Interactiva
1. Menú principal para seleccionar tipo de demostración.  
2. Creación de clientes y selección de productos disponibles.  
3. Generación automática de número de venta.  
4. Cálculo de totales y presentación formateada.  
5. Confirmación opcional de venta.

---

## 🧾 Validaciones Implementadas

- **CUIT**: 11 dígitos, con o sin guiones.  
- **DNI**: 7 a 8 caracteres.  
- **Teléfono**: Formato válido.  
- **Precios**: Mayores a cero.  
- **Stock**: No negativo.  
- **Códigos y descripciones**: Longitud mínima asegurada.  

---

## 🧮 Compilación y Ejecución

```bash
# Compilar todo el proyecto
javac -d out src/com/pintureria/model/*.java src/com/pintureria/exceptions/*.java src/com/pintureria/app/*.java

# Ejecutar
java -cp out com.pintureria.app.Main
