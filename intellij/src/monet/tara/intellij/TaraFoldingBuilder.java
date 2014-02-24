package monet.tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.TaraConcept;
import monet.tara.intellij.metamodel.psi.TaraFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaraFoldingBuilder extends FoldingBuilderEx {

	@NotNull
	@Override
	public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
		List<FoldingDescriptor> descriptors = new ArrayList<>();
		List<IConcept> concepts = ((TaraFile) root).getConcepts();
		for (final IConcept concept : concepts)
			if (concept.getText() != null) {
				if (concept.getConceptBody() != null)
					descriptors.add(new FoldingDescriptor(concept.getConceptBody().getNode(), getRange(concept)) {
						@Nullable
						@Override
						public String getPlaceholderText() {
							return getHolderText(concept);
						}
					});
			}
		return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
	}

	private String getHolderText(IConcept conceptDefinition) {
		return ((TaraConcept) conceptDefinition).getConceptSignature().getText() + " {...}";
	}

	private TextRange getRange(IConcept concept) {
		return new TextRange(concept.getTextRange().getStartOffset(), concept.getTextRange().getEndOffset());
	}

	@Nullable
	@Override
	public String getPlaceholderText(@NotNull ASTNode node) {
		return ((TaraConcept) node.getPsi()).getConceptSignature().getText() + " {...}";
	}

	@Override
	public boolean isCollapsedByDefault(@NotNull ASTNode node) {
		return true;
	}
}