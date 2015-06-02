package siani.tara.semantic;

import siani.tara.semantic.model.Node;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public abstract class Rejectable {

	public abstract SemanticError error();

	public static List<Rejectable> build(Node node) {
		List<Rejectable> rejectables = new ArrayList<>();
		if (!node.name().isEmpty()) rejectables.add(Name.instance);
		for (siani.tara.semantic.model.Parameter parameter : node.parameters())
			rejectables.add(new Parameter(parameter));
		for (siani.tara.semantic.model.Facet facet : node.facets())
			rejectables.add(new Facet(facet));
		for (Node content : node.includes())
			rejectables.add(new Include(content));
		return rejectables;
	}

	public static List<Rejectable> build(siani.tara.semantic.model.Facet facet) {
		List<Rejectable> rejectables = new ArrayList<>();
		for (siani.tara.semantic.model.Parameter parameter : facet.parameters())
			rejectables.add(new Parameter(parameter));
		for (Node content : facet.includes())
			rejectables.add(new Include(content));
		return rejectables;
	}


	public static class Name extends Rejectable {
		public static final Name instance = new Name();

		@Override
		public SemanticError error() {
			return new SemanticError("reject.name", null, Collections.EMPTY_LIST);
		}
	}

	public static class Parameter extends Rejectable {

		private final siani.tara.semantic.model.Parameter parameter;
		private Cause cause = Cause.NAME;
		private String expectedType;
		private List<String> expectedValues;

		public void invalidType(String expectedType) {
			this.cause = Cause.TYPE;
			this.expectedType = expectedType;
		}

		public void invalidValue(List<String> expectedValues) {
			this.cause = Cause.VALUE;
			this.expectedValues = expectedValues;
		}

		public Parameter(siani.tara.semantic.model.Parameter parameter) {
			this.parameter = parameter;
		}

		public siani.tara.semantic.model.Parameter getParameter() {
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
			NAME, TYPE, VALUE, MIXED_TYPE
		}
	}

	public static class Include extends Rejectable {

		private final Node node;
		private Cause cause = Cause.NOT_ALLOWED;

		public enum Cause {
			NOT_ALLOWED, NOT_SINGLE
		}

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
	}

	public static class Facet extends Rejectable {

		private final siani.tara.semantic.model.Facet facet;
		private Cause cause = Cause.NOT_ALLOWED;

		public enum Cause {
			NOT_ALLOWED, ERRONEUS_PARAMETER
		}

		public Facet(siani.tara.semantic.model.Facet facet) {
			this.facet = facet;
		}

		public siani.tara.semantic.model.Facet getFacet() {
			return facet;
		}

		@Override
		public SemanticError error() {
			return cause.equals(Cause.NOT_ALLOWED) ?
				new SemanticError("reject.unknown.facet.in.context", facet, singletonList(facet.type())) :
				new SemanticError("reject.parameter.in.context", facet, singletonList(facet.type()));
		}
	}

}
