package monet.tara.metamodel;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.util.PsiTreeUtil;
import monet.tara.metamodel.psi.impl.TaraUtil;
import monet.tara.metamodel.psi.TaraConceptDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraFoldingBuilder extends FoldingBuilderEx {

	@NotNull
	@Override
	public FoldingDescriptor[] buildFoldRegions(@NotNull com.intellij.psi.PsiElement root, @NotNull Document document, boolean quick) {
		FoldingGroup group = FoldingGroup.newGroup("tara");
		List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
		Collection<TaraConceptDefinition> concepts = PsiTreeUtil.findChildrenOfType(root, TaraConceptDefinition.class);
		for (final TaraConceptDefinition concept : concepts) {
			String value = concept.getText();
			if (value != null && value.startsWith("concept ")) {
				Project project = concept.getProject();
				final List<TaraConceptDefinition> conceptDefinitions = TaraUtil.findConcept(project, concept.getIdentifier());
				if (conceptDefinitions.size() == 1)
					descriptors.add(new FoldingDescriptor(conceptDefinitions.get(0).getNode(),
					                                     new TextRange(conceptDefinitions.get(0).getTextRange().getStartOffset(),
					                                                  conceptDefinitions.get(0).getTextRange().getEndOffset() - 1), group) {
						@Nullable @Override
						public String getPlaceholderText() {
							return conceptDefinitions.get(0).getIdentifier();
						}
					});
			}
		}
		return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
	}

	@Nullable
	@Override
	public String getPlaceholderText(@NotNull ASTNode node) {
		return "...";
	}

	@Override
	public boolean isCollapsedByDefault(@NotNull ASTNode node) {
		return true;
	}
}