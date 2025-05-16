package src.main.java.org.example;


public class Main {

static class  Entry<K, V>{
    K key;
    V value;
    Entry<K, V> next;

    Entry(K key, V value){
        this.key = key;
        this.value = value;
    }
}

public static class MyHashMap<K, V>{
    private Entry<K, V>[] breckets;
    private int size = 0;
    private static final int SIZE = 16;

    public MyHashMap(){
        breckets = new Entry[SIZE];
    }

    public void put(K key, V value){
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> entry = new Entry<>(key, value);

        if(breckets[index] == null){
            breckets[index] = entry;
        }else{
            Entry<K, V> current = breckets[index];
            while (current.next != null){
                if(current.key.equals(key)){
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = entry;
        }
        size++;
    }

    public V get(K key){
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> current = breckets[index];
        while (current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current=current.next;
        }
        return null;
    }

    public  void remove(K key){
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> current = breckets[index];
        Entry<K, V> prev = null;

        while (current != null){
            if(current.key.equals(key)){
                if(prev == null){
                    breckets[index] = current.next;
                }else{
                    prev.next = current.next;
                }
               size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }
    public int size(){
        return size;
    }

    // Метод для вывода всех элементов
    public void printAllEntries() {
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> current = breckets[i];
            while (current != null) {
                System.out.println("Key: " + current.key + ", Value: " + current.value);
                current = current.next;
            }
        }
    }



}






    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);

        map.put("aa", 11);
        map.put("bb", 22);
        map.put("cc", 33);
        map.put("dd", 44);
        map.put("ee", 55);

        System.out.println("Число элементов до удаления: " + map.size);

//        map.put("aaa", 111);
//        map.put("bbb", 222);
//        map.put("ccc", 333);
//        map.put("ddd", 444);
//        map.put("eee", 555);

//        map.put("aaaa", 1111);
//        map.put("bbbb", 2222);
//        map.put("cccc", 3333);
//        map.put("dddd", 4444);
//        map.put("eeee", 4555);

        //map.remove("a");


//        System.out.println(map.get("a"));
//        System.out.println(map.get("b"));
//        System.out.println(map.get("c"));
//        System.out.println(map.get("ddd"));
//        System.out.println(map.get("ccc"));
//        System.out.println(map.get("dddd"));
//        System.out.println(map.get("eeee"));

        map.remove("a");
        map.remove("c");

        System.out.println("Все элементы в HashMap:");
        map.printAllEntries();

        System.out.println("Число элементов после удаления: " + map.size);


//        map.put("a", 100);
//        System.out.println("a после перезаписи -> " + map.get("a")); // 100
//
//        map.remove("b");
//        System.out.println("b после удаления -> " + map.get("b")); // null
//
//        System.out.println("Размер: " + map.size()); // 1
    }
}