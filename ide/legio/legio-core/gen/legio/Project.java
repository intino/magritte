package legio;

import legio.*;

import java.util.*;

public class Project extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String groupId;
	protected java.lang.String version;
	protected java.util.List<legio.Project.DependsOf> dependsOfList = new java.util.ArrayList<>();

	public Project(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String groupId() {
		return groupId;
	}

	public java.lang.String version() {
		return version;
	}

	public void groupId(java.lang.String value) {
		this.groupId = value;
	}

	public void version(java.lang.String value) {
		this.version = value;
	}

	public java.util.List<legio.Project.DependsOf> dependsOfList() {
		return dependsOfList;
	}

	public legio.Project.DependsOf dependsOf(int index) {
		return dependsOfList.get(index);
	}

	public java.util.List<legio.Project.DependsOf> dependsOfList(java.util.function.Predicate<legio.Project.DependsOf> predicate) {
		return dependsOfList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	public legio.generatinglevel.GeneratingLevelProject asGeneratingLevel() {
		return this.as(legio.generatinglevel.GeneratingLevelProject.class);
	}

	public legio.generatinglevel.GeneratingLevelProject asGeneratingLevel(java.lang.String outDSL) {
		legio.generatinglevel.GeneratingLevelProject newElement = addFacet(legio.generatinglevel.GeneratingLevelProject.class);
		newElement.node().set(newElement, "outDSL", java.util.Collections.singletonList(outDSL)); 
	    return newElement;
	}

	public boolean isGeneratingLevel() {
		return is(legio.generatinglevel.GeneratingLevelProject.class);
	}

	public void removeGeneratingLevel() {
		this.removeFacet(legio.generatinglevel.GeneratingLevelProject.class);
	}

	public legio.platform.PlatformProject asPlatform() {
		return this.as(legio.platform.PlatformProject.class);
	}

	public legio.platform.PlatformProject asPlatform(java.lang.String outDSL) {
		legio.platform.PlatformProject newElement = addFacet(legio.platform.PlatformProject.class);
		newElement.node().set(newElement, "outDSL", java.util.Collections.singletonList(outDSL)); 
	    return newElement;
	}

	public boolean isPlatform() {
		return is(legio.platform.PlatformProject.class);
	}

	public void removePlatform() {
		this.removeFacet(legio.platform.PlatformProject.class);
	}

	public legio.ontology.OntologyProject asOntology() {
		return this.as(legio.ontology.OntologyProject.class);
	}

	public legio.ontology.OntologyProject asOntology(java.lang.String outDSL) {
		legio.ontology.OntologyProject newElement = addFacet(legio.ontology.OntologyProject.class);
		newElement.node().set(newElement, "outDSL", java.util.Collections.singletonList(outDSL)); 
	    return newElement;
	}

	public boolean isOntology() {
		return is(legio.ontology.OntologyProject.class);
	}

	public void removeOntology() {
		this.removeFacet(legio.ontology.OntologyProject.class);
	}

	public legio.level.LevelProject asLevel() {
		tara.magritte.Layer as = this.as(legio.level.LevelProject.class);
		return as != null ? (legio.level.LevelProject) as : null;
	}

	public boolean isLevel() {
		return is(legio.level.LevelProject.class);
	}

	public legio.application.ApplicationProject asApplication() {
		return this.as(legio.application.ApplicationProject.class);
	}

	public legio.application.ApplicationProject asApplication(java.lang.String outDSL) {
		legio.application.ApplicationProject newElement = addFacet(legio.application.ApplicationProject.class);
		newElement.node().set(newElement, "outDSL", java.util.Collections.singletonList(outDSL)); 
	    return newElement;
	}

	public boolean isApplication() {
		return is(legio.application.ApplicationProject.class);
	}

	public void removeApplication() {
		this.removeFacet(legio.application.ApplicationProject.class);
	}

	public legio.system.SystemProject asSystem() {
		tara.magritte.Layer as = this.as(legio.system.SystemProject.class);
		return as != null ? (legio.system.SystemProject) as : addFacet(legio.system.SystemProject.class);
	}

	public boolean isSystem() {
		return is(legio.system.SystemProject.class);
	}

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		dependsOfList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("groupId", new java.util.ArrayList(java.util.Collections.singletonList(this.groupId)));
		map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(legio.Project.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Project$DependsOf")) this.dependsOfList.add(node.as(legio.Project.DependsOf.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Project$DependsOf")) this.dependsOfList.remove(node.as(legio.Project.DependsOf.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("groupId")) this.groupId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("groupId")) this.groupId = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("version")) this.version = (java.lang.String) values.get(0);
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

		public legio.Project.DependsOf dependsOf(java.lang.String id) {
		    legio.Project.DependsOf newElement = graph().concept(legio.Project.DependsOf.class).createNode(name, node()).as(legio.Project.DependsOf.class);
			newElement.node().set(newElement, "id", java.util.Collections.singletonList(id)); 
		    return newElement;
		}
		
	}
	
	public static class DependsOf extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String id;
		protected java.lang.String groupId;
		protected java.lang.String artifactId;
		protected java.lang.String version;
		protected Scope scope;

		public enum Scope {
			Compile, Runtime, Test;
		}
		protected boolean transitive;

		public DependsOf(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String id() {
			return id;
		}

		public java.lang.String groupId() {
			return groupId;
		}

		public java.lang.String artifactId() {
			return artifactId;
		}

		public java.lang.String version() {
			return version;
		}

		public Scope scope() {
			return scope;
		}

		public boolean transitive() {
			return transitive;
		}

		public void id(java.lang.String value) {
			this.id = value;
		}

		public void groupId(java.lang.String value) {
			this.groupId = value;
		}

		public void artifactId(java.lang.String value) {
			this.artifactId = value;
		}

		public void version(java.lang.String value) {
			this.version = value;
		}

		public void scope(legio.Project.DependsOf.Scope value) {
			this.scope = value;
		}

		public void transitive(boolean value) {
			this.transitive = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("id", new java.util.ArrayList(java.util.Collections.singletonList(this.id)));
			map.put("groupId", new java.util.ArrayList(java.util.Collections.singletonList(this.groupId)));
			map.put("artifactId", new java.util.ArrayList(java.util.Collections.singletonList(this.artifactId)));
			map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
			map.put("scope", new java.util.ArrayList(java.util.Collections.singletonList(this.scope)));
			map.put("transitive", new java.util.ArrayList(java.util.Collections.singletonList(this.transitive)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(legio.Project.DependsOf.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("id")) this.id = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("groupId")) this.groupId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("artifactId")) this.artifactId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("scope")) this.scope = tara.magritte.loaders.WordLoader.load(values, Scope.class, this).get(0);
			else if (name.equalsIgnoreCase("transitive")) this.transitive = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("id")) this.id = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("groupId")) this.groupId = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("artifactId")) this.artifactId = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("version")) this.version = (java.lang.String) values.get(0);
			else if (name.equalsIgnoreCase("scope")) this.scope = (Scope) values.get(0);
			else if (name.equalsIgnoreCase("transitive")) this.transitive = (java.lang.Boolean) values.get(0);
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
		
		public legio.LegioApplication application() {
			return ((legio.LegioApplication) graph().application());
		}
	}
	
	
	public legio.LegioApplication application() {
		return ((legio.LegioApplication) graph().application());
	}
}
