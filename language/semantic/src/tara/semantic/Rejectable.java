package tara.semantic;

import tara.semantic.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public abstract class Rejectable {

	public static List<Rejectable> build(Node node) {
		List<Rejectable> rejectables = new ArrayList<>();
		if (!node.name().isEmpty()) rejectables.add(Name.instance);
		for (tara.semantic.model.Parameter parameter : node.parameters())
			rejectables.add(new Parameter(parameter));
		for (tara.semantic.model.Facet facet : node.facets())
			rejectables.add(new Facet(facet));
		for (Node content : node.includes())
			rejectables.add(new Include(content));
		return rejectables;
	}

	public static List<Rejectable> build(tara.semantic.model.Facet facet) {
		List<Rejectable> rejectables = new ArrayList<>();
		for (tara.semantic.model.Parameter parameter : facet.parameters())
			rejectables.add(new Parameter(parameter));
		for (Node content : facet.includes())
			rejectables.add(new Include(content));
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

		private final tara.semantic.model.Parameter parameter;
		private Cause cause = Cause.NAME;
		private String expectedType;
		private List<String> expectedValues;

		public Parameter(tara.semantic.model.Parameter parameter) {
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

		public tara.semantic.model.Parameter getParameter() {
			return parameter;
		}

		@Override
		public SemanticError error() {
			if (cause.equals(Cause.NAME))
				return new SemanticError("reject.parameter.in.context", parameter, singletonList(parameter.getName()));
			if (cause.equals(Cause.MIXED_TYPE))
				return new SemanticError("reject.mixed.type.parameter", parameter, asList(parameter.getValues(), expectedType));
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
			return parameter.getName();
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

		private final tara.semantic.model.Facet facet;
		private Cause cause = Cause.NOT_ALLOWED;
		private List<String> neededTypes;

		public Facet(tara.semantic.model.Facet facet) {
			this.facet = facet;
		}

		public tara.semantic.model.Facet getFacet() {
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
