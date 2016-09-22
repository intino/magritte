package legio;

import tara.magritte.Graph;

import java.util.List;

public class GraphWrapper extends tara.magritte.GraphWrapper {

	protected Graph graph;
	private legio.Project project;
	private List<legio.level.LevelProject> levelProjectList;
	private List<legio.generatorlevel.GeneratorLevelProject> generatorLevelProjectList;
	private List<legio.platform.PlatformProject> platformProjectList;
	private List<legio.application.ApplicationProject> applicationProjectList;
	private List<legio.ontology.OntologyProject> ontologyProjectList;
	private List<legio.system.SystemProject> systemProjectList;

	public GraphWrapper(Graph graph) {
		this.graph = graph;
		this.graph.i18n().register("Legio");
	    update();
	}

	protected void update() {
		project = this.graph.rootList(legio.Project.class).stream().findFirst().orElse(null);
		levelProjectList = this.graph.rootList(legio.level.LevelProject.class);
		generatorLevelProjectList = this.graph.rootList(legio.generatorlevel.GeneratorLevelProject.class);
		platformProjectList = this.graph.rootList(legio.platform.PlatformProject.class);
		applicationProjectList = this.graph.rootList(legio.application.ApplicationProject.class);
		ontologyProjectList = this.graph.rootList(legio.ontology.OntologyProject.class);
		systemProjectList = this.graph.rootList(legio.system.SystemProject.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		if (node.is("Project")) this.project = node.as(legio.Project.class);
		if (node.is("LevelProject")) this.levelProjectList.add(node.as(legio.level.LevelProject.class));
		if (node.is("GeneratorLevelProject")) this.generatorLevelProjectList.add(node.as(legio.generatorlevel.GeneratorLevelProject.class));
		if (node.is("PlatformProject")) this.platformProjectList.add(node.as(legio.platform.PlatformProject.class));
		if (node.is("ApplicationProject")) this.applicationProjectList.add(node.as(legio.application.ApplicationProject.class));
		if (node.is("OntologyProject")) this.ontologyProjectList.add(node.as(legio.ontology.OntologyProject.class));
		if (node.is("SystemProject")) this.systemProjectList.add(node.as(legio.system.SystemProject.class));
	}

	@Override
	protected void removeNode(tara.magritte.Node node) {
		if (node.is("Project")) this.project = null;
		if (node.is("LevelProject")) this.levelProjectList.remove(node.as(legio.level.LevelProject.class));
		if (node.is("GeneratorLevelProject")) this.generatorLevelProjectList.remove(node.as(legio.generatorlevel.GeneratorLevelProject.class));
		if (node.is("PlatformProject")) this.platformProjectList.remove(node.as(legio.platform.PlatformProject.class));
		if (node.is("ApplicationProject")) this.applicationProjectList.remove(node.as(legio.application.ApplicationProject.class));
		if (node.is("OntologyProject")) this.ontologyProjectList.remove(node.as(legio.ontology.OntologyProject.class));
		if (node.is("SystemProject")) this.systemProjectList.remove(node.as(legio.system.SystemProject.class));
	}

	public String message(String language, String key, Object... parameters) {
		return graph.i18n().message(language, key, parameters);
	}

	public java.net.URL resourceAsMessage(String language, String key) {
		return graph.loadResource(graph.i18n().message(language, key));
	}

	public java.util.Map<String,String> keysIn(String language) {
		return graph.i18n().wordsIn(language);
	}

	public tara.magritte.Concept concept(String concept) {
		return graph.concept(concept);
	}

	public tara.magritte.Concept concept(java.lang.Class<? extends tara.magritte.Layer> layerClass) {
		return graph.concept(layerClass);
	}

	public List<tara.magritte.Concept> conceptList() {
		return graph.conceptList();
	}

	public List<tara.magritte.Concept> conceptList(java.util.function.Predicate<tara.magritte.Concept> predicate) {
		return graph.conceptList(predicate);
	}

	public tara.magritte.Node createRoot(tara.magritte.Concept concept, String namespace) {
		return graph.createRoot(concept, namespace);
	}

	public <T extends tara.magritte.Layer> T createRoot(java.lang.Class<T> layerClass, String namespace) {
		return graph.createRoot(layerClass, namespace);
	}

	public tara.magritte.Node createRoot(String concept, String namespace) {
		return graph.createRoot(concept, namespace);
	}

	public <T extends tara.magritte.Layer> T createRoot(java.lang.Class<T> layerClass, String namespace, String id) {
		return graph.createRoot(layerClass, namespace, id);
	}

	public tara.magritte.Node createRoot(String concept, String namespace, String id) {
		return graph.createRoot(concept, namespace, id);
	}

	public tara.magritte.Node createRoot(tara.magritte.Concept concept, String namespace, String id) {
		return graph.createRoot(concept, namespace, id);
	}

	public legio.Project project() {
	    return project;
	}

	public List<legio.level.LevelProject> levelProjectList() {
	    return levelProjectList;
	}

	public List<legio.generatorlevel.GeneratorLevelProject> generatorLevelProjectList() {
	    return generatorLevelProjectList;
	}

	public List<legio.platform.PlatformProject> platformProjectList() {
	    return platformProjectList;
	}

	public List<legio.application.ApplicationProject> applicationProjectList() {
	    return applicationProjectList;
	}

	public List<legio.ontology.OntologyProject> ontologyProjectList() {
	    return ontologyProjectList;
	}

	public List<legio.system.SystemProject> systemProjectList() {
	    return systemProjectList;
	}

	public List<legio.level.LevelProject> levelProjectList(java.util.function.Predicate<legio.level.LevelProject> predicate) {
	    return levelProjectList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public legio.level.LevelProject levelProject(int index) {
		return levelProjectList.get(index);
	}

	public List<legio.generatorlevel.GeneratorLevelProject> generatorLevelProjectList(java.util.function.Predicate<legio.generatorlevel.GeneratorLevelProject> predicate) {
	    return generatorLevelProjectList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public legio.generatorlevel.GeneratorLevelProject generatorLevelProject(int index) {
		return generatorLevelProjectList.get(index);
	}

	public List<legio.platform.PlatformProject> platformProjectList(java.util.function.Predicate<legio.platform.PlatformProject> predicate) {
	    return platformProjectList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public legio.platform.PlatformProject platformProject(int index) {
		return platformProjectList.get(index);
	}

	public List<legio.application.ApplicationProject> applicationProjectList(java.util.function.Predicate<legio.application.ApplicationProject> predicate) {
	    return applicationProjectList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public legio.application.ApplicationProject applicationProject(int index) {
		return applicationProjectList.get(index);
	}

	public List<legio.ontology.OntologyProject> ontologyProjectList(java.util.function.Predicate<legio.ontology.OntologyProject> predicate) {
	    return ontologyProjectList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public legio.ontology.OntologyProject ontologyProject(int index) {
		return ontologyProjectList.get(index);
	}

	public List<legio.system.SystemProject> systemProjectList(java.util.function.Predicate<legio.system.SystemProject> predicate) {
	    return systemProjectList.stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public legio.system.SystemProject systemProject(int index) {
		return systemProjectList.get(index);
	}

	public tara.magritte.Graph graph() {
		return graph;
	}

	public Create create() {
		return new Create("Misc", null);
	}

	public Create create(String namespace) {
		return new Create(namespace, null);
	}

	public Create create(String namespace, String name) {
		return new Create(namespace, name);
	}

	public class Create {
		private final String namespace;
		private final String name;

		public Create(String namespace, String name) {
			this.namespace = namespace;
			this.name = name;
		}

		public legio.Project project(java.lang.String groupId, java.lang.String version) {
			legio.Project newElement = GraphWrapper.this.graph.createRoot(legio.Project.class, namespace, name).as(legio.Project.class);
			newElement.node().set(newElement, "groupId", java.util.Collections.singletonList(groupId));
			newElement.node().set(newElement, "version", java.util.Collections.singletonList(version));
			return newElement;
		}

		public legio.level.LevelProject levelProject() {
			legio.level.LevelProject newElement = GraphWrapper.this.graph.createRoot(legio.level.LevelProject.class, namespace, name).as(legio.level.LevelProject.class);
			
			return newElement;
		}

		public legio.generatorlevel.GeneratorLevelProject generatorLevelProject() {
			legio.generatorlevel.GeneratorLevelProject newElement = GraphWrapper.this.graph.createRoot(legio.generatorlevel.GeneratorLevelProject.class, namespace, name).as(legio.generatorlevel.GeneratorLevelProject.class);
			
			return newElement;
		}

		public legio.platform.PlatformProject platformProject() {
			legio.platform.PlatformProject newElement = GraphWrapper.this.graph.createRoot(legio.platform.PlatformProject.class, namespace, name).as(legio.platform.PlatformProject.class);
			
			return newElement;
		}

		public legio.application.ApplicationProject applicationProject() {
			legio.application.ApplicationProject newElement = GraphWrapper.this.graph.createRoot(legio.application.ApplicationProject.class, namespace, name).as(legio.application.ApplicationProject.class);
			
			return newElement;
		}

		public legio.ontology.OntologyProject ontologyProject() {
			legio.ontology.OntologyProject newElement = GraphWrapper.this.graph.createRoot(legio.ontology.OntologyProject.class, namespace, name).as(legio.ontology.OntologyProject.class);
			
			return newElement;
		}

		public legio.system.SystemProject systemProject() {
			legio.system.SystemProject newElement = GraphWrapper.this.graph.createRoot(legio.system.SystemProject.class, namespace, name).as(legio.system.SystemProject.class);
			
			return newElement;
		}

	}


}