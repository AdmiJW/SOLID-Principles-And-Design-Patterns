package templateMethod;

public abstract class BubbleSorter<T> {

    private T[] array;

    public BubbleSorter(T[] array) {
        this.array = array;
    }

    // Template method
    public void sortAndPrint() {
        sort(array);
        print();
    }


    // Optional method
    public void print() {
        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println();
    }

    // Optional method
    public void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (comparator(array[j], array[j + 1])) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Abstract method
    public abstract boolean comparator(T a, T b);

}
