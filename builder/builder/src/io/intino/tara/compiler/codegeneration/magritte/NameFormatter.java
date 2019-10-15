package io.intino.tara.compiler.codegeneration.magritte;

import io.intino.tara.compiler.model.NodeImpl;
import io.intino.tara.lang.model.Aspect;
import io.intino.tara.lang.model.Node;

import static io.intino.tara.compiler.codegeneration.Format.*;

public class NameFormatter {
	public static final char DOT = '.';

	private NameFormatter() {
	}

	public static String getQn(Node node, String workingPackage) {
		return workingPackage.toLowerCase() + DOT + qualifiedName().format(node.layerQualifiedName());
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
