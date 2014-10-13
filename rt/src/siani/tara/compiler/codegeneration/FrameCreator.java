package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameCreator {
	public static Map<String, Frame> create(Model model) {
		Map<String, Frame> frames = new HashMap<>();
		for (final Node node : model.getNodeTable().values())
			frames.put(node.getQualifiedName(), new Frame(getTypes(node)) {
				{
					property("Name", node.getName());
					property("QualifiedName", node.getQualifiedName());
					property("Box", node.getBox());
					if (node.getObject().getParent() != null)
						property("Parent", node.getObject().getParentName());
					property("Doc", node.getObject().getDoc());

					for (final Variable variable : node.getObject().getVariables()) {
						property("Variables", new Frame(getTypes(variable)) {{
							property("Name", variable.getName());
							property("Type", variable.getType());
							property("DefaultValues", variable.getDefaultValues());
						}});
					}

					for (final FacetTarget facetTarget : node.getObject().getFacetTargets()) {
						property("FacetTarget", new Frame(getTypes(facetTarget)) {{
							property("Destiny", facetTarget.getDestinyQN());
							for (final Variable variable : node.getObject().getVariables()) {
								property("Variables", new Frame(getTypes(variable)) {{
									property("Name", variable.getName());
									property("Type", variable.getType());
									property("DefaultValues", variable.getDefaultValues());
								}});
							}
						}});
					}

					for (final Facet facet : node.getObject().getFacets())
						property("Facet", new Frame(getTypes(facet)) {{
							property("Name", facet.getName());
							if (facet.getImplementation() != null)
								property("implementation", facet.getImplementation());
						}});
				}


			});
		return frames;
	}

	private static String[] getTypes(Facet facet) {
		List<String> list = new ArrayList<>();
		list.add("facet");
		list.add(facet.getName());
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(FacetTarget facetTarget) {
		List<String> list = new ArrayList<>();
		list.add("facetTarget");
		list.add(facetTarget.getDestinyQN());
		if (facetTarget.isAlways())
			list.add("always");
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(Variable variable) {
		List<String> list = new ArrayList<>();
		list.add(variable.getClass().getSimpleName());
		list.add("variable");
		if (variable.isTerminal()) list.add("terminal");
		if (variable.isList()) list.add("list");
		if (variable.isProperty()) list.add("property");
		return list.toArray(new String[list.size()]);
	}

	private static String[] getTypes(Node node) {
		List<String> types = new ArrayList<>();
		NodeObject object = node.getObject();
		types.add(object.getType());
		types.add("node");
		for (Annotations.Annotation annotation : object.getAnnotations())
			types.add(annotation.getName());
		return types.toArray(new String[types.size()]);
	}
}
