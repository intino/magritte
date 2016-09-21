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

	public java.util.List<legio.Project.DependsOf> dependsOfList() {
		return (java.util.List<legio.Project.DependsOf>) _project.dependsOfList();
	}

	public legio.Project.DependsOf dependsOfList(int index) {
		return _project.dependsOfList().get(index);
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

		public legio.level.LevelProject.DSL dSL(boolean fromModule) {
		    legio.level.LevelProject.DSL newElement = graph().concept(legio.level.LevelProject.DSL.class).createNode(name, node()).as(legio.level.LevelProject.DSL.class);
			newElement.node().set(newElement, "fromModule", java.util.Collections.singletonList(fromModule)); 
		    return newElement;
		}

		public legio.Project.DependsOf dependsOf(java.lang.String id) {
		    legio.Project.DependsOf newElement = graph().concept(legio.Project.DependsOf.class).createNode(name, node()).as(legio.Project.DependsOf.class);
			newElement.node().set(newElement, "id", java.util.Collections.singletonList(id)); 
		    return newElement;
		}
		
	}
	
	public static class DSL extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected boolean fromModule;

		public DSL(tara.magritte.Node node) {
			super(node);
		}

		public boolean fromModule() {
			return fromModule;
		}

		public void fromModule(boolean value) {
			this.fromModule = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("fromModule", new java.util.ArrayList(java.util.Collections.singletonList(this.fromModule)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(legio.level.LevelProject.DSL.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("fromModule")) this.fromModule = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("fromModule")) this.fromModule = (java.lang.Boolean) values.get(0);
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
