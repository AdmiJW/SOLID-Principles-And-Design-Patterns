package strategy;

public class InsertionSortStrategy implements SortStrategy {
    
    @Override
    public void sort(int[] data) {
        System.out.println("Sorting using insertion sort strategy");
        
        for (int i = 1; i < data.length; i++) {
            int j = i;
            while (j > 0 && data[j] < data[j - 1]) {
                int temp = data[j];
                data[j] = data[j - 1];
                data[j - 1] = temp;
                j--;
            }
        }
    }
}
