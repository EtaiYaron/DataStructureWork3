import java.util.List;
import java.util.ArrayList;

public class MyDataStructure {
    /*
     * You may add any fields that you wish to add.
     * Remember that all the data-structures you use must be YOUR implementations,
     * except for the List and its implementation for the operation Range(low, high).
     */

    private ChainedHashTable<Integer ,AbstractSkipList.SkipListNode> table;
    private IndexableSkipList skipList;

    /***
     * This function is the Init function described in Part 4.
     *
     * @param N The maximal number of items that may reside in the DS.
     */


    /*
    Hash table allocates ~N buckets (capacity = 2^k ≈ N) theta of N.
    Skip list initialization is theta of 1.
    therefore we get theta of N.
     */

    public MyDataStructure(int N) {
        int k = (int) Math.round(Math.log(N) / Math.log(2));
        table = new ChainedHashTable<>(new ModularHash(), k, 1);
        skipList = new IndexableSkipList(0.5);
    }

    /*
     indexable Skip list insert is theta of log n expected.
     hash table insert/search is theta of 1 expected.
     therefore we get theta of log n.
     */

    public boolean insert(int value) {
        AbstractSkipList.SkipListNode node = skipList.insert(value);
        if (node == null)
            return false;
        table.insert(value, node);
        return true;
    }

    /*
     indexable Skip list delete is theta of log n expected.
     hash table delete/search is theta of 1 expected.
     therefore we get theta of log n expected.
     */

    public boolean delete(int value) {
        AbstractSkipList.SkipListNode node = table.search(value);
        if (node == null)
            return false;
        boolean skipDeleted = skipList.delete(node);
        boolean tableDeleted = table.delete(value);
        return skipDeleted && tableDeleted;
    }

    /*
     hash table search is theta of 1 expected.
     therefore we get theta of 1 expected.
     */
    public boolean contains(int value) {
        AbstractSkipList.SkipListNode node = table.search(value);
        return node != null;
    }

    /*
     indexable Skip list rank is theta of log n expected.
     therefore we get theta of log n expected.
     */
    public int rank(int value) {
        if (!contains(value))
            return -1;
        return skipList.rank(value);
    }

    /*
     indexable Skip list rank is theta of log n expected.
     therefore we get theta of log n expected.
     */
    public int select(int index) {
        return skipList.select(index);
    }

    /*
    contains is theta of 1 expected. initialization of list i theta of 1.
    hash table search is theta of 1 expected. the while loop is theta of L
    Where L is the output size. Each step is a skip list next pointer traversal.
    therefore we get theta of L expected.
    */
    public List<Integer> range(int low, int high) {
        if (!contains(low) || high < low)
            return null;
        ArrayList<Integer> list = new ArrayList<>();
        AbstractSkipList.SkipListNode skipListNode = table.search(low);
        while (skipListNode != null && skipListNode.key() <= high) {
            list.add(skipListNode.key());
            skipListNode = skipListNode.getNext(0);
        }
        return list;
    }
}
