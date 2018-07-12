package io.intino.tara.plugin.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.lang.ASTNode;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import io.intino.tara.plugin.lang.TaraLanguage;
import io.intino.tara.plugin.lang.psi.StringValue;
import io.intino.tara.plugin.lang.psi.Valued;
import io.intino.tara.plugin.lang.psi.impl.PsiCustomWordRule;
import io.intino.tara.plugin.lang.psi.impl.TaraPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import io.intino.tara.plugin.lang.psi.TaraTypes;
import io.intino.tara.plugin.lang.psi.TaraVariableType;
import io.intino.tara.lang.model.Node;
import io.intino.tara.lang.model.Parameter;
import io.intino.tara.lang.model.Primitive;
import io.intino.tara.lang.model.Variable;
import io.intino.tara.lang.model.rules.variable.WordRule;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;
import static com.intellij.patterns.PlatformPatterns.psiElement;


public class TaraVariableCompletionContributor extends CompletionContributor {

	private PsiElementPattern.Capture<PsiElement> afterVar = psiElement()
		.withLanguage(TaraLanguage.INSTANCE)
		.and(new FilterPattern(new AfterVarFitFilter()));

	public TaraVariableCompletionContributor() {
		extend(CompletionType.BASIC, afterVar,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
										   ProcessingContext context,
										   @NotNull CompletionResultSet resultSet) {
					for (Primitive primitive : Primitive.getPrimitives())
						resultSet.addElement(create(primitive.getName().toLowerCase() + (mustHaveContract(primitive) ? ":" :
							" ")).withTypeText(Primitive.class.getSimpleName()));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterEquals,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
										   ProcessingContext context,
										   @NotNull CompletionResultSet resultSet) {
					final Valued valued = TaraPsiImplUtil.contextOf(parameters.getPosition(), Valued.class);
					if (valued == null) return;
					if (valued instanceof Variable && Primitive.WORD.equals(valued.type())) {
						if (valued.rule() instanceof WordRule) ((WordRule) valued.rule()).words().forEach(w -> resultSet.addElement(create(w)));
						else ((PsiCustomWordRule) valued.rule()).words().forEach(w -> resultSet.addElement(create(w)));
					} else if (valued instanceof Parameter && Primitive.REFERENCE.equals(valued.type()) && !(parameters.getPosition().getParent() instanceof StringValue))
						resultSet.addElement(create("empty"));
					else if (Primitive.BOOLEAN.equals(valued.type())) {
						resultSet.addElement(create("true"));
						resultSet.addElement(create("false"));
					}
				}
			}
		);
	}

	private boolean mustHaveContract(Primitive primitive) {
		return Primitive.FUNCTION.equals(primitive);
	}

	private static class AfterVarFitFilter implements ElementFilter {
		public boolean isAcceptable(Object element, PsiElement context) {
			if (element instanceof PsiElement && isInAttribute(context)) {
				PsiElement parent = getVariableType(context);
				if (parent == null) return false;
				if (parent.getPrevSibling() == null || parent.getPrevSibling().getPrevSibling() == null) return false;

				final ASTNode ctxPreviousNode = parent.getPrevSibling().getPrevSibling().getNode();
				return TaraTypes.VAR.equals(ctxPreviousNode.getElementType());
			}
			return false;
		}

		private boolean isInAttribute(PsiElement context) {
			PsiElement parent = context.getParent();
			while (parent != null && !(parent instanceof Node)) {
				if (parent instanceof Variable) return true;
				parent = parent.getParent();
			}
			return false;
		}

		TaraVariableType getVariableType(PsiElement element) {
			PsiElement parent = element.getParent();
			while (parent != null && !(parent instanceof Node)) {
				if (parent instanceof TaraVariableType) return (TaraVariableType) parent;
				parent = parent.getParent();
			}
			return null;
		}

		public boolean isClassAcceptable(Class hintClass) {
			return true;
		}
	}
}