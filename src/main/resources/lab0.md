# SOLID Principles

## Objectives:
- Get familiar with the SOLID Principles.
- Implement 2 SOLID letters in a simple project.

## Used SOLID Principles:

### 2. **Open/Closed Principle (OCP):**
The `OrderService` class is open for extension but closed for modification. By using the `PaymentService` interface, we can add new payment methods (e.g., `PaypalPaymentImpl`, `P2pPaymentImpl`) without changing existing code.

### 3. **Liskov Substitution Principle (LSP):**
The `OrderService` class can use any implementation of `PaymentService` (e.g., `PaypalPaymentImpl`, `P2pPaymentImpl`) without knowing the specifics of the payment type, ensuring interchangeable behavior.

### 4. **Dependency Inversion Principle (DIP):**
The `OrderService` depends on the abstraction (`PaymentService` interface) rather than concrete implementations, allowing flexibility in choosing different payment methods.

## Implementation

This implementation focuses on a payment processing system that adheres to the SOLID principles to enhance maintainability and flexibility. The implementation includes:

- **OCP** by allowing `OrderService` to extend with new payment methods without modifying its core logic.
- **LSP** by ensuring that all implementations of `PaymentService` are interchangeable.
- **DIP** by depending on abstractions (`PaymentService`) rather than concrete implementations.

### Code Snippets:

```java
public interface PaymentService {

    void pay(BigDecimal amount);

}
```
```java
public class PaypalPaymentImpl implements PaymentService {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Paying " + amount + " using Paypal");
    }
}
```

```java
public class P2pPaymentImpl implements PaymentService {

   @Override
   public void pay(BigDecimal amount) {
      System.out.println("Paying " + amount + " using P2P");
   }
}
```

```java
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    public void processOrder(Order order) {
        amount = ....
        paymentService.pay(amount);
    }

}
```

```java
 public static void main(String[] args) {
        OrderService orderService1 = new OrderService(new PaypalPaymentImpl());
        System.out.println("Processing order with Paypal payment");
        orderService1.processOrder(OrderUtils.generateOrder());

        OrderService orderService2 = new OrderService(new P2pPaymentImpl());
        System.out.println("Processing order with P2P payment");
        orderService2.processOrder(OrderUtils.generateOrder());
    }
```
## Conclusions / Screenshots / Results

- The **Open/Closed Principle (OCP)** allows for easy extension of the payment system by simply adding new payment classes without modifying existing code.
- The **Liskov Substitution Principle (LSP)** guarantees that any payment service can be used interchangeably in `OrderService` without affecting correctness.
- The **Dependency Inversion Principle (DIP)** allows `OrderService` to depend on abstractions rather than concrete implementations, enhancing flexibility.

### Example Output:

1. **Processing order with Paypal payment:**
    - Output: `"Paying XXX using Paypal"`

2. **Processing order with P2P payment:**
    - Output: `"Paying XXX using P2P"`

## Conclusion

The implementation successfully demonstrates adherence to the SOLID principles, resulting in a maintainable, flexible, and extendable system.
