package bridge;

import bridge.abstractions.DetailedView;
import bridge.abstractions.SimpleView;
import bridge.abstractions.View;
import bridge.implementations.BookResource;
import bridge.implementations.MovieResource;
import bridge.implementations.Resource;

public class Main {
    public static void main(String[] args) {
        Resource book = new BookResource();
        Resource movie = new MovieResource();
        
        View v1 = new SimpleView(book);
        v1.show();
        
        v1 = new DetailedView(book);
        v1.show();

        v1 = new SimpleView(movie);
        v1.show();

        v1 = new DetailedView(movie);
        v1.show();
    }
}
