package chainOfResponsibility.handlers;

import chainOfResponsibility.Order;

public abstract class Handler {
    private Handler next;

    public abstract void handle(Order order);

    public Handler setNext(Handler handler) {
        this.next = handler;
        return handler;
    }

    protected void passToNext(Order order) {
        if (next != null) next.handle(order);
    }
}
