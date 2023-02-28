package iterator;

public class MyListIterator implements Iterator<Integer> {
    private MyList list;
    private int index;

    public MyListIterator(MyList list) {
        this.list = list;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public Integer next() {
        return list.get(index++);
    }
}
