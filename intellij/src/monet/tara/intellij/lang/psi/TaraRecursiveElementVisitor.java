package monet.tara.intellij.lang.psi;

public class TaraRecursiveElementVisitor extends TaraVisitor {

	public void visitElement(TaraPsiElement element) {
		element.acceptChildren(this);
	}
}
