package siani.tara.compiler.codegeneration;

import org.siani.itrules.Frame;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrameCreator {
	public static Frame create(Model model) {
		final Map<String, Frame> frames = new HashMap<>();
		for (final Node node : model.getNodeTable().values())
			frames.put(node.getQualifiedName(), new Frame(getTypes(node)) {{
				addSlot("Name", node.getName());
				addSlot("QualifiedName", node.getQualifiedName());
				addSlot("Box", node.getBox());
				if (node.getObject().getParent() != null)
					addSlot("Parent", node.getObject().getParentName());
				addSlot("Doc", node.getObject().getDoc());

				for (final Variable variable : node.getObject().getVariables())
					addSlot("Variables", new Frame(getTypes(variable)) {{
						addSlot("Name", variable.getName());
						addSlot("Type", variable.getType());
						addSlot("DefaultValues", variable.getDefaultValues());
					}});

				for (final Map.Entry<String, Variable> entry : node.getObject().getVariableInits().entrySet())
					addSlot("VarInit", new Frame("VarInit") {{
						addSlot("Name", entry.getValue().getName());
						addSlot("Type", entry.getValue().getType());
						addSlot("value", entry.getValue().getDefaultValues());
						if (entry.getValue() instanceof Attribute && isNumeric(entry.getValue().getType()))
							addSlot("measure", ((Attribute) entry.getValue()).getMeasure());
					}});

				for (final FacetTarget facetTarget : node.getObject().getFacetTargets())
					addSlot("FacetTarget", new Frame(getTypes(facetTarget)) {{
						addSlot("Destiny", facetTarget.getDestinyQN());
						for (final Variable variable : node.getObject().getVariables()) {
							addSlot("Variables", new Frame(getTypes(variable)) {{
								addSlot("Name", variable.getName());
								addSlot("Type", variable.getType());
								addSlot("DefaultValues", variable.getDefaultValues());
							}});
						}
					}});

				for (final Facet facet : node.getObject().getFacets())
					addSlot("Facet", new Frame(getTypes(facet)) {{
						addSlot("Name", facet.getName());
						if (facet.getImplementation() != null)
							addSlot("implementation", facet.getImplementation());
					}});
			}});
		return new Frame("model") {{
			for (Frame frame : frames.values()) frame.addSlot("concepts", frame);
		}};
	}

	private static boolean isNumeric(String type) {
		return type.equals(Primitives.DOUBLE) || type.equals(Primitives.INTEGER) || type.equals(Primitives.NATURAL);
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

	private static String[] asStringList(Annotations.Annotation[] annotations) {
		List<String> list = new ArrayList<>();
		for (Annotations.Annotation annotation : annotations) list.add(annotation.getName());
		return list.toArray(new String[list.size()]);
	}
}
