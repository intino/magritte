package pandora.file;

import pandora.*;

import java.util.*;

public class FileData extends pandora.type.TypeData implements tara.magritte.tags.Terminal {
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

	public void in(pandora.file.FileData.In value) {
		this.in = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("in", new java.util.ArrayList(java.util.Collections.singletonList(this.in)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Data.class);
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

	public class Create extends pandora.type.TypeData.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}
		
	}
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
