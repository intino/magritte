package monet.::projectName::.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import monet.::projectName::.intellij.lang.psi.*;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;
import monet.::projectName::.intellij.lang.psi.resolve.::projectProperName::ReferenceSolver;
import monet.tara.lang.*;
import monet.tara.lang.NodeAttribute;
import monet.tara.lang.NodeWord;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterAnnotator extends ::projectProperName::Annotator {
	\@Override
	public void annotate(\@NotNull PsiElement element, \@NotNull AnnotationHolder holder) {
		if (!(element instanceof Parameter)) return;
		MetaIdentifier metaIdentifier = ::projectProperName::PsiImplUtil.getContextOf(element).getMetaIdentifier();
		int index = getIndexOf((Parameters) element.getParent(), (Parameter) element);
		AbstractNode node = ::projectProperName::Language.getHeritage().getNodeNameLookUpTable().get(metaIdentifier.getText()).get(0);
		List<Variable> variables = node.getVariables();
		Variable actualVariable = variables.get(index);
		if (element.getFirstChild() instanceof ::projectProperName::IdentifierReference /*|| element.getFirstChild() instanceof ::projectProperName::IdentifierList*/) {
			processAsWordOrReference(element, holder, actualVariable);
		} else if (!areSameType(actualVariable, element)) {
			holder.createErrorAnnotation(element, "parameter missed");
		}
	}

	private void processAsWordOrReference(PsiElement element, AnnotationHolder holder, Variable actualVariable) {
		if (actualVariable instanceof NodeWord)
			processAsWord((::projectProperName::IdentifierReference) element.getFirstChild(), ((NodeWord) actualVariable));
		else if (element instanceof ::projectProperName::IdentifierReference &&
			checkReference((::projectProperName::IdentifierReference) element.getFirstChild(), (Reference) actualVariable))
			holder.createErrorAnnotation(element, "Parameter type error");
	}

	private boolean processAsWord(::projectProperName::IdentifierReference reference, NodeWord word) {
		return word.contains(getLastElementOf(reference).getText());
	}

	private boolean checkReference(::projectProperName::IdentifierReference reference, Reference variable) {
		::projectProperName::ReferenceSolver solver = new ::projectProperName::ReferenceSolver(getLastElementOf(reference), reference.getTextRange(), false);
		PsiElement resolve = solver.resolve();
		if (resolve instanceof Definition) {
			MetaIdentifier metaIdentifier = ((Definition) resolve).getMetaIdentifier();
			if ((metaIdentifier != null) && metaIdentifier.getText().equals(variable.getNode())) return true;
		}
		return false;
	}

	private boolean areSameType(Variable variable, PsiElement element) {
		if (!NodeAttribute.class.isInstance(variable)) return false;
		String varType = ((NodeAttribute) variable).getPrimitiveType();
		Types type = Types.valueOf(element.getFirstChild().getClass().getSimpleName());
		switch (type) {
			case ::projectProperName::StringValueImpl\:
				return varType.equals("String") | varType.equals("Uid");
			case ::projectProperName::BooleanValueImpl\:
				return varType.equals("Boolean");
			case ::projectProperName::NaturalValueImpl\:
				return varType.equals("Natural") || varType.equals("Integer") || varType.equals("Double");
			case ::projectProperName::IntegerValueImpl\:
				return varType.equals("Integer") || varType.equals("Double");
			case ::projectProperName::DoubleValueImpl\:
				return varType.equals("Double");
			case ::projectProperName::StringListImpl\:
				return varType.equals("String") && variable.isList();
			case ::projectProperName::BooleanListImpl\:
				return varType.equals("Boolean") && variable.isList();
			case ::projectProperName::IntegerListImpl\:
				return varType.equals("Integer") && variable.isList();
			case ::projectProperName::DoubleListImpl\:
				return varType.equals("Double") && variable.isList();
			case ::projectProperName::NaturalListImpl\:
				return varType.equals("Natural") && variable.isList();
		}
		return false;
	}

	private int getIndexOf(Parameters parent, Parameter p) {
		List<Parameter> parameters = new ArrayList<>();
		Collections.addAll(parameters, parent.getParameters());
		return parameters.indexOf(p);
	}

	private ::projectProperName::Identifier getLastElementOf(::projectProperName::IdentifierReference reference) {
		return reference.getIdentifierList().get(reference.getIdentifierList().size() - 1);
	}

	enum Types {
		::projectProperName::StringValueImpl, ::projectProperName::BooleanValueImpl, ::projectProperName::IntegerValueImpl, ::projectProperName::DoubleValueImpl, ::projectProperName::NaturalValueImpl,
		::projectProperName::StringListImpl, ::projectProperName::BooleanListImpl, ::projectProperName::IntegerListImpl, ::projectProperName::DoubleListImpl, ::projectProperName::NaturalListImpl
	}


}
