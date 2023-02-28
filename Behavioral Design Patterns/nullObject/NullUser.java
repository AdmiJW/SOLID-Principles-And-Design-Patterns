package nullObject;

public class NullUser implements IUser {

    @Override
    public String getUsername() {
        return "Not logged in";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
