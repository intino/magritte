package monet.::projectName::.intellij.codeinsight.parameterinfo;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.containers.ContainerUtil;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import monet.::projectName::.intellij.lang.psi.*;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;
import monet.tara.lang.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ::projectProperName::ParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<Parameters, Object, ::projectProperName::PsiElement> {

	private static final Set<Class> STOP_SEARCHING_CLASSES = ContainerUtil.<Class>newHashSet(::projectProperName::File.class);

	\@NotNull
	\@Override
	public ::projectProperName::PsiElement[] getActualParameters(\@NotNull Parameters o) {
		return o.getParameters();
	}

	\@NotNull
	\@Override
	public IElementType getActualParameterDelimiterType() {
		return ::projectProperName::Types.COMMA;
	}

	\@NotNull
	\@Override
	public IElementType getActualParametersRBraceType() {
		return ::projectProperName::Types.RIGHT_PARENTHESIS;
	}

	\@NotNull
	\@Override
	public Set<Class> getArgumentListAllowedParentClasses() {
		return new HashSet<Class>(Arrays.asList(Signature.class));
	}

	\@NotNull
	\@Override
	public Set<? extends Class> getArgListStopSearchClasses() {
		return STOP_SEARCHING_CLASSES;
	}

	\@NotNull
	\@Override
	public Class<Parameters> getArgumentListClass() {
		return Parameters.class;
	}

	\@Override
	public boolean couldShowInLookup() {
		return true;
	}

	\@Nullable
	\@Override
	public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
		return null;
	}

	\@Nullable
	\@Override
	public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
		return new Object[]{p};
	}

	\@Nullable
	\@Override
	public Parameters findElementForParameterInfo(\@NotNull CreateParameterInfoContext context) {
		final Parameters parameterList = ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
		if (parameterList != null) {
			TreeWrapper wrapper = ::projectProperName::Language.getHeritage();
			if (wrapper == null) return parameterList;
			MetaIdentifier metaIdentifier = ::projectProperName::PsiImplUtil.getContextOf(parameterList).getMetaIdentifier();
			AbstractNode node = wrapper.getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0);
			if (node.getVariables().isEmpty()) return parameterList;
			List<Attribute> attributes = new ArrayList<>();
			::projectProperName::ElementFactory instance = ::projectProperName::ElementFactory.getInstance(parameterList.getProject());
			for (Variable variable \: node.getVariables()) {
				Attribute attribute = null;
				if (variable instanceof NodeAttribute)
					attribute = instance.createAttribute(variable.getName(), ((NodeAttribute) variable).getPrimitiveType() + ((variable.isList()) ? "[]" \: ""));
				else if (variable instanceof Reference)
					attribute = instance.createAttribute(variable.getName(), ((Reference) variable).getNode() + ((variable.isList()) ? "[]" \: ""));
				else if (variable instanceof NodeWord) {
					List<String> wordTypes = ((NodeWord) variable).getWordTypes();
					attribute = instance.createWord(variable.getName(), wordTypes.toArray(new String[wordTypes.size()]));
				}
				if (attribute != null) attributes.add(attribute);
			}
			context.setItemsToShow(new Object[]{attributes.toArray()});
		}
		return parameterList;
	}

	\@Override
	public void showParameterInfo(\@NotNull Parameters element, \@NotNull CreateParameterInfoContext context) {
		context.showHint(element, element.getTextRange().getStartOffset() + 1, this);
	}

	\@Nullable
	\@Override
	public Parameters findElementForUpdatingParameterInfo(\@NotNull UpdateParameterInfoContext context) {
		return ParameterInfoUtils.findParentOfType(context.getFile(), context.getOffset(), Parameters.class);
	}

	\@Override
	public void updateParameterInfo(\@NotNull Parameters parameters, \@NotNull UpdateParameterInfoContext context) {
		int index = ParameterInfoUtils.getCurrentParameterIndex(parameters.getNode(), context.getOffset(), getActualParameterDelimiterType());
		context.setCurrentParameter(index);
		final Object[] objectsToView = context.getObjectsToView();
		context.setHighlightedParameter(index < objectsToView.length && index >= 0 ? (PsiElement) objectsToView[index] \: null);
	}

	\@Nullable
	\@Override
	public String getParameterCloseChars() {
		return ",)";
	}

	\@Override
	public boolean tracksParameterIndex() {
		return false;
	}

	\@Override
	public void updateUI(Object attributes, \@NotNull ParameterInfoUIContext context) {
		Attribute[] psiAttribute = (Attribute[]) ((Object[]) attributes)[0];
		StringBuilder builder = new StringBuilder();
		for (Attribute attribute \: psiAttribute) builder.append(", ").append(attribute.getText().substring(4));
		int highlightEndOffset = builder.toString().length();
		context.setupUIComponentPresentation(builder.toString(), 0, highlightEndOffset, false, false, false, context.getDefaultParameterColor());
	}

}
