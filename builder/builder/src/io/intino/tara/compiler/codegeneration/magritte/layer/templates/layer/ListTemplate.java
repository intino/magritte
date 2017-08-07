package io.intino.tara.compiler.codegeneration.magritte.layer.templates.layer;

import org.siani.itrules.*;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.*;

public class ListTemplate extends Template {

	protected ListTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new ListTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "reactive | inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(");")),
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(")) : java.util.Collections.emptyList());")),
			rule().add((condition("type", "Variable & reference & owner")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(")) : java.util.Collections.emptyList());")),
			rule().add((condition("type", "Variable & function & owner")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(")) : java.util.Collections.emptyList());")),
			rule().add((condition("type", "Variable & owner")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(")));")),
			rule().add((condition("type", "Variable & metaType & multiple")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", _")).add(mark("containerName", "FirstLowerCase")).add(literal(".")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal("());")),
			rule().add((condition("type", "Variable & metaType")), (condition("type", "function | reactive")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(io.intino.tara.magritte.utils.NativeExtractor.extract(\"")).add(mark("name")).add(literal("\", _")).add(mark("containerName", "FirstLowerCase")).add(literal("))));")),
			rule().add((condition("type", "Variable & metaType")), not(condition("type", "inherited | overriden | volatile")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "FirstLowerCase")).add(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(this._")).add(mark("containerName", "FirstLowerCase")).add(literal(".")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal("())));")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited | overriden | instance")), (condition("trigger", "list"))).add(literal("if (")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(" != null) nodes.add(this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(".core$());")),
			rule().add((condition("type", "Node & owner")), not(condition("type", "inherited | overriden | instance")), (condition("trigger", "list"))).add(mark("name", "toCamelCase", "FirstLowerCase")).add(literal("List.stream().forEach(c -> nodes.add(c.core$()));")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited | overriden | instance")), (condition("trigger", "componentList"))).add(literal("if (")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(" != null) components.add(this.")).add(mark("name", "FirstLowerCase", "javaValidName")).add(literal(".core$());")),
			rule().add((condition("type", "Node & owner")), not(condition("type", "inherited | overriden | instance")), (condition("trigger", "componentList"))).add(literal("new java.util.ArrayList<>(")).add(mark("name", "toCamelCase", "toCamelCase", "FirstLowerCase")).add(literal("List).forEach(c -> components.add(c.core$()));")),
			rule().add((condition("type", "Node")), (condition("trigger", "list"))),
			rule().add((condition("type", "Node")), (condition("trigger", "componentlist")))
		);
		return this;
	}
}