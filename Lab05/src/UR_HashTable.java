abstract public class UR_HashTable<Key,Value> {
    private static final int INIT_CAPACITY = 5 ;
    protected int n; // size of the data set
    protected int m ; // size of the hash table
    protected Key[] keys;
    Value[] vals;

    public UR_HashTable() {}

    public UR_HashTable(int cap) {
        n = 0;
        m = cap;
        //In Java, you can't create arrays of type parameters directly. Instead, you need to use type erasure, and you
        // can cast it to an Object array and then cast it back as needed.
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    abstract public void put (Key key, Value val) ;
    abstract public Value get (Key key) ;
    abstract public void delete(Key key) ;
    abstract public int size() ;
    abstract public boolean isEmpty() ;
    abstract public boolean contains(Key key);
    abstract public Iterable<Key> keys() ;

    // Useful helpers
    protected int hash(Key key) {
        if (key instanceof Integer) {
            int bucket = (int) key % m;
            return bucket;
        } else {
            return 0;
        }
    }

    // resizes the hash table to the given capacity by re-hashing all the keys
    protected void resize(int capacity){
        for(int i = 0; i < m; i++){
            if(keys[i] != null){
                int tempBucket = hash(keys[i]);
                Key tempKey = keys[i];
                Value tempVal = vals[i];
                keys[i] = null;
                vals[i] = null;
                keys[tempBucket] = tempKey;
                vals[tempBucket] = tempVal;
            }
        }
    }
}