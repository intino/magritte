package monet.tara.compiler.intellij;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import monet.tara.compiler.intellij.metamodel.TaraLanguage;
import monet.tara.compiler.intellij.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;


public class TaraCompletionContributor extends CompletionContributor {

	public TaraCompletionContributor() {
		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.CONCEPT_KEY).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("Concept"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.COMPONENT_ANNOTATIONS).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("multiple"));
					resultSet.addElement(LookupElementBuilder.create("optional"));
					resultSet.addElement(LookupElementBuilder.create("has-code"));
					resultSet.addElement(LookupElementBuilder.create("extensible"));
					resultSet.addElement(LookupElementBuilder.create("singleton"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.CONCEPT_ANNOTATIONS).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("root"));
					resultSet.addElement(LookupElementBuilder.create("has-code"));
					resultSet.addElement(LookupElementBuilder.create("extensible"));
					resultSet.addElement(LookupElementBuilder.create("singleton"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.FROM_CONCEPT_ANNOTATIONS).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("has-code"));
					resultSet.addElement(LookupElementBuilder.create("extensible"));
					resultSet.addElement(LookupElementBuilder.create("singleton"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.FROM_ANNOTATIONS).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("multiple"));
					resultSet.addElement(LookupElementBuilder.create("optional"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.AS).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("as"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.FROM).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("from"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.VAR).withLanguage(TaraLanguage.INSTANCE),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);

		extend(CompletionType.BASIC, PlatformPatterns.psiElement(TaraTypes.IDENTIFIER_KEY).withLanguage(TaraLanguage.INSTANCE),
			//.withSuperParent(2, PlatformPatterns.psiElement(TaraIdentifier.class).withName(ManifestConstants.Directives.NO_IMPORT)),
			new CompletionProvider<CompletionParameters>() {
				public void addCompletions(@NotNull CompletionParameters parameters,
				                           ProcessingContext context,
				                           @NotNull CompletionResultSet resultSet) {
					resultSet.addElement(LookupElementBuilder.create("var"));
				}
			}
		);
	}
}