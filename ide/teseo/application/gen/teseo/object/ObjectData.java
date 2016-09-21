package teseo.object;

import teseo.*;

import java.util.*;

public class ObjectData extends teseo.type.TypeData implements tara.magritte.tags.Terminal {
	protected teseo.Schema schema;

	public ObjectData(tara.magritte.Node node) {
		super(node);
	}

	public teseo.Schema schema() {
		return schema;
	}

	public void schema(teseo.Schema value) {
		this.schema = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("schema", this.schema != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.schema)) : java.util.Collections.emptyList());
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Data.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		_data.node().load(_data, name, values);
		if (name.equalsIgnoreCase("schema")) this.schema = tara.magritte.loaders.NodeLoader.load(values, teseo.Schema.class, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		_data.node().set(_data, name, values);
		if (name.equalsIgnoreCase("schema")) this.schema = values.get(0)!= null ? graph().loadNode(((tara.magritte.Layer) values.get(0)).id()).as(teseo.Schema.class) : null;
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends teseo.type.TypeData.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}
		
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
