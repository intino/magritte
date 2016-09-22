package legio.generatorlevel;

import legio.*;

import java.util.*;

public abstract class GeneratorLevelProject extends legio.level.LevelProject implements tara.magritte.tags.Terminal {
	protected int refactorId;
	protected boolean persistent;
	protected java.util.List<legio.generatorlevel.GeneratorLevelProject.OutDSL> outDSLList = new java.util.ArrayList<>();
	
	

	public GeneratorLevelProject(tara.magritte.Node node) {
		super(node);
	}

	public int refactorId() {
		return refactorId;
	}

	public boolean persistent() {
		return persistent;
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

	public void refactorId(int value) {
		this.refactorId = value;
	}

	public void persistent(boolean value) {
		this.persistent = value;
	}

	public void groupId(java.lang.String value) {
		this._project.groupId(value);
	}

	public void version(java.lang.String value) {
		this._project.version(value);
	}

	public java.util.List<legio.generatorlevel.GeneratorLevelProject.OutDSL> outDSLList() {
		return outDSLList;
	}

	public legio.generatorlevel.GeneratorLevelProject.OutDSL outDSL(int index) {
		return outDSLList.get(index);
	}

	public java.util.List<legio.generatorlevel.GeneratorLevelProject.OutDSL> outDSLList(java.util.function.Predicate<legio.generatorlevel.GeneratorLevelProject.OutDSL> predicate) {
		return outDSLList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
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
		outDSLList.stream().forEach(c -> components.add(c.node()));
		
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		map.put("refactorId", new java.util.ArrayList(java.util.Collections.singletonList(this.refactorId)));
		map.put("persistent", new java.util.ArrayList(java.util.Collections.singletonList(this.persistent)));
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(legio.Project.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("GeneratorLevel#Project$OutDSL")) this.outDSLList.add(node.as(legio.generatorlevel.GeneratorLevelProject.OutDSL.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("GeneratorLevel#Project$OutDSL")) this.outDSLList.remove(node.as(legio.generatorlevel.GeneratorLevelProject.OutDSL.class));
    }

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		_project.node().load(_project, name, values);
		if (name.equalsIgnoreCase("refactorId")) this.refactorId = tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
		else if (name.equalsIgnoreCase("persistent")) this.persistent = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		_project.node().set(_project, name, values);
		if (name.equalsIgnoreCase("refactorId")) this.refactorId = (java.lang.Integer) values.get(0);
		else if (name.equalsIgnoreCase("persistent")) this.persistent = (java.lang.Boolean) values.get(0);
	}

	public Create create() {
		return new Create(null);
	}

	public Create create(java.lang.String name) {
		return new Create(name);
	}

	public class Create extends legio.level.LevelProject.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}

		public legio.generatorlevel.GeneratorLevelProject.OutDSL outDSL() {
		    legio.generatorlevel.GeneratorLevelProject.OutDSL newElement = graph().concept(legio.generatorlevel.GeneratorLevelProject.OutDSL.class).createNode(name, node()).as(legio.generatorlevel.GeneratorLevelProject.OutDSL.class);
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
	
	public static class OutDSL extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		

		public OutDSL(tara.magritte.Node node) {
			super(node);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(legio.generatorlevel.GeneratorLevelProject.OutDSL.class);
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
