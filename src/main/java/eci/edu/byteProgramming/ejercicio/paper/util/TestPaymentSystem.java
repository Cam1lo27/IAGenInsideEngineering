package eci.edu.byteProgramming.ejercicio.paper.util;

public class TestPaymentSystem {
    public static void main(String[] args) {
        // Configurar módulos
        Inventory inventory = new Inventory();
        Facturation facturation = new Facturation();
        Notification notification = new Notification();

        // Configurar sistema de pagos con Observer
        ECIPayment eciPayment = new ECIPayment();
        PaymentEventObserver observer = new PaymentEventObserver(inventory, facturation, notification);
        eciPayment.addObserver(observer);

        System.out.println("=".repeat(60));
        System.out.println("🏪 SISTEMA DE PAGOS - TIENDA VIRTUAL");
        System.out.println("=".repeat(60));

        // TEST 1: Pago con tarjeta de crédito
        System.out.println("\n🧪 TEST 1: Credit Card Payment");
        System.out.println("-".repeat(60));
        PaymentFactory creditCardFactory = new CreditCardPaymentFactory(
                "4532015112830366",
                "John Doe",
                "12/25",
                "123",
                "123 Main St, NY"
        );

        eciPayment.processPayment(
                creditCardFactory,
                1200.00,
                "CUST001",
                "Gaming Laptop Purchase",
                "John Doe",
                "john.doe@email.com",
                "LAPTOP001"
        );

        // TEST 2: Pago con PayPal
        System.out.println("\n\n🧪 TEST 2: PayPal Payment");
        System.out.println("-".repeat(60));
        PaymentFactory paypalFactory = new PaypalPaymentFactory(
                "jane.smith@email.com",
                "AUTH_TOKEN_12345ABCDE"
        );

        eciPayment.processPayment(
                paypalFactory,
                800.00,
                "CUST002",
                "Smartphone Purchase",
                "Jane Smith",
                "jane.smith@email.com",
                "PHONE001"
        );

        // TEST 3: Pago con Criptomoneda
        System.out.println("\n\n🧪 TEST 3: Cryptocurrency Payment");
        System.out.println("-".repeat(60));
        PaymentFactory cryptoFactory = new CryptoPaymentFactory(
                "0x742d35Cc6634C0532925a3b844Bc9e7595f0bEb",
                "ETHEREUM",
                5.5
        );

        eciPayment.processPayment(
                cryptoFactory,
                45.99,
                "CUST003",
                "Java Programming Book",
                "Alice Johnson",
                "alice.j@email.com",
                "BOOK001"
        );

        // TEST 4: Pago fallido (tarjeta inválida)
        System.out.println("\n\n🧪 TEST 4: Failed Payment (Invalid Card)");
        System.out.println("-".repeat(60));
        PaymentFactory invalidCardFactory = new CreditCardPaymentFactory(
                "123", // CVV muy corto - fallará
                "Bob Wilson",
                "12/25",
                "12",
                "456 Oak Ave"
        );

        eciPayment.processPayment(
                invalidCardFactory,
                100.00,
                "CUST004",
                "Test Purchase",
                "Bob Wilson",
                "bob.wilson@email.com",
                "LAPTOP001"
        );

        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ PRUEBAS COMPLETADAS");
        System.out.println("=".repeat(60));
    }
}