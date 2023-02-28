package templateMethod;

public class AscIntBubbleSorter extends BubbleSorter<Integer> {
    
    public AscIntBubbleSorter(Integer[] array) {
        super(array);
    }

    @Override
    public boolean comparator(Integer a, Integer b) {
        return a > b;
    }
}