package siani.tara.compiler.model;

import java.util.Collection;

public interface Parameter {

	String getName();

	int getPosition();

	Collection<Object> getValues();

	String getExtension();
}
