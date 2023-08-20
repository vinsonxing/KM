package interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapAPITest {
	private static Map<String, Employee> map1 = new HashMap<>();
	private static Map<String, Employee> map2 = new HashMap<>();
	
	public static void main(String[] args) {
//		Map<String, List<String>> map = new HashMap<>();
//		map.computeIfAbsent("key1", k->new ArrayList<>()).add("value1");
//		map.computeIfAbsent("key1", k->new ArrayList<>()).add("value2");
//		System.out.println(map.get("key1").get(0));
//		System.out.println(map.get("key1").get(1));
//		
//		Map<String, String> map1 = new HashMap<>(); 
//	    String rtnVal = map1.put("key1", "val1");
//	    System.out.println(rtnVal);
//	    Set<String> keyset = map1.keySet();
//	    Collection<String> values = map1.values();
//	    Set<Entry<String,String>> entrySet = map1.entrySet();
	    
//	    Map<String, String> map3 = new HashMap<>();
//	    map3.put("name", "baeldung");
//	    map3.put("type", "blog");
//
//	    Set<String> keys = map3.keySet();
//	    Iterator<String> it = keys.iterator();
//	    map3.put("name", "updated");
//	    while (it.hasNext()) {
//	        String key = it.next();
//	    }
	    
//	    Map<String, String> emptyMap = Collections.emptyMap();
//	    emptyMap.put("key", "value");
//		Key k1 = new Key("k1");
//		Key k2 = new Key("k2");
//		Key k3 = new Key("k3");
//		Map<Key, String> mapUsingJava9 = Map.of(k1,"v1",k2,"v2",k3,"v3");
//		//mapUsingJava9.put("k1", "v3");
//		k1.setName("k1_new");
//		Set<Key> keys = mapUsingJava9.keySet();
//		Iterator<Key> it = keys.iterator();
//		while(it.hasNext()) {
//			Key key = it.next();
//			String value = mapUsingJava9.get(key);
//			System.out.println(key.getName() + ":" + value);
//		}
		
//		Map<String, String> mapUsingJava9 = Map.of("k1","v1","k2","v2","k3","v1");
//		List<String> res = new ArrayList<String>();
//	    System.out.println(keys(mapUsingJava9, "v1"));
		
		List<Integer> listDuplicate = Arrays.asList(1,2,3,3,4,4,5);
		List<Integer> duplicates = listDuplicateUsingMapAndCollectorsGroupingBy(listDuplicate);
		System.out.print(duplicates);
		
		
//		Map<String, String> asiaCity1 = new HashMap<String, String>();
//	    asiaCity1.put("k1", "v1");
//	    asiaCity1.put("k2", "v2");
//
//	    Map<String, String> asiaCity2 = new HashMap<String, String>();
//	    asiaCity2.put("k1", "v1");
//	    asiaCity2.put("k2", "v2");
//	    System.out.println(asiaCity1.equals(asiaCity2));
	    
//	    String[] a1 = new String[] { "Tokyo", "Osaka" };
//	    String[] a2 = new String[] { "Tokyo", "Osaka" };
//	    System.out.println(a1.equals(a2));
//	    System.out.println(Arrays.equals(a1, a2));
	    
	    Map<String, Employee> map3 = map2.entrySet()
	    		  .stream()
	    		  .collect(Collectors.toMap(
	    		    Map.Entry::getKey,
	    		    Map.Entry::getValue,
	    		    (v1, v2) -> new Employee(v1.getId(), v2.getName())));
//	    		    () -> new HashMap<>(map1)));
	    
	    LinkedHashMap<Integer, String> map = new MyLinkedHashMap<>(16, .75f, true);
	    map.put(1, null);
	    map.put(2, null);
	    map.put(3, null);
	    map.put(4, null);
	    map.put(5, null);
	    map.get(3);
	    map.entrySet().forEach(System.out::println);

	}
	
	//The advantage of returning a stream is that it can cater to a wide range of client needs. The calling code may require only one key or all the keys pointing to the supplied value. As the evaluation of a stream is lazy, the client can control the number of iteration based on its requirement.
	static public <K,V> List<K> keys(Map<K,V> map, V value){
		return map.entrySet()
				.stream()
				.filter(entry->value.equals(entry.getValue()))
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}	
	
	static public List<Integer> listDuplicateUsingMapAndCollectorsGroupingBy(List<Integer> list) {
	    List<Integer> duplicates = new ArrayList<>();
//	    Map<Integer, Long> test = list.stream()
//	      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	   // Collector<Integer, ?, Map<Integer, Long>> test1 = Collectors.groupingBy(Function.identity(), Collectors.counting());
	    Set<Integer> set = list.stream()
	      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
	      .entrySet()
	      .stream()
	      .filter(m -> m.getValue() > 1)
	      .map(Map.Entry::getKey)
	      .collect(Collectors.toSet());
	    duplicates.addAll(set);
	    return duplicates;
	}
	
	private boolean areEqualWithArrayValue(Map<String, String[]> first, Map<String, String[]> second) {
	    if (first.size() != second.size()) {
	        return false;
	    }

//	    return first.entrySet().stream()
//	      .allMatch(e -> Arrays.equals(e.getValue(), second.get(e.getKey())));
	    return first.entrySet().stream()
	    	      .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
	}
	//https://www.baeldung.com/java-copy-hashmap-no-changes
	Map<String, String> copyUsingPutAll(Map<String, String> sourceMap, Map<String, String> targetMap) {
	    sourceMap.keySet().removeAll(targetMap.keySet());
	    targetMap.putAll(sourceMap);
	    return targetMap;
	}
}
class Key {
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Key(String name) {
		this.name = name;
	}
}
class Employee {
	 
    private Long id;
    private String name;
    
    public Employee(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    // constructor, getters, setters
}
class MyLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private static final int MAX_ENTRIES = 5;

    public MyLinkedHashMap(
      int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

}
