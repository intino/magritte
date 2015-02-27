package siani.tara.intellij.codeinsight.parameterinfo;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.*;
import siani.tara.lang.Variable;
import siani.tara.lang.Word;

import java.util.*;

public class TaraParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<Parameters, Object, TaraPsiElement> {

	private static final Set<Class> STOP_SEARCHING_CLASSES = ContainerUtil.<Class>newHashSet(TaraBoxFile.class);

	@NotNull
	@Override
	public TaraPsiElement[] getActualParameters(@NotNull Parameters o) {
		return o.getParameters();
	}

	@NotNull
	@Override
	public IElementType getActualParameterDelimiterType() {
		return TaraTypes.COMMA;
	}

	@NotNull
	@Override
	public IElementType getActualParametersRBraceType() {
		return TaraTypes.RIGHT_PARENTHESIS;
	}

	@NotNull
	@Override
	public Set<Class> getArgumentListAllowedParentClasses() {
		return new HashSet<Class>(Arrays.asList(Signature.class));
	}

	@NotNull
	@Override
	public Set<? extends Class> getArgListStopSearchClasses() {
		return STOP_SEARCHING_CLASSES;
	}

	@NotNull
	@Override
	public Class<Parameters> getArgumentListClass() {
		return Parameters.class;
	}

	@Override
	public boolean couldShowInLookup() {
		return true;
	}

	@Nullable
	@Override
	public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
		Parameters parameters = ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
		if (parameters == null) {
			Signature signature = PsiTreeUtil.findElementOfClassAtOffset(context.getFile(), context.getOffset(), Signature.class, false);
			if (signature != null)
				parameters = signature.getParameters();
		}
		return new Object[]{parameters};
	}

	@Nullable
	@Override
	public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
		return new Object[]{p};
	}

	@Nullable
	@Override
	public Parameters findElementForParameterInfo(@NotNull CreateParameterInfoContext parameterInfoContext) {
		final Parameters parameters = findParameters(parameterInfoContext);
		if (parameters != null) {
			Model model = TaraLanguage.getMetaModel(parameters.getContainingFile());
			if (model == null) return parameters;
//			if (parameters.getParameterList().length == 0) return parameters;
			TaraFacetApply facet = parameters.isInFacet();
			Node node = TaraUtil.findNode(TaraPsiImplUtil.getConceptContainerOf(parameters), model);
			if (node == null) return parameters;
			List<Variable> variables = (facet != null) ?
				getFacetVariables(facet.getMetaIdentifierList().get(0).getText(), getContextNameOf(facet), node) :
				node.getObject().getVariables();
			if (variables.isEmpty()) return parameters;
			parameterInfoContext.setItemsToShow(new Object[]{buildParameterInfo(parameters.getProject(), variables)});
		}
		return parameters;
	}

	private List<siani.tara.intellij.lang.psi.Variable> buildParameterInfo(Project project, List<Variable> variables) {
		TaraElementFactory instance = TaraElementFactory.getInstance(project);
		List<siani.tara.intellij.lang.psi.Variable> attributes = new ArrayList<>();
		for (Variable variable : variables) {
			if (variable.getDefaultValues() != null && variable.getDefaultValues().length > 0) continue;
			siani.tara.intellij.lang.psi.Variable attribute = null;
			if (variable instanceof Attribute || variable instanceof Reference) {
				String[] ref = variable.getType().split("\\.");
				attribute = instance.createAttribute(variable.getName(), ref[ref.length - 1] + ((variable.isList()) ? "..." : ""));
			} else if (variable instanceof Word) {
				List<String> wordTypes = ((Word) variable).getWordTypes();
				attribute = instance.createWord(variable.getName(), wordTypes.toArray(new String[wordTypes.size()]));
			} else if (variable instanceof Resource)
				attribute = instance.createResource(variable.getName(), variable.getType());
			if (attribute != null) attributes.add(attribute);
		}
		return attributes;
	}

	private String getContextNameOf(TaraFacetApply facetApply) {
		PsiElement contextOf = TaraPsiImplUtil.getContextOf(facetApply);
		if (contextOf instanceof TaraFacetApply)
			return contextOf.getFirstChild().getText();
		return null;
	}

	private List<Variable> getFacetVariables(String name, String context, Node node) {
		FacetTarget target = node.getObject().getAllowedFacetByContext(name, context);
		return target != null ? target.getVariables() : Collections.EMPTY_LIST;
	}

	private Parameters findParameters(CreateParameterInfoContext context) {
		Parameters parameters = ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
		if (parameters == null) {
			Signature signature = PsiTreeUtil.findElementOfClassAtOffset(context.getFile(), context.getOffset(), Signature.class, false);
			if (signature != null)
				parameters = signature.getParameters();
		}
		return parameters;
	}

	@Override
	public void showParameterInfo(@NotNull Parameters element, @NotNull CreateParameterInfoContext context) {
		context.showHint(element, element.getTextRange().getStartOffset() + 1, this);
	}

	@Nullable
	@Override
	public Parameters findElementForUpdatingParameterInfo(@NotNull UpdateParameterInfoContext context) {
		return ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
	}

	@Override
	public void updateParameterInfo(@NotNull Parameters parameters, @NotNull UpdateParameterInfoContext context) {
		int index = ParameterInfoUtils.getCurrentParameterIndex(parameters.getNode(), context.getOffset(), getActualParameterDelimiterType());
		context.setCurrentParameter(index);
		final Object[] objectsToView = context.getObjectsToView();
		if (objectsToView.length == 0 || ((List) objectsToView[0]).isEmpty()) return;
		context.setHighlightedParameter(index < objectsToView.length && index >= 0 ? ((List) objectsToView[0]).get(index) : null);
	}

	@Nullable
	@Override
	public String getParameterCloseChars() {
		return ",)";
	}

	@Override
	public boolean tracksParameterIndex() {
		return false;
	}

	@Override
	public void updateUI(Object attributes, @NotNull ParameterInfoUIContext context) {
		ArrayList<siani.tara.intellij.lang.psi.Variable> psiVariable = (ArrayList<siani.tara.intellij.lang.psi.Variable>) attributes;
		if (psiVariable == null) return;
		StringBuilder builder = new StringBuilder();
		for (siani.tara.intellij.lang.psi.Variable variable : psiVariable)
			builder.append(", ").append(variable.getText().substring(4));
		int highlightEndOffset = builder.toString().length();
		context.setupUIComponentPresentation(builder.length() == 0 ? "" : builder.toString().substring(2),
			0, highlightEndOffset, false, false, false, context.getDefaultParameterColor());
	}

}
