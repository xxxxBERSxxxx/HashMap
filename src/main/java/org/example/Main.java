package src.main.java.org.example;


public class Main {
  public static class SimpleHashMap<K, V>{
      private Entry[] buckets;
      private int size;

      public SimpleHashMap(){
          buckets = new Entry[16]; // Фикс. размер для простоты
          size = 0;
      }

      static class Entry{
          Object key;
          Object value;
          Object next;

          Entry(Object key, Object value){
              this.key = key;
              this.value = value;
          }
      }
      // Добавляю  пару ключ-значение
      public void put(K key, V value){
          int index = Math.abs(key.hashCode()) % buckets.length;
          Entry entry = buckets[index];

          // Поиск существующего ключа
         while (entry != null){
             if(entry.key.equals(key)){
                 entry.value = value;
                 return;
             }
             entry = (Entry) entry.next;
         }

        // Добавить новую запись в начало цепочки
         Entry newEntry = new Entry(key, value);
         newEntry.next = buckets[index];
         buckets[index] = newEntry;
         size++;
      }

      // Получить значение по ключу
     public V get(K key){
          int index = Math.abs(key.hashCode()) % buckets.length;
          Entry entry = buckets[index];

          while (entry != null){
              if(entry.key.equals(key)){
                  return (V) entry.value;
              }
              entry = (Entry) entry.next;
          }
          return null;
}
      // Удалить запись по ключу
    public void remove(K key){
          int index = Math.abs(key.hashCode()) % buckets.length;
          Entry entry = buckets[index];
          Entry prev = null;

          while (entry != null){
              if(entry.key.equals(key)){
                  if(prev == null){
                      buckets[index] = (Entry) entry.next;// Удалить первую запись
                  }else{
                      prev.next = entry.next;// Удалить из середины/конца
                  }
                  size--;
                  return;
              }
              prev = entry;
              entry = (Entry) entry.next;
          }
    }
public int size(){
          return size;
}


  }





    public static void main(String[] args) {
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        map.put("Яблоко", 100);
        map.put("Банан", 200);
        System.out.println(map.get("Яблоко")); // 100
        map.remove("Банан");
    }
}