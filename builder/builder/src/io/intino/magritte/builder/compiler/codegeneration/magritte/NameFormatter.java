package io.intino.magritte.builder.compiler.codegeneration.magritte;

import io.intino.magritte.builder.compiler.model.NodeImpl;
import io.intino.magritte.builder.compiler.model.NodeReference;
import io.intino.magritte.lang.model.Aspect;
import io.intino.magritte.lang.model.Node;

import static io.intino.magritte.builder.compiler.utils.Format.*;

public class NameFormatter {
	public static final char DOT = '.';

	private NameFormatter() {
	}

	public static String getQn(Node node, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + qualifiedName().format(layerQn(node)).toString();
	}

	private static String layerQn(Node node) {
		return node instanceof NodeReference ? ((NodeReference) node).layerQualifiedName() : ((NodeImpl) node).layerQualifiedName();
	}

	public static String getQn(Aspect aspect, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + javaValidName().format(aspect.type());
	}

	public static String decorableInnerClassQn(Node node, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + javaClassNames().format(((NodeImpl) node).layerQn()).toString();
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANONYMOUS, "").replace("[", "").replace("]", "").replace(":", "").replace("$", ".");
	}
}