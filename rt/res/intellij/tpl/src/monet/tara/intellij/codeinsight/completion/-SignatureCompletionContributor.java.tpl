package monet.::projectName::.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::SignatureCompletionContributor extends CompletionContributor {

	public static final String MORPH = "morph";

	public ::projectProperName::SignatureCompletionContributor() {
		extend(CompletionType.BASIC, ::projectProperName::Filters.afterDefinitionKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create(MORPH));
					resultSet.addElement(LookupElementBuilder.create("polymorphic"));
					resultSet.addElement(LookupElementBuilder.create("abstract"));
					resultSet.addElement(LookupElementBuilder.create("final"));
				}
			}
		);

		extend(CompletionType.BASIC, ::projectProperName::Filters.afterNewLine,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Definition"));
					resultSet.addElement(LookupElementBuilder.create("new"));
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);

		extend(CompletionType.BASIC, ::projectProperName::Filters.afterModifierKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create(MORPH));
				}
			}
		);
	}

}