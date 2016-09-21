package teseo;

import teseo.*;

import java.util.*;

public class Service extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	

	public Service(tara.magritte.Node node) {
		super(node);
	}

	public teseo.jms.JMSService asJMS() {
		tara.magritte.Layer as = this.as(teseo.jms.JMSService.class);
		return as != null ? (teseo.jms.JMSService) as : addFacet(teseo.jms.JMSService.class);
	}

	public boolean isJMS() {
		return is(teseo.jms.JMSService.class);
	}

	public teseo.rest.RESTService asREST() {
		return this.as(teseo.rest.RESTService.class);
	}

	public teseo.rest.RESTService asREST(java.lang.String title, java.lang.String path) {
		teseo.rest.RESTService newElement = addFacet(teseo.rest.RESTService.class);
		newElement.node().set(newElement, "title", java.util.Collections.singletonList(title));
		newElement.node().set(newElement, "path", java.util.Collections.singletonList(path)); 
	    return newElement;
	}

	public boolean isREST() {
		return is(teseo.rest.RESTService.class);
	}

	public void removeREST() {
		this.removeFacet(teseo.rest.RESTService.class);
	}

	public teseo.jmx.JMXService asJMX() {
		tara.magritte.Layer as = this.as(teseo.jmx.JMXService.class);
		return as != null ? (teseo.jmx.JMXService) as : addFacet(teseo.jmx.JMXService.class);
	}

	public boolean isJMX() {
		return is(teseo.jmx.JMXService.class);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(teseo.Service.class);
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
		
	}
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
