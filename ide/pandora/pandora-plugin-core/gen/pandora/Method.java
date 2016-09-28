package pandora;

import pandora.*;

import java.util.*;

public abstract class Method extends tara.magritte.Layer implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	
	protected java.util.List<pandora.Parameter> parameterList = new java.util.ArrayList<>();
	protected java.util.List<pandora.Method.Exception> exceptionList = new java.util.ArrayList<>();
	protected pandora.Method.Response response;

	public Method(tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<pandora.Parameter> parameterList() {
		return parameterList;
	}

	public pandora.Parameter parameter(int index) {
		return parameterList.get(index);
	}

	public java.util.List<pandora.Parameter> parameterList(java.util.function.Predicate<pandora.Parameter> predicate) {
		return parameterList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<pandora.Method.Exception> exceptionList() {
		return exceptionList;
	}

	public pandora.Method.Exception exception(int index) {
		return exceptionList.get(index);
	}

	public java.util.List<pandora.Method.Exception> exceptionList(java.util.function.Predicate<pandora.Method.Exception> predicate) {
		return exceptionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.Method.Response response() {
		return response;
	}

	

	

	public void response(pandora.Method.Response value) {
		this.response = value;
	}

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		parameterList.stream().forEach(c -> components.add(c.node()));
		exceptionList.stream().forEach(c -> components.add(c.node()));
		if (response != null) components.add(this.response.node());
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Method.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Parameter")) this.parameterList.add(node.as(pandora.Parameter.class));
		if (node.is("Method$Exception")) this.exceptionList.add(node.as(pandora.Method.Exception.class));
		if (node.is("Method$Response")) this.response = node.as(pandora.Method.Response.class);
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Parameter")) this.parameterList.remove(node.as(pandora.Parameter.class));
        if (node.is("Method$Exception")) this.exceptionList.remove(node.as(pandora.Method.Exception.class));
        if (node.is("Method$Response")) this.response = null;
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

		public pandora.Parameter parameter() {
		    pandora.Parameter newElement = graph().concept(pandora.Parameter.class).createNode(name, node()).as(pandora.Parameter.class);
		    return newElement;
		}

		public pandora.Method.Exception exception(pandora.rules.ExceptionCodes code) {
		    pandora.Method.Exception newElement = graph().concept(pandora.Method.Exception.class).createNode(name, node()).as(pandora.Method.Exception.class);
			newElement.node().set(newElement, "code", java.util.Collections.singletonList(code)); 
		    return newElement;
		}

		public pandora.Method.Response response() {
		    pandora.Method.Response newElement = graph().concept(pandora.Method.Response.class).createNode(name, node()).as(pandora.Method.Response.class);
		    return newElement;
		}
		
	}
	
	public static class Exception extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected pandora.rules.ExceptionCodes code;
		protected java.lang.String description;

		public Exception(tara.magritte.Node node) {
			super(node);
		}

		public pandora.rules.ExceptionCodes code() {
			return code;
		}

		public java.lang.String description() {
			return description;
		}

		public void code(pandora.rules.ExceptionCodes value) {
			this.code = value;
		}

		public void description(java.lang.String value) {
			this.description = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("code", new java.util.ArrayList(java.util.Collections.singletonList(this.code)));
			map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.Method.Exception.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("code")) this.code = tara.magritte.loaders.WordLoader.load(values, pandora.rules.ExceptionCodes.class, this).get(0);
			else if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("code")) this.code = (pandora.rules.ExceptionCodes) values.get(0);
			else if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
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
			
		}
		
		public pandora.PandoraApplication application() {
			return ((pandora.PandoraApplication) graph().application());
		}
	}
	
	public static class Response extends pandora.Data implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
		protected java.lang.String description;

		public Response(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String description() {
			return description;
		}

		public void description(java.lang.String value) {
			this.description = value;
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
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.Method.Response.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends pandora.Data.Create {
			

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
