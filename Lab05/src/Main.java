import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main<Key, Value> extends UR_HashTable<Key, Value> {
    public Main(int cap) {
        super(cap);
    }
    public static void main(String[] args) {
        int n = 50;
        Main<Integer,String> hash = new Main<>(n);
        hash.put(26, "Sponge");
        hash.put(10, "Kerry");
        hash.put(2, "Santa");
        hash.put(45, "Blue");
        System.out.println(hash.size());
        System.out.println(hash.get(10));
        System.out.println(hash.contains(2));
        System.out.println(hash.contains(3));
        System.out.println(hash.isEmpty());
        hash.delete(2);
        System.out.println(hash.contains(2));
        System.out.println(hash.keys());
    }

    @Override
    public void put(Key key, Value val) {
        int bucket = hash(key);

        while(keys[bucket] != null){
            bucket = (bucket + 1) % m;
        }
        keys[bucket] = key;
        vals[bucket] = val;
        n++;
        System.out.println(bucket);
    }

    @Override
    public Value get(Key key) {
        int bucket = hash(key);

        while (keys[bucket] != null) {
            if (keys[bucket].equals(key)) {
                return vals[bucket];
            }
            bucket = (bucket + 1) % m;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        if(!contains(key)){
            return;
        }
        int bucket = hash(key);
        while (keys[bucket] != null) {
            if(keys[bucket].equals(key)){
                keys[bucket] = null;
                vals[bucket] = null;
                n--;
            }
            bucket = (bucket + 1) % m;
        }
        //rehash all the keys
        resize(n);
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Key key) {
        int bucket = hash(key);
        for(int i = bucket; keys[bucket] != null; i = (bucket + 1) % m){
            if(keys[bucket].equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();
        for(int i = 0; i < m; i++){
            if(keys[i] != null){
                list.add(keys[i]);
            }
        }
        return list;
    }
}