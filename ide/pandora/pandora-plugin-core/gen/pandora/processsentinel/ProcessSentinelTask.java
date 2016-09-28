package pandora.processsentinel;

import pandora.*;

import java.util.*;

public class ProcessSentinelTask extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String processName;
	protected List<Events> events = new java.util.ArrayList<>();

	public enum Events {
		OnStart, OnTerminate;
	}
	protected pandora.functions.ProcessChecker check;
	protected pandora.Task _task;

	public ProcessSentinelTask(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String processName() {
		return processName;
	}

	public java.util.List<Events> events() {
		return events;
	}

	public boolean check() {
		return check.check();
	}

	public void processName(java.lang.String value) {
		this.processName = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("processName", new java.util.ArrayList(java.util.Collections.singletonList(this.processName)));
		map.put("events", this.events);
		map.put("check", this.check != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.check)) : java.util.Collections.emptyList());
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Task.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("processName")) this.processName = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("events")) this.events = tara.magritte.loaders.WordLoader.load(values, Events.class, this);
		else if (name.equalsIgnoreCase("check")) this.check = tara.magritte.loaders.FunctionLoader.load(values, this, pandora.functions.ProcessChecker.class).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("processName")) this.processName = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("events")) this.events = new ArrayList<>((java.util.List<Events>) values);
		else if (name.equalsIgnoreCase("check")) this.check = tara.magritte.loaders.FunctionLoader.load(values.get(0), this, pandora.functions.ProcessChecker.class);
	}

	@Override
	protected void _sync(tara.magritte.Layer layer) {
		super._sync(layer);
	    if (layer instanceof pandora.Task) _task = (pandora.Task) layer;
	    
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
