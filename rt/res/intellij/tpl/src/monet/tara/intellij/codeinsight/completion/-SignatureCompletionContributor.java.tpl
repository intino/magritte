package monet.::projectName::.intellij.codeinsight.completion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

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
					::identifiers::
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
					Type collectionType = new TypeToken<Collection<String>>() {
					}.getType();
					try {
						List<String> astNodes = new Gson().fromJson(new InputStreamReader(new FileInputStream("ast.json")), collectionType);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		);
	}

}