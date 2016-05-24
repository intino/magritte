package org.jetbrains.jps.tara.model;

import org.jetbrains.jps.model.JpsElement;

public interface JpsTaraFacet extends JpsElement {

	String Application = "Application";
	String Platform = "Platform";

	String platformDsl();

	String applicationDsl();

	String systemDsl();

	String platformOutDsl();

	String applicationOutDsl();

	boolean isLazyLoad();

	boolean isPersistent();

	String type();

	int applicationRefactorId();

	int platformRefactorId();
}