package chainOfResponsibility.handlers;

import chainOfResponsibility.Order;

public class OrderValidationHandler extends Handler {
    
    @Override
    public void handle(Order order) {
        if (order.getOrderId() < 1000) {
            System.out.println("Order is valid");
            passToNext(order);
        } else {
            System.out.println("Order is not valid");
        }
    }
    
}
