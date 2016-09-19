package teseo.list;

import teseo.*;

import java.util.*;

public class ListData extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected tara.magritte.Expression<java.lang.String> type;
	protected teseo.Data _data;

	public ListData(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String type() {
		return type.value();
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("type", new java.util.ArrayList(java.util.Collections.singletonList(this.type)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Data.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("type")) this.type = tara.magritte.loaders.FunctionLoader.load(values, this, tara.magritte.Expression.class).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("type")) this.type = tara.magritte.loaders.FunctionLoader.load(values.get(0), this, tara.magritte.Expression.class);
	}

	@Override
	protected void _sync(tara.magritte.Layer layer) {
		super._sync(layer);
	    if (layer instanceof teseo.Data) _data = (teseo.Data) layer;
	    
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
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
