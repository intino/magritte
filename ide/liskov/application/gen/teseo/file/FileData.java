package teseo.file;

import teseo.*;

import java.util.*;

public class FileData extends teseo.type.TypeData implements tara.magritte.tags.Terminal {
	protected In in;

	public enum In {
		body, path, form, header, query;
	}

	public FileData(tara.magritte.Node node) {
		super(node);
	}

	public In in() {
		return in;
	}

	public void in(teseo.file.FileData.In value) {
		this.in = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("in", new java.util.ArrayList(java.util.Collections.singletonList(this.in)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Data.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		_data.node().load(_data, name, values);
		if (name.equalsIgnoreCase("in")) this.in = tara.magritte.loaders.WordLoader.load(values, In.class, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		_data.node().set(_data, name, values);
		if (name.equalsIgnoreCase("in")) this.in = (In) values.get(0);
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
