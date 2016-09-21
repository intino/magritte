package teseo.jmsqueuewatcher;

import teseo.*;

import java.util.*;

public class JMSQueueWatcherTrigger extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String queue;
	protected teseo.Trigger _trigger;

	public JMSQueueWatcherTrigger(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String queue() {
		return queue;
	}

	public void queue(java.lang.String value) {
		this.queue = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("queue", new java.util.ArrayList(java.util.Collections.singletonList(this.queue)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Trigger.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("queue")) this.queue = tara.magritte.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("queue")) this.queue = (java.lang.String) values.get(0);
	}

	@Override
	protected void _sync(tara.magritte.Layer layer) {
		super._sync(layer);
	    if (layer instanceof teseo.Trigger) _trigger = (teseo.Trigger) layer;
	    
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
