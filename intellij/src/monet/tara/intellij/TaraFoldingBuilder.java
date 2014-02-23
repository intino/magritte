package monet.tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.intellij.metamodel.psi.IConcept;
import monet.tara.intellij.metamodel.psi.TaraConcept;
import monet.tara.intellij.metamodel.psi.impl.TaraUtil;
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
		Collection<IConcept> concepts = PsiTreeUtil.findChildrenOfType(root, IConcept.class);
		for (final IConcept concept : concepts)
			if (concept.getText() != null && concept.getText().startsWith("concept ")) {
				final List<IConcept> conceptList = TaraUtil.findRootConcept(concept.getProject(), concept.getName());

				final TaraConcept taraConcept = (TaraConcept) conceptList.get(0);
				if (conceptList.size() == 1 && taraConcept.getConceptBody() != null)
					descriptors.add(new FoldingDescriptor(taraConcept.getConceptBody().getNode(),
						getRange(taraConcept)) {
						@Nullable
						@Override
						public String getPlaceholderText() {
							return getHolderText(taraConcept);
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