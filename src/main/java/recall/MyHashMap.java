package recall;

import java.util.LinkedList;

/**
 * @Author SunnyJ
 * @Date 2022/5/23 21:22
 */

class Entry {
    Object key,value;
    public Entry(Object key, Object value) {
        super();
        this.key = key;this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
public class MyHashMap {
    Object[] table = new Object[999];
    private int size = 0;
    public void put(Object key, Object value) {
        if(key!=null) {
            int hash = key.hashCode()%table.length;
            hash=hash<0?-hash:hash;
            LinkedList list = null;
            Entry entry = null;
            if (table[hash] == null) {
                list = new LinkedList();
                entry = new Entry(key,value);
                list.add(entry);
            }else {
                list = (LinkedList) table[hash];
                for(int i=0;i<list.size();i++){
                    entry = (Entry) list.get(i);
                    if(entry.getKey().equals(key)){
                        entry.setKey(value);
                    }
                    if(entry.getValue() == null){
                        list.add(new Entry(key,value));
                    }
                }
            }
            table[hash] = list;
            size++;
        }
    }
    public Object get(Object key) {
        if(key!=null){
            int hash = key.hashCode()%table.length;
            hash=hash<0?-hash:hash;
            LinkedList list = null;
            if (table[hash] == null) {
                return null;
            }else {
                list = (LinkedList)table[hash];
                for(int i=0;i<list.size();i++) {
                    Entry entry = (Entry) list.get(i);
                    if(entry.getKey().equals(key)){
                        return entry.getValue();
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(2,"s");
        myHashMap.put(3,"j");
        System.out.println(myHashMap.size);
        System.out.println(myHashMap.get(2));
    }
}
