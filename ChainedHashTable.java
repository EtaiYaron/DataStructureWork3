import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class ChainedHashTable<K, V> implements HashTable<K, V> {
    final static int DEFAULT_INIT_CAPACITY = 4;
    final static double DEFAULT_MAX_LOAD_FACTOR = 2;
    final private HashFactory<K> hashFactory;
    final private double maxLoadFactor;
    private int capacity;
    private HashFunctor<K> hashFunc;
    private List<Element<K,V>>[] table;

    /*
     * You should add additional private fields as needed.
     */

    public ChainedHashTable(HashFactory<K> hashFactory) {
        this(hashFactory, DEFAULT_INIT_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public ChainedHashTable(HashFactory<K> hashFactory, int k, double maxLoadFactor) {
        this.hashFactory = hashFactory;
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = 1 << k;
        this.hashFunc = hashFactory.pickHash(k);
        this.table = new List[this.capacity];

    }

    public V search(K key) {
        throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
    }

    public void insert(K key, V value) {
        throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
    }

    public boolean delete(K key) {
        throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
    }

    public HashFunctor<K> getHashFunc() {
        return hashFunc;
    }

    public int capacity() { return capacity; }
}
