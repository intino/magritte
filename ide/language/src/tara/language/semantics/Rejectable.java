package tara.language.semantics;

import tara.language.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public abstract class Rejectable {

	public static List<Rejectable> build(Node node) {
		List<Rejectable> rejectables = new ArrayList<>();
		if (node.name() != null && !node.name().isEmpty()) rejectables.add(Name.instance);
		rejectables.addAll(node.parameters().stream().map(Parameter::new).collect(Collectors.toList()));
		rejectables.addAll(node.facets().stream().map(Facet::new).collect(Collectors.toList()));
		rejectables.addAll(node.components().stream().map(Include::new).collect(Collectors.toList()));
		return rejectables;
	}

	public static List<Rejectable> build(tara.language.model.Facet facet) {
		List<Rejectable> rejectables = facet.parameters().stream().map(Parameter::new).collect(Collectors.toList());
		rejectables.addAll(facet.components().stream().map(Include::new).collect(Collectors.toList()));
		return rejectables;
	}

	public abstract SemanticError error();

	public static class Name extends Rejectable {
		public static final Name instance = new Name();

		@Override
		public SemanticError error() {
			return new SemanticError("reject.name", null, Collections.EMPTY_LIST);
		}
	}

	public static class Parameter extends Rejectable {

		private final tara.language.model.Parameter parameter;
		private Cause cause = Cause.NAME;
		private String expectedType;
		private List<String> expectedValues;

		public Parameter(tara.language.model.Parameter parameter) {
			this.parameter = parameter;
		}

		public void invalidType(String expectedType) {
			this.cause = Cause.TYPE;
			this.expectedType = expectedType;
		}

		public void invalidValue(List<String> expectedValues) {
			this.cause = Cause.VALUE;
			this.expectedValues = expectedValues;
		}

		public tara.language.model.Parameter getParameter() {
			return parameter;
		}

		@Override
		public SemanticError error() {
			if (cause.equals(Cause.NAME))
				return new SemanticError("reject.parameter.in.context", parameter, singletonList(parameter.name()));
			if (cause.equals(Cause.MIXED_TYPE))
				return new SemanticError("reject.mixed.type.parameter", parameter, asList(parameter.values(), expectedType));
			if (cause.equals(Cause.VALUE))
				return new SemanticError("reject.parameter.word.allowed.value.in.context", parameter, singletonList(String.join(", ", expectedValues)));
			else
				return new SemanticError("reject.parameter.type.in.context", parameter, singletonList(expectedType));
		}

		public void mixedTypesInArray(String type) {
			this.cause = Cause.MIXED_TYPE;
			this.expectedType = type;
		}

		@Override
		public String toString() {
			return parameter.name();
		}

		public enum Cause {
			NAME, TYPE, VALUE, MIXED_TYPE, NO_NATIVE_SIGNATURE
		}
	}

	public static class Include extends Rejectable {

		private final Node node;
		private Cause cause = Cause.NOT_ALLOWED;

		public Include(Node node) {
			this.node = node;
		}

		public Node getNode() {
			return node;
		}

		public void multiple() {
			cause = Cause.NOT_SINGLE;
		}

		@Override
		public SemanticError error() {
			return cause.equals(Cause.NOT_ALLOWED) ?
				new SemanticError("reject.unknown.type.in.context", node, asList(node.type(), node.name())) :
				new SemanticError("reject.multiple.node.in.context", node, asList(node.type(), node.name()));
		}

		public enum Cause {
			NOT_ALLOWED, NOT_SINGLE
		}
	}

	public static class Facet extends Rejectable {

		private final tara.language.model.Facet facet;
		private Cause cause = Cause.NOT_ALLOWED;
		private List<String> neededTypes;

		public Facet(tara.language.model.Facet facet) {
			this.facet = facet;
		}

		public tara.language.model.Facet getFacet() {
			return facet;
		}

		public void constrains(List<String> types) {
			cause = Cause.CONSTRAINS_NOT_FOUND;
			this.neededTypes = types;
		}

		@Override
		public SemanticError error() {
			if (cause.equals(Cause.NOT_ALLOWED))
				return new SemanticError("reject.unknown.facet.in.context", facet, singletonList(facet.type()));
			if (cause.equals(Cause.CONSTRAINS_NOT_FOUND))
				return new SemanticError("reject.facet.with.no.constrains.in.context", facet, singletonList(toString(neededTypes)));
			else return new SemanticError("reject.parameter.in.context", facet, singletonList(facet.type()));
		}

		private String toString(List<String> neededTypes) {
			String types = "";
			for (String neededType : neededTypes) types += ", " + neededType;
			return types.substring(2);
		}

		public enum Cause {
			NOT_ALLOWED, CONSTRAINS_NOT_FOUND, ERRONEUS_PARAMETER
		}
	}

}
