//Salam Fazil
//V00935894

import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

	private long getLoops;
	private long putLoops;
	private int  tableSize;

	private List<List<Entry<K,V>>> 	table;
	private int	count;

	public HashMap() {
		tableSize = 1000003; // prime number
		table = new ArrayList<List<Entry<K,V>>>(tableSize);

		// initializes table as a list of empty lists
		for (int i = 0; i < tableSize; i++) {
			table.add(new LinkedList<Entry<K,V>>());
		}
		
		count = 0;

		resetGetLoops();
		resetPutLoops();
	}

	public long getGetLoopCount() {
		return getLoops;
	}

	public long getPutLoopCount() {
		return putLoops;
	}

	public void resetGetLoops() {
		getLoops = 0;
	}
	public void resetPutLoops() {
		putLoops = 0;
	}

	public boolean containsKey(K key) {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;

		if(table.get(index) != null) {
			List<Entry<K, V>> list = table.get(index);
			Iterator<Entry<K, V>> curr = list.iterator();

			int i = 0;
			while(curr.hasNext()){
				if (list.get(i).key.compareTo(key) == 0) {
					return true;
				}
				curr.next();
				i++;
			}
		}
		return false;
	}

	public V get (K key) throws KeyNotFoundException {
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode()) % tableSize;

		if(table.get(index) != null) {
			List<Entry<K, V>> list = table.get(index);
			Iterator<Entry<K, V>> curr = list.iterator();

			int i = 0;
			while(curr.hasNext()){
				if (list.get(i).key.compareTo(key) == 0) {
					return list.get(i).value;
				}
				curr.next();
				i++;
			}
		}
		throw new KeyNotFoundException();
	}

	public List<Entry<K,V> >	entryList() {
		List <Entry<K,V>> l = new LinkedList<Entry<K,V>>();

		Iterator<List<Entry<K, V>>> tableIterator = table.iterator();

		for (int i = 0; tableIterator.hasNext(); i++) {

			List<Entry<K, V>> indexList = table.get(i);
			Iterator<Entry<K, V>> indexIterator = indexList.iterator();

			for (int j = 0; indexIterator.hasNext(); j++) {
				l.add(indexList.get(j));
				indexIterator.next();
			}

			tableIterator.next();
		}
		return l;
	}

	public void put (K key, V value){
		// gets the index in the table this key should be in
		int index = Math.abs(key.hashCode())%tableSize;
		List<Entry<K, V>> list = table.get(index);

		if(table.get(index) != null) {
			Iterator<Entry<K, V>> curr = list.iterator();
			int i = 0;
			while (curr.hasNext()) {
				if (list.get(i).key.compareTo(key) == 0) {
					list.get(i).setValue(value);
					return;
				}
				curr.next();
				i++;
			}
		}

		list.add(new Entry<K, V>(key, value));
		count++;
	}

	public int size() {
		return count;
	}

	public void clear() {
		table.clear();
		count = 0;
	}

}
