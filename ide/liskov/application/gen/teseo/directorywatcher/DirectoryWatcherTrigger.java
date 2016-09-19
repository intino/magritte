package teseo.directorywatcher;

import teseo.*;
import java.io.File;

import java.util.*;

public class DirectoryWatcherTrigger extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.net.URL directory;
	protected List<Events> events = new java.util.ArrayList<>();

	public enum Events {
		OnCreate, OnDelete;
	}
	protected teseo.functions.DirectoryChecker check;
	protected teseo.Trigger _trigger;

	public DirectoryWatcherTrigger(tara.magritte.Node node) {
		super(node);
	}

	public java.net.URL directory() {
		return directory;
	}

	public java.util.List<Events> events() {
		return events;
	}

	public boolean check(File directory) {
		return check.check(directory);
	}

	public void directory(java.net.URL url, String destiny) {
		this.directory = graph().save(url, destiny, this.directory, node());
	}

	public void directory(java.io.InputStream stream, String destiny) {
		this.directory = graph().save(stream, destiny, this.directory, node());
	}

	public java.io.OutputStream directory(String destiny) {
		this.directory = graph().save((java.io.InputStream)null, destiny, this.directory, node());
		try {
			return this.directory.openConnection().getOutputStream();
		} catch(java.io.IOException e) {
			java.util.logging.Logger.getGlobal().severe(e.getMessage());
			return null;
		}
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("directory", new java.util.ArrayList(java.util.Collections.singletonList(this.directory)));
		map.put("events", this.events);
		map.put("check", this.check != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.check)) : java.util.Collections.emptyList());
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Trigger.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("directory")) this.directory = tara.magritte.loaders.ResourceLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("events")) this.events = tara.magritte.loaders.WordLoader.load(values, Events.class, this);
		else if (name.equalsIgnoreCase("check")) this.check = tara.magritte.loaders.FunctionLoader.load(values, this, teseo.functions.DirectoryChecker.class).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("directory")) this.directory = (java.net.URL) values.get(0);
		else if (name.equalsIgnoreCase("events")) this.events = new ArrayList<>((java.util.List<Events>) values);
		else if (name.equalsIgnoreCase("check")) this.check = tara.magritte.loaders.FunctionLoader.load(values.get(0), this, teseo.functions.DirectoryChecker.class);
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
