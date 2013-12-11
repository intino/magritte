package monet.tara.metamodel;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import monet.tara.metamodel.psi.impl.TaraUtil;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import monet.tara.metamodel.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;
//.withSuperParent(2, PlatformPatterns.psiElement(Directive.class).withName(ManifestConstants.Directives.SPLIT_PACKAGE))

public class TaraCompletionContributor extends CompletionContributor {

	public TaraCompletionContributor() {
		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.IDENTIFIER).withLanguage(TaraLanguage.INSTANCE),
				      new CompletionProvider<CompletionParameters>() {
					      public void addCompletions(@NotNull CompletionParameters parameters,
					                                 ProcessingContext context,
					                                 @NotNull CompletionResultSet resultSet) {
						      PsiElement psiElement = parameters.getOriginalPosition();
						      Project project = psiElement.getProject();
						      List<TaraConceptDefinition> concepts = TaraUtil.getConcepts(project);
						      for (TaraConceptDefinition concept : concepts)
							      resultSet.addElement(LookupElementBuilder.create(concept.getIdentifier()));
					      }
				      }
		);
	}
}