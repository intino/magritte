package teseo;

import teseo.*;

import java.util.*;

public class Topic extends teseo.SubscriptionModel implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	protected Type type;

	public enum Type {
		Volatile, Durable;
	}

	public Topic(tara.magritte.Node node) {
		super(node);
	}

	public Type type() {
		return type;
	}

	public void type(teseo.Topic.Type value) {
		this.type = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("type", new java.util.ArrayList(java.util.Collections.singletonList(this.type)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Topic.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("type")) this.type = tara.magritte.loaders.WordLoader.load(values, Type.class, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("type")) this.type = (Type) values.get(0);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends teseo.SubscriptionModel.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}
		
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
