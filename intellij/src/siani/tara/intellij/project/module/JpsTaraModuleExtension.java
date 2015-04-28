package siani.tara.intellij.project.module;

import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Locale;

public interface JpsTaraModuleExtension extends JpsElement {

	JpsModule getModule();

	Locale getLocale();
}
