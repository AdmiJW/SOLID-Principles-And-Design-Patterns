package chainOfResponsibility.handlers;

import chainOfResponsibility.Order;

public class PlaceOrderHandler extends Handler {
    
    @Override
    public void handle(Order order) {
        System.out.println("Order successfully placed");
    }
    
}
