package bridge.implementations;

public class BookResource implements Resource {
    private String title = "Book Title";
    private String shortDescription = "This is a Book";
    private String longDescription = "This is a much longer description for a book. The book is created by AdmiJW in year 2021 and it is award winning";
    
    @Override
    public String getTitle() {
        return title;
    }
    
    @Override
    public String getShortDescription() {
        return shortDescription;
    }
    
    @Override
    public String getLongDescription() {
        return longDescription;
    }
}