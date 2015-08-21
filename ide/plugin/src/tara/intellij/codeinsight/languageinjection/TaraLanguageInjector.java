package tara.intellij.codeinsight.languageinjection;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import org.jetbrains.annotations.NotNull;
import org.siani.itrules.Template;
import org.siani.itrules.engine.FrameBuilder;
import tara.Language;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.Expression;
import tara.intellij.lang.psi.Valued;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Parameter;
import tara.language.model.Variable;
import tara.templates.NativeInjectionTemplate;

import static tara.intellij.lang.psi.impl.TaraPsiImplUtil.getParentByType;

public class TaraLanguageInjector implements LanguageInjector {

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!Expression.class.isInstance(host) || !host.isValidHost()) return;
		injectionPlacesRegistrar.addPlace(JavaLanguage.INSTANCE,
			getRangeInsideHost((Expression) host),
			createPrefix((Expression) host),
			createSuffix(isWithSemicolon((Expression) host)));
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
		return (Valued) getParentByType(expression, Valued.class);
	}

	private String createPrefix(Expression expression) {
		final Language language = TaraLanguage.getLanguage(expression.getContainingFile());
		TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(expression));
		if (facet == null) return "";
		final String generatedLanguage = facet.getConfiguration().getGeneratedDslName();
		if (language == null) return "";
		Template template = NativeInjectionTemplate.create();
		FrameBuilder builder = new FrameBuilder();
		builder.register(Parameter.class, new NativeParameterAdapter(generatedLanguage, language));
		builder.register(Variable.class, new NativeVariableAdapter(generatedLanguage, language));
		return template.format(builder.build(getValued(expression)));
	}

	private String createSuffix(boolean withSemicolon) {
		return (withSemicolon ? ";" : "") + "\n\t}\n" +
			"}";
	}
}
