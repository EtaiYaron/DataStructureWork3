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
    private int numofelemets;
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
        numofelemets = 0;
        loadfactor = 0;
        this.k = k;
        for (int i = 0; i < this.capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private void ReHash() {
        k++;
        this.capacity = 1 << k;
        List<Element<K,V>>[] temp = new List[this.capacity];
        for (int i = 0; i < this.capacity; i++) {
            temp[i] = new LinkedList<>();
        }
        HashFunctor<K> hashFunc1 = hashFactory.pickHash(k);
        for (int i = 0; i < this.table.length; i++){
            List<Element<K,V>> list = table[i];
            for (Element<K,V> element : list){
                temp[hashFunc1.hash(element.key())].add(element);
            }
        }
        this.hashFunc = hashFunc1;
        table = temp;
        loadfactor = (double) numofelemets / capacity;
    }

    public V search(K key) {
        if (key == null) {
            return null;
        }
        int place = hashFunc.hash(key);
        List<Element<K,V>> list = table[place];
        for (Element<K,V> element : list){
            if (element.key().equals(key))
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
        if (search(key) != null)
            return;
        Element<K, V> element = new Element<>(key, value);
        int place = hashFunc.hash(key);
        table[place].add(element);
        numofelemets++;
        loadfactor = (double) numofelemets / this.capacity;
    }

    public boolean delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("cannot insert with null key");
        }
        int place = hashFunc.hash(key);
        List<Element<K, V>> list = table[place];
        for (Element<K, V> element: list){
            if (element.key().equals(key)) {
                list.remove(element);
                numofelemets--;
                loadfactor = (double) numofelemets / capacity;
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
