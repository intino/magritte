package pandora.jmx;

import pandora.*;

import java.util.*;

public class JMXService extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String path;
	protected java.util.List<pandora.Operation> operationList = new java.util.ArrayList<>();
	protected java.util.List<pandora.Notification> notificationList = new java.util.ArrayList<>();
	protected pandora.Service _service;

	public JMXService(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String path() {
		return path;
	}

	public void path(java.lang.String value) {
		this.path = value;
	}

	public java.util.List<pandora.Operation> operationList() {
		return operationList;
	}

	public pandora.Operation operation(int index) {
		return operationList.get(index);
	}

	public java.util.List<pandora.Operation> operationList(java.util.function.Predicate<pandora.Operation> predicate) {
		return operationList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<pandora.Notification> notificationList() {
		return notificationList;
	}

	public pandora.Notification notification(int index) {
		return notificationList.get(index);
	}

	public java.util.List<pandora.Notification> notificationList(java.util.function.Predicate<pandora.Notification> predicate) {
		return notificationList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		operationList.stream().forEach(c -> components.add(c.node()));
		notificationList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("path", new java.util.ArrayList(java.util.Collections.singletonList(this.path)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Service.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Operation")) this.operationList.add(node.as(pandora.Operation.class));
		if (node.is("Notification")) this.notificationList.add(node.as(pandora.Notification.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Operation")) this.operationList.remove(node.as(pandora.Operation.class));
        if (node.is("Notification")) this.notificationList.remove(node.as(pandora.Notification.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("path")) this.path = tara.magritte.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("path")) this.path = (java.lang.String) values.get(0);
	}

	@Override
	protected void _sync(tara.magritte.Layer layer) {
		super._sync(layer);
	    if (layer instanceof pandora.Service) _service = (pandora.Service) layer;
	    
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

		public pandora.Operation operation() {
		    pandora.Operation newElement = graph().concept(pandora.Operation.class).createNode(name, node()).as(pandora.Operation.class);
		    return newElement;
		}

		public pandora.Notification notification() {
		    pandora.Notification newElement = graph().concept(pandora.Notification.class).createNode(name, node()).as(pandora.Notification.class);
		    return newElement;
		}
		
	}
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
