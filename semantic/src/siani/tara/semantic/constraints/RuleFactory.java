package siani.tara.semantic.constraints;

import siani.tara.semantic.*;
import siani.tara.semantic.model.*;

import java.util.*;

import static siani.tara.semantic.model.Tag.*;


public class RuleFactory {

	public static Allow.Name name() {
		return new Allow.Name() {
			@Override
			public void check(Element element, List<? extends Rejectable> rejectables) {
				List<Rejectable> toRemove = new ArrayList<>();
				for (Rejectable rejectable : rejectables) {
					if (!(rejectable instanceof Rejectable.Name)) continue;
					toRemove.add(rejectable);
				}
				rejectables.removeAll(toRemove);
			}
		};
	}

	public static Allow.Multiple multiple(final String type) {
		return multiple(type, new Tag[0]);
	}

	public static Allow.Multiple multiple(final String type, final Tag... annotations) {
		return new Allow.Multiple() {
			@Override
			public String type() {
				return type;
			}

			@Override
			public Tag[] annotations() {
				return annotations;
			}

			@Override
			public void check(Element element, List<? extends Rejectable> rejectables) {
				List<Rejectable.Include> rejectableIncludes = getRejectableIncludesBy(type, rejectables);
				addFlagsAndAnnotations(rejectableIncludes, annotations);
				rejectables.removeAll(rejectableIncludes);
			}
		};
	}

	private static void addFlagsAndAnnotations(List<Rejectable.Include> includes, Tag[] tags) {
		for (Rejectable.Include include : includes)
			addFlags(include.getNode(), tags);
	}

	private static void addFlags(Node node, Tag[] tags) {
		List<String> flags = createList(node.flags());
		for (String flag : names(tags)) {
			if (!flags.contains(flag.toUpperCase()))
				node.flags(flag.toUpperCase());
			flags.add(flag.toUpperCase());
		}
	}

	private static ArrayList<String> createList(String[] flags) {
		ArrayList<String> tags = new ArrayList<>();
		for (String flag : flags) tags.add(flag.toUpperCase());
		return tags;
	}

	private static String[] names(Tag[] tags) {
		List<String> annotations = new ArrayList<>();
		for (Tag tag : tags) annotations.add(tag.name());
		return annotations.toArray(new String[annotations.size()]);
	}

	public static Allow.Single single(final String type) {
		return single(type, new Tag[0]);
	}

	public static Allow.Single single(final String type, final Tag... annotations) {
		return new Allow.Single() {
			@Override
			public String type() {
				return type;
			}

			@Override
			public Tag[] annotations() {
				return annotations;
			}

			@Override
			public void check(Element element, List<? extends Rejectable> rejectables) {
				List<Rejectable.Include> rejectableIncludes = getRejectableIncludesBy(type, rejectables);
				addFlagsAndAnnotations(rejectableIncludes, annotations);
				if (rejectableIncludes.size() > 1) setCauseToRejectables(rejectableIncludes);
				rejectables.removeAll(rejectableIncludes);
			}

			private void setCauseToRejectables(List<Rejectable.Include> rejectableIncludesBy) {
				for (Rejectable.Include include : rejectableIncludesBy)
					include.multiple();
			}
		};
	}

	private static List<Rejectable.Include> getRejectableIncludesBy(String type, List<? extends Rejectable> rejectables) {
		List<Rejectable.Include> rejectableIncludes = new ArrayList<>();
		for (Rejectable rejectable : rejectables) {
			if (!(rejectable instanceof Rejectable.Include)) continue;
			Rejectable.Include include = (Rejectable.Include) rejectable;
			if (checkType(type, include.getNode()) || checkFacets(type, include))
				rejectableIncludes.add(include);
		}
		return rejectableIncludes;
	}

	private static boolean checkFacets(String type, Rejectable.Include rejectable) {
		for (Facet facet : rejectable.getNode().facets())
			if (facet.type().equals(shortType(type))) return true;
		return false;
	}

