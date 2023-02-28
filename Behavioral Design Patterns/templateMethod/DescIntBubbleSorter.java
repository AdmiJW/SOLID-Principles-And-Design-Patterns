package templateMethod;


public class DescIntBubbleSorter extends BubbleSorter<Integer> {
    
    public DescIntBubbleSorter(Integer[] array) {
        super(array);
    }

    @Override
    public boolean comparator(Integer a, Integer b) {
        return a < b;
    }
}
