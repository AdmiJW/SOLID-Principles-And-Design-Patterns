package strategy;



public class Main {
    
    public static void main(String[] args) {
        int[] data = { 1, 5, 4, 3, 2, 8 };
        DataSorter sorter = new DataSorter(new InsertionSortStrategy());
        sorter.sort(data);

        for (int i = 0; i < data.length; i++)
            System.out.print(data[i]);
        System.out.println();
        
        int[] data2 = { 1, 5, 4, 3, 2, 8 };
        sorter.setSortStrategy( new BubbleSortStrategy() );
        sorter.sort(data2);

        for (int i = 0; i < data.length; i++)
            System.out.print(data[i]);
        System.out.println();
    }    
}
