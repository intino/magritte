package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.lang.psi.resolve.TaraReferenceSolver;
import siani.tara.intellij.project.module.ModuleProvider;
import siani.tara.lang.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterAnnotator extends TaraAnnotator {
	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!(element instanceof Parameter)) return;
		Concept concept = TaraPsiImplUtil.getContextOf(element);
		int index = getIndexOf((Parameters) element.getParent(), (Parameter) element);
		TreeWrapper tree = TaraLanguage.getHeritage(ModuleProvider.getModuleOfDocument((TaraFile) element.getContainingFile()));
		Node node;
		if (tree == null || (node = findNode(concept, tree)) == null) return;
		NodeObject object = node.getObject();
		List<Variable> variables = object.getVariables();
		if (index >= variables.size()) annotateInsufficientParameters(element, holder);
		else {
			Variable actualVariable = variables.get(index);
			if (element.getFirstChild() instanceof TaraMetaWord ||
				element.getFirstChild() instanceof TaraIdentifierReference ||
				element.getFirstChild() instanceof TaraIdentifierList)
				processAsWordOrReference(element, holder, actualVariable);
			else if (!areSameType(actualVariable, element))
				holder.createErrorAnnotation(element, "Incorrect type: " + actualVariable.getType() + " " + actualVariable.getName());
		}
	}

	private Node findNode(Concept concept, TreeWrapper tree) {
		String metaQualifiedName = TaraUtil.getMetaQualifiedName(concept);
		Node node = tree.get(metaQualifiedName);
		return (node != null) ? node : tree.get(asAnonymous(metaQualifiedName));
	}

	private String asAnonymous(String name) {
		String subpath = name.substring(0, name.lastIndexOf("."));
		return subpath + "." + "[" + name.substring(name.lastIndexOf(".") + 1) + "@annonymous]";
	}

	private String getConceptQualifiedName(MetaIdentifier metaIdentifier) {
		return TaraUtil.getMetaQualifiedName(TaraPsiImplUtil.getContextOf(metaIdentifier));
	}

	private void annotateInsufficientParameters(PsiElement element, AnnotationHolder holder) {
		holder.createErrorAnnotation(element, "parameter missed");
	}

	private void processAsWordOrReference(PsiElement element, AnnotationHolder holder, Variable actualVariable) {
		if (actualVariable instanceof NodeWord)
			processAsWord((TaraMetaWord) element.getFirstChild(), ((NodeWord) actualVariable));
		else if (element instanceof TaraIdentifierReference &&
			checkReference((TaraIdentifierReference) element.getFirstChild(), (Reference) actualVariable))
			holder.createErrorAnnotation(element, "Parameter type error");
	}

	private boolean processAsWord(TaraMetaWord metaWord, NodeWord word) {
		return word.contains(metaWord.getLastChild().getText());
	}

	private boolean checkReference(TaraIdentifierReference reference, Reference variable) {
		TaraReferenceSolver solver = new TaraReferenceSolver(getLastElementOf(reference), reference.getTextRange(), false);
		PsiElement resolve = solver.resolve();
		if (resolve instanceof Concept) {
			MetaIdentifier metaIdentifier = ((Concept) resolve).getMetaIdentifier();
			if ((metaIdentifier != null) && metaIdentifier.getText().equals(variable.getType())) return true;
		}
		return false;
	}

	private boolean areSameType(Variable variable, PsiElement element) {
		if (!NodeAttribute.class.isInstance(variable)) return false;
		String varType = variable.getType();
		Types type = Types.valueOf(element.getFirstChild().getClass().getSimpleName());
		switch (type) {
			case TaraStringValueImpl:
				return varType.equals("String") | varType.equals("Alias");
			case TaraBooleanValueImpl:
				return varType.equals("Boolean");
			case TaraNaturalValueImpl:
				return varType.equals("Natural") || varType.equals("Integer") || varType.equals("Double");
			case TaraIntegerValueImpl:
				return varType.equals("Integer") || varType.equals("Double");
			case TaraDoubleValueImpl:
				return varType.equals("Double");
			case TaraStringListImpl:
				return varType.equals("String") && variable.isMultiple();
			case TaraBooleanListImpl:
				return varType.equals("Boolean") && variable.isMultiple();
			case TaraIntegerListImpl:
				return varType.equals("Integer") && variable.isMultiple();
			case TaraDoubleListImpl:
				return varType.equals("Double") && variable.isMultiple();
			case TaraNaturalListImpl:
				return varType.equals("Natural") && variable.isMultiple();
			default:
				return false;
		}
	}

	private int getIndexOf(Parameters parent, Parameter p) {
		List<Parameter> parameters = new ArrayList<>();
		Collections.addAll(parameters, parent.getParameters());
		return parameters.indexOf(p);
	}

	private TaraIdentifier getLastElementOf(TaraIdentifierReference reference) {
		return reference.getIdentifierList().get(reference.getIdentifierList().size() - 1);
	}

	enum Types {
		TaraStringValueImpl, TaraBooleanValueImpl, TaraIntegerValueImpl, TaraDoubleValueImpl, TaraNaturalValueImpl,
		TaraStringListImpl, TaraBooleanListImpl, TaraIntegerListImpl, TaraDoubleListImpl, TaraNaturalListImpl,
		TaraEmptyImpl, TaraWord
	}


}
