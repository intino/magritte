package tara.templates.layer;

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
			rule().add((condition("type", "Variable & multiple & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(");")),
			rule().add((condition("type", "Variable & reference & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(")) : java.util.Collections.emptyList());")),
			rule().add((condition("type", "Variable & function & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", this.")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" != null ? new java.util.ArrayList(java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(")) : java.util.Collections.emptyList());")),
			rule().add((condition("type", "Variable & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(")));")),
			rule().add((condition("type", "Variable & metaType & multiple")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", _")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("());")),
			rule().add((condition("type", "Variable & metaType")), (condition("type", "function | reactive")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(tara.magritte.utils.NativeExtractor.extract(\"")).add(mark("name")).add(literal("\", _")).add(mark("containerName", "firstLowerCase")).add(literal("))));")),
			rule().add((condition("type", "Variable & metaType")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("map.put(\"")).add(mark("name", "firstLowerCase")).add(literal("\", new java.util.ArrayList(java.util.Collections.singletonList(_")).add(mark("containerName", "firstLowerCase")).add(literal(".")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("())));")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), not(condition("type", "overriden")), (condition("trigger", "list"))).add(literal("if (")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" != null) instances.add(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("._instance());")),
			rule().add((condition("type", "Node")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("type", "owner")), (condition("trigger", "list"))).add(mark("name", "firstLowerCase")).add(literal("List.stream().forEach(c -> instances.add(c._instance()));")),
			rule().add((condition("type", "Node & single & feature & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "featureList"))).add(literal("if (")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" != null) features.add(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("._instance());")),
			rule().add((condition("type", "Node & feature & owner")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "featureList"))).add(mark("name", "firstLowerCase")).add(literal("List.stream().forEach(c -> features.add(c._instance()));")),
			rule().add((condition("type", "Node & single & owner")), not(condition("type", "feature")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "componentList"))).add(literal("if (")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" != null) components.add(")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal("._instance());")),
			rule().add((condition("type", "Node & owner")), not(condition("type", "feature")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "componentList"))).add(mark("name", "firstLowerCase")).add(literal("List.stream().forEach(c -> components.add(c._instance()));")),
			rule().add((condition("type", "Node")), (condition("trigger", "list"))),
			rule().add((condition("type", "Node")), (condition("trigger", "featurelist"))),
			rule().add((condition("type", "Node")), (condition("trigger", "componentlist")))
		);
		return this;
	}
}