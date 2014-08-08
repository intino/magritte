package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class TaraConceptCompletionContributor extends CompletionContributor {

	public TaraConceptCompletionContributor() {
		extend(CompletionType.BASIC, TaraFilters.afterNewLineInBody,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("case "));
					resultSet.addElement(LookupElementBuilder.create("has "));
					resultSet.addElement(LookupElementBuilder.create("var "));
					resultSet.addElement(LookupElementBuilder.create("is "));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.inFacetBody,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("on "));
				}
			}
		);

		extend(CompletionType.BASIC, TaraFilters.AfterNewLineNoMetamodel,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Concept "));
				}
			}
		);
	}

}