package teseo;

import java.util.List;

public class Channel extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	
	protected java.util.List<teseo.SubscriptionModel> subscriptionModelList = new java.util.ArrayList<>();

	public Channel(tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<teseo.SubscriptionModel> subscriptionModelList() {
		return subscriptionModelList;
	}

	public teseo.SubscriptionModel subscriptionModel(int index) {
		return subscriptionModelList.get(index);
	}

	public java.util.List<teseo.SubscriptionModel> subscriptionModelList(java.util.function.Predicate<teseo.SubscriptionModel> predicate) {
		return subscriptionModelList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		subscriptionModelList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Channel.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("SubscriptionModel")) this.subscriptionModelList.add(node.as(teseo.SubscriptionModel.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("SubscriptionModel")) this.subscriptionModelList.remove(node.as(teseo.SubscriptionModel.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
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

		
		public teseo.Queue queue(java.lang.String path) {
		    teseo.Queue newElement = graph().concept(teseo.Queue.class).createNode(name, node()).as(teseo.Queue.class);
			newElement.node().set(newElement, "path", java.util.Collections.singletonList(path)); 
		    return newElement;
		}

		public teseo.Topic topic(java.lang.String path) {
		    teseo.Topic newElement = graph().concept(teseo.Topic.class).createNode(name, node()).as(teseo.Topic.class);
			newElement.node().set(newElement, "path", java.util.Collections.singletonList(path)); 
		    return newElement;
		}
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
