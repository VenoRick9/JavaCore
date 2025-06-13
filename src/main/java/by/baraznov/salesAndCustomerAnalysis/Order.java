package by.baraznov.salesAndCustomerAnalysis;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    private String orderId;
    private LocalDate orderDate;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, LocalDate orderDate, Customer customer, List<OrderItem> items, OrderStatus status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.items = items;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(customer, order.customer) && Objects.equals(items, order.items) && status == order.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(orderId);
        result = 31 * result + Objects.hashCode(orderDate);
        result = 31 * result + Objects.hashCode(customer);
        result = 31 * result + Objects.hashCode(items);
        result = 31 * result + Objects.hashCode(status);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", items=" + items +
                ", status=" + status +
                '}';
    }
}

