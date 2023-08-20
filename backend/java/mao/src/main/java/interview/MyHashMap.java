package interview;

public class MyHashMap<K, V> {
	private Entry<K, V>[] buckets;
	private static final int INTERNAL_CAPACITY = 1 << 4;
	private int size = 0;
	
	public MyHashMap(int capacity) {
		this.buckets = new Entry[capacity];
		//this.buckets = new Entry<K, V>[capacity];
		//https://www.tothenew.com/blog/why-is-generic-array-creation-not-allowed-in-java/
	}
	
	public void put(K key, V value) {
		Entry<K,V> entry = new Entry<>(key, value, null);
		int bucket = indexFor(hash(key.hashCode()),buckets.length);
		Entry<K,V> existing = buckets[bucket];
		if(existing == null) {
			buckets[bucket]=entry;
			size++;
		} else {
			while(existing.next!=null) {
				if(existing.key.equals(key)) { 
					existing.value = value;
					return;
				} else {
					existing = existing.next;
				}
			}
			if (existing.key.equals(key)) {
				existing.value = value;
			} else {
				existing.next = entry;
				size++;
			}
		}
	}
	
	static int hash(int h) {
	         // This function ensures that hashCodes that differ only by
	         // constant multiples at each bit position have a bounded
	         // number of collisions (approximately 8 at default load factor).
	         h ^= (h >>> 20) ^ (h >>> 12);
	         return h ^ (h >>> 7) ^ (h >>> 4);
	}
	
	public V get(K key) {
		int bucket = indexFor(hash(key.hashCode()),buckets.length);
		Entry<K,V> existing = buckets[bucket];
		while(existing.next!=null) {
			if(existing.key.equals(key)) {
				return existing.value;
			}
			existing = existing.next;
		}
		return null;
	}
	
	
	static int indexFor(int h, int length) { 
        return h & (length-1);  
    }
	
	class Entry<K, V>{
	    K key;
	    V value;
		Entry<K, V> next;
		public Entry(K key, V value, Entry<K,V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		public final int hashCode() {
			return(key==null?0:key.hashCode()) ^ (value==null?0:value.hashCode());
		}
		public final boolean equals(Object o) {
			if (!(o instanceof Entry)) {
				return false;
			}
			Entry e = (Entry)o;
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || k1 != null && k1.equals(k2)) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || v1 != null && v1.equals(v2)) {
					return true;
				}
			}
			return false;
		}
		public final K getKey() {
			return key;
		}
		public final V getValue() {
			return value;
		}
	}
	
}
