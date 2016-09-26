package org.siani.legio;

import java.util.*;

public class Project extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
	protected java.lang.String groupId;
	protected java.lang.String version;
	protected tara.magritte.Expression<java.util.List<java.lang.String>> supportedLanguages;
	protected java.util.List<Repository> repositoryList = new java.util.ArrayList<>();
	protected Project.DSL dSL;
	protected java.util.List<Project.Generation> generationList = new java.util.ArrayList<>();
	protected java.util.List<Project.Dependencies> dependenciesList = new java.util.ArrayList<>();

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

	public java.util.List<Repository> repositoryList() {
		return repositoryList;
	}

	public Repository repository(int index) {
		return repositoryList.get(index);
	}

	public java.util.List<Repository> repositoryList(java.util.function.Predicate<Repository> predicate) {
		return repositoryList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public Project.DSL dSL() {
		return dSL;
	}

	public java.util.List<Project.Generation> generationList() {
		return generationList;
	}

	public Project.Generation generation(int index) {
		return generationList.get(index);
	}

	public java.util.List<Project.Generation> generationList(java.util.function.Predicate<Project.Generation> predicate) {
		return generationList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	public java.util.List<Project.Dependencies> dependenciesList() {
		return dependenciesList;
	}

	public Project.Dependencies dependencies(int index) {
		return dependenciesList.get(index);
	}

	public java.util.List<Project.Dependencies> dependenciesList(java.util.function.Predicate<Project.Dependencies> predicate) {
		return dependenciesList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
	}

	

	public void dSL(Project.DSL value) {
		this.dSL = value;
	}

	

	

	public List<tara.magritte.Node> componentList() {
		java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
		repositoryList.stream().forEach(c -> components.add(c.node()));
		if (dSL != null) components.add(this.dSL.node());
		generationList.stream().forEach(c -> components.add(c.node()));
		dependenciesList.stream().forEach(c -> components.add(c.node()));
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
		return this.graph().concept(Project.class);
	}

	@Override
	protected void addNode(tara.magritte.Node node) {
		super.addNode(node);
		if (node.is("Repository")) this.repositoryList.add(node.as(Repository.class));
		if (node.is("Project$DSL")) this.dSL = node.as(Project.DSL.class);
		if (node.is("Project$Generation")) this.generationList.add(node.as(Project.Generation.class));
		if (node.is("Project$Dependencies")) this.dependenciesList.add(node.as(Project.Dependencies.class));
	}

	@Override
    protected void removeNode(tara.magritte.Node node) {
        super.removeNode(node);
        if (node.is("Repository")) this.repositoryList.remove(node.as(Repository.class));
        if (node.is("Project$DSL")) this.dSL = null;
        if (node.is("Project$Generation")) this.generationList.remove(node.as(Project.Generation.class));
        if (node.is("Project$Dependencies")) this.dependenciesList.remove(node.as(Project.Dependencies.class));
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

		public Repository repository(java.lang.String url) {
		    Repository newElement = graph().concept(Repository.class).createNode(name, node()).as(Repository.class);
			newElement.node().set(newElement, "url", java.util.Collections.singletonList(url)); 
		    return newElement;
		}

		public Project.DSL dSL(java.lang.String version) {
		    Project.DSL newElement = graph().concept(Project.DSL.class).createNode(name, node()).as(Project.DSL.class);
			newElement.node().set(newElement, "version", java.util.Collections.singletonList(version)); 
		    return newElement;
		}

		public Project.Generation generation() {
		    Project.Generation newElement = graph().concept(Project.Generation.class).createNode(name, node()).as(Project.Generation.class);
		    return newElement;
		}

		public Project.Dependencies dependencies() {
		    Project.Dependencies newElement = graph().concept(Project.Dependencies.class).createNode(name, node()).as(Project.Dependencies.class);
		    return newElement;
		}
		
	}
	
	public static class DSL extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected java.lang.String version;

		public DSL(tara.magritte.Node node) {
			super(node);
		}

		public java.lang.String version() {
			return version;
		}

		public void version(java.lang.String value) {
			this.version = value;
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(Project.DSL.class);
		}

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("version")) this.version = (java.lang.String) values.get(0);
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
		
		public LegioApplication application() {
			return ((LegioApplication) graph().application());
		}
	}
	
	public static class Generation extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		protected int refactorId;
		protected boolean persistent;
		protected Project.Generation.OutDSL outDSL;

		public Generation(tara.magritte.Node node) {
			super(node);
		}

		public int refactorId() {
			return refactorId;
		}

		public boolean persistent() {
			return persistent;
		}

		public void refactorId(int value) {
			this.refactorId = value;
		}

		public void persistent(boolean value) {
			this.persistent = value;
		}

		public Project.Generation.OutDSL outDSL() {
			return outDSL;
		}

		public void outDSL(Project.Generation.OutDSL value) {
			this.outDSL = value;
		}

		public List<tara.magritte.Node> componentList() {
			java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
			if (outDSL != null) components.add(this.outDSL.node());
			return new java.util.ArrayList<>(components);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			map.put("refactorId", new java.util.ArrayList(java.util.Collections.singletonList(this.refactorId)));
			map.put("persistent", new java.util.ArrayList(java.util.Collections.singletonList(this.persistent)));
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(Project.Generation.class);
		}

		@Override
		protected void addNode(tara.magritte.Node node) {
			super.addNode(node);
			if (node.is("Project$Generation$OutDSL")) this.outDSL = node.as(Project.Generation.OutDSL.class);
		}

		@Override
	    protected void removeNode(tara.magritte.Node node) {
	        super.removeNode(node);
	        if (node.is("Project$Generation$OutDSL")) this.outDSL = null;
	    }

		@Override
		protected void _load(java.lang.String name, java.util.List<?> values) {
			super._load(name, values);
			if (name.equalsIgnoreCase("refactorId")) this.refactorId = tara.magritte.loaders.IntegerLoader.load(values, this).get(0);
			else if (name.equalsIgnoreCase("persistent")) this.persistent = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
		}

		@Override
		protected void _set(java.lang.String name, java.util.List<?> values) {
			super._set(name, values);
			if (name.equalsIgnoreCase("refactorId")) this.refactorId = (java.lang.Integer) values.get(0);
			else if (name.equalsIgnoreCase("persistent")) this.persistent = (java.lang.Boolean) values.get(0);
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

			public Project.Generation.OutDSL outDSL() {
			    Project.Generation.OutDSL newElement = graph().concept(Project.Generation.OutDSL.class).createNode(name, node()).as(Project.Generation.OutDSL.class);
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
				return this.graph().concept(Project.Generation.OutDSL.class);
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
			
			public LegioApplication application() {
				return ((LegioApplication) graph().application());
			}
		}
		
		
		public LegioApplication application() {
			return ((LegioApplication) graph().application());
		}
	}
	
	public static class Dependencies extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
		
		protected java.util.List<Project.Dependencies.Dependency> dependencyList = new java.util.ArrayList<>();
		protected java.util.List<Project.Dependencies.Compile> compileList = new java.util.ArrayList<>();
		protected java.util.List<Project.Dependencies.Runtime> runtimeList = new java.util.ArrayList<>();
		protected java.util.List<Project.Dependencies.Test> testList = new java.util.ArrayList<>();

		public Dependencies(tara.magritte.Node node) {
			super(node);
		}

		public java.util.List<Project.Dependencies.Dependency> dependencyList() {
			return dependencyList;
		}

		public Project.Dependencies.Dependency dependency(int index) {
			return dependencyList.get(index);
		}

		public java.util.List<Project.Dependencies.Dependency> dependencyList(java.util.function.Predicate<Project.Dependencies.Dependency> predicate) {
			return dependencyList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<Project.Dependencies.Compile> compileList() {
			return compileList;
		}

		public Project.Dependencies.Compile compile(int index) {
			return compileList.get(index);
		}

		public java.util.List<Project.Dependencies.Compile> compileList(java.util.function.Predicate<Project.Dependencies.Compile> predicate) {
			return compileList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<Project.Dependencies.Runtime> runtimeList() {
			return runtimeList;
		}

		public Project.Dependencies.Runtime runtime(int index) {
			return runtimeList.get(index);
		}

		public java.util.List<Project.Dependencies.Runtime> runtimeList(java.util.function.Predicate<Project.Dependencies.Runtime> predicate) {
			return runtimeList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		public java.util.List<Project.Dependencies.Test> testList() {
			return testList;
		}

		public Project.Dependencies.Test test(int index) {
			return testList.get(index);
		}

		public java.util.List<Project.Dependencies.Test> testList(java.util.function.Predicate<Project.Dependencies.Test> predicate) {
			return testList().stream().filter(predicate).collect(java.util.stream.Collectors.toList());
		}

		

		

		

		

		public List<tara.magritte.Node> componentList() {
			java.util.Set<tara.magritte.Node> components = new java.util.LinkedHashSet<>(super.componentList());
			dependencyList.stream().forEach(c -> components.add(c.node()));
			compileList.stream().forEach(c -> components.add(c.node()));
			runtimeList.stream().forEach(c -> components.add(c.node()));
			testList.stream().forEach(c -> components.add(c.node()));
			return new java.util.ArrayList<>(components);
		}

		@Override
		public java.util.Map<java.lang.String, java.util.List<?>> variables() {
			java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
			return map;
		}

		public tara.magritte.Concept concept() {
			return this.graph().concept(Project.Dependencies.class);
		}

		@Override
		protected void addNode(tara.magritte.Node node) {
			super.addNode(node);
			if (node.is("Project$Dependencies$Dependency")) this.dependencyList.add(node.as(Project.Dependencies.Dependency.class));
			if (node.is("Project$Dependencies$Compile")) this.compileList.add(node.as(Project.Dependencies.Compile.class));
			if (node.is("Project$Dependencies$Runtime")) this.runtimeList.add(node.as(Project.Dependencies.Runtime.class));
			if (node.is("Project$Dependencies$Test")) this.testList.add(node.as(Project.Dependencies.Test.class));
		}

		@Override
	    protected void removeNode(tara.magritte.Node node) {
	        super.removeNode(node);
	        if (node.is("Project$Dependencies$Dependency")) this.dependencyList.remove(node.as(Project.Dependencies.Dependency.class));
	        if (node.is("Project$Dependencies$Compile")) this.compileList.remove(node.as(Project.Dependencies.Compile.class));
	        if (node.is("Project$Dependencies$Runtime")) this.runtimeList.remove(node.as(Project.Dependencies.Runtime.class));
	        if (node.is("Project$Dependencies$Test")) this.testList.remove(node.as(Project.Dependencies.Test.class));
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

			

			public Project.Dependencies.Compile compile(java.lang.String groupId, java.lang.String artifactId, java.lang.String version) {
			    Project.Dependencies.Compile newElement = graph().concept(Project.Dependencies.Compile.class).createNode(name, node()).as(Project.Dependencies.Compile.class);
				newElement.node().set(newElement, "groupId", java.util.Collections.singletonList(groupId));
				newElement.node().set(newElement, "artifactId", java.util.Collections.singletonList(artifactId));
				newElement.node().set(newElement, "version", java.util.Collections.singletonList(version)); 
			    return newElement;
			}

			public Project.Dependencies.Runtime runtime(java.lang.String groupId, java.lang.String artifactId, java.lang.String version) {
			    Project.Dependencies.Runtime newElement = graph().concept(Project.Dependencies.Runtime.class).createNode(name, node()).as(Project.Dependencies.Runtime.class);
				newElement.node().set(newElement, "groupId", java.util.Collections.singletonList(groupId));
				newElement.node().set(newElement, "artifactId", java.util.Collections.singletonList(artifactId));
				newElement.node().set(newElement, "version", java.util.Collections.singletonList(version)); 
			    return newElement;
			}

			public Project.Dependencies.Test test(java.lang.String groupId, java.lang.String artifactId, java.lang.String version) {
			    Project.Dependencies.Test newElement = graph().concept(Project.Dependencies.Test.class).createNode(name, node()).as(Project.Dependencies.Test.class);
				newElement.node().set(newElement, "groupId", java.util.Collections.singletonList(groupId));
				newElement.node().set(newElement, "artifactId", java.util.Collections.singletonList(artifactId));
				newElement.node().set(newElement, "version", java.util.Collections.singletonList(version)); 
			    return newElement;
			}
			
		}
		
		public static abstract class Dependency extends tara.magritte.Layer implements tara.magritte.tags.Terminal {
			protected java.lang.String groupId;
			protected java.lang.String artifactId;
			protected java.lang.String version;
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

			public void transitive(boolean value) {
				this.transitive = value;
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
				map.put("groupId", new java.util.ArrayList(java.util.Collections.singletonList(this.groupId)));
				map.put("artifactId", new java.util.ArrayList(java.util.Collections.singletonList(this.artifactId)));
				map.put("version", new java.util.ArrayList(java.util.Collections.singletonList(this.version)));
				map.put("transitive", new java.util.ArrayList(java.util.Collections.singletonList(this.transitive)));
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(Project.Dependencies.Dependency.class);
			}

			@Override
			protected void _load(java.lang.String name, java.util.List<?> values) {
				super._load(name, values);
				if (name.equalsIgnoreCase("groupId")) this.groupId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("artifactId")) this.artifactId = tara.magritte.loaders.StringLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("version")) this.version = tara.magritte.loaders.StringLoader.load(values, this).get(0);
				else if (name.equalsIgnoreCase("transitive")) this.transitive = tara.magritte.loaders.BooleanLoader.load(values, this).get(0);
			}

			@Override
			protected void _set(java.lang.String name, java.util.List<?> values) {
				super._set(name, values);
				if (name.equalsIgnoreCase("groupId")) this.groupId = (java.lang.String) values.get(0);
				else if (name.equalsIgnoreCase("artifactId")) this.artifactId = (java.lang.String) values.get(0);
				else if (name.equalsIgnoreCase("version")) this.version = (java.lang.String) values.get(0);
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
			
			public LegioApplication application() {
				return ((LegioApplication) graph().application());
			}
		}
		
		public static class Compile extends Project.Dependencies.Dependency implements tara.magritte.tags.Terminal {
			

			public Compile(tara.magritte.Node node) {
				super(node);
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(Project.Dependencies.Compile.class);
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

			public class Create extends Project.Dependencies.Dependency.Create {
				

				public Create(java.lang.String name) {
					super(name);
				}
				
			}
			
			public LegioApplication application() {
				return ((LegioApplication) graph().application());
			}
		}
		
		public static class Runtime extends Project.Dependencies.Dependency implements tara.magritte.tags.Terminal {
			

			public Runtime(tara.magritte.Node node) {
				super(node);
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(Project.Dependencies.Runtime.class);
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

			public class Create extends Project.Dependencies.Dependency.Create {
				

				public Create(java.lang.String name) {
					super(name);
				}
				
			}
			
			public LegioApplication application() {
				return ((LegioApplication) graph().application());
			}
		}
		
		public static class Test extends Project.Dependencies.Dependency implements tara.magritte.tags.Terminal {
			

			public Test(tara.magritte.Node node) {
				super(node);
			}

			@Override
			public java.util.Map<java.lang.String, java.util.List<?>> variables() {
				java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
				return map;
			}

			public tara.magritte.Concept concept() {
				return this.graph().concept(Project.Dependencies.Test.class);
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

			public class Create extends Project.Dependencies.Dependency.Create {
				

				public Create(java.lang.String name) {
					super(name);
				}
				
			}
			
			public LegioApplication application() {
				return ((LegioApplication) graph().application());
			}
		}
		
		
		public LegioApplication application() {
			return ((LegioApplication) graph().application());
		}
	}
	
	
	public LegioApplication application() {
		return ((LegioApplication) graph().application());
	}
}
