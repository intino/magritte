package teseo;

import teseo.*;

import java.util.*;

public abstract class SubscriptionModel extends tara.magritte.Layer implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	protected java.lang.String path;
	protected teseo.SubscriptionModel.Message message;

	public SubscriptionModel(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String path() {
		return path;
	}

	public void path(java.lang.String value) {
		this.path = value;
	}

	public teseo.SubscriptionModel.Message message() {
		return message;
	}

	public void message(teseo.SubscriptionModel.Message value) {
		this.message = value;
	}

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		if (message != null) components.add(this.message.node());
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("path", new java.util.ArrayList(java.util.Collections.singletonList(this.path)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.SubscriptionModel.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("SubscriptionModel$Message")) this.message = node.as(teseo.SubscriptionModel.Message.class);
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("SubscriptionModel$Message")) this.message = null;
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("path")) this.path = tara.magritte.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("path")) this.path = (java.lang.String) values.get(0);
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

		public teseo.SubscriptionModel.Message message() {
		    teseo.SubscriptionModel.Message newElement = graph().concept(teseo.SubscriptionModel.Message.class).createNode(name, node()).as(teseo.SubscriptionModel.Message.class);
		    return newElement;
		}
		
	}
	
	public static class Message extends teseo.Data implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
		

		public Message(tara.magritte.Node node) {
			super(node);
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
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(teseo.SubscriptionModel.Message.class);
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

		public class Create extends teseo.Data.Create {
			

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
