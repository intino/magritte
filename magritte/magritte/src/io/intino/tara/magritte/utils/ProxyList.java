package io.intino.tara.magritte.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

public class ProxyList<T, E extends T> implements List<E> {

	private final List<T> list;
	private final Class<E> aClass;

	public ProxyList(List<T> list, Class<E> aClass) {
		this.list = list;
		this.aClass = aClass;
	}

	@Override
	public int size() {
		return filteredList().size();
	}

	@Override
	public boolean isEmpty() {
		return filteredList().isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return filteredList().contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return filteredList().iterator();
	}

	@Override
	public Object[] toArray() {
		return filteredList().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return filteredList().toArray(a);
	}

	@Override
	public boolean add(E e) {
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return filteredList().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		return list.addAll(list.indexOf(filteredList().get(index)), c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.removeIf(aClass::isInstance);
	}

	@Override
	public E get(int index) {
		return filteredList().get(index);
	}

	@Override
	public E set(int index, E element) {
		E removed = filteredList().get(index);
		list.set(list.indexOf(removed), element);
		return removed;
	}

	@Override
	public void add(int index, E element) {
		list.add(list.indexOf(filteredList().get(index)), element);
	}

	@Override
	public E remove(int index) {
		E element = filteredList().get(index);
		list.remove(element);
		return element;
	}

	@Override
	public int indexOf(Object o) {
		return filteredList().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return filteredList().lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return unmodifiableList(filteredList()).listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return unmodifiableList(filteredList()).listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return unmodifiableList(filteredList().subList(fromIndex, toIndex));
	}

	private List<E> filteredList() {
		return this.list.stream().filter(aClass::isInstance).map(e -> (E) e).collect(toList());
	}
}