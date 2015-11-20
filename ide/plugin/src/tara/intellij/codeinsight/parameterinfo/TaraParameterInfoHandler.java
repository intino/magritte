package tara.intellij.codeinsight.parameterinfo;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.lang.model.Primitive;
import tara.lang.semantics.Constraint;
import tara.lang.semantics.constraints.parameter.ReferenceParameter;

import java.util.*;
import java.util.stream.Collectors;

public class TaraParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<Parameters, Object, TaraParameter> {

	private static final Set<Class> STOP_SEARCHING_CLASSES = ContainerUtil.<Class>newHashSet(TaraModel.class);

	@NotNull
	@Override
	public TaraParameter[] getActualParameters(@NotNull Parameters o) {
		return o.getParameters().toArray(new TaraParameter[o.getParameters().size()]);
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
		return new HashSet<>(Collections.singletonList(Signature.class));
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
		Parameters parameters = getParameters(context.getFile(), context.getOffset());
		return new Object[]{parameters};
	}

	@Nullable
	@Override
	public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
		return new Object[]{p};
	}

	@Nullable
	@Override
	public Parameters findElementForParameterInfo(@NotNull CreateParameterInfoContext context) {
		return findParameters(context);
	}

	private Parameters findParameters(CreateParameterInfoContext context) {
		Parameters parameters = getParameters(context.getFile(), context.getOffset());
		if (parameters == null) return null;
		int index = ParameterInfoUtils.getCurrentParameterIndex(parameters.getNode(), context.getOffset(), getActualParameterDelimiterType());
		if (!parameters.getParameters().isEmpty())
			context.setHighlightedElement((PsiElement) parameters.getParameters().get(index));
		return parameters;
	}

	@Nullable
	private Parameters getParameters(PsiFile file, int offset) {
		Parameters parameters = ParameterInfoUtils.findParentOfType(file, offset, Parameters.class);
		if (parameters == null) {
			Signature signature = PsiTreeUtil.findElementOfClassAtOffset(file, offset, Signature.class, false);
			if (signature != null) parameters = signature.getParameters();
		}
		return parameters;
	}

	@Override
	public void showParameterInfo(@NotNull Parameters parameters, @NotNull CreateParameterInfoContext context) {
		Language language = TaraUtil.getLanguage(parameters);
		if (language == null) return;
		final String type = TaraPsiImplUtil.getContainerNodeOf(parameters).resolve().type();
		List<Constraint> constraints = language.constraints(type);
		if (constraints == null) return;
		List<Constraint.Parameter> parameterAllows = collectParameterConstraints(constraints, parameters.isInFacet());
		if (!parameterAllows.isEmpty())
			context.setItemsToShow(new Object[]{buildParameterInfo(parameterAllows)});
		context.showHint(parameters, parameters.getTextRange().getStartOffset(), this);
	}

	public List<Constraint.Parameter> collectParameterConstraints(List<Constraint> nodeAllows, TaraFacetApply inFacet) {
		List<Constraint> scopeAllows = nodeAllows;
		if (inFacet != null) scopeAllows = collectFacetParameterConstraints(nodeAllows, inFacet.type());
		return scopeAllows.stream().
			filter(constraint -> constraint instanceof Constraint.Parameter).
			map(constraint -> (Constraint.Parameter) constraint).collect(Collectors.toList());
	}

	private List<Constraint> collectFacetParameterConstraints(List<Constraint> nodeAllows, String type) {
		for (Constraint constraint : nodeAllows)
			if ((constraint instanceof Constraint.Facet) && ((Constraint.Facet) constraint).type().equals(type))
				return ((Constraint.Facet) constraint).constraints();
		return Collections.emptyList();
	}

	private List<Constraint.Parameter> requires(Language language, String type) {
		return language.constraints(type).stream().
			filter(require -> require instanceof Constraint.Parameter).
			map(require -> (Constraint.Parameter) require).collect(Collectors.toList());
	}

	private String[] buildParameterInfo(List<Constraint.Parameter> constraints) {
		List<String> parameters = new ArrayList<>();
		for (Constraint.Parameter constraint : constraints) {
			String parameter = Primitive.REFERENCE.equals(constraint.type()) ?
				asReferenceParameter(constraint) :
				asWordParameter(constraint);
			parameters.add(parameter + (constraint.size().isRequired() ? "*" : ""));
		}
		return parameters.toArray(new String[parameters.size()]);
	}

	@NotNull
	private String asWordParameter(Constraint.Parameter constraint) {
		return constraint.type() + (multiple(constraint)) + constraint.name();
	}

	@NotNull
	private String asReferenceParameter(Constraint.Parameter constraint) {
		return presentableText((ReferenceParameter) constraint) + multiple(constraint) + constraint.name();
	}

	@NotNull
	private String multiple(Constraint.Parameter constraint) {
		return constraint.size().max() > 1 ? "[] " : " ";
	}

	@NotNull
	private String presentableText(ReferenceParameter constraint) {
		return String.join(", ", constraint.rule().getAllowedReferences());
	}

	@Nullable
	@Override
	public Parameters findElementForUpdatingParameterInfo(@NotNull UpdateParameterInfoContext context) {
		return ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
	}

	@Override
	public void updateParameterInfo(@NotNull Parameters parameters, @NotNull UpdateParameterInfoContext context) {
		if (context.getParameterOwner() != parameters) {
			context.removeHint();
			return;
		}
		int index = ParameterInfoUtils.getCurrentParameterIndex(parameters.getNode(), context.getOffset(), getActualParameterDelimiterType());
		context.setCurrentParameter(index);
		context.setParameterOwner(parameters);
		final Object[] objectsToView = context.getObjectsToView();
		context.setHighlightedParameter(index < objectsToView.length && index >= 0 ? objectsToView[index] : null);

	}

	@Nullable
	@Override
	public String getParameterCloseChars() {
		return ",)";
	}

	@Override
	public boolean tracksParameterIndex() {
		return true;
	}

	@Override
	public void updateUI(Object attributes, @NotNull ParameterInfoUIContext context) {
		String[] parameters = (String[]) attributes;
		if (parameters == null) return;
		StringBuilder builder = new StringBuilder();
		for (String parameter : parameters)
			builder.append(", ").append(parameter);
		int highlightEndOffset = builder.length();
		context.setupUIComponentPresentation(builder.length() == 0 ? "" : builder.toString().substring(2),
			0, highlightEndOffset, false, false, false, context.getDefaultParameterColor());
	}
}
