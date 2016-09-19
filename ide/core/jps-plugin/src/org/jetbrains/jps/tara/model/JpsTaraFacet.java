package org.jetbrains.jps.tara.model;

import org.jetbrains.jps.model.JpsElement;

import java.util.List;

public interface JpsTaraFacet extends JpsElement {

	String workingDirectory();

	String platformDsl();

	String applicationDsl();

	String systemDsl();

	String platformOutDsl();

	String applicationOutDsl();

	boolean isPersistent();

	String type();

	int applicationRefactorId();

	int platformRefactorId();

	List<String> supportedLanguages();
}