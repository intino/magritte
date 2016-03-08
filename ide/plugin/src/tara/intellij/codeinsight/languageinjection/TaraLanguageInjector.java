package tara.intellij.codeinsight.languageinjection;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import tara.Language;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.settings.TaraSettings;
import tara.lang.model.Parameter;
import tara.lang.model.Variable;
import tara.templates.ExpressionInjectionTemplate;

import static tara.intellij.project.module.ModuleProvider.getModuleOf;
import static tara.lang.model.Primitive.FUNCTION;
import static tara.templates.NativeInjectionTemplate.create;

public class TaraLanguageInjector implements LanguageInjector {

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!Expression.class.isInstance(host) || !host.isValidHost()) return;
		injectionPlacesRegistrar.addPlace(injectionLanguage(host.getProject()),
			getRangeInsideHost((Expression) host),
			createPrefix((Expression) host),
			createSuffix(isWithSemicolon((Expression) host)));
	}

	private com.intellij.lang.Language injectionLanguage(Project project) {
		final String language = TaraSettings.getSafeInstance(project).destinyLanguage();
		return com.intellij.lang.Language.findLanguageByID(language.toUpperCase());
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

	private String createPrefix(Expression expression) {
		final Language language = TaraUtil.getLanguage(expression);
		final Module module = getModuleOf(expression);
		TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return "";
		String generatedLanguage = facet.getConfiguration().outputDsl().isEmpty() ? module.getName() : facet.getConfiguration().outputDsl();
		if (language == null) return "";
		final Valued valued = getValued(expression);
		FrameBuilder builder = new FrameBuilder();
		builder.register(Parameter.class, new NativeParameterAdapter(module, generatedLanguage, language));
		builder.register(Variable.class, new NativeVariableAdapter(module, generatedLanguage, language));
		Template template = isFromFunction(valued) ? create() : ExpressionInjectionTemplate.create();
		final String prefix = template.format(builder.build(valued));
		return prefix.isEmpty() ? defaultPrefix() : prefix;
	}

	private String defaultPrefix() {
		return "package org.sample;\n" +
			"public class Loading implements tara.magritte.Function {" +
			"\tContainer $;" +
			"public void sample() {";
	}

	private boolean isFromFunction(Valued valued) {
		return FUNCTION.equals(valued.type());
	}

	private String createSuffix(boolean withSemicolon) {
		return (withSemicolon ? ";" : "") + "\n\t}\n" +
			"}";
	}
}
