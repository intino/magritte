package monet.tara.intellij.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.formatter.common.AbstractBlock;
import monet.tara.intellij.psi.TaraConcept;
import monet.tara.intellij.psi.TaraTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaraBlock extends AbstractBlock {

	private static final Spacing ONE_LINE_BREAK_SPACING = Spacing.createSpacing(0, 0, 1, true, 2);
	private static final Spacing MIN_SPACE = Spacing.createSpacing(1, 1, 0, false, 1);

	protected TaraBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment) {
		super(node, wrap, alignment);
	}

	@Override
	protected List<Block> buildChildren() {
		List<Block> blocks = new ArrayList<>();
		for (ASTNode child : getNode().getChildren(null)) {
			if (child.getElementType() == TaraTypes.CONCEPT)
				blocks.add(new TaraBlock(child, Wrap.createWrap(WrapType.ALWAYS, false), Alignment.createAlignment()));
			else if (child.getElementType() == TaraTypes.CONCEPT_CONSTITUENTS)
				blocks.add(new TaraBlock(child, Wrap.createWrap(WrapType.ALWAYS, false), Alignment.createAlignment()));

		}
		return blocks;
	}

	@Override
	public Indent getIndent() {
		return Indent.getNoneIndent();
	}

	@Nullable
	@Override
	public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
		TaraBlock leftBlock = (TaraBlock) child1;
		TaraBlock rightBlock = (TaraBlock) child2;
		if (child1 == null) return null;
		PsiElement rightPsi = rightBlock.getNode().getPsi();
		if (rightPsi instanceof TaraConcept) return ONE_LINE_BREAK_SPACING;
		else if (leftBlock.getNode().getElementType() == TaraTypes.ASSIGN)
			return MIN_SPACE;
		else if (rightBlock.getNode().getElementType() == TaraTypes.ASSIGN)
			return MIN_SPACE;
		return null;
	}

	@Override
	public boolean isLeaf() {
		return myNode.getFirstChildNode() == null;
	}

	@NotNull
	public TextRange getTextRange() {
		return myNode.getTextRange();
	}
}
