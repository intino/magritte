package legio;

import legio.*;

import java.util.*;

public class SnapshotRepository extends legio.Repository implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	

	public SnapshotRepository(tara.magritte.Node node) {
		super(node);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>(super.variables());
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(legio.SnapshotRepository.class);
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

	public class Create extends legio.Repository.Create {
		

		public Create(java.lang.String name) {
			super(name);
		}
		
	}
	
	public legio.LegioApplication application() {
		return ((legio.LegioApplication) graph().application());
	}
}
