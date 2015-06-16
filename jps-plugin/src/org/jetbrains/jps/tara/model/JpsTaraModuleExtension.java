package org.jetbrains.jps.tara.model;

import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.module.JpsModule;

public interface JpsTaraModuleExtension extends JpsElement {
	JpsModule getModule();

	String getDsl();

	String getDslsDirectoy();

	String getDictionary();

	String getGeneratedDslName();

	boolean isPlateRequired();

	int getLevel();
}