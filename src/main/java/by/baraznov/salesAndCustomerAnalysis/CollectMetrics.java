package by.baraznov.salesAndCustomerAnalysis;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectMetrics {

    public List<String> cityMetrics(List<Order> orders) {
        return orders.stream().map(c -> c.getCustomer().getCity())
                .collect(Collectors.groupingBy( c -> c, Collectors.counting()))
                .entrySet().stream().filter(c -> c.getValue() == 1)
                .map(Map.Entry :: getKey).collect(Collectors.toList());
    }
    public double totalIncome(List<Order> orders) {
        return orders.stream().filter(o -> o.getStatus().equals(OrderStatus.DELIVERED))
                .flatMap(o -> o.getItems().stream())
                .map(o -> o.getPrice() * o.getQuantity()).reduce(0.0, Double::sum);
    }
    public Map.Entry<String, Integer> theMostPopularItem(List<Order> orders) {
        return orders.stream().filter(o -> o.getStatus().equals(OrderStatus.DELIVERED))
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElse(null);
    }
    public Map<String, Double> averageCheck(List<Order> orders) {
        return orders.stream().filter(o -> o.getStatus().equals(OrderStatus.DELIVERED))
                .collect(Collectors.toMap(
                        Order::getOrderId,
                        order -> {
                            List<OrderItem> items = order.getItems();
                            int totalQuantity = items.stream()
                                    .map(OrderItem::getQuantity)
                                    .reduce(0, Integer::sum);
                            double totalSum = items.stream()
                                    .map(o -> o.getPrice() * o.getQuantity())
                                    .reduce(0.0, Double::sum);
                            return totalQuantity == 0 ? 0.0 : totalSum / totalQuantity;
                        }
                ));
    }
    public List<Map.Entry<String, Long>> moreThanFiveOrders(List<Order> orders) {
        return orders.stream().filter(o -> !o.getStatus().equals(OrderStatus.CANCELLED))
                .map(o -> o.getCustomer().getCustomerId())
                .collect(Collectors.groupingBy( c -> c, Collectors.counting()))
                .entrySet().stream().filter( c -> c.getValue() >= 5)
                .collect(Collectors.toList());
    }



}
