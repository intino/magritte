package pandora.date;

import pandora.*;

import java.util.*;

public class DateData extends pandora.type.TypeData implements tara.magritte.tags.Terminal {
	protected java.lang.String format;

	public DateData(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String format() {
		return format;
	}

	public void format(java.lang.String value) {
		this.format = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("format", new java.util.ArrayList(java.util.Collections.singletonList(this.format)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Data.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		_data.node().load(_data, name, values);
		if (name.equalsIgnoreCase("format")) this.format = tara.magritte.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		_data.node().set(_data, name, values);
		if (name.equalsIgnoreCase("format")) this.format = (java.lang.String) values.get(0);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends pandora.type.TypeData.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}
		
	}
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