	private static boolean checkType(String type, Node node) {
		return node != null && areCompatibles(node, type);
	}

	private static boolean areCompatibles(Node node, String type) {
		for (String nodeType : node.types())
			if (nodeType != null && nodeType.equals(type)) return true;
		return false;
	}

	public static Allow.OneOf oneOf(final Allow... allows) {
		return new Allow.OneOf() {
			@Override
			public void check(Element element, List<? extends Rejectable> rejectables) {
				for (Allow allow : allows) {
					try {
						allow.check(element, rejectables);
					} catch (SemanticException ignored) {
					}
				}
			}

			@Override
			public String type() {
				return "";
			}

			@Override
			public Tag[] annotations() {
				return new Tag[0];
			}
		};
	}

	public static Allow.Parameter parameter(final String name, final String type, final boolean multiple, final int position, String nativeName, String... annotations) {
		return new PrimitiveParameterAllow(name, type, multiple, position, nativeName, annotations);
	}

	public static Allow.Parameter parameter(final String name, final String[] values, final boolean multiple, final int position, String nativeName, String... annotations) {
		return new ReferenceParameterAllow(name, values, multiple, position, nativeName, annotations);
	}

	public static Constraint.Require _multiple(final String type) {
		return _multiple(type, new Tag[0]);
	}

