package chainOfResponsibility.handlers;

import chainOfResponsibility.Order;

public class AuthorizationHandler extends Handler {
    
    @Override
    public void handle(Order order) {
        if (order.getUserType().equals("admin")) {
            System.out.println("User is authorized");
            passToNext(order);
        } else {
            System.out.println("User is not authorized");
        }
    }
}
