package tara.intellij.codeinsight.languageinjection;

import com.intellij.lang.Language;
import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import org.siani.itrules.model.Frame;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.settings.TaraSettings;
import tara.lang.model.Parameter;
import tara.lang.model.Tag;
import tara.lang.model.Variable;
import tara.templates.ExpressionInjectionTemplate;

import static tara.intellij.project.module.ModuleProvider.getModuleOf;
import static tara.lang.model.Primitive.FUNCTION;

public class TaraLanguageInjector implements LanguageInjector {

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!Expression.class.isInstance(host) || !host.isValidHost()) return;
		final Language language = injectionLanguage(host.getProject());
		injectionPlacesRegistrar.addPlace(language,
			getRangeInsideHost((Expression) host),
			createPrefix((Expression) host, language),
			createSuffix(language, isWithSemicolon((Expression) host)));
	}

	private Language injectionLanguage(Project project) {
		final String language = TaraSettings.getSafeInstance(project).destinyLanguage();
		final Language languageByID = Language.findLanguageByID(language.toUpperCase());
		return languageByID == null ? Language.findLanguageByID(language) : languageByID;
	}

	private boolean isWithSemicolon(@NotNull Expression host) {
		return !host.getValue().trim().endsWith(";") && !host.getValue().trim().endsWith("}");
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
		final tara.Language language = TaraUtil.getLanguage(expression);
		final Module module = getModuleOf(expression);
		TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return "";
		String generatedLanguage = facet.getConfiguration().outputDsl().isEmpty() ? module.getName() : facet.getConfiguration().outputDsl();
		if (language == null) return "";
		final Valued valued = getValued(expression);
		FrameBuilder builder = new FrameBuilder();
		builder.register(Parameter.class, new NativeParameterAdapter(module, generatedLanguage, language));
		builder.register(Variable.class, new NativeVariableAdapter(module, generatedLanguage, language));
		Template template = ExpressionInjectionTemplate.create();
		final String prefix = template.format(buildFrame(injectionLanguage, valued, builder));
		return prefix.isEmpty() ? defaultPrefix() : prefix;
	}

	private Frame buildFrame(Language injectionLanguage, Valued valued, FrameBuilder builder) {
		return ((Frame) builder.build(valued)).addTypes(injectionLanguage.getDisplayName(), isFunction(valued) ? valued.type().getName() : Tag.Native.name());
	}

	private String defaultPrefix() {
		return "package org.sample;\n" +
			"public class Loading implements tara.magritte.Function {" +
			"\tContainer $;" +
			"public void sample() {";
	}

	private String groovySuffix() {
		return "\n\tvoid self(tara.magritte.Layer context) {\n" +
			"\t}\n" +
			"\n" +
			"\tClass<? extends tara.magritte.Layer> selfClass() {\n" +
			"\t\treturn null\n" +
			"\t}\n" +
			"}";
	}

	private boolean isFunction(Valued valued) {
		return FUNCTION.equals(valued.type());
	}

	private String createSuffix(Language language, boolean withSemicolon) {
		final boolean isJava = language.equals(JavaLanguage.INSTANCE);
		return (withSemicolon && isJava ? ";" : "") + "\n\t}\n" + (isJava ? "}" : groovySuffix());
	}
}