	public static Constraint.Require _multiple(final String type, final Tag... annotations) {
		return new Constraint.Require.Multiple() {
			@Override
			public String type() {
				return type;
			}

			@Override
			public Tag[] annotations() {
				return annotations;
			}

			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				for (Node inner : node.includes())
					if (inner.type().equals(type)) {
						addFlags(inner, annotations);
						return;
					}
				throw new SemanticException(new SemanticError("required.type.in.context", type, node.type()));
			}
		};
	}

	public static Constraint.Require _single(final String type) {
		return _multiple(type, new Tag[0]);
	}

	public static Constraint.Require.Single _single(final String type, final Tag... annotations) {
		return new Constraint.Require.Single() {
			@Override
			public String type() {
				return type;
			}

			@Override
			public Tag[] annotations() {
				return annotations;
			}

			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				for (Node inner : node.includes())
					if (inner.type().equals(type)) {
						addFlags(inner, annotations);
						return;
					}
				throw new SemanticException(new SemanticError("required.type.in.context", type, node.type()));
			}
		};
	}

	public static Constraint.Require oneOf(final Constraint.Require... requires) {
		return new Constraint.Require.OneOf() {
			@Override
			public void check(Element element) throws SemanticException {
				List<String> requireTypes = new ArrayList<>();
				for (Require require : requires) {
					requireTypes.add(((Require.Include) require).type());
					try {
						require.check(element);
						return;
					} catch (SemanticException ignored) {
					}
				}
				throw new SemanticException(new SemanticError("required.any.type.in.context", Arrays.toString(requireTypes.toArray(new String[requireTypes.size()]))));
			}

			@Override
			public Require[] requires() {
				return requires;
			}

			@Override
			public String type() {
				return null;
			}

			@Override
			public Tag[] annotations() {
				return new Tag[0];
			}
		};
	}

	public static Constraint.Require.Parameter _parameter(final String name, final String type, final boolean multiple, final int position, final String metric, final String... annotations) {
		return new Constraint.Require.Parameter() {
			@Override
			public String name() {
				return name;
			}

			@Override
			public String type() {
				return type;
			}

			@Override
			public boolean multiple() {
				return multiple;
			}

			@Override
			public String[] allowedValues() {
				return new String[0];
			}

			@Override
			public int position() {
				return position;
			}

			@Override
			public String metric() {
				return metric;
			}

			@Override
			public String[] annotations() {
				return annotations;
			}

			@Override
			public void check(Element element) throws SemanticException {
				if (element instanceof Node && ((Node) element).isReference()) return;
				siani.tara.semantic.model.Parameter[] parameters = (element instanceof Facet) ? ((Facet) element).parameters() : ((Node) element).parameters();
				if (checkParameterExists(parameters, name(), position)) return;
				String elementType = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
				throw new SemanticException(new SemanticError("required.parameter", element, new String[]{elementType, type, name}));
			}
		};
	}

	public static Constraint.Require.Parameter _parameter(final String name, final String[] values, final boolean multiple, final int position, final String metric, final String... annotations) {
		return new Constraint.Require.Parameter() {
			@Override
			public String name() {
				return name.endsWith(":word") ? name.replace(":word", "") : name;
			}

			@Override
			public String type() {
				return name.endsWith(":word") ? "word" : "reference";
			}

			@Override
			public boolean multiple() {
				return multiple;
			}

			@Override
			public String[] allowedValues() {
				return values;
			}

			@Override
			public int position() {
				return position;
			}

			@Override
			public String metric() {
				return metric;
			}

			@Override
			public String[] annotations() {
				return annotations;
			}

			@Override
			public void check(Element element) throws SemanticException {
				if (!(element instanceof Node)) return;
				if (((Node) element).isReference()) return;
				siani.tara.semantic.model.Parameter[] parameters = (element instanceof Facet) ? ((Facet) element).parameters() : ((Node) element).parameters();
				if (checkParameterExists(parameters, name(), position)) return;
				String type = (element instanceof Facet) ? ((Facet) element).type() : ((Node) element).type();
				throw new SemanticException(new SemanticError("required.parameter", element, new String[]{type, "word", name}));
			}
		};
	}

	private static boolean checkParameterExists(Parameter[] parameters, String name, int position) {
		List<Parameter> signatureParameters = new ArrayList<>();
		for (Parameter parameter : parameters)
			if (name.equals(parameter.getName())) return true;
			else if (!parameter.isVariableInit()) signatureParameters.add(parameter);
		removeNamedCandidates(signatureParameters);
		return signatureParameters.size() > position;
	}

	private static void removeNamedCandidates(List<Parameter> signatureParameters) {
		List<Parameter> toRemove = new ArrayList<>();
		for (Parameter parameter : signatureParameters)
			if (parameter.getName() != null && !parameter.getName().isEmpty())
				toRemove.add(parameter);
		remove(signatureParameters, toRemove);
	}

	private static void remove(List<Parameter> signatureParameters, List<Parameter> toRemove) {
		for (Parameter parameter : toRemove) signatureParameters.remove(parameter);
	}

	public static Allow.Facet facet(final String type) {
		return new Allow.Facet() {
			List<Constraint> constraints = new ArrayList<>();
			List<Allow> allows = new ArrayList<>();

			@Override
			public String type() {
				return type;
			}

			@Override
			public Facet require(Constraint.Require.Parameter... parameter) {
				Collections.addAll(constraints, parameter);
				for (Constraint.Require.Parameter require : parameter) addParameter(require);
				return this;
			}

			private void addParameter(Constraint.Require.Parameter parameter) {
				if (isWordOrReference(parameter))
					allow(parameter(parameter.name() + (parameter.type().equals("word") ? ":word" : ""), parameter.allowedValues(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
				else
					allow(parameter(parameter.name(), parameter.type(), parameter.multiple(), parameter.position(), parameter.metric(), parameter.annotations()));
			}

			private boolean isWordOrReference(Constraint.Require.Parameter parameter) {
				return parameter.type().equals("word") || parameter.type().equals("reference");
			}

			@Override
			public Facet allow(Parameter... parameter) {
				this.allows.addAll(Arrays.asList(parameter));
				return add(new Constraint.Reject() {
					@Override
					public void check(Element element) throws SemanticException {
						siani.tara.semantic.model.Facet facet = (siani.tara.semantic.model.Facet) element;
						List<Rejectable> rejectables = Rejectable.build(facet);
						for (Allow allow : allows)
							allow.check(facet, rejectables);
						if (!rejectables.isEmpty()) throw new SemanticException(rejectables.get(0).error());
					}
				});
			}

			@Override
			public Collection<Allow> allows() {
				return allows;
			}

			@Override
			public Collection<Constraint> constraints() {
				return constraints;
			}

			private Facet add(Constraint... constraints) {
				this.constraints.addAll(Arrays.asList(constraints));
				return this;
			}

			@Override
			public void check(Element element, List<? extends Rejectable> rejectables) {
				List<Rejectable> toRemove = new ArrayList<>();
				for (Rejectable rejectable : rejectables) {
					if (!(rejectable instanceof Rejectable.Facet)) continue;
					siani.tara.semantic.model.Facet facet = ((Rejectable.Facet) rejectable).getFacet();
					if (facet.type().equals(shortType(type)) && checkParameters(facet))
						toRemove.add(rejectable);
				}
				rejectables.removeAll(toRemove);
			}

			private boolean checkParameters(siani.tara.semantic.model.Facet facet) {
				try {
					for (Constraint require : constraints)
						require.check(facet);
				} catch (SemanticException ignored) {
				}
				return true;

			}
		};
	}

	public static Constraint.Require _name() {
		return new Constraint.Require.Name() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (node.name().isEmpty() && !node.isReference())
					throw new SemanticException(new SemanticError("required.name"));
			}
		};
	}

	public static Constraint.Require _address() {
		return new Constraint.Require.Address() {
			@Override
			public void check(Element element) throws SemanticException {
				Node node = (Node) element;
				if (!node.isReference() && node.address() == Long.MIN_VALUE)
					throw new SemanticException(new SemanticError("required.address", node.type()));
			}
		};
	}

	public static Assumption isSingle() {
		return new Assumption.Single() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(SINGLE.getName())) node.flags(SINGLE.getName());
			}
		};
	}

	public static Assumption isRequired() {
		return new Assumption.Required() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(REQUIRED.getName())) node.flags(REQUIRED.getName());
			}
		};
	}

	public static Assumption isRoot() {
		return new Assumption.Root() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(ROOT.getName())) node.flags(ROOT.getName());
				node.moveToTheTop();
			}
		};
	}

	public static Assumption isFacet() {
		return new Assumption.Facet() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(FACET.getName())) node.flags(FACET.getName());
				node.flags(NAMED.getName());
			}
		};
	}

	public static Assumption isFacetInstance() {
		return new Assumption.FacetInstance() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(FACET_INSTANCE.getName()))
					node.flags(FACET_INSTANCE.getName());
				node.flags(NAMED.getName());
			}
		};
	}

	public static Assumption isProperty() {
		return new Assumption.Property() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(PROPERTY.getName())) node.flags(PROPERTY.getName());
			}
		};
	}

	public static Assumption isPropertyInstance() {
		return new Assumption.PropertyInstance() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(PROPERTY_INSTANCE.getName()))
					node.flags(PROPERTY_INSTANCE.getName());
			}
		};
	}

	public static Assumption isFeature() {
		return new Assumption.Feature() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(FEATURE.getName())) node.flags(FEATURE.getName());
			}
		};
	}

	public static Assumption isFeatureInstance() {
		return new Assumption.FeatureInstance() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(FEATURE_INSTANCE.getName()))
					node.flags(FEATURE_INSTANCE.getName());
			}
		};
	}

	public static Assumption isTerminal() {
		return new Assumption.Terminal() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(TERMINAL.getName())) node.flags(TERMINAL.getName());
			}
		};
	}

	public static Assumption isTerminalInstance() {
		return new Assumption.TerminalInstance() {
			@Override
			public void assume(Node node) {
				if (!Arrays.asList(node.flags()).contains(TERMINAL_INSTANCE.getName()))
					node.flags(TERMINAL_INSTANCE.getName());
			}
		};
	}

	private static String shortType(String absoluteType) {
		return absoluteType.contains(".") ? absoluteType.substring(absoluteType.lastIndexOf(".") + 1) : absoluteType;
	}

}
