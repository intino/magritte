package monet.::projectName::.intellij.metamodel.psi;

public class ::projectProperName::RecursiveElementVisitor extends ::projectProperName::Visitor {

	public void visitElement(::projectProperName::PsiElement element) {
		element.acceptChildren(this);
	}
}
