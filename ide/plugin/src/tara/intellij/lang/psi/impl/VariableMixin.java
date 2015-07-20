package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.language.model.Tag;

import java.util.*;
import java.util.stream.Collectors;

public class VariableMixin extends ASTWrapperPsiElement {

	private Set<Tag> inheritedFlags = new HashSet<>();

	public VariableMixin(@NotNull ASTNode node) {
		super(node);
	}

	public void name(String newName) {
		setName(newName);
	}

	@NotNull
	public PsiElement setName(String newName) {
		ASTNode keyNode = getNode().findChildByType(TaraTypes.IDENTIFIER);
		if (keyNode != null) {
			Variable variable = TaraElementFactoryImpl.getInstance(this.getProject()).createVariable(newName, type());
			ASTNode node = variable.getFirstChild().getChildren()[0].getNode();
			this.getNode().replaceChild(keyNode, node);
		}
		return this;
	}

	public Contract getContract() {
		TaraAttributeType attributeType = ((TaraVariable) this).getAttributeType();
		if (attributeType == null) return null;
		return attributeType.getContract();
	}

	@Nullable
	public String name() {
		ASTNode[] child = this.getNode().getChildren(TokenSet.create(TaraTypes.IDENTIFIER));
		if (child.length == 0) return null;
		return child[0].getText();
	}

	@Nullable
	public String getName() {
		return name();
	}

	@Nullable
	public String type() {
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		return type == null ? null : type.getText();
	}

	public boolean isReference() {
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		return type != null && type.getIdentifierReference() != null;
	}

	public boolean isMultiple() {
		final List<PsiElement> multiple = findChildrenByType(TaraTypes.LIST);
		return multiple != null && !multiple.isEmpty();
	}

	public int getSize() {
		final TaraCount count = ((TaraVariable) this).getCount();
		if (count == null) return isMultiple() ? 0 : 1;
		return Integer.parseInt(count.getText().substring(1, count.getTextLength() - 1));
	}

	public boolean isOverriden() {
		return TaraUtil.getOverriddenVariable((Variable) this) != null;
	}

	public List<Tag> flags() {
		List<Tag> tags = new ArrayList<>();
		tags.addAll(inheritedFlags);
		if (((TaraVariable) this).getFlags() != null)
			tags.addAll(((TaraVariable) this).getFlags().getFlagList().stream().
				map(f -> Tag.valueOf(f.getText().toUpperCase())).collect(Collectors.toList()));
		return Collections.unmodifiableList(tags);
	}

	public void addFlags(Tag... flags) {
		Collections.addAll(inheritedFlags, flags);
	}

	public String contract() {
		return getContract().getFormattedName();
	}

	public tara.language.model.NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public void container(tara.language.model.NodeContainer container) {
	}

	public void type(String type) {

	}

	public int size() {
		return 0;
	}

	public void size(int tupleSize) {
	}

	public void contract(String extension) {
	}

	public boolean isTerminal() {
		return flags().contains(Tag.TERMINAL);
	}

	public boolean isTerminalInstance() {
		return flags().contains(Tag.TERMINAL_INSTANCE);
	}

	public boolean isFinal() {
		return flags().contains(Tag.FINAL);
	}

	public boolean isPrivate() {
		return flags().contains(Tag.PRIVATE);
	}

	public boolean isInherited() {
		return false;
	}

	public List<Object> allowedValues() {
		return null;
	}

	public List<Object> defaultValues() {
		TaraValue value = ((TaraVariable) this).getValue();
		return value != null ? value.values() : Collections.emptyList();
	}


	public String defaultExtension() {
		TaraMeasureValue measureValue = ((TaraVariable) this).getMeasureValue();
		return measureValue != null ? measureValue.getText() : "";
	}

	public void defaultExtension(String defaultExtension) {
	}

	public void overriden(boolean overriden) {

	}

	public String getUID() {
		return null;
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	@Override
	public String toString() {
		return type() + " " + name();
	}
}
