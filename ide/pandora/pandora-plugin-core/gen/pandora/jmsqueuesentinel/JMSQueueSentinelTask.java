package pandora.jmsqueuesentinel;

import pandora.*;

import java.util.*;

public class JMSQueueSentinelTask extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String queue;
	protected pandora.Task _task;

	public JMSQueueSentinelTask(tara.magritte.Node node) {
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
		return this.graph().concept(pandora.Task.class);
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
