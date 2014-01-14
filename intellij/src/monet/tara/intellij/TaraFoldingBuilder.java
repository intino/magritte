package monet.tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.psi.TaraConcept;
import monet.tara.intellij.psi.impl.TaraUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraFoldingBuilder extends FoldingBuilderEx {

	@NotNull
	@Override
	public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
		List<FoldingDescriptor> descriptors = new ArrayList<>();
		Collection<TaraConcept> concepts = PsiTreeUtil.findChildrenOfType(root, TaraConcept.class);
		for (final TaraConcept concept : concepts)
			if (concept.getText() != null && concept.getText().startsWith("concept ")) {
				final List<TaraConcept> conceptDefinitions = TaraUtil.findConcept(concept.getProject(), concept.getName());
				if (conceptDefinitions.size() == 1 && conceptDefinitions.get(0).getConceptBody() != null)
					descriptors.add(new FoldingDescriptor(conceptDefinitions.get(0).getConceptBody().getNode(),
						getRange(conceptDefinitions.get(0))) {
						@Nullable
						@Override
						public String getPlaceholderText() {
							return getHolderText(conceptDefinitions.get(0));
						}
					});
			}
		return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
	}

	private String getHolderText(TaraConcept conceptDefinition) {
		return conceptDefinition.getConceptSignature().getText() + " {...}";
	}

	private TextRange getRange(TaraConcept conceptDefinition) {
		return new TextRange(conceptDefinition.getTextRange().getStartOffset(),
			conceptDefinition.getTextRange().getEndOffset() - 1);
	}

	@Nullable
	@Override
	public String getPlaceholderText(@NotNull ASTNode node) {
		return ((TaraConcept) node.getPsi()).getConceptSignature() + " {...}";
	}

	@Override
	public boolean isCollapsedByDefault(@NotNull ASTNode node) {
		return true;
	}
}