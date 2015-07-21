package tara.intellij.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraBody;
import tara.intellij.lang.psi.TaraTypes;
import tara.language.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaraBlock implements ASTBlock {

	private static final Spacing ONELINEBREAKSPACING = Spacing.createSpacing(0, 0, 1, true, 2);
	private static final Spacing MINSPACE = Spacing.createSpacing(1, 1, 0, false, 1);
	private final TaraBlock myParent;
	private final Alignment alignment;
	private final Indent indent;
	private final ASTNode node;
	private final Wrap wrap;
	private final TaraBlockContext myContext;
	private Alignment myChildAlignment;
	private List<TaraBlock> subBlocks = null;


	public TaraBlock(final TaraBlock parent,
	                 final ASTNode node,
	                 final Alignment alignment,
	                 final Indent indent,
	                 final Wrap wrap,
	                 final TaraBlockContext context) {
		this.myParent = parent;
		this.alignment = alignment;
		this.indent = indent;
		this.node = node;
		this.wrap = wrap;
		this.myContext = context;
	}

	private static boolean isIndentNext(ASTNode child) {
		return PsiTreeUtil.getParentOfType(child.getPsi(), TaraBody.class) instanceof Node;
	}

	@NotNull
	public ASTNode getNode() {
		return node;
	}

	@NotNull
	public TextRange getTextRange() {
		return node.getTextRange();
	}

	private Alignment getAlignmentForChildren() {
		if (myChildAlignment == null) myChildAlignment = Alignment.createAlignment();
		return myChildAlignment;
	}

	@NotNull
	public List<Block> getSubBlocks() {
		if (subBlocks == null) subBlocks = buildSubBlocks();
		return new ArrayList<Block>(subBlocks);
	}

	@Nullable
	@Override
	public Wrap getWrap() {
		return wrap;
	}

	@Override
	public Indent getIndent() {
		return Indent.getNoneIndent();
	}

	@Nullable
	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@Nullable
	@Override
	public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
		TaraBlock leftBlock = (TaraBlock) child1;
		TaraBlock rightBlock = (TaraBlock) child2;
		if (child1 == null) return null;
		PsiElement rightPsi = rightBlock.getNode().getPsi();
		if (rightPsi instanceof Node) return ONELINEBREAKSPACING;
		else if (leftBlock.getNode().getElementType() == TaraTypes.COLON)
			return MINSPACE;
		else if (rightBlock.getNode().getElementType() == TaraTypes.COLON)
			return MINSPACE;
		return null;
	}

	@NotNull
	@Override
	public ChildAttributes getChildAttributes(int i) {
		return null;
	}

	@Override
	public boolean isIncomplete() {
		return false;
	}

	private List<TaraBlock> buildSubBlocks() {
		List<TaraBlock> blocks = new ArrayList<>();
		for (ASTNode child = node.getFirstChildNode(); child != null; child = child.getTreeNext()) {
			IElementType childType = child.getElementType();
			if (child.getTextRange().getLength() == 0) continue;
			if (childType == TokenType.WHITE_SPACE) continue;
			blocks.add(buildSubBlock(child));
		}
		return Collections.unmodifiableList(blocks);
	}

	private TaraBlock buildSubBlock(ASTNode child) {
		IElementType parentType = node.getElementType();
		IElementType childType = child.getElementType();
		Wrap wrap = null;
		Indent childIndent = Indent.getNoneIndent();
		Alignment childAlignment = null;
		if (parentType == TaraTypes.NODE && childType == TaraTypes.BODY) {
			childAlignment = getAlignmentForChildren();
			childIndent = Indent.getNormalIndent();
		}
		ASTNode prev = child.getTreePrev();
		while (prev != null && prev.getElementType() == TokenType.WHITE_SPACE) {
			if (prev.textContains('\\') &&
				!childIndent.equals(Indent.getContinuationIndent(false)) &&
				!childIndent.equals(Indent.getContinuationIndent(true))) {
				childIndent = isIndentNext(child) ? Indent.getContinuationIndent() : Indent.getNormalIndent();
				break;
			}
			prev = prev.getTreePrev();
		}
		return new TaraBlock(this, child, childAlignment, childIndent, wrap, myContext);
	}

	@Override
	public boolean isLeaf() {
		return node.getFirstChildNode() == null;
	}

}
