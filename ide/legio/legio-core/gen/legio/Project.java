package legio;

import legio.*;

import java.util.*;

public class Project extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String groupId;
	protected java.lang.String version;
	protected tara.magritte.Expression<java.util.List<java.lang.String>> supportedLanguages;
	protected java.util.List<legio.Repository> repositoryList = new java.util.ArrayList<>();
	protected java.util.List<legio.Project.Dependency> dependencyList = new java.util.ArrayList<>();

	public Project(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String groupId() {
		return groupId;
	}

	public java.lang.String version() {
		return version;
	}

	public java.util.List<java.lang.String> supportedLanguages() {
		return supportedLanguages.value();
	}

	public void groupId(java.lang.String value) {
		this.groupId = value;
	}

	public void version(java.lang.String value) {
		this.version = value;
	}

	public java.util.List<legio.Repository> repositoryList() {
		return repositoryList;
	}

	public legio.Repository repository(int index) {
		return repositoryList.get(index);
	}

	public java.util.List<legio.Repository> repositoryList(java.util.function.Predicate<legio.Repository> predicate) {
		return repositoryList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<legio.Project.Dependency> dependencyList() {
		return dependencyList;
	}

	public legio.Project.Dependency dependency(int index) {
		return dependencyList.get(index);
	}

	public java.util.List<legio.Project.Dependency> dependencyList(java.util.function.Predicate<legio.Project.Dependency> predicate) {
		return dependencyList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	

	public legio.platform.PlatformProject asPlatform() {
		tara.magritte.Layer as = this.as(legio.platform.PlatformProject.class);
		return as != null ? (legio.platform.PlatformProject) as : addFacet(legio.platform.PlatformProject.class);
	}

	public boolean isPlatform() {
		return is(legio.platform.PlatformProject.class);
	}

	public legio.ontology.OntologyProject asOntology() {
		tara.magritte.Layer as = this.as(legio.ontology.OntologyProject.class);
		return as != null ? (legio.ontology.OntologyProject) as : addFacet(legio.ontology.OntologyProject.class);
	}

	public boolean isOntology() {
		return is(legio.ontology.OntologyProject.class);
	}

	public legio.generatorlevel.GeneratorLevelProject asGeneratorLevel() {
		tara.magritte.Layer as = this.as(legio.generatorlevel.GeneratorLevelProject.class);
		return as != null ? (legio.generatorlevel.GeneratorLevelProject) as : null;
	}

	public boolean isGeneratorLevel() {
		return is(legio.generatorlevel.GeneratorLevelProject.class);
	}

	public legio.level.LevelProject asLevel() {
		tara.magritte.Layer as = this.as(legio.level.LevelProject.class);
		return as != null ? (legio.level.LevelProject) as : null;
	}

	public boolean isLevel() {
		return is(legio.level.LevelProject.class);
	}

	public legio.application.ApplicationProject asApplication() {
		tara.magritte.Layer as = this.as(legio.application.ApplicationProject.class);
		return as != null ? (legio.application.ApplicationProject) as : addFacet(legio.application.ApplicationProject.class);
	}

	public boolean isApplication() {
		return is(legio.application.ApplicationProject.class);
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
		repositoryList.stream().forEach(c -> components.add(c.node()));
		dependencyList.stream().forEach(c -> components.add(c.node()));
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("groupId", new java.util.ArrayList(java.util.Collections.singletonList(this.groupId)));
		map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
		map.put("supportedLanguages", this.supportedLanguages != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.supportedLanguages)) : java.util.Collections.emptyList());
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(legio.Project.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Repository")) this.repositoryList.add(node.as(legio.Repository.class));
		if (node.is("Project$Dependency")) this.dependencyList.add(node.as(legio.Project.Dependency.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Repository")) this.repositoryList.remove(node.as(legio.Repository.class));
        if (node.is("Project$Dependency")) this.dependencyList.remove(node.as(legio.Project.Dependency.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		if (name.equalsIgnoreCase("groupId")) this.groupId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("supportedLanguages")) this.supportedLanguages = tara.magritte.loaders.FunctionLoader.load(values, this, tara.magritte.Expression.class).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		if (name.equalsIgnoreCase("groupId")) this.groupId = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("version")) this.version = (java.lang.String) values.get(0);
		else if (name.equalsIgnoreCase("supportedLanguages")) this.supportedLanguages = tara.magritte.loaders.FunctionLoader.load(values.get(0), this, tara.magritte.Expression.class);
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

		public legio.Repository repository(java.lang.String url) {
		    legio.Repository newElement = graph().concept(legio.Repository.class).createNode(name, node()).as(legio.Repository.class);
			newElement.node().set(newElement, "url", java.util.Collections.singletonList(url)); 
		    return newElement;
		}

		public legio.Project.Dependency dependency(java.lang.String groupId, java.lang.String artifactId, java.lang.String version) {
		    legio.Project.Dependency newElement = graph().concept(legio.Project.Dependency.class).createNode(name, node()).as(legio.Project.Dependency.class);
			newElement.node().set(newElement, "groupId", java.util.Collections.singletonList(groupId));
			newElement.node().set(newElement, "artifactId", java.util.Collections.singletonList(artifactId));
			newElement.node().set(newElement, "version", java.util.Collections.singletonList(version)); 
		    return newElement;
		}
		
	}
	
	public static class Dependency extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String groupId;
		protected java.lang.String artifactId;
		protected java.lang.String version;
		protected Scope scope;

		public enum Scope {
			Compile, Runtime, Test;
		}
		protected boolean transitive;

		public Dependency(tara.magritte.Node node) {
			super(node);
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

		public void groupId(java.lang.String value) {
			this.groupId = value;
		}

		public void artifactId(java.lang.String value) {
			this.artifactId = value;
		}

		public void version(java.lang.String value) {
			this.version = value;
		}

		public void scope(legio.Project.Dependency.Scope value) {
			this.scope = value;
		}

		public void transitive(boolean value) {
			this.transitive = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("groupId", new java.util.ArrayList(java.util.Collections.singletonList(this.groupId)));
			map.put("artifactId", new java.util.ArrayList(java.util.Collections.singletonList(this.artifactId)));
			map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
			map.put("scope", new java.util.ArrayList(java.util.Collections.singletonList(this.scope)));
			map.put("transitive", new java.util.ArrayList(java.util.Collections.singletonList(this.transitive)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(legio.Project.Dependency.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("groupId")) this.groupId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("artifactId")) this.artifactId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("scope")) this.scope = tara.magritte.loaders.WordLoader.load(values, Scope.class, this).get(0);
			else if (name.equalsIgnoreCase("transitive")) this.transitive = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("groupId")) this.groupId = (java.lang.String) values.get(0);
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
