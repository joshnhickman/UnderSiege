package com.kerbii.undersiege;

public class ArrayList<E> {
	
	private static final int DEFAULTCAPACITY = 20;
	
	private E[] elementData;
	private int size;
	
	public ArrayList() {
		this(DEFAULTCAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		elementData = (E[]) new Object[capacity];
		size = 0;
	}
	
	public void add(E item) {
		checkCapacity(size + 1);
		elementData[size] = item;
		size++;
	}
	
	public void addAll(ArrayList<E> otherElementData) {
		if (otherElementData != null) {
			checkCapacity(size + otherElementData.size());
			for (int i = 0; i < otherElementData.size(); i++) {
				add(otherElementData.get(i));
			}
		}
	}
	
	public void remove(int index) {
		size--;
		elementData[index] = elementData[size];
		elementData[size] = null;
	}
	
	public void remove(E item) {
		for (int i = 0; i < size; i++) {
			if (elementData[i] == item) {
				remove(i);
				break;
			}
		}
	}
	
	public boolean contains(E item) {
		for (int i = 0; i < size; i++) {
			if (elementData[i] == item) {
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			elementData[i] = null;
		}
		size = 0;
	}
	
	public void removeAll() {
		size = 0;
	}
	
	public E get(int index) {
		return elementData[index];
	}
	
	private void checkCapacity(int capacity) {
		if (capacity > elementData.length) {
			ensureCapacity(capacity);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int capacity) {
		int newCapacity = elementData.length * 2 + 1;
		if (capacity > newCapacity) {
			newCapacity = capacity;
		}
		E[] newList = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newList[i] = elementData[i];
		}
		elementData = newList;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
