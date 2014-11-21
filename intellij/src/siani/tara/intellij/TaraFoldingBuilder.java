package siani.tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Primitives;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraFoldingBuilder extends CustomFoldingBuilder {
	@Override
	protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> descriptors,
	                                        @NotNull PsiElement root,
	                                        @NotNull Document document,
	                                        boolean quick) {
		List<Concept> concepts = TaraUtil.getAllConceptsOfFile((TaraBoxFileImpl) root);
		for (final Concept concept : concepts) {
			if (concept.getText() != null && concept.getBody() != null)
				descriptors.add(new FoldingDescriptor(concept.getBody().getNode(), getRange(concept)) {
					@Nullable
					@Override
					public String getPlaceholderText() {
						return buildConceptHolderText(concept);
					}
				});
			if (concept.getBody() != null)
				for (final PsiElement multiLine : searchStringMultiLineValues(concept)) {
					descriptors.add(new FoldingDescriptor(multiLine, getRange((TaraStringValue) multiLine.getParent())) {
						@Nullable
						@Override
						public String getPlaceholderText() {
							return buildMultiLineStringHolderText(multiLine);
						}
					});
				}
		}

	}

	private Collection<PsiElement> searchStringMultiLineValues(Concept concept) {
		List<PsiElement> strings = new ArrayList<>();
		searchMultiLineVariables(concept, strings);
		searchMultiLineVarInit(concept, strings);
		return strings;
	}

	private void searchMultiLineVariables(Concept concept, List<PsiElement> strings) {
		for (Variable variable : concept.getVariables()) {
			if (!variable.getType().equals(Primitives.STRING) ||
				((TaraVariable) variable).getStringValueList().isEmpty())
				continue;
			addMultiLineString((TaraVariable) variable, strings);
		}
	}

	private void searchMultiLineVarInit(Concept concept, List<PsiElement> strings) {
		//noinspection ConstantConditions
		for (VarInit variable : concept.getBody().getVarInitList()) {
			TaraVarInitValue value = variable.getValue();
			if (!variable.getValueType().equals(Primitives.STRING)) continue;
			addMultiLineString(value, strings);
		}
	}

	private void addMultiLineString(TaraVariable variable, List<PsiElement> strings) {
		for (TaraStringValue value : variable.getStringValueList())
			if (isMultiLineValue(value))
				strings.add(findMultiLineInValue(value));
	}

	private void addMultiLineString(TaraVarInitValue value, List<PsiElement> strings) {
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
	protected boolean isCustomFoldingRoot(ASTNode node) {
		return node.getPsi() instanceof TaraConcept;
	}

	private String buildConceptHolderText(Concept concept) {
		String text = " > ";
		for (Concept inner : concept.getInnerConcepts())
			text += ((inner.getName() != null) ? inner.getName() : inner.getType() + "{unNamed}") + "; ";
		return text;
	}

	private String buildMultiLineStringHolderText(PsiElement element) {
		return " ...";
	}

	private TextRange getRange(Concept concept) {
		return new TextRange(concept.getBody().getTextRange().getStartOffset(), concept.getTextRange().getEndOffset());
	}

	private TextRange getRange(TaraStringValue value) {
		return new TextRange(value.getTextRange().getStartOffset(), value.getTextRange().getEndOffset());
	}
}
