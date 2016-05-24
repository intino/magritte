package tara.compiler.codegeneration.magritte.layer.templates.lazy;

import org.siani.itrules.LineSeparator;
import org.siani.itrules.Template;

import java.util.Locale;

import static org.siani.itrules.LineSeparator.LF;

public class Declaration_referenceTemplate extends Template {

	protected Declaration_referenceTemplate(Locale locale, LineSeparator separator) {
		super(locale, separator);
	}

	public static Template create() {
		return new Declaration_referenceTemplate(Locale.ENGLISH, LF).define();
	}

	public Template define() {
		add(
			rule().add((condition("type", "Variable & reference & multiple & owner")), not(condition("type", "concept")), (condition("trigger", "declaration"))).add(literal("protected java.util.List<tara.magritte.Reference> ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(" = new java.util.ArrayList<>();")),
			rule().add((condition("type", "Variable & reference & owner")), not(condition("type", "concept")), not(condition("type", "inherited")), not(condition("type", "overriden")), (condition("trigger", "declaration"))).add(literal("protected tara.magritte.Reference ")).add(mark("name", "firstLowerCase", "javaValidWord")).add(literal(";"))
		);
		return this;
	}
}