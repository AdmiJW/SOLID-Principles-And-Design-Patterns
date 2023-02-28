package iterator;

import java.util.List;

public class MyList implements Iterable<Integer> {
    private List<Integer> list;

    public MyList(List<Integer> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public Integer get(int index) {
        return list.get(index);
    }

    @Override
    public Iterator<Integer> createIterator() {
        return new MyListIterator(this);
    }

    public Iterator<Integer> createReverseIterator() {
        return new MyListReverseIterator(this);
    }
}
