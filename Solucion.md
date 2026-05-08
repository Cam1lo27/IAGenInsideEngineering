## EJERCICIO 1 (15 minutos)

IA utilizada: Claude

# Solución — Problema #1: El Videoclub de Don Mario

---

## 1. Patrones de Diseño Utilizados

### 🏭 Factory Method — `MovieFactory`
Se utiliza para delegar la creación de objetos `Movie` a una clase especializada.  
El cliente (en este caso `VideoClub`) solicita una película pasando solo el tipo (`"fisica"` o `"digital"`), sin conocer ni depender de las clases concretas `PhysicalMovie` o `DigitalMovie`.

**Beneficio:** Si se agrega un nuevo tipo de película (p. ej. `"streaming"`), solo se modifica `MovieFactory` con un nuevo `case`; el resto del sistema no cambia.

### 🎯 Strategy — `PricingStrategy`
Se utiliza para encapsular los distintos algoritmos de descuento según la membresía del cliente.

| Estrategia       | Clase            | Comportamiento              |
|------------------|------------------|-----------------------------|
| Membresía Básica | `BasicPricing`   | Sin descuento (precio normal) |
| Membresía Premium| `PremiumPricing` | 20% de descuento             |

**Beneficio:** `RentalService` y `Receipt` dependen únicamente de la interfaz `PricingStrategy`. Agregar una membresía `"VIP"` con 30% no requiere tocar ninguna clase existente.

---

## 2. Principios SOLID Aplicados

### S — Single Responsibility (Responsabilidad Única)
Cada clase tiene **una única razón para cambiar**:

- `Movie` → gestiona los datos de una película.
- `MovieFactory` → crea instancias de películas.
- `RentalService` → orquesta la selección y el proceso de alquiler.
- `Receipt` → formatea e imprime el recibo.
- `BasicPricing` / `PremiumPricing` → calculan el precio según membresía.

### O — Open/Closed (Abierto/Cerrado)
Las clases están **abiertas a extensión pero cerradas a modificación**:

- Nuevo tipo de película → nuevo `case` en `MovieFactory`, sin tocar `Movie`.
- Nueva membresía → nueva clase que implemente `PricingStrategy`, sin tocar `RentalService` ni `Receipt`.

### L — Liskov Substitution (Sustitución de Liskov)
`PhysicalMovie` y `DigitalMovie` **sustituyen a `Movie`** en cualquier contexto sin alterar el comportamiento esperado. Cualquier método que reciba un `Movie` funciona correctamente recibiendo cualquiera de las dos subclases.

### I — Interface Segregation (Segregación de Interfaces)
`PricingStrategy` expone **únicamente los métodos necesarios** para calcular precios y mostrar información de membresía:
- `calculate(double subtotal)`
- `getMembershipLabel()`
- `getDiscountPercent()`

Ninguna clase implementadora se ve obligada a implementar métodos que no necesita.

### D — Dependency Inversion (Inversión de Dependencias)
Los módulos de alto nivel dependen de **abstracciones, no de implementaciones concretas**:

- `RentalService` depende de `Movie` (abstracta) y `PricingStrategy` (interfaz).
- `Receipt` depende de `Movie` y `PricingStrategy`.
- Ninguno de los dos conoce `PhysicalMovie`, `DigitalMovie`, `BasicPricing` ni `PremiumPricing`.

---

## 3. Polimorfismo y Encapsulamiento

### Polimorfismo
El método `getType()` está declarado como `abstract` en `Movie` y es sobreescrito por cada subclase:

```java
// PhysicalMovie
public String getType() { return "Fisica"; }

// DigitalMovie
public String getType() { return "Digital"; }
```

Esto permite que `Receipt` itere una lista de `Movie` e imprima el tipo correcto sin usar `instanceof` ni condicionales.

### Encapsulamiento
- Los atributos `title`, `price` y `available` en `Movie` son `private`.
- El acceso se realiza exclusivamente mediante getters públicos.
- La disponibilidad solo puede modificarse a través del setter controlado `setAvailable(boolean)`, evitando modificaciones directas desde fuera de la clase.

---

