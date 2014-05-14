package monet.::projectName::.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class ::projectProperName::SignatureCompletionContributor extends CompletionContributor {

	public static final String CASE = "case";

	public ::projectProperName::SignatureCompletionContributor() {
		extend(CompletionType.BASIC, ::projectProperName::Filters.afterDefinitionKey,
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(\@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           \@NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("base"));
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
					::identifiers::
					resultSet.addElement(LookupElementBuilder.create(CASE));
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);

//		extend(CompletionType.BASIC, ::projectProperName::Filters.afterModifierKey,
//			new CompletionProvider<CompletionParameters>() {
//				public void addCompletions(\@NotNull CompletionParameters parameters,
//				                           ProcessingContext context,
//				                           \@NotNull CompletionResultSet resultSet) {
//					Type collectionType = new TypeToken<Collection<String>>() {
//					}.getType();
//					try {
//						List<String> astNodes = new Gson().fromJson(new InputStreamReader(new FileInputStream("ast.json")), collectionType);
//					} catch (FileNotFoundException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		);
	}

}