package tara.semantic;

import tara.semantic.model.Facet;
import tara.semantic.model.Node;
import tara.semantic.model.Parameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ScriptFacetDefinition implements FacetDefinition {
	String facet;
	List<ScriptNode> includes = new ArrayList<>();
	List<Parameter> parameters = new ArrayList<>();

	@Override
	public FacetDefinition as(String facet) {
		this.facet = facet;
		return this;
	}

	@Override
	public FacetDefinition parameter(final int position, final String name, final Object... values) {
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
			public void multiple(boolean multiple) {

			}

			@Override
			public boolean isMultiple() {
				return false;
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
				return null;
			}

			@Override
			public String metric() {
				return null;
			}

			@Override
			public void contract(String contract) {

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
	public FacetDefinition parameter(final int position, final Object... values) {
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
				return "";
			}

			@Override
			public void name(String name) {

			}

			@Override
			public List<Object> values() {
				return null;
			}

			@Override
			public String contract() {
				return null;
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

		return null;
	}

	@Override
	public FacetDefinition include(Node node) {
		includes.add((ScriptNode) node);
		return this;
	}

	@Override
	public Facet facet() {
		return new Facet() {
			@Override
			public String type() {
				return facet;
			}

			@Override
			public List<String> nodeTypes() {
				return Collections.emptyList();
			}

			@Override
			public List<Parameter> parameters() {
				return parameters;
			}

			@Override
			public List<Node> components() {
				return Collections.emptyList();//nodes
			}
		};
	}
}
