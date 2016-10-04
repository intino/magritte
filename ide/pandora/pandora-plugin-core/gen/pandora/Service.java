package pandora;

import pandora.*;

import java.util.*;

public class Service extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	
	protected java.util.List<pandora.Exception> exceptionList = new java.util.ArrayList<>();

	public Service(tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<pandora.Exception> exceptionList() {
		return exceptionList;
	}

	public pandora.Exception exception(int index) {
		return exceptionList.get(index);
	}

	public java.util.List<pandora.Exception> exceptionList(java.util.function.Predicate<pandora.Exception> predicate) {
		return exceptionList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	public pandora.jms.JMSService asJMS() {
		tara.magritte.Layer as = this.as(pandora.jms.JMSService.class);
		return as != null ? (pandora.jms.JMSService) as : addFacet(pandora.jms.JMSService.class);
	}

	public boolean isJMS() {
		return is(pandora.jms.JMSService.class);
	}

	public pandora.rest.RESTService asREST() {
		return this.as(pandora.rest.RESTService.class);
	}

	public pandora.rest.RESTService asREST(java.lang.String title, java.lang.String path) {
		pandora.rest.RESTService newElement = addFacet(pandora.rest.RESTService.class);
		newElement.node().set(newElement, "title", java.util.Collections.singletonList(title));
		newElement.node().set(newElement, "path", java.util.Collections.singletonList(path)); 
	    return newElement;
	}

	public boolean isREST() {
		return is(pandora.rest.RESTService.class);
	}

	public void removeREST() {
		this.removeFacet(pandora.rest.RESTService.class);
	}

	public pandora.jmx.JMXService asJMX() {
		tara.magritte.Layer as = this.as(pandora.jmx.JMXService.class);
		return as != null ? (pandora.jmx.JMXService) as : addFacet(pandora.jmx.JMXService.class);
	}

	public boolean isJMX() {
		return is(pandora.jmx.JMXService.class);
	}

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		exceptionList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Service.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Exception")) this.exceptionList.add(node.as(pandora.Exception.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Exception")) this.exceptionList.remove(node.as(pandora.Exception.class));
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

		public pandora.Exception exception(pandora.rules.ExceptionCodes code) {
		    pandora.Exception newElement = graph().concept(pandora.Exception.class).createNode(name, node()).as(pandora.Exception.class);
			newElement.node().set(newElement, "code", java.util.Collections.singletonList(code)); 
		    return newElement;
		}
		
	}
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
