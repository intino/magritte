package monet.::projectName::.intellij.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.formatter.common.AbstractBlock;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::Types;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ::projectProperName::Block extends AbstractBlock {

	private static final Spacing ONE_LINE_BREAK_SPACING = Spacing.createSpacing(0, 0, 1, true, 2);
	private static final Spacing MIN_SPACE = Spacing.createSpacing(1, 1, 0, false, 1);

	protected ::projectProperName::Block(\@NotNull ASTNode node, \@Nullable Wrap wrap, \@Nullable Alignment alignment) {
		super(node, wrap, alignment);
	}

	\@Override
	protected List<Block> buildChildren() {
		List<Block> blocks = new ArrayList<>();
		for (ASTNode child \: getNode().getChildren(null)) {
			if (child.getElementType() == ::projectProperName::Types.DEFINITION_KEY)
				blocks.add(new ::projectProperName::Block(child, Wrap.createWrap(WrapType.ALWAYS, false), Alignment.createAlignment()));
			else if (child.getElementType() == ::projectProperName::Types.BODY)
				blocks.add(new ::projectProperName::Block(child, Wrap.createWrap(WrapType.ALWAYS, false), Alignment.createAlignment()));

		}
		return blocks;
	}

	\@Override
	public Indent getIndent() {
		return Indent.getNoneIndent();
	}

	\@Nullable
	\@Override
	public Spacing getSpacing(\@Nullable Block child1, \@NotNull Block child2) {
		::projectProperName::Block leftBlock = (::projectProperName::Block) child1;
		::projectProperName::Block rightBlock = (::projectProperName::Block) child2;
		if (child1 == null) return null;
		PsiElement rightPsi = rightBlock.getNode().getPsi();
		if (rightPsi instanceof Definition) return ONE_LINE_BREAK_SPACING;
		else if (leftBlock.getNode().getElementType() == ::projectProperName::Types.COLON)
			return MIN_SPACE;
		else if (rightBlock.getNode().getElementType() == ::projectProperName::Types.COLON)
			return MIN_SPACE;
		return null;
	}

	\@Override
	public boolean isLeaf() {
		return myNode.getFirstChildNode() == null;
	}

	\@NotNull
	public TextRange getTextRange() {
		return myNode.getTextRange();
	}
}
