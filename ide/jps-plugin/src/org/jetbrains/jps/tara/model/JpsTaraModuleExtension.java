package org.jetbrains.jps.tara.model;

import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.module.JpsModule;

public interface JpsTaraModuleExtension extends JpsElement {
	JpsModule getModule();

	String dsl();

	String generatedDsl();

	boolean isDynamicLoad();

	boolean customLayers();

	int level();

	boolean testModule();
}