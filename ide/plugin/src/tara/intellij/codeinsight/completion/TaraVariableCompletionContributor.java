package tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiElement;
import com.intellij.psi.filters.ElementFilter;
import com.intellij.psi.filters.position.FilterPattern;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.TaraTypes;
import tara.intellij.lang.psi.TaraVariableType;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Primitive;
import tara.lang.model.Variable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
						resultSet.addElement(LookupElementBuilder.create(primitive.getName() + (mustHaveContract(primitive) ? ":" :
							" ")).withTypeText(Primitive.class.getSimpleName()));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterEquals,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("empty"));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterColon,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					for (String rule : collectNativeInterfaces(parameters.getOriginalPosition()))
						resultSet.addElement(LookupElementBuilder.create(rule));
				}
			}
		);
	}

	private List<String> collectNativeInterfaces(PsiElement originalPosition) {
		final Module module = ModuleProvider.getModuleOf(originalPosition);
		final TaraFacet facet = TaraFacet.of(module);
		if (facet == null) return Collections.emptyList();
		final String dsl = facet.getConfiguration().outputDsl();
		VirtualFile directory = TaraUtil.getSrcRoot(TaraUtil.getSourceRoots(module));
		if (directory == null) return Collections.emptyList();
		directory = directory.findFileByRelativePath(dsl + "/functions/");
		if (directory == null) return Collections.emptyList();
		List<String> list = new ArrayList<>();
		for (VirtualFile virtualFile : directory.getChildren()) list.add(virtualFile.getNameWithoutExtension());
		return list;
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
				if (TaraTypes.VAR.equals(ctxPreviousNode.getElementType()))
					return true;
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

		public TaraVariableType getVariableType(PsiElement element) {
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