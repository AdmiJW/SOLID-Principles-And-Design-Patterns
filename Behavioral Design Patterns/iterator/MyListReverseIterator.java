package iterator;

public class MyListReverseIterator implements Iterator<Integer> {
    private MyList list;
    private int index;

    public MyListReverseIterator(MyList list) {
        this.list = list;
        index = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public Integer next() {
        return list.get(index--);
    }
}
