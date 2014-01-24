package monet.tara.transpiler.intellij;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import monet.tara.transpiler.intellij.metamodel.TaraLanguage;
import monet.tara.transpiler.intellij.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;


public class TaraCompletionContributor extends CompletionContributor {

	public TaraCompletionContributor() {
		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.CONCEPT).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("concept"));
				}
			}
		);
	}
}