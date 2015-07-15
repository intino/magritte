// This is a generated file. Not intended for manual editing.
package tara.intellij.lang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.TaraIdentifierReference;
import tara.intellij.lang.psi.TaraNodeReference;
import tara.intellij.lang.psi.TaraTags;
import tara.intellij.lang.psi.TaraVisitor;
import tara.language.model.Node;
import tara.language.model.NodeContainer;
import tara.language.model.Parameter;

import java.util.List;

public class TaraNodeReferenceImpl extends NodeReferenceMixin implements TaraNodeReference {

	public TaraNodeReferenceImpl(ASTNode node) {
		super(node);
	}

	public void accept(@NotNull PsiElementVisitor visitor) {
		if (visitor instanceof TaraVisitor) ((TaraVisitor) visitor).visitNodeReference(this);
		else super.accept(visitor);
	}

	@Override
	@NotNull
	public TaraIdentifierReference getIdentifierReference() {
		return findNotNullChildByClass(TaraIdentifierReference.class);
	}

	@Override
	@Nullable
	public TaraTags getTags() {
		return findChildByClass(TaraTags.class);
	}

	@Override
	public String name() {
		return null;
	}

	@Override
	public void name(String name) {

	}

	@Override
	public boolean isMain() {
		return false;
	}

	@Override
	public List<? extends Node> subs() {
		return null;
	}

	@Override
	public <T extends Node> boolean contains(T node) {
		return false;
	}

	@Override
	public <T extends Node> boolean remove(T node) {
		return false;
	}

	@Override
	public <T extends NodeContainer> void container(T container) {

	}

	@Override
	public String qualifiedName() {
		return null;
	}

	@Override
	public String doc() {
		return null;
	}

	@Override
	public void addDoc(String doc) {

	}

	@Override
	public List<String> types() {
		return null;
	}



	@Override
	public List<? extends Parameter> parameters() {
		return null;
	}

}
