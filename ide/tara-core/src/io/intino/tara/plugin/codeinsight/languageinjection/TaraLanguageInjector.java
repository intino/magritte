package io.intino.tara.plugin.codeinsight.languageinjection;

import com.intellij.lang.Language;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.InjectedLanguagePlaces;
import com.intellij.psi.LanguageInjector;
import com.intellij.psi.PsiLanguageInjectionHost;
import io.intino.itrules.Frame;
import io.intino.itrules.FrameBuilder;
import io.intino.itrules.Template;
import io.intino.tara.Checker;
import io.intino.tara.compiler.shared.Configuration;
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

import static io.intino.tara.lang.model.Primitive.FUNCTION;
import static io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil.getContainerByType;
import static io.intino.tara.plugin.project.module.ModuleProvider.moduleOf;

public class TaraLanguageInjector implements LanguageInjector {


	private static String defaultPrefix() {
		return "package org.sample;\n" +
				"public class Loading implements io.intino.tara.magritte.Function {" +
				"\tContainer $;" +
				"public void sample() {";
	}

	private static String suffix() {
		return "\n\t}\n\n" +
				"\tpublic void self(io.intino.tara.magritte.Layer context) {\n" +
				"\t}\n" +
				"\n" +
				"\tpublic Class<? extends io.intino.tara.magritte.Layer> selfClass() {\n" +
				"\t\treturn null;\n" +
				"\t}\n" +
				"}";
	}

	@Override
	public void getLanguagesToInject(@NotNull PsiLanguageInjectionHost host, @NotNull InjectedLanguagePlaces injectionPlacesRegistrar) {
		if (!(host instanceof Expression) || !host.isValidHost()) return;
		final Language language = injectionLanguage(host);
		if (language == null) return;
		resolve(host);
		injectionPlacesRegistrar.addPlace(language,
				getRangeInsideHost((Expression) host),
				createPrefix((Expression) host),
				(isWithSemicolon((Expression) host) ? ";" : "") + suffix());
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
		final Configuration configuration = TaraUtil.configurationOf(languageInjectionHost);
		if (configuration == null || configuration.nativeLanguage() == null) return null;
		return Language.findLanguageByID("JAVA");
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

	private String createPrefix(Expression expression) {
		resolve(expression);
		final io.intino.tara.Language language = TaraUtil.getLanguage(expression.getOriginalElement().getContainingFile());
		final Module module = moduleOf(expression);
		if (language == null || module == null) return "";
		Template template = new ExpressionInjectionTemplate();
		String prefix = template.render(buildFrame(expression, language, module));
		return prefix.isEmpty() ? defaultPrefix() : prefix;
	}

	private Frame buildFrame(Expression expression, io.intino.tara.Language language, Module module) {
		Valued valued = getContainerByType(expression, Valued.class);
		if (valued == null) return null;
		String workingPackage = TaraUtil.graphPackage(expression).isEmpty() ? module.getName() : TaraUtil.graphPackage(expression);
		FrameBuilder builder = new FrameBuilder()
				.put(Parameter.class, new NativeParameterAdapter(module, workingPackage, language))
				.put(Variable.class, new NativeVariableAdapter(module, workingPackage, language))
				.append(valued);
		return builder.add(isFunction(valued) ? valued.type().getName() : Tag.Reactive.name()).toFrame();
	}

	private boolean isFunction(Valued valued) {
		return FUNCTION.equals(valued.type());
	}
}
