package chainOfResponsibility;

public class Order {
    private String user;
    private String password;
    private String userType;
    private int orderId;

    public Order(String user, String password, String userType, int orderId) {
        this.user = user;
        this.password = password;
        this.userType = userType;
        this.orderId = orderId;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public int getOrderId() {
        return orderId;
    }
}
