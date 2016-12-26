package io.intino.tara.compiler.codegeneration.magritte;

import io.intino.tara.compiler.model.Model;
import io.intino.tara.lang.model.*;

import java.util.Arrays;
import java.util.List;

import static io.intino.tara.compiler.codegeneration.Format.javaClassNames;
import static io.intino.tara.compiler.codegeneration.Format.javaValidName;
import static io.intino.tara.compiler.codegeneration.Format.qualifiedName;

public class NameFormatter {

	public static final char DOT = '.';

	private NameFormatter() {
	}

	public static String getQn(Node node, String workingPackage) {
		if (node.facetTarget() != null) return getQn(node.facetTarget(), workingPackage).replace(":", "");
		final FacetTarget facetTarget = isInFacetTarget(node);
		return workingPackage.toLowerCase() + DOT +
			(facetTarget != null ? composeInFacetTargetQN(node, facetTarget) : qualifiedName().format(node.qualifiedName()));
	}

	private static String qnInsideFacet(String qn) {
		final List<String> names = Arrays.asList(qn.split(":"));
		final List<String> qnValues = Arrays.asList(names.get(names.size() - 1).split("\\."));
		return String.join(".", qnValues.subList(1, qnValues.size()));
	}

	public static String getQn(FacetTarget target, String workingPackage) {
		return facetLayerPackage(target, workingPackage) + javaValidName().format(target.owner().name() + target.targetNode().name()).toString();
	}

	public static String getQn(FacetTarget target, Node owner, String workingPackage) {
		return facetLayerPackage(target, workingPackage) + javaValidName().format(owner.name() + target.targetNode().name()).toString();
	}

	public static String getQn(Facet facet, String outDsl) {
		return outDsl.toLowerCase() + DOT + javaValidName().format(facet.type());
	}

	public static String getStashQn(Node node, String workingPackage) {
		final FacetTarget facet = isInFacetTarget(node);
		final String name = facet != null ?
			facetLayerPackage(facet, workingPackage) + javaClassNames().format(node.cleanQn()).toString() :
			workingPackage.toLowerCase() + DOT + javaClassNames().format(node.cleanQn()).toString();
		return name.replace("#", "");
	}

	private static FacetTarget isInFacetTarget(Node node) {
		NodeContainer container = node.container();
		while (container != null && ((Node) container).facetTarget() == null)
			container = container.container();
		return container != null ? ((Node) container).facetTarget() : null;
	}

	public static String cleanQn(String qualifiedName) {
		return qualifiedName.replace(Node.ANONYMOUS, "").replace("[", "").replace("]", "").replace(":", "").replace("#", "");
	}

	public static String composeInFacetTargetQN(Node node, FacetTarget target) {
		return target.owner().name().toLowerCase() + DOT +
			(!(target.targetNode().container() instanceof NodeRoot) ?
				target.targetNode().container().qualifiedName().toLowerCase() + DOT : "") +
			qualifiedName().format(target.owner().name() + "#" + target.targetNode().name()).toString() + DOT +
			qnInsideFacet(node.qualifiedName());
	}

	public static String facetLayerPackage(FacetTarget target, String workingPackage) {
		return (workingPackage.toLowerCase() + DOT + target.owner().name()).toLowerCase() +
			(!(target.targetNode().container() instanceof Model) ? DOT + target.targetNode().container().qualifiedName().toLowerCase().replace(":", ".") + DOT : DOT);
	}
}
