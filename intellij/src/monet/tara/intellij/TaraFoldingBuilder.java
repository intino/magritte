package monet.tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.TaraFile;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TaraFoldingBuilder extends CustomFoldingBuilder {
	@Override
	protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors,
	                                        @NotNull PsiElement root,
	                                        @NotNull Document document,
	                                        boolean quick) {
		List<IConcept> concepts = TaraUtil.findAllConceptsOfFile((TaraFileImpl) root);
		for (final IConcept concept : concepts)
			if (concept.getText() != null && concept.getConceptBody() != null)
				descriptors.add(new FoldingDescriptor(concept.getConceptBody().getNode(), getRange(concept)) {
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
		return node.getPsi() instanceof TaraFile;
	}

	private String getHolderText() {
		return " ...";
	}

	private TextRange getRange(IConcept concept) {
		return new TextRange(concept.getConceptBody().getTextRange().getStartOffset(), concept.getTextRange().getEndOffset());
	}
}
