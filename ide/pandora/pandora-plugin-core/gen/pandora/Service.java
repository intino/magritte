package pandora;

import pandora.*;

import java.util.*;

public class Service extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	

	public Service(tara.magritte.Node node) {
		super(node);
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

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Service.class);
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
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
