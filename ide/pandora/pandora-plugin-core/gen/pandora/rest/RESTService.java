package pandora.rest;

import pandora.*;

import java.util.*;

public class RESTService extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String title;
	protected java.lang.String description;
	protected java.lang.String path;
	protected List<Protocols> protocols = new java.util.ArrayList<>();

	public enum Protocols {
		http, https, ws, wss;
	}
	protected pandora.rest.RESTService.Authenticated authenticated;
	protected pandora.rest.RESTService.AuthenticatedWithCertificate authenticatedWithCertificate;
	protected pandora.rest.RESTService.AuthenticatedWithPassword authenticatedWithPassword;
	protected java.util.List<pandora.Resource> resourceList = new java.util.ArrayList<>();
	protected pandora.rest.RESTService.Info info;
	protected pandora.rest.RESTService.Contact contact;
	protected pandora.rest.RESTService.License license;
	protected java.util.List<pandora.rest.RESTService.Tag> tagList = new java.util.ArrayList<>();
	
	protected pandora.Service _service;

	public RESTService(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String title() {
		return title;
	}

	public java.lang.String description() {
		return description;
	}

	public java.lang.String path() {
		return path;
	}

	public java.util.List<Protocols> protocols() {
		return protocols;
	}

	public void title(java.lang.String value) {
		this.title = value;
	}

	public void description(java.lang.String value) {
		this.description = value;
	}

	public void path(java.lang.String value) {
		this.path = value;
	}

	public pandora.rest.RESTService.Authenticated authenticated() {
		return authenticated;
	}

	public pandora.rest.RESTService.AuthenticatedWithCertificate authenticatedWithCertificate() {
		return authenticatedWithCertificate;
	}

	public pandora.rest.RESTService.AuthenticatedWithPassword authenticatedWithPassword() {
		return authenticatedWithPassword;
	}

	public java.util.List<pandora.Resource> resourceList() {
		return resourceList;
	}

	public pandora.Resource resource(int index) {
		return resourceList.get(index);
	}

	public java.util.List<pandora.Resource> resourceList(java.util.function.Predicate<pandora.Resource> predicate) {
		return resourceList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public pandora.rest.RESTService.Info info() {
		return info;
	}

	public pandora.rest.RESTService.Contact contact() {
		return contact;
	}

	public pandora.rest.RESTService.License license() {
		return license;
	}

	public java.util.List<pandora.rest.RESTService.Tag> tagList() {
		return tagList;
	}

	public pandora.rest.RESTService.Tag tag(int index) {
		return tagList.get(index);
	}

	public java.util.List<pandora.rest.RESTService.Tag> tagList(java.util.function.Predicate<pandora.rest.RESTService.Tag> predicate) {
		return tagList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<pandora.Exception> exceptionList() {
		return (java.util.List<pandora.Exception>) _service.exceptionList();
	}

	public pandora.Exception exceptionList(int index) {
		return _service.exceptionList().get(index);
	}

	public void authenticated(pandora.rest.RESTService.Authenticated value) {
		this.authenticated = value;
	}

	public void authenticatedWithCertificate(pandora.rest.RESTService.AuthenticatedWithCertificate value) {
		this.authenticatedWithCertificate = value;
	}

	public void authenticatedWithPassword(pandora.rest.RESTService.AuthenticatedWithPassword value) {
		this.authenticatedWithPassword = value;
	}

	

	public void info(pandora.rest.RESTService.Info value) {
		this.info = value;
	}

	public void contact(pandora.rest.RESTService.Contact value) {
		this.contact = value;
	}

	public void license(pandora.rest.RESTService.License value) {
		this.license = value;
	}

	

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		if (authenticated != null) components.add(this.authenticated.node());
		if (authenticatedWithCertificate != null) components.add(this.authenticatedWithCertificate.node());
		if (authenticatedWithPassword != null) components.add(this.authenticatedWithPassword.node());
		resourceList.stream().forEach(c -> components.add(c.node()));
		if (info != null) components.add(this.info.node());
		if (contact != null) components.add(this.contact.node());
		if (license != null) components.add(this.license.node());
		tagList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("title", new java.util.ArrayList(java.util.Collections.singletonList(this.title)));
		map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
		map.put("path", new java.util.ArrayList(java.util.Collections.singletonList(this.path)));
		map.put("protocols", this.protocols);
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Service.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("REST#Service$Authenticated")) this.authenticated = node.as(pandora.rest.RESTService.Authenticated.class);
		if (node.is("REST#Service$AuthenticatedWithCertificate")) this.authenticatedWithCertificate = node.as(pandora.rest.RESTService.AuthenticatedWithCertificate.class);
		if (node.is("REST#Service$AuthenticatedWithPassword")) this.authenticatedWithPassword = node.as(pandora.rest.RESTService.AuthenticatedWithPassword.class);
		if (node.is("Resource")) this.resourceList.add(node.as(pandora.Resource.class));
		if (node.is("REST#Service$Info")) this.info = node.as(pandora.rest.RESTService.Info.class);
		if (node.is("REST#Service$Contact")) this.contact = node.as(pandora.rest.RESTService.Contact.class);
		if (node.is("REST#Service$License")) this.license = node.as(pandora.rest.RESTService.License.class);
		if (node.is("REST#Service$Tag")) this.tagList.add(node.as(pandora.rest.RESTService.Tag.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("REST#Service$Authenticated")) this.authenticated = null;
        if (node.is("REST#Service$AuthenticatedWithCertificate")) this.authenticatedWithCertificate = null;
        if (node.is("REST#Service$AuthenticatedWithPassword")) this.authenticatedWithPassword = null;
        if (node.is("Resource")) this.resourceList.remove(node.as(pandora.Resource.class));
        if (node.is("REST#Service$Info")) this.info = null;
        if (node.is("REST#Service$Contact")) this.contact = null;
        if (node.is("REST#Service$License")) this.license = null;
        if (node.is("REST#Service$Tag")) this.tagList.remove(node.as(pandora.rest.RESTService.Tag.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("title")) this.title = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("path")) this.path = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("protocols")) this.protocols = tara.magritte.loaders.WordLoader.load(values, Protocols.class, this);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("title")) this.title = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("path")) this.path = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("protocols")) this.protocols = new ArrayList<>((java.util.List<Protocols>) values);
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

		

		public pandora.rest.RESTService.AuthenticatedWithCertificate authenticatedWithCertificate(java.net.URL store, java.lang.String storePassword) {
		    pandora.rest.RESTService.AuthenticatedWithCertificate newElement = graph().concept(pandora.rest.RESTService.AuthenticatedWithCertificate.class).createNode(name, node()).as(pandora.rest.RESTService.AuthenticatedWithCertificate.class);
			newElement.node().set(newElement, "store", java.util.Collections.singletonList(store));
			newElement.node().set(newElement, "storePassword", java.util.Collections.singletonList(storePassword)); 
		    return newElement;
		}

		public pandora.rest.RESTService.AuthenticatedWithPassword authenticatedWithPassword() {
		    pandora.rest.RESTService.AuthenticatedWithPassword newElement = graph().concept(pandora.rest.RESTService.AuthenticatedWithPassword.class).createNode(name, node()).as(pandora.rest.RESTService.AuthenticatedWithPassword.class);
		    return newElement;
		}

		public pandora.Resource resource(java.lang.String title, java.lang.String path, pandora.Resource.Type type) {
		    pandora.Resource newElement = graph().concept(pandora.Resource.class).createNode(name, node()).as(pandora.Resource.class);
			newElement.node().set(newElement, "title", java.util.Collections.singletonList(title));
			newElement.node().set(newElement, "path", java.util.Collections.singletonList(path));
			newElement.node().set(newElement, "type", java.util.Collections.singletonList(type)); 
		    return newElement;
		}

		public pandora.rest.RESTService.Info info(java.lang.String title, java.lang.String version) {
		    pandora.rest.RESTService.Info newElement = graph().concept(pandora.rest.RESTService.Info.class).createNode(name, node()).as(pandora.rest.RESTService.Info.class);
			newElement.node().set(newElement, "title", java.util.Collections.singletonList(title));
			newElement.node().set(newElement, "version", java.util.Collections.singletonList(version)); 
		    return newElement;
		}

		public pandora.rest.RESTService.Contact contact() {
		    pandora.rest.RESTService.Contact newElement = graph().concept(pandora.rest.RESTService.Contact.class).createNode(name, node()).as(pandora.rest.RESTService.Contact.class);
		    return newElement;
		}

		public pandora.rest.RESTService.License license() {
		    pandora.rest.RESTService.License newElement = graph().concept(pandora.rest.RESTService.License.class).createNode(name, node()).as(pandora.rest.RESTService.License.class);
		    return newElement;
		}

		public pandora.rest.RESTService.Tag tag() {
		    pandora.rest.RESTService.Tag newElement = graph().concept(pandora.rest.RESTService.Tag.class).createNode(name, node()).as(pandora.rest.RESTService.Tag.class);
		    return newElement;
		}

		public pandora.Exception exception(pandora.rules.ExceptionCodes code) {
		    pandora.Exception newElement = graph().concept(pandora.Exception.class).createNode(name, node()).as(pandora.Exception.class);
			newElement.node().set(newElement, "code", java.util.Collections.singletonList(code)); 
		    return newElement;
		}
		
	}
	
	public static abstract class Authenticated extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		

		public Authenticated(tara.magritte.Node node) {
			super(node);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.Authenticated.class);
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
	
	public static class AuthenticatedWithCertificate extends pandora.rest.RESTService.Authenticated implements tara.magritte.tags.Terminal {
		protected java.net.URL store;
		protected java.lang.String storePassword;
		protected java.util.List<pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers> allowedUsersList = new java.util.ArrayList<>();

		public AuthenticatedWithCertificate(tara.magritte.Node node) {
			super(node);
		}

		public java.net.URL store() {
			return store;
		}

		public java.lang.String storePassword() {
			return storePassword;
		}

		public void store(java.net.URL url, String destiny) {
			this.store = graph().save(url, destiny, this.store, node());
		}

		public void store(java.io.InputStream stream, String destiny) {
			this.store = graph().save(stream, destiny, this.store, node());
		}

		public java.io.OutputStream store(String destiny) {
			this.store = graph().save((java.io.InputStream)null, destiny, this.store, node());
			try {
				return this.store.openConnection().getOutputStream();
			} catch(java.io.IOException e) {
				java.util.logging.Logger.getGlobal().severe(e.getMessage());
				return null;
			}
		}

		public void storePassword(java.lang.String value) {
			this.storePassword = value;
		}

		public java.util.List<pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers> allowedUsersList() {
			return allowedUsersList;
		}

		public pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers allowedUsers(int index) {
			return allowedUsersList.get(index);
		}

		public java.util.List<pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers> allowedUsersList(java.util.function.Predicate<pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers> predicate) {
			return allowedUsersList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		

		public List<tara.magritte.Node> componentList() {
			java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
			allowedUsersList.stream().forEach(c -> components.add(c.node()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			map.put("store", new java.util.ArrayList(java.util.Collections.singletonList(this.store)));
			map.put("storePassword", new java.util.ArrayList(java.util.Collections.singletonList(this.storePassword)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.AuthenticatedWithCertificate.class);
		}

		@Override
		protected void addNode(tara.magritte.Node node) {
			super.addNode(node);
			if (node.is("REST#Service$AuthenticatedWithCertificate$AllowedUsers")) this.allowedUsersList.add(node.as(pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers.class));
		}

		@Override
	    protected void removeNode(tara.magritte.Node node) {
	        super.removeNode(node);
	        if (node.is("REST#Service$AuthenticatedWithCertificate$AllowedUsers")) this.allowedUsersList.remove(node.as(pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers.class));
	    }

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("store")) this.store = tara.magritte.loaders.ResourceLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("storePassword")) this.storePassword = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("store")) this.store = (java.net.URL) values.get(0);
			else if (name.equalsIgnoreCase("storePassword")) this.storePassword = (java.lang.String) values.get(0);
		}

		public Create create() {
			return new Create(null);
		}

		public Create create(java.lang.String name) {
			return new Create(name);
		}

		public class Create extends pandora.rest.RESTService.Authenticated.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}

			public pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers allowedUsers(java.util.List<java.net.URL> certificate) {
			    pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers newElement = graph().concept(pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers.class).createNode(name, node()).as(pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers.class);
				newElement.node().set(newElement, "certificate", certificate); 
			    return newElement;
			}
			
		}
		
		public static class AllowedUsers extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
			protected java.util.List<java.net.URL> certificate = new java.util.ArrayList<>();

			public AllowedUsers(tara.magritte.Node node) {
				super(node);
			}

			public java.util.List<java.net.URL> certificate() {
				return certificate;
			}

			public java.net.URL certificate(int index) {
				return certificate.get(index);
			}

			public java.util.List<java.net.URL> certificate(java.util.function.Predicate<java.net.URL> predicate) {
				return certificate().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
			}

			public void addCertificate(java.net.URL url, String destiny) {
				java.net.URL newElement = graph().save(url, destiny, null, node());
				this.certificate.add(newElement);
			}

			public void addCertificate(java.io.InputStream stream, String destiny) {
				java.net.URL newElement = graph().save(stream, destiny, null, node());
				this.certificate.add(newElement);
			}

			public java.io.OutputStream addCertificate(String destiny) {
				java.net.URL newElement = graph().save((java.io.InputStream)null, destiny, null, node());
				this.certificate.add(newElement);
				try {
					return newElement.openConnection().getOutputStream();
				} catch(java.io.IOException e) {
					java.util.logging.Logger.getGlobal().severe(e.getMessage());
					return null;
				}
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("certificate", this.certificate);
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(pandora.rest.RESTService.AuthenticatedWithCertificate.AllowedUsers.class);
			}

			@Override
			protected void _load(java.lang.String name, java.util.List<?> values) {
				super._load(name, values);
				if (name.equalsIgnoreCase("certificate")) this.certificate = tara.magritte.loaders.ResourceLoader.load(values, this);
			}

			@Override
			protected void _set(java.lang.String name, java.util.List<?> values) {
				super._set(name, values);
				if (name.equalsIgnoreCase("certificate")) this.certificate = new ArrayList<>((java.util.List<java.net.URL>) values);
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
		
		
		public pandora.PandoraApplication application() {
			return ((pandora.PandoraApplication) graph().application());
		}
	}
	
	public static class AuthenticatedWithPassword extends pandora.rest.RESTService.Authenticated implements tara.magritte.tags.Terminal {
		
		protected java.util.List<pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers> allowedUsersList = new java.util.ArrayList<>();

		public AuthenticatedWithPassword(tara.magritte.Node node) {
			super(node);
		}

		public java.util.List<pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers> allowedUsersList() {
			return allowedUsersList;
		}

		public pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers allowedUsers(int index) {
			return allowedUsersList.get(index);
		}

		public java.util.List<pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers> allowedUsersList(java.util.function.Predicate<pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers> predicate) {
			return allowedUsersList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		

		public List<tara.magritte.Node> componentList() {
			java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
			allowedUsersList.stream().forEach(c -> components.add(c.node()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.AuthenticatedWithPassword.class);
		}

		@Override
		protected void addNode(tara.magritte.Node node) {
			super.addNode(node);
			if (node.is("REST#Service$AuthenticatedWithPassword$AllowedUsers")) this.allowedUsersList.add(node.as(pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers.class));
		}

		@Override
	    protected void removeNode(tara.magritte.Node node) {
	        super.removeNode(node);
	        if (node.is("REST#Service$AuthenticatedWithPassword$AllowedUsers")) this.allowedUsersList.remove(node.as(pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers.class));
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

		public class Create extends pandora.rest.RESTService.Authenticated.Create {
			

			public Create(java.lang.String name) {
				super(name);
			}

			public pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers allowedUsers(java.lang.String user, java.lang.String password) {
			    pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers newElement = graph().concept(pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers.class).createNode(name, node()).as(pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers.class);
				newElement.node().set(newElement, "user", java.util.Collections.singletonList(user));
				newElement.node().set(newElement, "password", java.util.Collections.singletonList(password)); 
			    return newElement;
			}
			
		}
		
		public static class AllowedUsers extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
			protected java.lang.String user;
			protected java.lang.String password;

			public AllowedUsers(tara.magritte.Node node) {
				super(node);
			}

			public java.lang.String user() {
				return user;
			}

			public java.lang.String password() {
				return password;
			}

			public void user(java.lang.String value) {
				this.user = value;
			}

			public void password(java.lang.String value) {
				this.password = value;
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("user", new java.util.ArrayList(java.util.Collections.singletonList(this.user)));
				map.put("password", new java.util.ArrayList(java.util.Collections.singletonList(this.password)));
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(pandora.rest.RESTService.AuthenticatedWithPassword.AllowedUsers.class);
			}

			@Override
			protected void _load(java.lang.String name, java.util.List<?> values) {
				super._load(name, values);
				if (name.equalsIgnoreCase("user")) this.user = tara.magritte.loaders.StringLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("password")) this.password = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			}

			@Override
			protected void _set(java.lang.String name, java.util.List<?> values) {
				super._set(name, values);
				if (name.equalsIgnoreCase("user")) this.user = (java.lang.String) values.get(0);
				else if (name.equalsIgnoreCase("password")) this.password = (java.lang.String) values.get(0);
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
		
		
		public pandora.PandoraApplication application() {
			return ((pandora.PandoraApplication) graph().application());
		}
	}
	
	public static class Info extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String title;
		protected java.lang.String version;
		protected java.lang.String description;
		protected java.lang.String termsOfService;

		public Info(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String title() {
			return title;
		}

		public java.lang.String version() {
			return version;
		}

		public java.lang.String description() {
			return description;
		}

		public java.lang.String termsOfService() {
			return termsOfService;
		}

		public void title(java.lang.String value) {
			this.title = value;
		}

		public void version(java.lang.String value) {
			this.version = value;
		}

		public void description(java.lang.String value) {
			this.description = value;
		}

		public void termsOfService(java.lang.String value) {
			this.termsOfService = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("title", new java.util.ArrayList(java.util.Collections.singletonList(this.title)));
			map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
			map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
			map.put("termsOfService", new java.util.ArrayList(java.util.Collections.singletonList(this.termsOfService)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.Info.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("title")) this.title = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("termsOfService")) this.termsOfService = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("title")) this.title = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("version")) this.version = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("termsOfService")) this.termsOfService = (java.lang.String) values.get(0);
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
	
	public static class Contact extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String url;
		protected java.lang.String email;

		public Contact(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String url() {
			return url;
		}

		public java.lang.String email() {
			return email;
		}

		public void url(java.lang.String value) {
			this.url = value;
		}

		public void email(java.lang.String value) {
			this.email = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("url", new java.util.ArrayList(java.util.Collections.singletonList(this.url)));
			map.put("email", new java.util.ArrayList(java.util.Collections.singletonList(this.email)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.Contact.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("url")) this.url = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("email")) this.email = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("url")) this.url = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("email")) this.email = (java.lang.String) values.get(0);
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
	
	public static class License extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String url;

		public License(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String url() {
			return url;
		}

		public void url(java.lang.String value) {
			this.url = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("url", new java.util.ArrayList(java.util.Collections.singletonList(this.url)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.License.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("url")) this.url = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("url")) this.url = (java.lang.String) values.get(0);
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
	
	public static class Tag extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String description;
		protected java.util.List<pandora.rest.RESTService.Tag.Document> documentList = new java.util.ArrayList<>();

		public Tag(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String description() {
			return description;
		}

		public void description(java.lang.String value) {
			this.description = value;
		}

		public java.util.List<pandora.rest.RESTService.Tag.Document> documentList() {
			return documentList;
		}

		public pandora.rest.RESTService.Tag.Document document(int index) {
			return documentList.get(index);
		}

		public java.util.List<pandora.rest.RESTService.Tag.Document> documentList(java.util.function.Predicate<pandora.rest.RESTService.Tag.Document> predicate) {
			return documentList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		

		public List<tara.magritte.Node> componentList() {
			java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
			documentList.stream().forEach(c -> components.add(c.node()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(pandora.rest.RESTService.Tag.class);
		}

		@Override
		protected void addNode(tara.magritte.Node node) {
			super.addNode(node);
			if (node.is("REST#Service$Tag$Document")) this.documentList.add(node.as(pandora.rest.RESTService.Tag.Document.class));
		}

		@Override
	    protected void removeNode(tara.magritte.Node node) {
	        super.removeNode(node);
	        if (node.is("REST#Service$Tag$Document")) this.documentList.remove(node.as(pandora.rest.RESTService.Tag.Document.class));
	    }

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
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

			public pandora.rest.RESTService.Tag.Document document(java.lang.String url) {
			    pandora.rest.RESTService.Tag.Document newElement = graph().concept(pandora.rest.RESTService.Tag.Document.class).createNode(name, node()).as(pandora.rest.RESTService.Tag.Document.class);
				newElement.node().set(newElement, "url", java.util.Collections.singletonList(url)); 
			    return newElement;
			}
			
		}
		
		public static class Document extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
			protected java.lang.String description;
			protected java.lang.String url;

			public Document(tara.magritte.Node node) {
				super(node);
			}

			public java.lang.String description() {
				return description;
			}

			public java.lang.String url() {
				return url;
			}

			public void description(java.lang.String value) {
				this.description = value;
			}

			public void url(java.lang.String value) {
				this.url = value;
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("description", new java.util.ArrayList(java.util.Collections.singletonList(this.description)));
				map.put("url", new java.util.ArrayList(java.util.Collections.singletonList(this.url)));
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(pandora.rest.RESTService.Tag.Document.class);
			}

			@Override
			protected void _load(java.lang.String name, java.util.List<?> values) {
				super._load(name, values);
				if (name.equalsIgnoreCase("description")) this.description = tara.magritte.loaders.StringLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("url")) this.url = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			}

			@Override
			protected void _set(java.lang.String name, java.util.List<?> values) {
				super._set(name, values);
				if (name.equalsIgnoreCase("description")) this.description = (java.lang.String) values.get(0);
				else if (name.equalsIgnoreCase("url")) this.url = (java.lang.String) values.get(0);
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
		
		
		public pandora.PandoraApplication application() {
			return ((pandora.PandoraApplication) graph().application());
		}
	}
	
	
	public pandora.PandoraApplication application() {
		return ((pandora.PandoraApplication) graph().application());
	}
}
