package com.shop;

import com.shop.adapter.ExternalPaymentGateway;
import com.shop.adapter.PaymentAdapter;
import com.shop.adapter.PaymentService;
import com.shop.chain.AddressValidator;
import com.shop.chain.BalanceValidator;
import com.shop.chain.OrderValidator;
import com.shop.chain.StockValidator;
import com.shop.decorator.Notifier;
import com.shop.decorator.NotifierFactory;
import com.shop.model.Customer;
import com.shop.model.Order;
import com.shop.model.OrderBuilder;
import com.shop.model.Product;
import com.shop.proxy.RealWarehouse;
import com.shop.proxy.Warehouse;
import com.shop.proxy.WarehouseProxy;
import com.shop.strategy.CourierDelivery;
import com.shop.strategy.DeliveryStrategy;
import com.shop.strategy.PickupDelivery;

public class App {

    public static void main(String[] args) {

        // -- PROXY: создаём склад через прокси --------------------------------
        System.out.println("=== PROXY: Запросы к складу ===");
        Warehouse warehouse = new WarehouseProxy(new RealWarehouse());
        Product laptop = warehouse.findProduct("Ноутбук");
        Product mouse = warehouse.findProduct("Мышь");
        warehouse.findProduct("Ноутбук");
        System.out.println();

        // -- BUILDER: строим заказ ---------------------------------------------
        System.out.println("=== BUILDER: Построение заказа ===");
        Customer customer = new Customer(
            "Иван Иванов", "ivan@example.com", "+79001234567",
            "Москва, ул. Ленина, д.1", 100_000
        );

        Order order = new OrderBuilder(customer, "Москва, ул. Ленина, д.1")
            .addProduct(laptop)
            .addProduct(mouse)
            .promoCode("SALE10")
            .comment("Позвонить перед доставкой")
            .build();

        System.out.println("  Заказ построен: " + order);
        System.out.println();

        // -- CHAIN OF RESPONSIBILITY: валидируем заказ -------------------------
        System.out.println("=== CHAIN OF RESPONSIBILITY: Валидация заказа ===");
        OrderValidator stockValidator = new StockValidator();
        OrderValidator balanceValidator = new BalanceValidator();
        OrderValidator addressValidator = new AddressValidator();

        stockValidator.setNext(balanceValidator);
        balanceValidator.setNext(addressValidator);

        boolean isValid = stockValidator.validate(order);
        System.out.println("  Результат валидации: " + (isValid ? "УСПЕХ" : "ОТКАЗ"));
        System.out.println();

        if (!isValid) return;

        // -- ADAPTER: оплата через внешнюю систему ----------------------------
        System.out.println("=== ADAPTER: Оплата заказа ===");
        PaymentService payment = new PaymentAdapter(new ExternalPaymentGateway());
        boolean paid = payment.pay(order);
        System.out.println("  Результат оплаты: " + (paid ? "УСПЕХ" : "ОТКАЗ"));
        System.out.println();

        // -- STRATEGY: выбираем способ доставки -------------------------------
        System.out.println("=== STRATEGY: Доставка заказа ===");
        DeliveryStrategy courier = new CourierDelivery();
        System.out.println("  Стоимость доставки курьером: " + courier.calculateCost(order) + " руб.");
        courier.deliver(order);

        DeliveryStrategy pickup = new PickupDelivery("ул. Тверская, д.5");
        System.out.println("  Стоимость самовывоза: " + pickup.calculateCost(order) + " руб.");
        pickup.deliver(order);
        System.out.println();

        // -- DECORATOR: уведомляем покупателя ---------------------------------
        System.out.println("=== DECORATOR: Уведомления покупателя ===");
        Notifier notifier = NotifierFactory.all();
        notifier.notify(order);
    }
}
