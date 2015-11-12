package tara.intellij.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraBody;
import tara.intellij.lang.psi.TaraModel;
import tara.lang.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.intellij.psi.TokenType.WHITE_SPACE;
import static tara.intellij.lang.psi.TaraTypes.*;

public class TaraBlock implements ASTBlock {

	private static final Spacing ONELINEBREAKSPACING = Spacing.createSpacing(0, 0, 1, false, 2);
	private static final Spacing MINSPACE = Spacing.createSpacing(1, 1, 0, false, 1);
	private static final Spacing NOSPACE = Spacing.createSpacing(0, 0, 0, false, 0);
	private final Alignment alignment;
	private final Indent indent;
	private final ASTNode node;
	private final Wrap wrap;
	private final TaraBlockContext context;
	private Alignment myChildAlignment;
	private List<TaraBlock> subBlocks = null;
	private static final TokenSet untouchableBeginnings = TokenSet.create(WHITE_SPACE, CHARACTER, NEWLINE, NEW_LINE_INDENT, QUOTE_BEGIN, LEFT_PARENTHESIS, LEFT_SQUARE, LEFT_CURLY, COLON, IMPORTS, AN_IMPORT, DOT);
	private static final TokenSet untouchableEndings = TokenSet.create(WHITE_SPACE, CHARACTER, NEWLINE, NEW_LINE_INDENT, QUOTE_END, PARAMETERS, SIZE_RANGE, RIGHT_PARENTHESIS, RIGHT_SQUARE, RIGHT_CURLY, RULE_CONTAINER, COMMA, DOT);


	public TaraBlock(final ASTNode node, final Alignment alignment, final Indent indent, final Wrap wrap, final TaraBlockContext context) {
		this.alignment = alignment;
		this.indent = indent;
		this.node = node;
		this.wrap = wrap;
		this.context = context;
	}

	@Nullable
	@Override
	public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
		if (child1 == null) return null;
		TaraBlock leftBlock = (TaraBlock) child1;
		TaraBlock rightBlock = (TaraBlock) child2;
		final IElementType leftType = leftBlock.getNode().getElementType();
		final IElementType rightType = rightBlock.getNode().getElementType();
		return calculateSpace(leftBlock, rightBlock, leftType, rightType);
	}

	private Spacing calculateSpace(TaraBlock leftBlock, TaraBlock rightBlock, IElementType leftType, IElementType rightType) {
		if (asOneLineSpace(leftBlock, rightBlock, rightType))
			return ONELINEBREAKSPACING;
		else if (rightType == EQUALS || leftType == EQUALS) return MINSPACE;
		else if (isLineSeparatorCharacter(leftBlock, leftType) && rightType == NODE) return MINSPACE;
		else if (!untouchableBeginnings.contains(leftType) && !untouchableEndings.contains(rightType))
			return MINSPACE;
		return NOSPACE;
	}

	private boolean isLineSeparatorCharacter(TaraBlock leftBlock, IElementType leftType) {
		return (leftType == NEW_LINE_INDENT || leftType == NEWLINE) && (leftBlock.getNode().getText().contains(">") ||
			leftBlock.getNode().getText().contains(";"));
	}

	private boolean asOneLineSpace(TaraBlock leftBlock, TaraBlock rightBlock, IElementType rightType) {
		return rightType == NODE && rightBlock.getNode().getPsi().getParent() instanceof TaraModel && !isEnoughSeparated(leftBlock);
	}

	private boolean isEnoughSeparated(TaraBlock leftBlock) {
		return leftBlock.getNode().getText().endsWith("\n\n");
	}

	@NotNull
	public ASTNode getNode() {
		return node;
	}

	@NotNull
	public TextRange getTextRange() {
		return node.getTextRange();
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
	public ChildAttributes getChildAttributes(int newChildIndex) {
		if (newChildIndex > 0 && node.getPsi() instanceof TaraModel) return ChildAttributes.DELEGATE_TO_PREV_CHILD;
		return new ChildAttributes(indent, alignment);
	}

	@Override
	public boolean isIncomplete() {
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
			if (child.getTextRange().getLength() == 0 || childType == WHITE_SPACE) continue;
			blocks.add(buildSubBlock(child));
		}
		return Collections.unmodifiableList(blocks);
	}

	//
	private TaraBlock buildSubBlock(ASTNode child) {
		IElementType parentType = node.getElementType();
		IElementType childType = child.getElementType();
		Indent childIndent = Indent.getNoneIndent();
		Alignment childAlignment = null;
		if (parentType == NODE && childType == BODY) {
			childAlignment = getAlignmentForChildren();
			childIndent = Indent.getNormalIndent();
		}
		ASTNode prev = child.getTreePrev();
		while (prev != null && prev.getElementType() == WHITE_SPACE) {
			if (prev.textContains('\\') &&
				!childIndent.equals(Indent.getContinuationIndent(false)) &&
				!childIndent.equals(Indent.getContinuationIndent(false))) {
				childIndent = isIndentNext(child) ? Indent.getContinuationIndent() : Indent.getNormalIndent();
				break;
			}
			prev = prev.getTreePrev();
		}
		return new TaraBlock(child, childAlignment, childIndent, null, context);
	}

	private Alignment getAlignmentForChildren() {
		if (myChildAlignment == null) myChildAlignment = Alignment.createAlignment();
		return myChildAlignment;
	}

	private boolean isIndentNext(ASTNode child) {
		return PsiTreeUtil.getParentOfType(child.getPsi(), TaraBody.class) instanceof Node;
	}

	@Override
	public boolean isLeaf() {
		return node.getFirstChildNode() == null;
	}
}
