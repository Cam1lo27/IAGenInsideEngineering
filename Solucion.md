## EJERCICIO 1 (15 minutos)

IA utilizada: Claude

Prompt utilizado: Ayudame a relizar este ejercicio, dame el codigo estructurado de tal forma que cumplamos los puntos de los objetivos de el ejercicio, realiza el ejercicio en java y dame solo los codigos de las clases que creas

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


## EJERCICIO 2 (23 minutos)

IA usada: Claude

prompt usado: Ayudame a realizar todo este ejercicio y dame aqui en el chat su solucion, las clases escribelas en el chat y dame tambien escrito en el chat las respuestas a las preguntas que hay, ten en cuenta y revisa las estructuras que plantea el ejercicio junto con los diagramas que tambien los debes de tener en cuenta, revisa la estructura de carpetas de forma correcta


## 1. Identificación de Patrones

### Patrones utilizados:
1. **Factory Method** (mal llamado Abstract Factory en las clases)
    - Clases Factory: `CreditCardFactory`, `PaypalFactory`, `CryptoFactory`
    - Interfaz necesaria: `PaymentFactory`

2. **Observer**
    - Subject: `ECIPayment`
    - Observer: `PaymentEventObserver`
    - Observables: `Inventory`, `Facturation`, `Notification`

### ¿Son adecuados?
- ✅ **Observer**: Perfecto para notificar a múltiples módulos
- ⚠️ **Factory Method vs Abstract Factory**: El código usa Factory Method (una jerarquía de factories), no Abstract Factory (múltiples familias). Los nombres de las clases son engañosos.

## 2. Clases/Interfaces Faltantes

### Interfaz Principal
- `PaymentFactory` - Define el contrato para crear métodos de pago

### Implementaciones Concretas
- `CreditCardPaymentFactory`
- `PaypalPaymentFactory`
- `CryptoPaymentFactory`

## 3. Cambios al Diagrama de Contexto

1. Agregar componente "PaymentFactory (interface)" entre Cliente y Sistema de pago
2. Mostrar implementaciones concretas del Factory
3. Indicar uso explícito de patrón Observer
4. Mostrar flujo de notificaciones desde ECIPayment hacia observers

## 4. Errores Identificados

1. **Import incorrecto** en `PaymentEventObserver`
    - Importaba `javax.management.Notification`
    - Debía importar clase propia

2. **Constructor mal parametrizado** en `PaymentMethod`
    - Recibía `transactionID` pero asignaba a `customerID`
    - Debía recibir `customerID`

3. **Falta interfaz `PaymentFactory`**
    - El código la usa pero no existe

4. **Métodos mal declarados**
    - `setAmount()` retornaba double, debía ser void
