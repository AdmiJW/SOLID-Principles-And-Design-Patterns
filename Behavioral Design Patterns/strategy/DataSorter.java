package strategy;



public class DataSorter {
    private SortStrategy sortStrategy;

    public DataSorter(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(int[] data) {
        sortStrategy.sort(data);
    }
}