import java.util.List;
import java.util.ArrayList;

public class MyDataStructure {
    /*
     * You may add any fields that you wish to add.
     * Remember that all the data-structures you use must be YOUR implementations,
     * except for the List and its implementation for the operation Range(low, high).
     */

    private ChainedHashTable<Integer ,Element<Integer, AbstractSkipList.SkipListNode>> table;
    private IndexableSkipList skipList;

    /***
     * This function is the Init function described in Part 4.
     *
     * @param N The maximal number of items that may reside in the DS.
     */
    public MyDataStructure(int N) {
        int k = (int) Math.round(Math.log(N) / Math.log(2));
        table = new ChainedHashTable<>(new ModularHash(), k, 1);
        skipList = new IndexableSkipList(0.5);
    }

    /*
     * In the following functions,
     * you should REMOVE the place-holder return statements.
     */
    public boolean insert(int value) {
        boolean ans = false;
        AbstractSkipList.SkipListNode node = skipList.insert(value);
        if (node != null)
            ans = true;
        table.insert(value, new Element<>(value, node));
        return ans;
    }

    public boolean delete(int value) {
        Element<Integer,AbstractSkipList.SkipListNode> element = table.search(value);
        if (!contains(value) || table.delete(value) || skipList.delete(element.satelliteData()))
            return false;
        return true;
    }

    public boolean contains(int value) {
        Element<Integer,AbstractSkipList.SkipListNode> element = table.search(value);
        return element != null;
    }

    public int rank(int value) {
        if (!contains(value))
            return -1;
        return skipList.rank(value);
    }

    public int select(int index) {
        return skipList.select(index);
    }

    public List<Integer> range(int low, int high) {
        if (!contains(low) || high < low)
            return null;
        ArrayList<Integer> list = new ArrayList<>();
        list.addLast(low);
        int index = skipList.rank(low);
        int selected = skipList.select(index+1);
        AbstractSkipList.SkipListNode skipListNode = table.search(selected).satelliteData();
        while (selected <= high) {
            list.addLast(selected);
            skipListNode = skipListNode.getNext(0);
            selected = skipListNode.key();
        }
        return list;
    }
}
