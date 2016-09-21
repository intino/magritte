package teseo.jmx;

import teseo.*;

import java.util.*;

public class JMXService extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String path;
	protected java.util.List<teseo.Operation> operationList = new java.util.ArrayList<>();
	protected java.util.List<teseo.Notification> notificationList = new java.util.ArrayList<>();
	protected teseo.Service _service;

	public JMXService(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String path() {
		return path;
	}

	public void path(java.lang.String value) {
		this.path = value;
	}

	public java.util.List<teseo.Operation> operationList() {
		return operationList;
	}

	public teseo.Operation operation(int index) {
		return operationList.get(index);
	}

	public java.util.List<teseo.Operation> operationList(java.util.function.Predicate<teseo.Operation> predicate) {
		return operationList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<teseo.Notification> notificationList() {
		return notificationList;
	}

	public teseo.Notification notification(int index) {
		return notificationList.get(index);
	}

	public java.util.List<teseo.Notification> notificationList(java.util.function.Predicate<teseo.Notification> predicate) {
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
		return this.graph().concept(teseo.Service.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Operation")) this.operationList.add(node.as(teseo.Operation.class));
		if (node.is("Notification")) this.notificationList.add(node.as(teseo.Notification.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Operation")) this.operationList.remove(node.as(teseo.Operation.class));
        if (node.is("Notification")) this.notificationList.remove(node.as(teseo.Notification.class));
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
	    if (layer instanceof teseo.Service) _service = (teseo.Service) layer;
	    
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

		public teseo.Operation operation() {
		    teseo.Operation newElement = graph().concept(teseo.Operation.class).createNode(name, node()).as(teseo.Operation.class);
		    return newElement;
		}

		public teseo.Notification notification() {
		    teseo.Notification newElement = graph().concept(teseo.Notification.class).createNode(name, node()).as(teseo.Notification.class);
		    return newElement;
		}
		
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
