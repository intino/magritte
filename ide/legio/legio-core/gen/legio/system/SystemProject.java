package legio.system;

import legio.*;

import java.util.*;

public class SystemProject extends legio.level.LevelProject implements tara.magritte.tags.Terminal {
	
	

	public SystemProject(tara.magritte.Node node) {
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

	public java.util.List<legio.Project.DependsOf> dependsOfList() {
		return (java.util.List<legio.Project.DependsOf>) _project.dependsOfList();
	}

	public legio.Project.DependsOf dependsOfList(int index) {
		return _project.dependsOfList().get(index);
	}

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		return new java.util.ArrayList<>(components);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(legio.Project.class);
	}

	@Override
	protected void _load(java.lang.String name, java.util.List<?> values) {
		super._load(name, values);
		_project.node().load(_project, name, values);
	}

	@Override
	protected void _set(java.lang.String name, java.util.List<?> values) {
		super._set(name, values);
		_project.node().set(_project, name, values);
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

		public legio.Project.DependsOf dependsOf(java.lang.String id) {
		    legio.Project.DependsOf newElement = graph().concept(legio.Project.DependsOf.class).createNode(name, node()).as(legio.Project.DependsOf.class);
			newElement.node().set(newElement, "id", java.util.Collections.singletonList(id)); 
		    return newElement;
		}
		
	}
	
	public legio.LegioApplication application() {
		return ((legio.LegioApplication) graph().application());
	}
}
