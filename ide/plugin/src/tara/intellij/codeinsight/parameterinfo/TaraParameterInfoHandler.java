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
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.semantics.Allow;
import tara.language.semantics.Constraint;

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
		Language language = TaraLanguage.getLanguage(parameters.getContainingFile());
		if (language == null) return;
		TaraFacetApply facet = parameters.isInFacet();
		final String type = facet != null ? facet.type() : TaraPsiImplUtil.getContainerNodeOf(parameters).resolve().type();
		List<Allow> allows = language.allows(type);
		if (allows == null) return;
		List<Allow.Parameter> parameterAllows = allows.stream().
			filter(allow -> (allow instanceof Allow.Parameter)).
			map(allow -> (Allow.Parameter) allow).collect(Collectors.toList());
		if (!parameterAllows.isEmpty())
			context.setItemsToShow(new Object[]{buildParameterInfo(parameterAllows, requires(language, type))});
		context.showHint(parameters, parameters.getTextRange().getStartOffset(), this);
	}

	private List<Constraint.Require.Parameter> requires(Language language, String type) {
		return language.constraints(type).stream().
			filter(require -> (require instanceof Constraint.Require.Parameter)).
			map(require -> (Constraint.Require.Parameter) require).collect(Collectors.toList());
	}

	private String[] buildParameterInfo(List<Allow.Parameter> allows, List<Constraint.Require.Parameter> requires) {
		List<String> parameters = new ArrayList<>();
		for (Allow.Parameter allow : allows) {
			String parameter = allow.type().equals("reference") || allow.type().equals("word") ?
				presentableText(allow) + (allow.multiple() ? "... " : " ") + allow.name() :
				allow.type() + (allow.multiple() ? "... " : " ") + allow.name();
			parameters.add(parameter + (isRequired(requires, allow.name()) ? "*" : ""));
		}
		return parameters.toArray(new String[parameters.size()]);
	}

	private boolean isRequired(List<Constraint.Require.Parameter> requires, String name) {
		for (Constraint.Require.Parameter require : requires)
			if (require.name().equals(name)) return true;
		return false;
	}

	@NotNull
	private String presentableText(Allow.Parameter allow) {
		return Arrays.toString(allow.allowedValues().toArray(new String[allow.allowedValues().size()]));
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
