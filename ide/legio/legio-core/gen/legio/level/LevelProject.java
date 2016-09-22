package legio.level;

import legio.*;

import java.util.*;

public abstract class LevelProject extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	
	protected java.util.List<legio.level.LevelProject.DSL> dSLList = new java.util.ArrayList<>();
	
	
	protected legio.Project _project;

	public LevelProject(tara.magritte.Node node) {
		super(node);
	}

	public java.lang.String groupId() {
		return _project.groupId();
	}

	public java.lang.String version() {
		return _project.version();
	}

	public java.util.List<java.lang.String> supportedLanguages() {
		return _project.supportedLanguages();
	}

	public java.lang.String supportedLanguages(int index) {
		return _project.supportedLanguages().get(index);
	}

	public void groupId(java.lang.String value) {
		this._project.groupId(value);
	}

	public void version(java.lang.String value) {
		this._project.version(value);
	}

	public java.util.List<legio.level.LevelProject.DSL> dSLList() {
		return dSLList;
	}

	public legio.level.LevelProject.DSL dSL(int index) {
		return dSLList.get(index);
	}

	public java.util.List<legio.level.LevelProject.DSL> dSLList(java.util.function.Predicate<legio.level.LevelProject.DSL> predicate) {
		return dSLList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<legio.Repository> repositoryList() {
		return (java.util.List<legio.Repository>) _project.repositoryList();
	}

	public legio.Repository repositoryList(int index) {
		return _project.repositoryList().get(index);
	}

	public java.util.List<legio.Project.Dependency> dependencyList() {
		return (java.util.List<legio.Project.Dependency>) _project.dependencyList();
	}

	public legio.Project.Dependency dependencyList(int index) {
		return _project.dependencyList().get(index);
	}

	

	

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		dSLList.stream().forEach(c -> components.add(c.node()));
		
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(legio.Project.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Level#Project$DSL")) this.dSLList.add(node.as(legio.level.LevelProject.DSL.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Level#Project$DSL")) this.dSLList.remove(node.as(legio.level.LevelProject.DSL.class));
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
	    if (layer instanceof legio.Project) _project = (legio.Project) layer;
	    
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

		public legio.level.LevelProject.DSL dSL() {
		    legio.level.LevelProject.DSL newElement = graph().concept(legio.level.LevelProject.DSL.class).createNode(name, node()).as(legio.level.LevelProject.DSL.class);
		    return newElement;
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
	
	public static class DSL extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		

		public DSL(tara.magritte.Node node) {
			super(node);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(legio.level.LevelProject.DSL.class);
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
		
		public legio.LegioApplication application() {
			return ((legio.LegioApplication) graph().application());
		}
	}
	
	
	public legio.LegioApplication application() {
		return ((legio.LegioApplication) graph().application());
	}
}
