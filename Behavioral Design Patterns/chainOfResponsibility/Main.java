package chainOfResponsibility;

import chainOfResponsibility.handlers.AuthorizationHandler;
import chainOfResponsibility.handlers.Handler;
import chainOfResponsibility.handlers.LoginHandler;
import chainOfResponsibility.handlers.OrderValidationHandler;
import chainOfResponsibility.handlers.PlaceOrderHandler;

public class Main {
    public static void main(String[] args) {
        Order order = new Order("user", "password", "admin", 1);
        
        Handler orderHandler = new LoginHandler();
        orderHandler.setNext(new AuthorizationHandler())
            .setNext(new OrderValidationHandler())
            .setNext(new PlaceOrderHandler());

        orderHandler.handle(order);

        Order order2 = new Order("user", "password", "admin", 1001);
        orderHandler.handle(order2);
    }
}
