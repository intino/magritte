package pandora;

import pandora.*;

import java.util.*;

public abstract class Data extends tara.magritte.Layer implements tara.magritte.tags.Component, tara.magritte.tags.Terminal {
	

	public Data(tara.magritte.Node node) {
		super(node);
	}

	public pandora.integer.IntegerData asInteger() {
		tara.magritte.Layer as = this.as(pandora.integer.IntegerData.class);
		return as != null ? (pandora.integer.IntegerData) as : addFacet(pandora.integer.IntegerData.class);
	}

	public boolean isInteger() {
		return is(pandora.integer.IntegerData.class);
	}

	public pandora.type.TypeData asType() {
		return this.as(pandora.type.TypeData.class);
	}

	public pandora.type.TypeData asType(tara.magritte.Expression<java.lang.String> type) {
		pandora.type.TypeData newElement = addFacet(pandora.type.TypeData.class);
		newElement.node().set(newElement, "type", java.util.Collections.singletonList(type)); 
	    return newElement;
	}

	public boolean isType() {
		return is(pandora.type.TypeData.class);
	}

	public void removeType() {
		this.removeFacet(pandora.type.TypeData.class);
	}

	public pandora.bool.BoolData asBool() {
		tara.magritte.Layer as = this.as(pandora.bool.BoolData.class);
		return as != null ? (pandora.bool.BoolData) as : addFacet(pandora.bool.BoolData.class);
	}

	public boolean isBool() {
		return is(pandora.bool.BoolData.class);
	}

	public pandora.real.RealData asReal() {
		tara.magritte.Layer as = this.as(pandora.real.RealData.class);
		return as != null ? (pandora.real.RealData) as : addFacet(pandora.real.RealData.class);
	}

	public boolean isReal() {
		return is(pandora.real.RealData.class);
	}

	public pandora.text.TextData asText() {
		tara.magritte.Layer as = this.as(pandora.text.TextData.class);
		return as != null ? (pandora.text.TextData) as : addFacet(pandora.text.TextData.class);
	}

	public boolean isText() {
		return is(pandora.text.TextData.class);
	}

	public pandora.object.ObjectData asObject() {
		return this.as(pandora.object.ObjectData.class);
	}

	public pandora.object.ObjectData asObject(pandora.Format format) {
		pandora.object.ObjectData newElement = addFacet(pandora.object.ObjectData.class);
		newElement.node().set(newElement, "format", java.util.Collections.singletonList(format)); 
	    return newElement;
	}

	public boolean isObject() {
		return is(pandora.object.ObjectData.class);
	}

	public void removeObject() {
		this.removeFacet(pandora.object.ObjectData.class);
	}

	public pandora.list.ListData asList() {
		tara.magritte.Layer as = this.as(pandora.list.ListData.class);
		return as != null ? (pandora.list.ListData) as : addFacet(pandora.list.ListData.class);
	}

	public boolean isList() {
		return is(pandora.list.ListData.class);
	}

	public pandora.file.FileData asFile() {
		tara.magritte.Layer as = this.as(pandora.file.FileData.class);
		return as != null ? (pandora.file.FileData) as : addFacet(pandora.file.FileData.class);
	}

	public boolean isFile() {
		return is(pandora.file.FileData.class);
	}

	public pandora.date.DateData asDate() {
		tara.magritte.Layer as = this.as(pandora.date.DateData.class);
		return as != null ? (pandora.date.DateData) as : addFacet(pandora.date.DateData.class);
	}

	public boolean isDate() {
		return is(pandora.date.DateData.class);
	}

	public pandora.datetime.DateTimeData asDateTime() {
		tara.magritte.Layer as = this.as(pandora.datetime.DateTimeData.class);
		return as != null ? (pandora.datetime.DateTimeData) as : addFacet(pandora.datetime.DateTimeData.class);
	}

	public boolean isDateTime() {
		return is(pandora.datetime.DateTimeData.class);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<?>> variables() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		return map;
	}

	public tara.magritte.Concept concept() {
		return this.graph().concept(pandora.Data.class);
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
