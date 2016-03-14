package org.jetbrains.jps.tara.model;

import org.jetbrains.jps.model.JpsElement;

public interface JpsTaraFacet extends JpsElement {

	String dsl();

	String generatedDsl();

	boolean isDynamicLoad();

	int level();

	boolean ontology();

	int domainRefactorId();

	int engineRefactorId();
}