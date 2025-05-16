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
    private double loadfactor;
    private int k;

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
        loadfactor = 0;
        k = k;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private void ReHash() {
        loadfactor /= 2;
        k++;
        this.capacity = 1 << k;
        List<Element<K,V>>[] temp = new List[this.capacity];
        HashFunctor<K> hashFunc1 = hashFactory.pickHash(k);
        for (int i = 0; i < this.table.length; i++){
            List<Element<K,V>> list = table[i];
            for (Element<K,V> element : list){
                temp[hashFunc1.hash(element.key())].add(element);
            }
        }
        this.hashFunc = hashFunc1;
        table = temp;
    }

    public V search(K key) {
        if (key == null) {
            return null;
        }
        int place = hashFunc.hash(key);
        List<Element<K,V>> list = table[place];
        for (Element<K,V> element : list){
            if (element.key() == key)
                return element.satelliteData();
        }
        return null;
    }

    public void insert(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("cannot insert with null key");
        }
        if (loadfactor >= maxLoadFactor)
            ReHash();
        Element<K, V> element = new Element<>(key, value);
        int place = hashFunc.hash(key);
        table[place].add(element);
        loadfactor += (1/this.capacity);
    }

    public boolean delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("cannot insert with null key");
        }
        int place = hashFunc.hash(key);
        boolean ans = table[place].remove(search(key));
        if (ans)
            loadfactor -= (1/this.capacity);
        return ans;

    }

    public HashFunctor<K> getHashFunc() {
        return hashFunc;
    }

    public int capacity() { return capacity; }
}
