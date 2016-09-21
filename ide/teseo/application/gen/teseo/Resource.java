package teseo;

import teseo.*;

import java.util.*;

public class Resource extends teseo.Method implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	protected java.lang.String title;
	protected java.lang.String description;
	protected java.lang.String path;
	protected Type type;

	public enum Type {
		Get, Post, Put, Delete;
	}
	
	protected teseo.Response response;
	protected java.util.List<teseo.Exception> exceptionList = new java.util.ArrayList<>();

	public Resource(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String title() {
		return title;
	}

	public java.lang.String description() {
		return description;
	}

	public java.lang.String path() {
		return path;
	}

	public Type type() {
		return type;
	}

	public void title(java.lang.String value) {
		this.title = value;
	}

	public void description(java.lang.String value) {
		this.description = value;
	}

	public void path(java.lang.String value) {
		this.path = value;
	}

	public void type(teseo.Resource.Type value) {
		this.type = value;
	}

	public java.util.List<teseo.Resource.Parameter> resourceParameterList() {
		return new tara.magritte.utils.ProxyList<>(parameterList, teseo.Resource.Parameter.class);
	}

	public teseo.Resource.Parameter resourceParameter(int index) {
		return resourceParameterList().get(index);
	}

	public java.util.List<teseo.Resource.Parameter> resourceParameterList(java.util.function.Predicate<teseo.Resource.Parameter> predicate) {
		return resourceParameterList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
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
		
		if (response != null) components.add(this.response.node());
		exceptionList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("title", new java.util.ArrayList(java.util.Collections.singletonList(this.title)));
		map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
		map.put("path", new java.util.ArrayList(java.util.Collections.singletonList(this.path)));
		map.put("type", new java.util.ArrayList(java.util.Collections.singletonList(this.type)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Resource.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		
		if (node.is("Response")) this.response = node.as(teseo.Response.class);
		if (node.is("Exception")) this.exceptionList.add(node.as(teseo.Exception.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        
        if (node.is("Response")) this.response = null;
        if (node.is("Exception")) this.exceptionList.remove(node.as(teseo.Exception.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("title")) this.title = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("path")) this.path = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("type")) this.type = tara.magritte.loaders.WordLoader.load(values, Type.class, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("title")) this.title = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("path")) this.path = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("type")) this.type = (Type) values.get(0);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends teseo.Method.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}

		public teseo.Resource.Parameter parameter(teseo.Resource.Parameter.In in) {
		    teseo.Resource.Parameter newElement = graph().concept(teseo.Resource.Parameter.class).createNode(name, node()).as(teseo.Resource.Parameter.class);
			newElement.node().set(newElement, "in", java.util.Collections.singletonList(in)); 
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
	
	public static class Parameter extends teseo.Parameter implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
		protected java.lang.String description;
		protected In in;

		public enum In {
			body, path, form, header, query;
		}

		public Parameter(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String description() {
			return description;
		}

		public In in() {
			return in;
		}

		public void description(java.lang.String value) {
			this.description = value;
		}

		public void in(teseo.Resource.Parameter.In value) {
			this.in = value;
		}

		public teseo.integer.IntegerData asInteger() {
			tara.magritte.Layer as = this.as(teseo.integer.IntegerData.class);
			return as != null ? (teseo.integer.IntegerData) as : addFacet(teseo.integer.IntegerData.class);
		}

		public boolean isInteger() {
			return is(teseo.integer.IntegerData.class);
		}

		public teseo.type.TypeData asType() {
			return this.as(teseo.type.TypeData.class);
		}

		public teseo.type.TypeData asType(tara.magritte.Expression<java.lang.String> type) {
			teseo.type.TypeData newElement = addFacet(teseo.type.TypeData.class);
			newElement.node().set(newElement, "type", java.util.Collections.singletonList(type)); 
		    return newElement;
		}

		public boolean isType() {
			return is(teseo.type.TypeData.class);
		}

		public void removeType() {
			this.removeFacet(teseo.type.TypeData.class);
		}

		public teseo.bool.BoolData asBool() {
			tara.magritte.Layer as = this.as(teseo.bool.BoolData.class);
			return as != null ? (teseo.bool.BoolData) as : addFacet(teseo.bool.BoolData.class);
		}

		public boolean isBool() {
			return is(teseo.bool.BoolData.class);
		}

		public teseo.real.RealData asReal() {
			tara.magritte.Layer as = this.as(teseo.real.RealData.class);
			return as != null ? (teseo.real.RealData) as : addFacet(teseo.real.RealData.class);
		}

		public boolean isReal() {
			return is(teseo.real.RealData.class);
		}

		public teseo.text.TextData asText() {
			tara.magritte.Layer as = this.as(teseo.text.TextData.class);
			return as != null ? (teseo.text.TextData) as : addFacet(teseo.text.TextData.class);
		}

		public boolean isText() {
			return is(teseo.text.TextData.class);
		}

		public teseo.object.ObjectData asObject() {
			return this.as(teseo.object.ObjectData.class);
		}

		public teseo.object.ObjectData asObject(teseo.Schema schema) {
			teseo.object.ObjectData newElement = addFacet(teseo.object.ObjectData.class);
			newElement.node().set(newElement, "schema", java.util.Collections.singletonList(schema)); 
		    return newElement;
		}

		public boolean isObject() {
			return is(teseo.object.ObjectData.class);
		}

		public void removeObject() {
			this.removeFacet(teseo.object.ObjectData.class);
		}

		public teseo.list.ListData asList() {
			tara.magritte.Layer as = this.as(teseo.list.ListData.class);
			return as != null ? (teseo.list.ListData) as : addFacet(teseo.list.ListData.class);
		}

		public boolean isList() {
			return is(teseo.list.ListData.class);
		}

		public teseo.file.FileData asFile() {
			tara.magritte.Layer as = this.as(teseo.file.FileData.class);
			return as != null ? (teseo.file.FileData) as : addFacet(teseo.file.FileData.class);
		}

		public boolean isFile() {
			return is(teseo.file.FileData.class);
		}

		public teseo.date.DateData asDate() {
			tara.magritte.Layer as = this.as(teseo.date.DateData.class);
			return as != null ? (teseo.date.DateData) as : addFacet(teseo.date.DateData.class);
		}

		public boolean isDate() {
			return is(teseo.date.DateData.class);
		}

		public teseo.datetime.DateTimeData asDateTime() {
			tara.magritte.Layer as = this.as(teseo.datetime.DateTimeData.class);
			return as != null ? (teseo.datetime.DateTimeData) as : addFacet(teseo.datetime.DateTimeData.class);
		}

		public boolean isDateTime() {
			return is(teseo.datetime.DateTimeData.class);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
			map.put("in", new java.util.ArrayList(java.util.Collections.singletonList(this.in)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(teseo.Resource.Parameter.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("in")) this.in = tara.magritte.loaders.WordLoader.load(values, In.class, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("in")) this.in = (In) values.get(0);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends teseo.Parameter.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}
			
		}
		
		public teseo.TeseoApplication application() {
			return ((teseo.TeseoApplication) graph().application());
		}
	}
	
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
