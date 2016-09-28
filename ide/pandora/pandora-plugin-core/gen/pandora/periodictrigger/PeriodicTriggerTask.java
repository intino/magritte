package pandora.periodictrigger;

import pandora.*;

import java.util.*;

public class PeriodicTriggerTask extends pandora.scheduled.ScheduledTask implements tara.magritte.tags.Terminal {
	protected int delay;
	protected int interval;
	protected pandora.Task _task;

	public PeriodicTriggerTask(tara.magritte.Node node) {
		super(node);
	}

	public int delay() {
		return delay;
	}

	public int interval() {
		return interval;
	}

	public void delay(int value) {
		this.delay = value;
	}

	public void interval(int value) {
		this.interval = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("delay", new java.util.ArrayList(java.util.Collections.singletonList(this.delay)));
		map.put("interval", new java.util.ArrayList(java.util.Collections.singletonList(this.interval)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Task.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("delay")) this.delay = tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("interval")) this.interval = tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("delay")) this.delay = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("interval")) this.interval = (java.lang.Integer) values.get(0);
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

	public class Create extends pandora.scheduled.ScheduledTask.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}
		
	}
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
