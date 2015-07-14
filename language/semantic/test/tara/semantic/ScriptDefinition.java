package tara.semantic;

import tara.semantic.model.*;

import java.util.*;
import java.util.stream.Collectors;

class ScriptDefinition implements Definition {
	public Set<String> annotations = new HashSet<>();
	public Set<String> flags = new HashSet<>();
	public List<FacetTarget> facetTargets = new ArrayList<>();
	public List<Facet> facets = new ArrayList<>();
	private String type = "";
	private String name = "";
	private List<Parameter> parameters = new ArrayList<>();
	private Node parent = null;
	private String plate = "";
	private List<ScriptNode> includes = new ArrayList<>();

	@Override
	public Definition name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public Definition parameter(final int position, final String name, final Object... values) {
		parameters.add(new Parameter() {
			@Override
			public String inferredType() {
				return null;
			}

			@Override
			public void inferredType(String type) {

			}

			@Override
			public List<String> annotations() {
				return null;
			}

			@Override
			public void annotations(List<String> annotations) {

			}

			@Override
			public boolean isMultiple() {
				return false;
			}

			@Override
			public void multiple(boolean multiple) {

			}

			@Override
			public int position() {
				return position;
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public void name(String name) {

			}

			@Override
			public List<Object> values() {
				return Arrays.asList(values);
			}

			@Override
			public String contract() {
				return null;//TODO
			}

			@Override
			public void contract(String contract) {

			}

			@Override
			public String metric() {
				return null;
			}

			@Override
			public boolean isVariableInit() {
				return false;
			}

			@Override
			public void addAllowedParameters(List<String> values) {

			}

		});
		return this;
	}

	@Override
	public Definition annotations(String... annotations) {
		Collections.addAll(this.annotations, annotations);
		return this;
	}

	@Override
	public Definition flags(String... flags) {
		Collections.addAll(this.flags, flags);
		return this;
	}


	@Override
	public Definition plate(String address) {
		this.plate = address;
		return this;
	}

	@Override
	public Definition facetTarget(FacetTarget on) {
		facetTargets.add(on);
		return this;
	}

	@Override
	public Definition facet(Facet as) {
		facets.add(as);
		return this;
	}

	@Override
	public Definition extendsTo(Node parent) {
		this.parent = parent;
		return this;
	}


	@Override
	public Definition type(String type) {
		this.type = type;
		return this;
	}

	@Override
	public Definition include(Node... nodes) {
		for (Node node : nodes) includes.add((ScriptNode) node);
		return this;
	}

	@Override
	public Definition has(Node... nodes) {
		for (final Node node : nodes) {
			includes.add(new ScriptNode() {
				@Override
				public Node context() {
					return node.context();
				}

				@Override
				public String type() {
					return node.type();
				}

				@Override
				public boolean isReference() {
					return true;
				}

				@Override
				public Node destinyOfReference() {
					return this;
				}

				@Override
				public void type(String type) {
					node.type(type);
				}

				@Override
				public List<String> secondaryTypes() {
					return node.secondaryTypes();
				}

				@Override
				public List<String> types() {
					return Collections.emptyList();
				}

				@Override
				public String name() {
					return node.name();
				}

				@Override
				public Node parent() {
					return node.parent();
				}

				@Override
				public boolean hasSubs() {
					return node.hasSubs();
				}

				@Override
				public String plate() {
					return node.plate();
				}

				@Override
				public List<String> annotations() {
					return node.annotations();
				}

				@Override
				public List<String> flags() {
					return node.flags();
				}

				@Override
				public void flags(String... flags) {
					node.flags(flags);
				}

				@Override
				public void annotations(String... annotations) {
					node.annotations(annotations);
				}

				@Override
				public void moveToTheTop() {
					//TODO
				}

				@Override
				public List<Facet> facets() {
					return node.facets();
				}

				@Override
				public List<FacetTarget> facetTargets() {
					return node.facetTargets();
				}

				@Override
				public List<Parameter> parameters() {
					return node.parameters();
				}

				@Override
				public List<Node> components() {
					return node.components();
				}

				@Override
				public List<Variable> variables() {
					return Collections.emptyList();
				}

			});
		}
		return this;
	}

	@Override
	public Node node() {
		return setContextOfIncludesWith(createNode());
	}

	private Node setContextOfIncludesWith(Node node) {
		for (ScriptNode include : includes) include.context(node);
		return node;
	}

	private ScriptNode createNode() {
		return new ScriptNode() {
			@Override
			public String name() {
				return name;
			}

			@Override
			public Node parent() {
				return parent;
			}

			@Override
			public boolean hasSubs() {
				return false; //TODO
			}


			@Override
			public Node destinyOfReference() {
				return this;
			}

			@Override
			public String plate() {
				return plate;
			}

			@Override
			public String type() {
				return type;
			}

			@Override
			public boolean isReference() {
				return false; //TODO
			}

			@Override
			public void type(String newType) {
				type = newType;
			}

			@Override
			public List<String> secondaryTypes() {
				return Collections.emptyList();
			}

			@Override
			public List<String> types() {
				List<String> types = new ArrayList<>();
				types.add(type());
				types.addAll(facets.stream().map(Facet::type).collect(Collectors.toList()));
				return types;
			}

			@Override
			public List<String> annotations() {
				return new ArrayList<>(annotations);
			}

			@Override
			public List<String> flags() {
				return new ArrayList<>(flags);

			}

			public void annotations(String... annotationList) {
				Collections.addAll(annotations, annotationList);
			}

			@Override
			public void flags(String... flags) {
				Collections.addAll(ScriptDefinition.this.flags, flags);
			}

			@Override
			public void moveToTheTop() {

			}

			@Override
			public List<Facet> facets() {
				return facets;
			}

			@Override
			public List<FacetTarget> facetTargets() {
				return facetTargets;
			}

			@Override
			public List<Parameter> parameters() {
				return parameters;
			}

			@Override
			public List<Node> components() {
				return Collections.emptyList();//components;
			}

			@Override
			public List<Variable> variables() {
				return Collections.emptyList();
			}

		};
	}
}
