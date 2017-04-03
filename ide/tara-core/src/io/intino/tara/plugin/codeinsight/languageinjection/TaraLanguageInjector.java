package io.intino.tara.plugin.codeinsight.languageinjection;

import com.intellij.lang.Language;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import io.intino.tara.Checker;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.model.Tag;
import io.intino.tara.lang.model.Variable;
import io.intino.tara.lang.semantics.errorcollector.SemanticFatalException;
import io.intino.tara.plugin.lang.psi.Expression;
import io.intino.tara.plugin.lang.psi.Valued;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import io.intino.tara.plugin.lang.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;

import java.util.HashMap;
import java.util.Map;

import static io.intino.tara.lang.model.Primitive.FUNCTION;
import static io.intino.tara.plugin.project.module.ModuleProvider.moduleOf;

public class TaraLanguageInjector implements LanguageInjector {


	private static Map<String, String> languageMap = new HashMap<>();

	static {
		languageMap.put("java", "JAVA");
		languageMap.put("groovy", "Groovy");
		languageMap.put("kotlin", "kotlin");
	}

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!Expression.class.isInstance(host) || !host.isValidHost()) return;
		final Language language = injectionLanguage(host);
		this.resolve(host);
		injectionPlacesRegistrar.addPlace(language,
				getRangeInsideHost((Expression) host),
				createPrefix((Expression) host, language),
				createSuffix(language, isWithSemicolon((Expression) host)));
	}

	private void resolve(PsiLanguageInjectionHost host) {
		final Node node = TaraPsiImplUtil.getContainerNodeOf(host);
		if (node != null) try {
			final io.intino.tara.Language language = TaraUtil.getLanguage(host);
			if (language != null) new Checker(language).check(node.resolve());
		} catch (SemanticFatalException ignored) {
		}
	}

	private Language injectionLanguage(PsiLanguageInjectionHost languageInjectionHost) {
		return Language.findLanguageByID(languageMap.get(TaraUtil.configurationOf(languageInjectionHost).nativeLanguage().toLowerCase()));
	}

	private boolean isWithSemicolon(@NotNull Expression host) {
		return !host.isMultiLine() && !host.getValue().trim().endsWith(";") && !host.getValue().trim().endsWith("}");
	}

	@NotNull
	private TextRange getRangeInsideHost(@NotNull Expression host) {
		return (!host.isMultiLine()) ? new TextRange(1, host.getTextLength() - 1) : getMultiLineBounds(host);
	}

	private TextRange getMultiLineBounds(Expression host) {
		final String value = host.getValue();
		final int i = host.getText().indexOf(value);
		return new TextRange(i, i + value.length());
	}

	private Valued getValued(Expression expression) {
		return TaraPsiImplUtil.getContainerByType(expression, Valued.class);
	}

	private String createPrefix(Expression expression, Language injectionLanguage) {
		resolve(expression);
		final io.intino.tara.Language language = TaraUtil.getLanguage(expression.getOriginalElement().getContainingFile());
		final Module module = moduleOf(expression);
		if (language == null || module == null) return "";
		String workingPackage = TaraUtil.workingPackage(expression).isEmpty() ? module.getName() : TaraUtil.workingPackage(expression);
		final Valued valued = getValued(expression);
		FrameBuilder builder = new FrameBuilder();
		builder.register(Parameter.class, new NativeParameterAdapter(module, workingPackage, language));
		builder.register(Variable.class, new NativeVariableAdapter(module, workingPackage, language));
		Template template = ExpressionInjectionTemplate.create();
		String prefix = build(injectionLanguage, valued, builder, template);
		return prefix.isEmpty() ? defaultPrefix() : prefix;
	}

	private String build(Language injectionLanguage, Valued valued, FrameBuilder builder, Template template) {
		return template.format(buildFrame(injectionLanguage, valued, builder));
	}

	private Frame buildFrame(Language injectionLanguage, Valued valued, FrameBuilder builder) {
		return ((Frame) builder.build(valued)).addTypes(injectionLanguage.getDisplayName(), isFunction(valued) ? valued.type().getName() : Tag.Reactive.name());
	}

	private static String defaultPrefix() {
		return "package org.sample;\n" +
				"public class Loading implements io.intino.tara.magritte.Function {" +
				"\tContainer $;" +
				"public void sample() {";
	}

	private static String groovySuffix() {
		return "\n\t}\n\n" +
				"\tvoid self(io.intino.tara.magritte.Layer context) {\n" +
				"\t}\n" +
				"\n" +
				"\tClass<? extends io.intino.tara.magritte.Layer> selfClass() {\n" +
				"\t\treturn null\n" +
				"\t}\n" +
				"}";
	}

	private static String kotlinSuffix() {
		return "\n\t}\n\n" +
				"\tfun self(context: tara.magritte.Layer) {\n" +
				"\t}\n" +
				"\n" +
				"\tfun selfClass(): Class<out io.intino.tara.magritte.Layer> {\n" +
				"\t\treturn null\n" +
				"\t}" +
				"}";
	}

	private boolean isFunction(Valued valued) {
		return FUNCTION.equals(valued.type());
	}

	private String createSuffix(Language language, boolean withSemicolon) {
		switch (language.getID()) {
			case "Groovy":
				return groovySuffix();
			case "kotlin":
				return kotlinSuffix();
			default:
				return (withSemicolon ? ";" : "") + "\n\t}\n}";
		}
	}
}
