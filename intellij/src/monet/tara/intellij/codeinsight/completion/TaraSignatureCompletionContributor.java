package monet.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class TaraSignatureCompletionContributor extends CompletionContributor {

	public TaraSignatureCompletionContributor() {
		extend(CompletionType.BASIC, TaraFilters.afterConceptKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("base"));
					resultSet.addElement(LookupElementBuilder.create("abstract"));
					resultSet.addElement(LookupElementBuilder.create("final"));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.afterNewLine,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					//gen %identifiers%
					resultSet.addElement(LookupElementBuilder.create("Concept"));
					//end
					resultSet.addElement(LookupElementBuilder.create("case"));
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);
	}

}