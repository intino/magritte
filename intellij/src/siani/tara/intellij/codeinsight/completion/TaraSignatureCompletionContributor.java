package siani.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class TaraSignatureCompletionContributor extends CompletionContributor {

	public TaraSignatureCompletionContributor() {
		extend(CompletionType.BASIC, TaraFilters.afterNewLineInBody,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("case "));
					resultSet.addElement(LookupElementBuilder.create("var "));
					resultSet.addElement(LookupElementBuilder.create("is "));
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