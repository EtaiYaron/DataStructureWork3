import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

public class ProbingHashTable<K, V> implements HashTable<K, V> {
    final static int DEFAULT_INIT_CAPACITY = 4;
    final static double DEFAULT_MAX_LOAD_FACTOR = 0.75;
    final private HashFactory<K> hashFactory;
    final private double maxLoadFactor;
    private int capacity;
    private HashFunctor<K> hashFunc;
    private Element<K,V>[] table;
    private double loadfactor;
    private int numofelements;
    private int k;
    private static final Element<Object, Object> DELETED = new Element<>(null, null);


    /*
     * You should add additional private fields as needed.
     */

    public ProbingHashTable(HashFactory<K> hashFactory, int k, double maxLoadFactor) {
        this.hashFactory = hashFactory;
        this.maxLoadFactor = maxLoadFactor;
        this.capacity = 1 << k;
        this.hashFunc = hashFactory.pickHash(k);
        this.table = new Element[capacity];
        loadfactor = 0;
        this.k = k;
        this.numofelements = 0;

    }
	
	public ProbingHashTable(HashFactory<K> hashFactory) {
        this(hashFactory, DEFAULT_INIT_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public V search(K key) {
        if (key == null) {
            return null;
        }
        int place = hashFunc.hash(key);
        for (int i = place; i < place + table.length; i++){
            if (table[i % table.length] == null)
                return null;
            else if (table[i % table.length] != DELETED && table[i % table.length].key().equals(key))
                return table[i % table.length].satelliteData();
        }
        return null;
    }

    public void insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("cannot insert with null key");
        }
        V data = search(key);
        if (data != null)
            delete(key);
        if (loadfactor >= maxLoadFactor)
            ReHash();
        Element<K, V> element = new Element<>(key, value);
        int place = hashFunc.hash(key);
        for (int i = place; i < place + table.length; i++){
            if (table[i % table.length] == null || table[i % table.length] == DELETED) {
                table[i % table.length] = element;
                numofelements++;
                loadfactor = (double) numofelements/this.capacity;
                return;
            }
        }
    }

    private void ReHash(){
        k++;
        this.capacity = 1 << k;
        Element<K,V>[] temp = new Element[this.capacity];
        HashFunctor<K> hashFunc1 = hashFactory.pickHash(k);
        for (int i = 0; i < this.table.length; i++){
            if (table[i] != null && table[i] != DELETED) {
                int place = hashFunc1.hash(table[i].key());
                for (int j = place; j < place + temp.length; j++) {
                    if (temp[j % temp.length] == null) {
                        temp[j % temp.length] = table[i];
                        break;
                    }
                }
            }
        }
        this.hashFunc = hashFunc1;
        table = temp;
        loadfactor = (double) numofelements / capacity;
    }

    public boolean delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("cannot insert with null key");
        }
        int place = hashFunc.hash(key);
        for (int i = place; i < place + table.length; i++) {
            Element<K, V> e = table[i % table.length];
            if (e == null) break;
            if (e != DELETED && e.key().equals(key)) {
                table[i % table.length] = (Element<K, V>) DELETED;
                numofelements--;
                loadfactor = (double) numofelements / capacity;
                return true;
            }
        }
        return false;
    }

    public HashFunctor<K> getHashFunc() {
        return hashFunc;
    }

    public int capacity() { return capacity; }
}
