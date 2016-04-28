package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.Parameters;
import tara.intellij.lang.psi.TaraElementFactory;
import tara.intellij.lang.psi.TaraFacetApply;
import tara.intellij.lang.psi.TaraParameters;
import tara.lang.model.Node;
import tara.lang.model.Parameter;
import tara.lang.model.Primitive;
import tara.lang.model.rules.CompositionRule;

import java.util.*;
import java.util.stream.Collectors;

public class FacetApplyMixin extends ASTWrapperPsiElement {

	public FacetApplyMixin(@NotNull ASTNode node) {
		super(node);
	}

	public List<Parameter> parameters() {
		List<Parameter> parameterList = new ArrayList<>();
		final TaraParameters parameters = ((TaraFacetApply) this).getParameters();
		if (parameters != null) parameterList.addAll(parameters.getParameters());
		return parameterList;
	}

	public void addParameter(String name, int position, String extension, int line, int column, List<Object> values) {
		final TaraElementFactory factory = TaraElementFactory.getInstance(this.getProject());
		Map<String, String> params = new HashMap();
		params.put(name, String.join(" ", toString(values)));
		final Parameters newParameters = factory.createExplicitParameters(params);
		final TaraParameters parameters = ((TaraFacetApply) this).getParameters();
		if (parameters == null) this.addAfter(newParameters, ((TaraFacetApply) this).getMetaIdentifierList().get(0));
		else {
			PsiElement anchor = calculateAnchor(parameters, position);
			parameters.addBefore((PsiElement) newParameters.getParameters().get(0), anchor);
			parameters.addBefore(factory.createParameterSeparator(), anchor);
		}
	}

	public List<String> toString(List<Object> values) {
		return values.stream().map(v -> {
			final String quote = mustBeQuoted(v);
			return quote + v.toString() + quote;
		}).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "as " + type();
	}

	private String mustBeQuoted(Object v) {
		if (v instanceof Primitive.Expression) return "'";
		else if (v instanceof String) return "\"";
		else return "";
	}

	private PsiElement calculateAnchor(TaraParameters parameters, int position) {
		return parameters.getParameters().size() <= position ?
			parameters.getLastChild() :
			(PsiElement) parameters.getParameters().get(position);
	}

	public String type() {
		if (!((TaraFacetApply) this).getMetaIdentifierList().isEmpty())
			return ((TaraFacetApply) this).getMetaIdentifierList().get(0).getText();
		return "";
	}

	public CompositionRule ruleOf(Node component) {
		return null;//TODO
	}

	public Node container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String doc() {
		return null;
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	public List<String> uses() {
		return Collections.emptyList();
	}

}
