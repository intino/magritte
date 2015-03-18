package siani.tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.lexer.TaraPrimitives;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraFoldingBuilder extends CustomFoldingBuilder {
	@Override
	protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors,
	                                        @NotNull PsiElement root,
	                                        @NotNull Document document,
	                                        boolean quick) {
		List<Node> nodes = TaraUtil.getAllNodesOfFile((TaraModelImpl) root);
		for (final Node node : nodes) {
			if (node.getText() != null && node.getBody() != null)
				descriptors.add(new FoldingDescriptor(node.getBody().getNode(), getRange(node)) {
					@Nullable
					@Override
					public String getPlaceholderText() {
						return buildConceptHolderText(node);
					}
				});
			if (node.getBody() != null)
				for (final PsiElement multiLine : searchStringMultiLineValues(node)) {
					descriptors.add(new FoldingDescriptor(multiLine, getRange((TaraStringValue) multiLine.getParent())) {
						@Nullable
						@Override
						public String getPlaceholderText() {
							return buildMultiLineStringHolderText();
						}
					});
				}
		}

	}

	private Collection<PsiElement> searchStringMultiLineValues(Node node) {
		List<PsiElement> strings = new ArrayList<>();
		searchMultiLineVariables(node, strings);
		searchMultiLineVarInit(node, strings);
		return strings;
	}

	private void searchMultiLineVariables(Node node, List<PsiElement> strings) {
		for (Variable variable : node.getVariables()) {
			if (isStringType(variable) || hasStringValue(variable))
				continue;
			addMultiLineString((TaraVariable) variable, strings);
		}
	}

	private boolean isStringType(Variable variable) {
		return variable.getType() != null && !variable.getType().equals(TaraPrimitives.STRING);
	}

	private boolean hasStringValue(Variable variable) {
		return variable.getValue() != null && !variable.getValue().getStringValueList().isEmpty();
	}

	private void searchMultiLineVarInit(Node node, List<PsiElement> strings) {
		//noinspection ConstantConditions
		for (VarInit variable : node.getBody().getVarInitList()) {
			TaraValue value = variable.getValue();
			if (!variable.getValueType().equals(TaraPrimitives.STRING)) continue;
			addMultiLineString(value, strings);
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void addMultiLineString(TaraVariable variable, List<PsiElement> strings) {
		if (variable.getValue() == null) return;
		for (TaraStringValue value : variable.getValue().getStringValueList())
			if (isMultiLineValue(value))
				strings.add(findMultiLineInValue(value));
	}

	private void addMultiLineString(TaraValue value, List<PsiElement> strings) {
		for (TaraStringValue stringValue : value.getStringValueList())
			if (isMultiLineValue(stringValue))
				strings.add(findMultiLineInValue(stringValue));
	}

	private boolean isMultiLineValue(TaraStringValue value) {
		return value.getLastChild().getNode().getElementType().equals(TaraTypes.STRING_MULTILINE_VALUE_KEY);
	}

	private PsiElement findMultiLineInValue(TaraStringValue value) {
		return value.getLastChild();
	}

	@Override
	protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
		return " ...";
	}

	@Override
	protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
		return node.getPsi().getParent() instanceof TaraStringValue;
	}

	@Override
	protected boolean isCustomFoldingRoot(ASTNode astNode) {
		return astNode.getPsi() instanceof Node;
	}

	private String buildConceptHolderText(Node node) {
		String text = "";
		for (Node inner : node.getInnerConcepts())
			if (inner.getName() != null) text += " " + inner.getName();
		return text;
	}

	private String buildMultiLineStringHolderText() {
		return " ...";
	}

	private TextRange getRange(Node node) {
		return new TextRange(node.getBody().getTextRange().getStartOffset(), node.getTextRange().getEndOffset());
	}

	private TextRange getRange(TaraStringValue value) {
		return new TextRange(value.getTextRange().getStartOffset(), value.getTextRange().getEndOffset());
	}
}
