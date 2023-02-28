package strategy;


public class BubbleSortStrategy implements SortStrategy {

    @Override
    public void sort(int[] data) {
        System.out.println("Sorting using bubble sort strategy");

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }
}