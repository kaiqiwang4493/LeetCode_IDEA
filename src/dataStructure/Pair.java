package dataStructure;

import java.io.ObjectOutputStream;

public class Pair<K, V> {
    Object k;
    Object v;

    public Pair(Object k, Object v){
        this.k = k;
        this.v = v;
    }

    public Object getK() {
        return k;
    }

    public Object getV() {
        return v;
    }
}
