package teseo.real;

import teseo.*;

import java.util.*;

public class RealData extends teseo.type.TypeData implements tara.magritte.tags.Terminal {
	protected double defaultValue;

	public RealData(tara.magritte.Node node) {
		super(node);
	}

	public double defaultValue() {
		return defaultValue;
	}

	public void defaultValue(double value) {
		this.defaultValue = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("defaultValue", new java.util.ArrayList(java.util.Collections.singletonList(this.defaultValue)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Data.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		_data.node().load(_data, name, values);
		if (name.equalsIgnoreCase("defaultValue")) this.defaultValue = tara.magritte.loaders.DoubleLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		_data.node().set(_data, name, values);
		if (name.equalsIgnoreCase("defaultValue")) this.defaultValue = (java.lang.Double) values.get(0);
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
