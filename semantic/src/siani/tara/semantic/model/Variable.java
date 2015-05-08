package siani.tara.semantic.model;

public interface Variable extends Element {

	String name();

	String type();

	String[] flags();

	Object[] defaultValue();
}
