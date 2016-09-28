package pandora.list;

import pandora.*;

import java.util.*;

public class ListData extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	
	protected pandora.Data _data;

	public ListData(tara.magritte.Node node) {
		super(node);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Data.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
	}

	@Override
	protected void _sync(tara.magritte.Layer layer) {
		super._sync(layer);
	    if (layer instanceof pandora.Data) _data = (pandora.Data) layer;
	    
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
