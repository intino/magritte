package tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.Primitives;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaraFoldingBuilder extends CustomFoldingBuilder {

	private static final int VALUE_MAX_SIZE = 5;

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
						return buildNodeHolderText(node);
					}
				});
			processMultiLineValues(descriptors, node);
			processMultiValuesParameters(descriptors, node);
			for (FacetApply facetApply : node.facets()) processMultiValuesParameters(descriptors, facetApply);
		}
	}

	private void processMultiValuesParameters(@NotNull List<FoldingDescriptor> descriptors, Parametrized node) {
		descriptors.addAll(searchMultiValuedParameters(node).stream().
			map(multivalued -> new FoldingDescriptor(multivalued, getRange(multivalued.getValue())) {
				public String getPlaceholderText() {
					return buildHolderText();
				}
			}).collect(Collectors.toList()));
	}

	private void processMultiLineValues(@NotNull List<FoldingDescriptor> descriptors, Node node) {
		descriptors.addAll(searchStringMultiLineValues(node).stream().
			map(multiLine -> new FoldingDescriptor(multiLine, getRange((TaraStringValue) multiLine)) {
				public String getPlaceholderText() {
					return buildHolderText();
				}
			}).
			collect(Collectors.toList()));
		descriptors.addAll(searchStringMultiLineValues(node.facetTargets()).stream().
			map(multiLine -> new FoldingDescriptor(multiLine, getRange((TaraStringValue) multiLine)) {
				public String getPlaceholderText() {
					return buildHolderText();
				}
			}).collect(Collectors.toList()));
	}

	private List<Parameter> searchMultiValuedParameters(Parametrized node) {
		return node.parameters().stream().filter(parameter -> parameter.values().size() >= VALUE_MAX_SIZE).collect(Collectors.toList());
	}

	private List<PsiElement> searchStringMultiLineValues(List<? extends FacetTarget> facetTargets) {
		List<PsiElement> strings = new ArrayList<>();
		for (FacetTarget facetTarget : facetTargets) {
			searchMultiLineVariables(facetTarget, strings);
			addAllFacetInnerNodes(facetTarget.components(), strings);
		}
		return strings;
	}

	private void addAllFacetInnerNodes(List<? extends Node> nodes, List<PsiElement> strings) {
		for (Node node : nodes) {
			strings.addAll(searchStringMultiLineValues(node));
			addAllFacetInnerNodes(node.components(), strings);
			addAllFacetInnerNodes(node.subs(), strings);
		}
	}

	private List<PsiElement> searchStringMultiLineValues(Node node) {
		List<PsiElement> strings = new ArrayList<>();
		searchMultiLineVariables(node, strings);
		searchMultiLineVarInit(node, strings);
		return strings;
	}

	private void searchMultiLineVariables(Node node, List<PsiElement> strings) {
		node.variables().stream().
			filter(variable -> isStringOrNativeType(variable) && hasStringValue(variable)).
			forEach(variable -> TaraFoldingBuilder.this.addMultiLineString((TaraVariable) variable, strings));
	}

	private void searchMultiLineVariables(FacetTarget node, List<PsiElement> strings) {
		node.variables().stream().
			filter(variable -> isStringOrNativeType(variable) && hasStringValue(variable)).
			forEach(variable -> TaraFoldingBuilder.this.addMultiLineString((TaraVariable) variable, strings));
	}

	private void searchMultiLineVarInit(Node node, List<PsiElement> strings) {
		if (node.getBody() == null) return;
		for (Parameter variable : node.getBody().getVarInitList()) {
			Value value = variable.getValue();
			if (!variable.getValueType().equals(Primitives.STRING)) continue;
			addMultiLineString(value, strings);
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void addMultiLineString(TaraVariable variable, List<PsiElement> strings) {
		if (variable.getValue() == null) return;
		strings.addAll(variable.getValue().getStringValueList().stream().filter(StringValue::isMultiLine).collect(Collectors.toList()));
	}

	private void addMultiLineString(Value value, List<PsiElement> strings) {
		strings.addAll(((TaraValue) value).getStringValueList().stream().filter(StringValue::isMultiLine).collect(Collectors.toList()));
	}

	@Override
	protected String getLanguagePlaceholderText(@NotNull ASTNode node, @NotNull TextRange range) {
		return " ...";
	}

	@Override
	protected boolean isRegionCollapsedByDefault(@NotNull ASTNode node) {
		final PsiElement value = node.getPsi().getParent();
		return value instanceof TaraStringValue || (value instanceof TaraValue && ((TaraValue) value).values().size() >= VALUE_MAX_SIZE);
	}

	@Override
	protected boolean isCustomFoldingRoot(ASTNode astNode) {
		return astNode.getPsi() instanceof Node;
	}

	private String buildNodeHolderText(Node node) {
		String text = "";
		for (Node inner : node.components())
			if (inner.name() != null) text += " " + inner.name();
		return text;
	}

	private String buildHolderText() {
		return " ...";
	}

	private TextRange getRange(Node node) {
		return new TextRange(node.getBody().getTextRange().getStartOffset(), node.getTextRange().getEndOffset());
	}

	private TextRange getRange(TaraStringValue value) {
		return new TextRange(value.getTextRange().getStartOffset(), value.getTextRange().getEndOffset());
	}

	private TextRange getRange(Value value) {
		return new TextRange(value.getTextRange().getStartOffset(), value.getTextRange().getEndOffset());
	}

	private boolean isStringOrNativeType(Variable variable) {
		return variable.type() != null && (variable.type().equals(Primitives.STRING) || variable.type().equals(Primitives.NATIVE));
	}

	private boolean hasStringValue(Variable variable) {
		return variable.getValue() != null && !variable.getValue().getStringValueList().isEmpty();
	}
}
