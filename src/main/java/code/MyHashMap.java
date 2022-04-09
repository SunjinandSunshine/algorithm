package code;

import java.util.LinkedList;

/**
 * @Author SunnyJ
 * @Date 2022/2/24 10:47 上午
 */
class Entry{
    Object key,value;
    public Entry(Object key,Object value){
        super();
        this.key = key;
        this.value = value;
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
class  MyHashMap {
    private int size;
    private Object[] table = new Object[999];

    public void put(Object key, Object value) {
        if (key != null) {
            int hash = key.hashCode() % table.length;
            hash = hash < 0 ? -hash : hash;
            LinkedList list = null;
            Entry entry = null;
            if (table[hash] == null) {
                list = new LinkedList();
                entry = new Entry(key, value);
                list.add(entry);
            } else {
                list = (LinkedList) table[hash];
                for (int i = 0; i < list.size(); i++) {
                    entry = (Entry) list.get(i);
                    if (entry.getKey().equals(key)) {
                        entry.setValue(value);
                    }
                    if (entry.getValue() == null) {
                        list.add(new Entry(key, value));
                    }
                }
            }
            table[hash] = list;
            size++;
        }
    }

    public Object get(Object key) {
        if (key != null) {
            int hash = key.hashCode() % table.length;
            hash = hash < 0 ? -hash : hash;
            LinkedList list = null;
            if (table[hash] == null) {
                return null;
            } else {
                list = (LinkedList) table[hash];
                for (int i = 0; i < list.size(); i++) {
                    Entry entry = (Entry) list.get(i);
                    if (entry.getKey().equals(key)) {
                        return entry.getValue();
                    }
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }
}
