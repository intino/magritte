package monet.tara.intellij.metamodel.psi;

public class TaraRecursiveElementVisitor extends TaraVisitor {

	public void visitElement(TaraPsiElement element) {
		element.acceptChildren(this);
	}
}
