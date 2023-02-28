package nullObject;

public class User implements IUser {
    private String username;
    private String description;
    
    public User(String username, String description) {
        this.username = username;
        this.description = description;
    }
    
    @Override
    public String getUsername() {
        return username;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
