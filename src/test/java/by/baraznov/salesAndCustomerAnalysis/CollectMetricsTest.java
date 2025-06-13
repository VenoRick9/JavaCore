package by.baraznov.salesAndCustomerAnalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CollectMetricsTest {
    private static List<Order> orders;
    private CollectMetrics collectMetrics = new CollectMetrics();
    @BeforeAll
    static void setUp() {
        orders = new ArrayList<>();

        orders.add(new Order(
                "ORD001",
                LocalDate.of(2025, 6, 1),
                new Customer("CUST001", "Alice Smith", "alice@example.com", LocalDateTime.of(2023, 5, 10, 10, 30), 28, "Paris"),
                List.of(
                        new OrderItem("Laptop", 1, 1200.00, Category.ELECTRONICS),
                        new OrderItem("Mouse", 2, 25.00, Category.ELECTRONICS)
                ),
                OrderStatus.NEW
        ));

        orders.add(new Order(
                "ORD002",
                LocalDate.of(2025, 5, 30),
                new Customer("CUST002", "Bob Johnson", "bob@example.com", LocalDateTime.of(2022, 9, 18, 15, 0), 34, "London"),
                List.of(
                        new OrderItem("T-shirt", 3, 20.00, Category.CLOTHING)
                ),
                OrderStatus.PROCESSING
        ));

        orders.add(new Order(
                "ORD003",
                LocalDate.of(2025, 5, 28),
                new Customer("CUST003", "Carol White", "carol@example.com", LocalDateTime.of(2021, 11, 5, 9, 15), 41, "Paris"),
                List.of(
                        new OrderItem("Book: Java 17", 1, 45.50, Category.BOOKS),
                        new OrderItem("Board Game", 1, 50.00, Category.TOYS)
                ),
                OrderStatus.SHIPPED
        ));

        orders.add(new Order(
                "ORD004",
                LocalDate.of(2025, 6, 2),
                new Customer("CUST006", "David Lee", "david@example.com", LocalDateTime.of(2024, 1, 20, 13, 40), 22, "Tokyo"),
                List.of(
                        new OrderItem("Smartphone", 1, 900.00, Category.ELECTRONICS),
                        new OrderItem("Case", 1, 15.00, Category.ELECTRONICS),
                        new OrderItem("Board Game", 1, 50.00, Category.TOYS)
                ),
                OrderStatus.NEW
        ));

        orders.add(new Order(
                "ORD005",
                LocalDate.of(2025, 5, 25),
                new Customer("CUST005", "Eva Green", "eva@example.com", LocalDateTime.of(2022, 7, 9, 8, 0), 30, "Paris"),
                List.of(
                        new OrderItem("Perfume", 2, 60.00, Category.BEAUTY),
                        new OrderItem("Toy Car", 1, 35.00, Category.TOYS),
                        new OrderItem("Board Game", 1, 50.00, Category.TOYS)
                ),
                OrderStatus.DELIVERED
        ));

        orders.add(new Order(
                "ORD006",
                LocalDate.of(2025, 5, 20),
                new Customer("CUST006", "Frank Martin", "frank@example.com", LocalDateTime.of(2023, 3, 2, 11, 45), 38, "Moscow"),
                List.of(
                        new OrderItem("Toy Car", 1, 35.00, Category.TOYS),
                        new OrderItem("Board Game", 1, 50.00, Category.TOYS)
                ),
                OrderStatus.SHIPPED
        ));

        orders.add(new Order(
                "ORD007",
                LocalDate.of(2025, 6, 3),
                new Customer("CUST006", "Grace Hall", "grace@example.com", LocalDateTime.of(2024, 5, 5, 10, 0), 27, "Rome"),
                List.of(
                        new OrderItem("Vacuum Cleaner", 1, 180.00, Category.HOME)
                ),
                OrderStatus.NEW
        ));

        orders.add(new Order(
                "ORD008",
                LocalDate.of(2025, 5, 27),
                new Customer("CUST006", "Henry Adams", "henry@example.com", LocalDateTime.of(2023, 9, 12, 14, 30), 31, "Paris"),
                List.of(
                        new OrderItem("Notebook", 5, 3.50, Category.BOOKS),
                        new OrderItem("Pen", 10, 1.00, Category.BOOKS),
                        new OrderItem("Camera", 1, 550.00, Category.ELECTRONICS),
                        new OrderItem("Toy Car", 1, 35.00, Category.TOYS),
                        new OrderItem("Board Game", 1, 50.00, Category.TOYS)
                ),
                OrderStatus.PROCESSING
        ));

        orders.add(new Order(
                "ORD009",
                LocalDate.of(2025, 5, 15),
                new Customer("CUST006", "Ivy Wilson", "ivy@example.com", LocalDateTime.of(2022, 12, 25, 17, 0), 36, "Madrid"),
                List.of(
                        new OrderItem("Dress", 2, 75.00, Category.CLOTHING)
                ),
                OrderStatus.CANCELLED
        ));

        orders.add(new Order(
                "ORD010",
                LocalDate.of(2025, 6, 5),
                new Customer("CUST006", "Jack Black", "jack@example.com", LocalDateTime.of(2021, 8, 18, 9, 20), 45, "Moscow"),
                List.of(
                        new OrderItem("Camera", 1, 550.00, Category.ELECTRONICS)
                ),
                OrderStatus.NEW
        ));

        orders.add(new Order(
                "ORD011",
                LocalDate.of(2025, 6, 6),
                new Customer("CUST005", "Karen Young", "karen@example.com", LocalDateTime.of(2022, 10, 1, 12, 30), 29, "Moscow"),
                List.of(
                        new OrderItem("Skincare Set", 1, 90.00, Category.BEAUTY)
                ),
                OrderStatus.PROCESSING
        ));

        orders.add(new Order(
                "ORD012",
                LocalDate.of(2025, 5, 22),
                new Customer("CUST005", "Liam Scott", "liam@example.com", LocalDateTime.of(2024, 2, 10, 15, 0), 24, "Minsk"),
                List.of(
                        new OrderItem("Clean Code", 1, 49.99, Category.BOOKS),
                        new OrderItem("Perfume", 1, 60.00, Category.BEAUTY)
                ),
                OrderStatus.DELIVERED
        ));

        orders.add(new Order(
                "ORD013",
                LocalDate.of(2025, 6, 7),
                new Customer("CUST005", "Mia Clark", "mia@example.com", LocalDateTime.of(2023, 4, 14, 10, 10), 33, "Moscow"),
                List.of(
                        new OrderItem("Hair Dryer", 1, 55.00, Category.HOME)
                ),
                OrderStatus.NEW
        ));

        orders.add(new Order(
                "ORD014",
                LocalDate.of(2025, 5, 18),
                new Customer("CUST005", "Noah King", "noah@example.com", LocalDateTime.of(2021, 6, 30, 11, 11), 39, "Minsk"),
                List.of(
                        new OrderItem("Sweater", 2, 40.00, Category.CLOTHING)
                ),
                OrderStatus.SHIPPED
        ));

        orders.add(new Order(
                "ORD015",
                LocalDate.of(2025, 6, 8),
                new Customer("CUST005", "Olivia Turner", "olivia@example.com", LocalDateTime.of(2023, 1, 8, 14, 14), 26, "Minsk"),
                List.of(
                        new OrderItem("Makeup Kit", 1, 65.00, Category.BEAUTY),
                        new OrderItem("Mirror", 1, 20.00, Category.HOME),
                        new OrderItem("Notebook", 5, 3.50, Category.BOOKS),
                        new OrderItem("Sweater", 2, 40.00, Category.CLOTHING),
                        new OrderItem("Pen", 10, 1.00, Category.BOOKS)
                ),
                OrderStatus.NEW
        ));
    }

    @Test
    void cityMetrics() {
        List<String> result = collectMetrics.cityMetrics(orders);
        assertEquals(4, result.size());
        assertTrue(result.contains("Rome"));
        assertFalse(result.contains("Berlin"));
        assertFalse(result.contains("Minsk"));
        assertEquals(List.of("Rome", "Tokyo", "Madrid", "London"),collectMetrics.cityMetrics(orders));
    }

    @Test
    void totalIncome() {
        double result = collectMetrics.totalIncome(orders);
        assertTrue(result > 0);
        assertEquals(314.99,result,0.001);
    }
    @Test
    void theMostPopularItem() {
        Map.Entry<String, Integer> result = collectMetrics.theMostPopularItem(orders);
        assertNotNull(result);
        assertEquals("Perfume", result.getKey());
        assertEquals(3, result.getValue());
    }

    @Test
    void averageCheck() {
        Map<String, Double> result = collectMetrics.averageCheck(orders);
        assertEquals(2, result.size());
        assertEquals(54.995, result.get("ORD012"), 0.001);
        assertEquals(51.25, result.get("ORD005"), 0.001);
    }

    @Test
    void moreThanFiveOrders() {
        List<Map.Entry<String, Long>> result = collectMetrics.moreThanFiveOrders(orders);
        assertEquals(2, result.size());
        assertEquals("CUST006", result.get(0).getKey());
        assertEquals(5L, result.get(0).getValue());
        assertEquals("CUST005", result.get(1).getKey());
        assertEquals(6L, result.get(1).getValue());
    }

}