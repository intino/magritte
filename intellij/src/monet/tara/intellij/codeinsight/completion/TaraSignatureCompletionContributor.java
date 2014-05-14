package monet.tara.intellij.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class TaraSignatureCompletionContributor extends CompletionContributor {

	public static final String CASE = "case";

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
					resultSet.addElement(LookupElementBuilder.create(CASE));
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);

//		extend(CompletionType.BASIC, TaraFilters.afterModifierKey,
//			new CompletionProvider<CompletionParameters>() {
//				public void addCompletions(@NotNull CompletionParameters parameters,
//				                           ProcessingContext context,
//				                           @NotNull CompletionResultSet resultSet) {
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