package templateMethod;

public class Main {
        
    public static void main(String[] args) {
        Integer[] array = { 1, 3, 2, 5, 4 };
        BubbleSorter<Integer> sorter = new DescIntBubbleSorter(array);
        sorter.sortAndPrint();

        sorter = new AscIntBubbleSorter(array);
        sorter.sortAndPrint();
    }    
}
