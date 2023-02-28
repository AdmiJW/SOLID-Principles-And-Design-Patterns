package chainOfResponsibility.handlers;

import chainOfResponsibility.Order;

public class LoginHandler extends Handler {
    
    @Override
    public void handle(Order order) {
        if (order.getUser().equals("user") && order.getPassword().equals("password")) {
            System.out.println("User is logged in");
            passToNext(order);
        } else {
            System.out.println("User is not logged in");
        }
    }
}
