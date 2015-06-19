package siani.tara.semantic;

import siani.tara.semantic.model.Facet;
import siani.tara.semantic.model.Node;
import siani.tara.semantic.model.Parameter;

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
			public void setInferredType(String type) {

			}

			@Override
			public List<String> getAnnotations() {
				return null;
			}

			@Override
			public void setAnnotations(List<String> annotations) {

			}


			@Override
			public void setMultiple(boolean multiple) {

			}

			@Override
			public boolean isMultiple() {
				return false;
			}

			@Override
			public int getPosition() {
				return position;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public void setName(String name) {

			}

			@Override
			public List<Object> getValues() {
				return Arrays.asList(values);
			}

			@Override
			public String getContract() {
				return null;
			}

			@Override
			public String getMetric() {
				return null;
			}

			@Override
			public void setContract(String contract) {

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
			public void setInferredType(String type) {

			}

			@Override
			public List<String> getAnnotations() {
				return null;
			}

			@Override
			public void setAnnotations(List<String> annotations) {

			}

			@Override
			public boolean isMultiple() {
				return false;
			}

			@Override
			public void setMultiple(boolean multiple) {

			}

			@Override
			public int getPosition() {
				return position;
			}

			@Override
			public String getName() {
				return "";
			}

			@Override
			public void setName(String name) {

			}

			@Override
			public List<Object> getValues() {
				return null;
			}

			@Override
			public String getContract() {
				return null;
			}

			@Override
			public void setContract(String contract) {

			}

			@Override
			public String getMetric() {
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
			public List<Parameter> parameters() {
				return parameters;
			}

			@Override
			public List<Node> includes() {
				return Collections.emptyList();//nodes
			}
		};
	}
}
