package teseo;

import teseo.*;

import java.util.*;

public abstract class Method extends tara.magritte.Layer implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	
	protected java.util.List<teseo.Parameter> parameterList = new java.util.ArrayList<>();
	protected teseo.Response response;
	protected java.util.List<teseo.Exception> exceptionList = new java.util.ArrayList<>();

	public Method(tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<teseo.Parameter> parameterList() {
		return parameterList;
	}

	public teseo.Parameter parameter(int index) {
		return parameterList.get(index);
	}

	public java.util.List<teseo.Parameter> parameterList(java.util.function.Predicate<teseo.Parameter> predicate) {
		return parameterList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public teseo.Response response() {
		return response;
	}

	public java.util.List<teseo.Exception> exceptionList() {
		return exceptionList;
	}

	public teseo.Exception exception(int index) {
		return exceptionList.get(index);
	}

	public java.util.List<teseo.Exception> exceptionList(java.util.function.Predicate<teseo.Exception> predicate) {
		return exceptionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	public void response(teseo.Response value) {
		this.response = value;
	}

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		parameterList.stream().forEach(c -> components.add(c.node()));
		if (response != null) components.add(this.response.node());
		exceptionList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Method.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Parameter")) this.parameterList.add(node.as(teseo.Parameter.class));
		if (node.is("Response")) this.response = node.as(teseo.Response.class);
		if (node.is("Exception")) this.exceptionList.add(node.as(teseo.Exception.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Parameter")) this.parameterList.remove(node.as(teseo.Parameter.class));
        if (node.is("Response")) this.response = null;
        if (node.is("Exception")) this.exceptionList.remove(node.as(teseo.Exception.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create {
		protected final java.lang.String name;

		public Create(java.lang.String name) {
			this.name = name;
		}

		public teseo.Parameter parameter() {
		    teseo.Parameter newElement = graph().concept(teseo.Parameter.class).createNode(name, node()).as(teseo.Parameter.class);
		    return newElement;
		}

		public teseo.Response response() {
		    teseo.Response newElement = graph().concept(teseo.Response.class).createNode(name, node()).as(teseo.Response.class);
		    return newElement;
		}

		public teseo.Exception exception(teseo.rules.ExceptionCodes code) {
		    teseo.Exception newElement = graph().concept(teseo.Exception.class).createNode(name, node()).as(teseo.Exception.class);
			newElement.node().set(newElement, "code", java.util.Collections.singletonList(code)); 
		    return newElement;
		}
		
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
