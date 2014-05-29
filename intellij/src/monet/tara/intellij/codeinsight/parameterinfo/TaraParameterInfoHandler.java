package monet.tara.intellij.codeinsight.parameterinfo;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.*;
import monet.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import monet.tara.lang.ASTNode;
import monet.tara.lang.ASTWrapper;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TaraParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<Parameters, Parameter, Parameter> {

	private static final Set


		<Class> STOP_SEARCHING_CLASSES = ContainerUtil.<Class>newHashSet(TaraFile.class);

	@NotNull
	@Override
	public Parameter[] getActualParameters(@NotNull Parameters o) {
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
		return null;
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
		return false;
	}

	@Nullable
	@Override
	public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
		return null;
	}

	@Nullable
	@Override
	public Object[] getParametersForDocumentation(Parameter p, ParameterInfoContext context) {
		return new Object[]{p};
	}

	@Nullable
	@Override
	public Parameters findElementForParameterInfo(@NotNull CreateParameterInfoContext context) {
		final Parameters parameterList = ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
		if (parameterList != null) {
			if (!(parameterList.getParent() instanceof Signature)) return null;
			final Parameter[] parameters = parameterList.getParameters();
			if (parameters.length == 0) return null;
			context.setItemsToShow(parameters);
			return parameterList;
		}
		return null;
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
		context.setHighlightedParameter(index < objectsToView.length && index >= 0 ? (PsiElement) objectsToView[index] : null);
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
	public void updateUI(Parameter p, @NotNull ParameterInfoUIContext context) {
		ASTWrapper wrapper = TaraLanguage.getHeritage();
		if (wrapper == null) return;
		@NonNls StringBuilder buffer = new StringBuilder();
		MetaIdentifier metaIdentifier = PsiTreeUtil.getChildrenOfType(TaraPsiImplUtil.getContextOf(p).getSignature(), MetaIdentifier.class)[0];
		ASTNode node = wrapper.getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0);
		ASTNode.Variable variable = node.getVariables().get(getIndexOf((Parameters) p.getParent(), p));
		if (variable instanceof ASTNode.Attribute) buffer.append(((ASTNode.Attribute) variable).getPrimitiveType());
		else if (variable instanceof ASTNode.Reference) buffer.append(((ASTNode.Reference) variable).getNode());

		if (variable.isList()) buffer.append("[]");
		buffer.append(" ");
		buffer.append(variable.getName());
		if (variable instanceof ASTNode.Word) {
			List<String> wordTypes = ((ASTNode.Word) variable).getWordTypes();
			buffer.append(" -> ").append(Arrays.toString(wordTypes.toArray(new String[wordTypes.size()])));
		}
		int highlightEndOffset = buffer.length();
		context.setupUIComponentPresentation(buffer.toString(), 0, highlightEndOffset, false, false, false, context.getDefaultParameterColor());
	}

	private int getIndexOf(Parameters parent, Parameter p) {
		List<Parameter> parameters = new ArrayList<>();
		Collections.addAll(parameters, parent.getParameters());
		return parameters.indexOf(p);
	}
}
