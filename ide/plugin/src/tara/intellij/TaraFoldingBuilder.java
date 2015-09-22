package tara.intellij;

import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.language.model.*;

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
			processNode(descriptors, node);
			processDocumentations(descriptors, node);
			processMultiLineValues(descriptors, node);
			processMultiValuesParameters(descriptors, node);
			for (Facet facetApply : node.facets()) processMultiValuesParameters(descriptors, facetApply);
		}
	}

	private void processNode(@NotNull List<FoldingDescriptor> descriptors, final Node node) {
		if (((TaraNode) node).getText() != null && ((TaraNode) node).getBody() != null)
			descriptors.add(new FoldingDescriptor(((TaraNode) node).getBody().getNode(), getRange(node)) {
				@Nullable
				@Override
				public String getPlaceholderText() {
					return buildNodeHolderText(node);
				}
			});
	}

	private void processDocumentations(List<FoldingDescriptor> descriptors, Node node) {
		Doc doc = ((TaraNode) node).getDoc();
		if (doc == null) return;
		descriptors.add(new FoldingDescriptor(doc.getNode(), getRange(doc), FoldingGroup.newGroup(node.qualifiedName())) {
			public String getPlaceholderText() {
				return buildDocHolderText();
			}
		});
	}

	private void processMultiValuesParameters(@NotNull List<FoldingDescriptor> descriptors, Parametrized node) {
		descriptors.addAll(searchMultiValuedParameters(node).stream().
			map(multivalued -> new FoldingDescriptor((PsiElement) multivalued, getRange(((Valued) multivalued).getValue())) {
				public String getPlaceholderText() {
					return buildHolderText();
				}
			}).collect(Collectors.toList()));
	}

	private void processMultiLineValues(@NotNull List<FoldingDescriptor> descriptors, Node node) {
		descriptors.addAll(searchStringMultiLineValues(node).stream().
			map(multiLine -> new FoldingDescriptor(multiLine, getRange(multiLine)) {
				public String getPlaceholderText() {
					return buildHolderText();
				}
			}).
			collect(Collectors.toList()));
		descriptors.addAll(searchStringMultiLineValues(node.facetTargets()).stream().
			map(multiLine -> new FoldingDescriptor(multiLine, getRange(multiLine)) {
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
		if (((TaraNode) node).getBody() == null) return;
		for (Parameter parameter : ((TaraNode) node).getBody().getVarInitList()) {
			Value value = ((Valued) parameter).getValue();
			if (!Primitives.STRING.equals(((Valued) parameter).getInferredType())) continue;
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
		return value instanceof TaraStringValue || (value instanceof TaraValue && ((TaraValue) value).values().size() >= VALUE_MAX_SIZE) || node.getPsi() instanceof TaraDoc;
	}

	@Override
	protected boolean isCustomFoldingRoot(ASTNode astNode) {
		return astNode.getPsi() instanceof Node;
	}

	private String buildNodeHolderText(Node node) {
		StringBuilder text = new StringBuilder();
		node.components().stream().filter(inner -> inner.name() != null).forEach(inner -> text.append(" ").append(inner.name()));
		return text.toString();
	}

	private String buildHolderText() {
		return " ...";
	}

	private String buildDocHolderText() {
		return "doc";
	}

	private TextRange getRange(Node node) {
		return new TextRange(((TaraNode) node).getBody().getTextRange().getStartOffset(), ((TaraNode) node).getTextRange().getEndOffset());
	}

	private TextRange getRange(PsiElement value) {
		return new TextRange(value.getTextRange().getStartOffset(), value.getTextRange().getEndOffset());
	}

	private boolean isStringOrNativeType(Variable variable) {
		return variable.type() != null && (variable.type().equals(Primitives.STRING) || variable.type().equals(Primitives.NATIVE));
	}

	private boolean hasStringValue(Variable variable) {
		return ((Valued) variable).getValue() != null && !((Valued) variable).getValue().getStringValueList().isEmpty();
	}
}
