package io.intino.tara.plugin.codeinsight.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.util.ProcessingContext;
import io.intino.tara.plugin.lang.psi.Identifier;
import io.intino.tara.plugin.lang.psi.MetaIdentifier;
import org.jetbrains.annotations.NotNull;

import static com.intellij.codeInsight.lookup.LookupElementBuilder.create;

public class TaraNodeCompletionContributor extends CompletionContributor {

	public TaraNodeCompletionContributor() {
		bodyCompletion();
		newLine();
		afterAs();
		afterIdentifier();
		parameterNames();
	}

	private void bodyCompletion() {
		extend(CompletionType.BASIC, TaraFilters.afterNewLineInBody, new BodyCompletionProvider());
	}

	private void newLine() {
		extend(CompletionType.BASIC, TaraFilters.AfterNewLine,
				new CompletionProvider<CompletionParameters>() {
					public void addCompletions(@NotNull CompletionParameters parameters,
											   ProcessingContext context,
											   @NotNull CompletionResultSet resultSet) {
						if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
						final CompletionUtils completionUtils = new CompletionUtils(parameters, resultSet);
						completionUtils.collectAllowedTypes();
					}
				}
		);
	}

	private void afterAs() {
		extend(CompletionType.BASIC, TaraFilters.afterAs,
				new CompletionProvider<CompletionParameters>() {
					public void addCompletions(@NotNull CompletionParameters parameters,
											   ProcessingContext context,
											   @NotNull CompletionResultSet resultSet) {
						if (!(parameters.getPosition().getContext() instanceof MetaIdentifier)) return;
						final CompletionUtils completionUtils = new CompletionUtils(parameters, resultSet);
						completionUtils.collectAllowedFacets();
					}
				}
		);
	}

	private void afterIdentifier() {
		extend(CompletionType.BASIC, TaraFilters.afterNodeIdentifier,
				new CompletionProvider<>() {
					public void addCompletions(@NotNull CompletionParameters parameters,
											   ProcessingContext context,
											   @NotNull CompletionResultSet resultSet) {
						resultSet.addElement(create("extends "));
						resultSet.addElement(create("is "));
						resultSet.addElement(create("into "));
					}
				}
		);
	}

	private void parameterNames() {
		extend(CompletionType.BASIC, TaraFilters.inParameterName,
				new CompletionProvider<>() {
					public void addCompletions(@NotNull CompletionParameters parameters,
											   ProcessingContext context,
											   @NotNull CompletionResultSet resultSet) {
						if (!(parameters.getPosition().getContext() instanceof Identifier)) return;
						final CompletionUtils completionUtils = new CompletionUtils(parameters, resultSet);
						completionUtils.collectSignatureParameters();
					}
				}
		);
	}

}