package tara.intellij.codeinsight.languageinjection;

import com.intellij.lang.java.JavaLanguage;
import com.intellij.openapi.module.Module;
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
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Parameter;
import tara.lang.model.Variable;
import tara.templates.ExpressionInjectionTemplate;
import tara.templates.NativeInjectionTemplate;

import static tara.lang.model.Primitive.FUNCTION;

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
		return TaraPsiImplUtil.getContainerByType(expression, Valued.class);
	}

	private String createPrefix(Expression expression) {
		final Language language = TaraUtil.getLanguage(expression);
		final Module module = ModuleProvider.getModuleOf(expression);
		TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return "";
		String generatedLanguage = facet.getConfiguration().getGeneratedDslName().isEmpty() ? module.getName() : facet.getConfiguration().getGeneratedDslName();
		if (language == null) return "";
		final Valued valued = getValued(expression);
		Template template = isFromFunction(valued) ? NativeInjectionTemplate.create() : ExpressionInjectionTemplate.create();
		FrameBuilder builder = new FrameBuilder();
		builder.register(Parameter.class, new NativeParameterAdapter(generatedLanguage, language));
		builder.register(Variable.class, new NativeVariableAdapter(generatedLanguage, language));
		return template.format(builder.build(valued));
	}

	private boolean isFromFunction(Valued valued) {
		return FUNCTION.equals(valued.type());
	}

	private String createSuffix(boolean withSemicolon) {
		return (withSemicolon ? ";" : "") + "\n\t}\n" +
			"}";
	}
}
