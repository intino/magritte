package monet.::projectName::.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::File;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ::projectProperName::FoldingBuilder extends CustomFoldingBuilder {
	@Override
	protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors,
	                                        @NotNull PsiElement root,
	                                        @NotNull Document document,
	                                        boolean quick) {
		List<Definition> definitions = ::projectProperName::Util.findAllDefinitionsOfFile((::projectProperName::FileImpl) root);
		for (final Definition definition : definitions)
			if (definition.getText() != null && definition.getBody() != null)
				descriptors.add(new FoldingDescriptor(definition.getBody().getNode(), getRange(definition)) {
					@Nullable
					@Override
					public String getPlaceholderText() {
						return getHolderText();
					}
				});

	}

	@Override
	protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
		return " ...";
	}

	@Override
	protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
		return false;
	}

	@Override
	protected boolean isCustomFoldingRoot(ASTNode node) {
		return node.getPsi() instanceof ::projectProperName::File;
	}

	private String getHolderText() {
		return " ...";
	}

	private TextRange getRange(Definition definition) {
		return new TextRange(definition.getBody().getTextRange().getStartOffset(), definition.getTextRange().getEndOffset());
	}
}
