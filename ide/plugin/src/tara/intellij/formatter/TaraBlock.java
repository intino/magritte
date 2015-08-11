package tara.intellij.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraBody;
import tara.language.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static tara.intellij.lang.psi.TaraTypes.*;

public class TaraBlock implements ASTBlock {

	private static final Spacing ONELINEBREAKSPACING = Spacing.createSpacing(0, 0, 1, false, 2);
	private static final Spacing MINSPACE = Spacing.createSpacing(1, 1, 0, false, 1);
	private static final Spacing NOSPACE = Spacing.createSpacing(0, 0, 0, false, 0);
	private final TaraBlock myParent;
	private final Alignment alignment;
	private final Indent indent;
	private final ASTNode node;
	private final Wrap wrap;
	private final TaraBlockContext myContext;
	private Alignment myChildAlignment;
	private List<TaraBlock> subBlocks = null;
	private static final TokenSet untouchableBeginnings = TokenSet.create(TokenType.WHITE_SPACE, TokenType.NEW_LINE_INDENT, NEWLINE, CHARACTER, QUOTE_BEGIN, LEFT_PARENTHESIS, LEFT_SQUARE, COLON);
	private static final TokenSet untouchableEndings = TokenSet.create(TokenType.WHITE_SPACE, TokenType.NEW_LINE_INDENT, NEWLINE, CHARACTER, QUOTE_END, RIGHT_PARENTHESIS, RIGHT_SQUARE, ATTRIBUTE_TYPE);


	public TaraBlock(final TaraBlock parent, final ASTNode node, final Alignment alignment, final Indent indent, final Wrap wrap, final TaraBlockContext context) {
		this.myParent = parent;
		this.alignment = alignment;
		this.indent = indent;
		this.node = node;
		this.wrap = wrap;
		this.myContext = context;
	}

	@Nullable
	@Override
	public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
		if (child1 == null) return null;
		TaraBlock leftBlock = (TaraBlock) child1;
		TaraBlock rightBlock = (TaraBlock) child2;
		final IElementType leftType = leftBlock.getNode().getElementType();
		final IElementType rightType = rightBlock.getNode().getElementType();
		if (leftType == NODE && rightType == NODE)
			return ONELINEBREAKSPACING;
		else if (rightType == EQUALS || leftType == EQUALS) return MINSPACE;
		else if (!untouchableBeginnings.contains(leftType) && !untouchableEndings.contains(rightType))
			return MINSPACE;
		return NOSPACE;
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
		return new ArrayList<>(subBlocks);
	}

	@Nullable
	@Override
	public Wrap getWrap() {
		return wrap;
	}

	@Override
	public Indent getIndent() {
		assert indent != null;
		return indent;
	}

	@Nullable
	@Override
	public Alignment getAlignment() {
		return alignment;
	}

	@NotNull
	@Override
	public ChildAttributes getChildAttributes(int i) {
		return null;
	}

	@Override
	public boolean isIncomplete() {
		// if there's something following us, we're not incomplete
		if (!PsiTreeUtil.hasErrorElements(node.getPsi())) {
			PsiElement element = node.getPsi().getNextSibling();
			while (element instanceof PsiWhiteSpace) element = element.getNextSibling();
			if (element != null) return false;
		}
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
		if (parentType == NODE && childType == BODY) {
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

	private boolean isIndentNext(ASTNode child) {
		return PsiTreeUtil.getParentOfType(child.getPsi(), TaraBody.class) instanceof Node;
	}

	@Override
	public boolean isLeaf() {
		return node.getFirstChildNode() == null;
	}
}
