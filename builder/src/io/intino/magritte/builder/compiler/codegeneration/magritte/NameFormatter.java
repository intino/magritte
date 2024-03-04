package io.intino.magritte.builder.compiler.codegeneration.magritte;

import io.intino.tara.builder.model.MogramImpl;
import io.intino.tara.builder.model.MogramReference;
import io.intino.tara.language.model.Facet;
import io.intino.tara.language.model.Mogram;

import static io.intino.tara.builder.utils.Format.*;

public class NameFormatter {
	public static final char DOT = '.';

	private NameFormatter() {
	}

	public static String getQn(Mogram mogram, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + qualifiedName().format(layerQn(mogram)).toString();
	}

	private static String layerQn(Mogram node) {
		return node instanceof MogramReference ? ((MogramReference) node).layerQualifiedName() : ((MogramImpl) node).layerQualifiedName();
	}

	public static String getQn(Facet aspect, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + javaValidName().format(aspect.type());
	}

	public static String decorableInnerClassQn(Mogram node, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + javaClassNames().format(((MogramImpl) node).layerQn()).toString();
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Mogram.ANONYMOUS, "").replace("[", "").replace("]", "").replace(":", "").replace("$", ".");
	}
}