package bridge.implementations;

public class MovieResource implements Resource {

    private String title = "Movie Title";
    private String shortDescription = "This is a Movie";
    private String longDescription = "This is a much longer description for a movie. The movie is created by AdmiJW in year 2021 and it is award winning";

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