package pandora;

import pandora.*;

import java.util.*;

public class Resource extends pandora.Method implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	protected java.lang.String title;
	protected java.lang.String description;
	protected java.lang.String path;
	protected Type type;

	public enum Type {
		Get, Post, Put, Delete;
	}
	

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

	public void type(pandora.Resource.Type value) {
		this.type = value;
	}

	public java.util.List<pandora.Resource.Parameter> resourceParameterList() {
		return new tara.magritte.utils.ProxyList<>(parameterList, pandora.Resource.Parameter.class);
	}

	public pandora.Resource.Parameter resourceParameter(int index) {
		return resourceParameterList().get(index);
	}

	public java.util.List<pandora.Resource.Parameter> resourceParameterList(java.util.function.Predicate<pandora.Resource.Parameter> predicate) {
		return resourceParameterList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
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
		return this.graph().concept(pandora.Resource.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        
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

	public class Create extends pandora.Method.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}

		public pandora.Resource.Parameter parameter(pandora.Resource.Parameter.In in) {
		    pandora.Resource.Parameter newElement = graph().concept(pandora.Resource.Parameter.class).createNode(name, node()).as(pandora.Resource.Parameter.class);
			newElement.node().set(newElement, "in", java.util.Collections.singletonList(in)); 
		    return newElement;
		}
		
	}
	
	public static class Parameter extends pandora.Parameter implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
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

		public void in(pandora.Resource.Parameter.In value) {
			this.in = value;
		}

		public pandora.integer.IntegerData asInteger() {
			tara.magritte.Layer as = this.as(pandora.integer.IntegerData.class);
			return as != null ? (pandora.integer.IntegerData) as : addFacet(pandora.integer.IntegerData.class);
		}

		public boolean isInteger() {
			return is(pandora.integer.IntegerData.class);
		}

		public pandora.type.TypeData asType() {
			return this.as(pandora.type.TypeData.class);
		}

		public pandora.type.TypeData asType(tara.magritte.Expression<java.lang.String> type) {
			pandora.type.TypeData newElement = addFacet(pandora.type.TypeData.class);
			newElement.node().set(newElement, "type", java.util.Collections.singletonList(type)); 
		    return newElement;
		}

		public boolean isType() {
			return is(pandora.type.TypeData.class);
		}

		public void removeType() {
			this.removeFacet(pandora.type.TypeData.class);
		}

		public pandora.bool.BoolData asBool() {
			tara.magritte.Layer as = this.as(pandora.bool.BoolData.class);
			return as != null ? (pandora.bool.BoolData) as : addFacet(pandora.bool.BoolData.class);
		}

		public boolean isBool() {
			return is(pandora.bool.BoolData.class);
		}

		public pandora.real.RealData asReal() {
			tara.magritte.Layer as = this.as(pandora.real.RealData.class);
			return as != null ? (pandora.real.RealData) as : addFacet(pandora.real.RealData.class);
		}

		public boolean isReal() {
			return is(pandora.real.RealData.class);
		}

		public pandora.text.TextData asText() {
			tara.magritte.Layer as = this.as(pandora.text.TextData.class);
			return as != null ? (pandora.text.TextData) as : addFacet(pandora.text.TextData.class);
		}

		public boolean isText() {
			return is(pandora.text.TextData.class);
		}

		public pandora.object.ObjectData asObject() {
			return this.as(pandora.object.ObjectData.class);
		}

		public pandora.object.ObjectData asObject(pandora.Format format) {
			pandora.object.ObjectData newElement = addFacet(pandora.object.ObjectData.class);
			newElement.node().set(newElement, "format", java.util.Collections.singletonList(format)); 
		    return newElement;
		}

		public boolean isObject() {
			return is(pandora.object.ObjectData.class);
		}

		public void removeObject() {
			this.removeFacet(pandora.object.ObjectData.class);
		}

		public pandora.list.ListData asList() {
			tara.magritte.Layer as = this.as(pandora.list.ListData.class);
			return as != null ? (pandora.list.ListData) as : addFacet(pandora.list.ListData.class);
		}

		public boolean isList() {
			return is(pandora.list.ListData.class);
		}

		public pandora.file.FileData asFile() {
			tara.magritte.Layer as = this.as(pandora.file.FileData.class);
			return as != null ? (pandora.file.FileData) as : addFacet(pandora.file.FileData.class);
		}

		public boolean isFile() {
			return is(pandora.file.FileData.class);
		}

		public pandora.date.DateData asDate() {
			tara.magritte.Layer as = this.as(pandora.date.DateData.class);
			return as != null ? (pandora.date.DateData) as : addFacet(pandora.date.DateData.class);
		}

		public boolean isDate() {
			return is(pandora.date.DateData.class);
		}

		public pandora.datetime.DateTimeData asDateTime() {
			tara.magritte.Layer as = this.as(pandora.datetime.DateTimeData.class);
			return as != null ? (pandora.datetime.DateTimeData) as : addFacet(pandora.datetime.DateTimeData.class);
		}

		public boolean isDateTime() {
			return is(pandora.datetime.DateTimeData.class);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
			map.put("in", new java.util.ArrayList(java.util.Collections.singletonList(this.in)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.Resource.Parameter.class);
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

		public class Create extends pandora.Parameter.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}
			
		}
		
		public pandora.PandoraApplication application() {
			return ((pandora.PandoraApplication) graph().application());
		}
	}
	
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
