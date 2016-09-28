package pandora.crontrigger;

import pandora.*;

import java.util.*;

public class CronTriggerTask extends pandora.scheduled.ScheduledTask implements tara.magritte.tags.Terminal {
	protected java.lang.String pattern;
	protected java.lang.String mean;
	protected pandora.Task _task;

	public CronTriggerTask(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String pattern() {
		return pattern;
	}

	public java.lang.String mean() {
		return mean;
	}

	public void pattern(java.lang.String value) {
		this.pattern = value;
	}

	public void mean(java.lang.String value) {
		this.mean = value;
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("pattern", new java.util.ArrayList(java.util.Collections.singletonList(this.pattern)));
		map.put("mean", new java.util.ArrayList(java.util.Collections.singletonList(this.mean)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Task.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("pattern")) this.pattern = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("mean")) this.mean = tara.magritte.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("pattern")) this.pattern = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("mean")) this.mean = (java.lang.String) values.get(0);
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
