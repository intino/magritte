package tara.magritte.utils;

import tara.magritte.Layer;
import tara.magritte.Reference;

import java.util.*;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class ReferenceList<T extends Layer> implements List<T> {

	private final List<Reference> references;
	private Class<T> tClass;

	public ReferenceList(List<Reference> references, Class<T> tClass) {
		this.references = references;
		this.tClass = tClass;
	}

	@Override
	public int size() {
		return references.size();
	}

	@Override
	public boolean isEmpty() {
		return references.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return referenceByName(((Layer) o).id()).isPresent();
	}

	@Override
	public Iterator<T> iterator() {
		return layerList().iterator();
	}

	@Override
	public Object[] toArray() {
		return layerList().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return layerList().toArray(a);
	}

	@Override
	public boolean add(T t) {
		return references.add(new Reference(t.node()));
	}

	@Override
	public boolean remove(Object o) {
		return references.remove(referenceByName(((Layer) o).id()).get());
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layerList().contains(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return references.addAll(c.stream().map(o -> new Reference(o.node())).collect(toList()));
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return references.addAll(index, c.stream().map(o -> new Reference(o.node())).collect(toList()));
	}

	@Override
	public boolean removeAll(Collection<?> c) {//TODO
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public boolean retainAll(Collection<?> c) {//TODO
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public void clear() {
		references.clear();
	}

	@Override
	public T get(int index) {
		return references.get(index).node().as(tClass);
	}

	@Override
	public T set(int index, T element) {
		references.set(index, new Reference(element.node()));
		return element;
	}

	@Override
	public void add(int index, T element) {
		references.add(index, new Reference(element.node()));
	}

	@Override
	public T remove(int index) {
		return references.remove(index).node().as(tClass);
	}

	@Override
	public int indexOf(Object o) {
		return range(0, references.size()).filter(i -> references.get(i).name().equals(((Layer) o).id())).findFirst().getAsInt();
	}

	@Override
	public int lastIndexOf(Object o) {
		return range(0, references.size())
			.filter(i -> references.get(i).name().equals(((Layer) o).id()))
				.reduce((first, second) -> second).getAsInt();
	}

	@Override
	public ListIterator<T> listIterator() {
		return unmodifiableList(layerList()).listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return unmodifiableList(layerList()).listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return unmodifiableList(layerList().subList(fromIndex, toIndex));
	}

	private List<T> layerList() {
		return references.stream().map(r -> r.node().as(tClass)).collect(toList());
	}

	private Optional<Reference> referenceByName(String name) {
		return references.stream().filter(r -> r.name().equals(name)).findFirst();
	}
}
