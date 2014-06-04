package monet.tara.intellij.codeinsight.parameterinfo;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.*;
import monet.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import monet.tara.lang.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TaraParameterInfoHandler implements ParameterInfoHandlerWithTabActionSupport<Parameters, Object, TaraPsiElement> {

	private static final Set<Class> STOP_SEARCHING_CLASSES = ContainerUtil.<Class>newHashSet(TaraFile.class);

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
		return null;
	}

	@Nullable
	@Override
	public Object[] getParametersForDocumentation(Object p, ParameterInfoContext context) {
		return new Object[]{p};
	}

	@Nullable
	@Override
	public Parameters findElementForParameterInfo(@NotNull CreateParameterInfoContext parameterInfoContext) {
		final Parameters parameterList = findParameters(parameterInfoContext);
		if (parameterList != null) {
			TreeWrapper wrapper = TaraLanguage.getHeritage();
			if (wrapper == null) return parameterList;
			Concept conceptContext = TaraPsiImplUtil.getContextOf(parameterList);
			Node node = findCorrespondentNode(conceptContext, wrapper);
			if (node == null) return parameterList;
			if (node.getObject().getVariables().isEmpty()) return parameterList;
			List<Attribute> attributes = new ArrayList<>();
			TaraElementFactory instance = TaraElementFactory.getInstance(parameterList.getProject());
			for (Variable variable : node.getObject().getVariables()) {
				Attribute attribute = null;
				if (variable instanceof NodeAttribute)
					attribute = instance.createAttribute(variable.getName(), ((NodeAttribute) variable).getPrimitiveType() + ((variable.isList()) ? "[]" : ""));
				else if (variable instanceof Reference)
					attribute = instance.createAttribute(variable.getName(), ((Reference) variable).getType() + ((variable.isList()) ? "[]" : ""));
				else if (variable instanceof NodeWord) {
					List<String> wordTypes = ((NodeWord) variable).getWordTypes();
					attribute = instance.createWord(variable.getName(), wordTypes.toArray(new String[wordTypes.size()]));
				} else if (variable instanceof Resource)
					attribute = instance.createResource(variable.getName(), ((Resource) variable).type);

				if (attribute != null) attributes.add(attribute);
			}
			parameterInfoContext.setItemsToShow(new Object[]{attributes});
		}
		return parameterList;
	}

	private Node findCorrespondentNode(Concept concept, TreeWrapper wrapper) {
		String id = getConceptQualifiedName(concept) + "." + concept.getMetaIdentifier().getText();
		for (Node node : wrapper.getNodeNameLookUpTable().get(concept.getMetaIdentifier().getText()))
			if (node.getQualifiedName().equals(id)) return node;
		for (Node node : wrapper.getNodeNameLookUpTable().get(""))
			if (node.getQualifiedName().equals(id)) return node;
		return null;
	}

	private String getConceptQualifiedName(Concept concept) {
		Concept container = concept;
		String id = "";
		while ((container = TaraPsiImplUtil.getContextOf(container)) != null)
			id = container.getMetaIdentifier().getText() + "." + id;
		return id.substring(0, id.length() - 1);
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
		ArrayList<Attribute> psiAttribute = (ArrayList<Attribute>) attributes;
		if (psiAttribute == null) return;
		StringBuilder builder = new StringBuilder();
		for (Attribute attribute : psiAttribute) builder.append(", ").append(attribute.getText().substring(4));
		int highlightEndOffset = builder.toString().length();
		context.setupUIComponentPresentation(builder.toString().substring(2), 0, highlightEndOffset, false, false, false, context.getDefaultParameterColor());
	}

}
