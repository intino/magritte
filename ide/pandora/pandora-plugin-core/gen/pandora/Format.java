package pandora;

import pandora.*;

import java.util.*;

public class Format extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected boolean required;
	protected boolean multiple;
	protected pandora.Format.AttributeMap attributeMap;
	protected java.util.List<pandora.Format.Attribute> attributeList = new java.util.ArrayList<>();
	protected java.util.List<pandora.Format.Has> hasList = new java.util.ArrayList<>();
	protected java.util.List<pandora.Format> formatList = new java.util.ArrayList<>();

	public Format(tara.magritte.Node node) {
		super(node);
	}

	public boolean required() {
		return required;
	}

	public boolean multiple() {
		return multiple;
	}

	public void required(boolean value) {
		this.required = value;
	}

	public void multiple(boolean value) {
		this.multiple = value;
	}

	public pandora.Format.AttributeMap attributeMap() {
		return attributeMap;
	}

	public java.util.List<pandora.Format.Attribute> attributeList() {
		return attributeList;
	}

	public pandora.Format.Attribute attribute(int index) {
		return attributeList.get(index);
	}

	public java.util.List<pandora.Format.Attribute> attributeList(java.util.function.Predicate<pandora.Format.Attribute> predicate) {
		return attributeList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<pandora.Format.Has> hasList() {
		return hasList;
	}

	public pandora.Format.Has has(int index) {
		return hasList.get(index);
	}

	public java.util.List<pandora.Format.Has> hasList(java.util.function.Predicate<pandora.Format.Has> predicate) {
		return hasList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<pandora.Format> formatList() {
		return formatList;
	}

	public pandora.Format format(int index) {
		return formatList.get(index);
	}

	public java.util.List<pandora.Format> formatList(java.util.function.Predicate<pandora.Format> predicate) {
		return formatList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public void attributeMap(pandora.Format.AttributeMap value) {
		this.attributeMap = value;
	}

	

	

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		if (attributeMap != null) components.add(this.attributeMap.node());
		attributeList.stream().forEach(c -> components.add(c.node()));
		hasList.stream().forEach(c -> components.add(c.node()));
		formatList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("required", new java.util.ArrayList(java.util.Collections.singletonList(this.required)));
		map.put("multiple", new java.util.ArrayList(java.util.Collections.singletonList(this.multiple)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Format.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Format$AttributeMap")) this.attributeMap = node.as(pandora.Format.AttributeMap.class);
		if (node.is("Format$Attribute")) this.attributeList.add(node.as(pandora.Format.Attribute.class));
		if (node.is("Format$Has")) this.hasList.add(node.as(pandora.Format.Has.class));
		if (node.is("Format")) this.formatList.add(node.as(pandora.Format.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Format$AttributeMap")) this.attributeMap = null;
        if (node.is("Format$Attribute")) this.attributeList.remove(node.as(pandora.Format.Attribute.class));
        if (node.is("Format$Has")) this.hasList.remove(node.as(pandora.Format.Has.class));
        if (node.is("Format")) this.formatList.remove(node.as(pandora.Format.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("required")) this.required = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("multiple")) this.multiple = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("required")) this.required = (java.lang.Boolean) values.get(0);
		else if (name.equalsIgnoreCase("multiple")) this.multiple = (java.lang.Boolean) values.get(0);
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

		public pandora.Format.AttributeMap attributeMap() {
		    pandora.Format.AttributeMap newElement = graph().concept(pandora.Format.AttributeMap.class).createNode(name, node()).as(pandora.Format.AttributeMap.class);
		    return newElement;
		}

		public pandora.Format.Attribute attribute() {
		    pandora.Format.Attribute newElement = graph().concept(pandora.Format.Attribute.class).createNode(name, node()).as(pandora.Format.Attribute.class);
		    return newElement;
		}

		public pandora.Format.Has has(pandora.Format reference) {
		    pandora.Format.Has newElement = graph().concept(pandora.Format.Has.class).createNode(name, node()).as(pandora.Format.Has.class);
			newElement.node().set(newElement, "reference", java.util.Collections.singletonList(reference)); 
		    return newElement;
		}

		public pandora.Format format() {
		    pandora.Format newElement = graph().concept(pandora.Format.class).createNode(name, node()).as(pandora.Format.class);
		    return newElement;
		}
		
	}
	
	public static class AttributeMap extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		

		public AttributeMap(tara.magritte.Node node) {
			super(node);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.Format.AttributeMap.class);
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
			
		}
		
		public pandora.PandoraApplication application() {
			return ((pandora.PandoraApplication) graph().application());
		}
	}
	
	public static class Attribute extends pandora.Data implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
		protected boolean required;
		protected boolean multiple;

		public Attribute(tara.magritte.Node node) {
			super(node);
		}

		public boolean required() {
			return required;
		}

		public boolean multiple() {
			return multiple;
		}

		public void required(boolean value) {
			this.required = value;
		}

		public void multiple(boolean value) {
			this.multiple = value;
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
			map.put("required", new java.util.ArrayList(java.util.Collections.singletonList(this.required)));
			map.put("multiple", new java.util.ArrayList(java.util.Collections.singletonList(this.multiple)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.Format.Attribute.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("required")) this.required = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("multiple")) this.multiple = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("required")) this.required = (java.lang.Boolean) values.get(0);
			else if (name.equalsIgnoreCase("multiple")) this.multiple = (java.lang.Boolean) values.get(0);
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
	
	public static class Has extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected pandora.Format reference;
		protected boolean required;
		protected boolean multiple;

		public Has(tara.magritte.Node node) {
			super(node);
		}

		public pandora.Format reference() {
			return reference;
		}

		public boolean required() {
			return required;
		}

		public boolean multiple() {
			return multiple;
		}

		public void reference(pandora.Format value) {
			this.reference = value;
		}

		public void required(boolean value) {
			this.required = value;
		}

		public void multiple(boolean value) {
			this.multiple = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("reference", this.reference != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.reference)) : java.util.Collections.emptyList());
			map.put("required", new java.util.ArrayList(java.util.Collections.singletonList(this.required)));
			map.put("multiple", new java.util.ArrayList(java.util.Collections.singletonList(this.multiple)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.Format.Has.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("reference")) this.reference = tara.magritte.loaders.NodeLoader.load(values, pandora.Format.class, this).get(0);
			else if (name.equalsIgnoreCase("required")) this.required = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("multiple")) this.multiple = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("reference")) this.reference = values.get(0)!= null ? graph().loadNode(((tara.magritte.Layer) values.get(0)).id()).as(pandora.Format.class) : null;
			else if (name.equalsIgnoreCase("required")) this.required = (java.lang.Boolean) values.get(0);
			else if (name.equalsIgnoreCase("multiple")) this.multiple = (java.lang.Boolean) values.get(0);
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
	
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
