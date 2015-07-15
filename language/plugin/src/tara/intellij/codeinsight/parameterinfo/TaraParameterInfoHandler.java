package tara.intellij.codeinsight.parameterinfo;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import tara.language.semantics.Constraint;

import java.util.*;

public class TaraParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<Parameters, Object, TaraPsiElement> {

	private static final Set<Class> STOP_SEARCHING_CLASSES = ContainerUtil.<Class>newHashSet(TaraModel.class);

	@NotNull
	@Override
	public TaraPsiElement[] getActualParameters(@NotNull Parameters o) {
		return o.getParameters().toArray(new Parameter[o.getParameters().size()]);
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
			Language language = TaraLanguage.getLanguage(parameters.getContainingFile());
			if (language == null) return parameters;
			TaraFacetApply facet = parameters.isInFacet();
			Collection<Constraint> constraints = language.constraints(facet != null ? facet.type() : TaraPsiImplUtil.getContainerNodeOf(parameters).resolve().fullType());
			if (constraints == null) return parameters;
			List<Constraint.Require.Parameter> requiredParameters = new ArrayList<>();
			for (Constraint require : constraints) {
				if (!(require instanceof Constraint.Require.Parameter)) continue;
				requiredParameters.add((Constraint.Require.Parameter) require);
			}
			if (requiredParameters.isEmpty()) return parameters;
			parameterInfoContext.setItemsToShow(new Object[]{buildParameterInfo(requiredParameters)});
		}
		return parameters;
	}

	private String[] buildParameterInfo(List<Constraint.Require.Parameter> requires) {
		List<String> parameters = new ArrayList<>();
		for (Constraint.Require.Parameter require : requires) {
			String parameter = require.type().equals("reference") || require.type().equals("word") ?
				Arrays.toString(require.allowedValues()) + (require.multiple() ? "... " : " ") + require.name() :
				require.type() + (require.multiple() ? "... " : " ") + require.name();
			parameters.add(parameter);
		}
		return parameters.toArray(new String[parameters.size()]);
	}

	private Parameters findParameters(CreateParameterInfoContext context) {
		Parameters parameters = ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
		if (parameters == null) {
			Signature signature = PsiTreeUtil.findElementOfClassAtOffset(context.getFile(), context.getOffset(), Signature.class, false);
			if (signature != null) parameters = signature.getParameters();
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
		if (objectsToView.length == 0 || ((String[]) objectsToView[0]).length == 0) return;
		context.setHighlightedParameter(index < objectsToView.length && index >= 0 ? ((String[]) objectsToView[0])[index] : null);
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
