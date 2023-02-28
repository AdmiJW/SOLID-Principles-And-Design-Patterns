package facade;

public class Form {
    private String name;
    private Object photo;
    private Object documents;

    public Form(String name, Object photo, Object documents) {
        this.name = name;
        this.photo = photo;
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public Object getPhoto() {
        return photo;
    }

    public Object getDocuments() {
        return documents;
    }
}
