package teseo.jms;

import teseo.*;

import java.util.*;

public class JMSService extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	
	protected java.util.List<teseo.jms.JMSService.Resource> resourceList = new java.util.ArrayList<>();
	protected java.util.List<teseo.jms.JMSService.Authentication> authenticationList = new java.util.ArrayList<>();
	protected java.util.List<teseo.jms.JMSService.CredentialSecured> credentialSecuredList = new java.util.ArrayList<>();
	protected java.util.List<teseo.jms.JMSService.CertificateSecured> certificateSecuredList = new java.util.ArrayList<>();
	protected teseo.Service _service;

	public JMSService(tara.magritte.Node node) {
		super(node);
	}

	public java.util.List<teseo.jms.JMSService.Resource> resourceList() {
		return resourceList;
	}

	public teseo.jms.JMSService.Resource resource(int index) {
		return resourceList.get(index);
	}

	public java.util.List<teseo.jms.JMSService.Resource> resourceList(java.util.function.Predicate<teseo.jms.JMSService.Resource> predicate) {
		return resourceList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<teseo.jms.JMSService.Authentication> authenticationList() {
		return authenticationList;
	}

	public teseo.jms.JMSService.Authentication authentication(int index) {
		return authenticationList.get(index);
	}

	public java.util.List<teseo.jms.JMSService.Authentication> authenticationList(java.util.function.Predicate<teseo.jms.JMSService.Authentication> predicate) {
		return authenticationList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<teseo.jms.JMSService.CredentialSecured> credentialSecuredList() {
		return credentialSecuredList;
	}

	public teseo.jms.JMSService.CredentialSecured credentialSecured(int index) {
		return credentialSecuredList.get(index);
	}

	public java.util.List<teseo.jms.JMSService.CredentialSecured> credentialSecuredList(java.util.function.Predicate<teseo.jms.JMSService.CredentialSecured> predicate) {
		return credentialSecuredList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<teseo.jms.JMSService.CertificateSecured> certificateSecuredList() {
		return certificateSecuredList;
	}

	public teseo.jms.JMSService.CertificateSecured certificateSecured(int index) {
		return certificateSecuredList.get(index);
	}

	public java.util.List<teseo.jms.JMSService.CertificateSecured> certificateSecuredList(java.util.function.Predicate<teseo.jms.JMSService.CertificateSecured> predicate) {
		return certificateSecuredList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	

	

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		resourceList.stream().forEach(c -> components.add(c.node()));
		authenticationList.stream().forEach(c -> components.add(c.node()));
		credentialSecuredList.stream().forEach(c -> components.add(c.node()));
		certificateSecuredList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
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
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("JMS#Service$Resource")) this.resourceList.add(node.as(teseo.jms.JMSService.Resource.class));
		if (node.is("JMS#Service$Authentication")) this.authenticationList.add(node.as(teseo.jms.JMSService.Authentication.class));
		if (node.is("JMS#Service$CredentialSecured")) this.credentialSecuredList.add(node.as(teseo.jms.JMSService.CredentialSecured.class));
		if (node.is("JMS#Service$CertificateSecured")) this.certificateSecuredList.add(node.as(teseo.jms.JMSService.CertificateSecured.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("JMS#Service$Resource")) this.resourceList.remove(node.as(teseo.jms.JMSService.Resource.class));
        if (node.is("JMS#Service$Authentication")) this.authenticationList.remove(node.as(teseo.jms.JMSService.Authentication.class));
        if (node.is("JMS#Service$CredentialSecured")) this.credentialSecuredList.remove(node.as(teseo.jms.JMSService.CredentialSecured.class));
        if (node.is("JMS#Service$CertificateSecured")) this.certificateSecuredList.remove(node.as(teseo.jms.JMSService.CertificateSecured.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
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

		public teseo.jms.JMSService.Resource resource(java.lang.String queue) {
		    teseo.jms.JMSService.Resource newElement = graph().concept(teseo.jms.JMSService.Resource.class).createNode(name, node()).as(teseo.jms.JMSService.Resource.class);
			newElement.node().set(newElement, "queue", java.util.Collections.singletonList(queue)); 
		    return newElement;
		}

		

		public teseo.jms.JMSService.CredentialSecured credentialSecured(java.lang.String username, java.lang.String password) {
		    teseo.jms.JMSService.CredentialSecured newElement = graph().concept(teseo.jms.JMSService.CredentialSecured.class).createNode(name, node()).as(teseo.jms.JMSService.CredentialSecured.class);
			newElement.node().set(newElement, "username", java.util.Collections.singletonList(username));
			newElement.node().set(newElement, "password", java.util.Collections.singletonList(password)); 
		    return newElement;
		}

		public teseo.jms.JMSService.CertificateSecured certificateSecured(java.lang.String certificate) {
		    teseo.jms.JMSService.CertificateSecured newElement = graph().concept(teseo.jms.JMSService.CertificateSecured.class).createNode(name, node()).as(teseo.jms.JMSService.CertificateSecured.class);
			newElement.node().set(newElement, "certificate", java.util.Collections.singletonList(certificate)); 
		    return newElement;
		}
		
	}
	
	public static class Resource extends teseo.Method implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
		protected java.lang.String queue;

		public Resource(tara.magritte.Node node) {
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
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			map.put("queue", new java.util.ArrayList(java.util.Collections.singletonList(this.queue)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(teseo.jms.JMSService.Resource.class);
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

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends teseo.Method.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}
			
		}
		
		public teseo.TeseoApplication application() {
			return ((teseo.TeseoApplication) graph().application());
		}
	}
	
	public static abstract class Authentication extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		

		public Authentication(tara.magritte.Node node) {
			super(node);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(teseo.jms.JMSService.Authentication.class);
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
	
	public static class CredentialSecured extends teseo.jms.JMSService.Authentication implements tara.magritte.tags.Terminal {
		protected java.lang.String username;
		protected java.lang.String password;

		public CredentialSecured(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String username() {
			return username;
		}

		public java.lang.String password() {
			return password;
		}

		public void username(java.lang.String value) {
			this.username = value;
		}

		public void password(java.lang.String value) {
			this.password = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			map.put("username", new java.util.ArrayList(java.util.Collections.singletonList(this.username)));
			map.put("password", new java.util.ArrayList(java.util.Collections.singletonList(this.password)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(teseo.jms.JMSService.CredentialSecured.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("username")) this.username = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("password")) this.password = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("username")) this.username = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("password")) this.password = (java.lang.String) values.get(0);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends teseo.jms.JMSService.Authentication.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}
			
		}
		
		public teseo.TeseoApplication application() {
			return ((teseo.TeseoApplication) graph().application());
		}
	}
	
	public static class CertificateSecured extends teseo.jms.JMSService.Authentication implements tara.magritte.tags.Terminal {
		protected java.lang.String certificate;

		public CertificateSecured(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String certificate() {
			return certificate;
		}

		public void certificate(java.lang.String value) {
			this.certificate = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			map.put("certificate", new java.util.ArrayList(java.util.Collections.singletonList(this.certificate)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(teseo.jms.JMSService.CertificateSecured.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("certificate")) this.certificate = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("certificate")) this.certificate = (java.lang.String) values.get(0);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends teseo.jms.JMSService.Authentication.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}
			
		}
		
		public teseo.TeseoApplication application() {
			return ((teseo.TeseoApplication) graph().application());
		}
	}
	
	
	public teseo.TeseoApplication application() {
		return ((teseo.TeseoApplication) graph().application());
	}
}
