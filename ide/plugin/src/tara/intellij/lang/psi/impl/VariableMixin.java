package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.module.Module;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Node;
import tara.language.model.Primitive;
import tara.language.model.Tag;
import tara.language.model.Variable;

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
			TaraVariable variable = TaraElementFactoryImpl.getInstance(this.getProject()).createVariable(newName, type());
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
	public Primitive type() {
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		return type == null ? null : Primitive.value(type.getText());
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
		final Contract contract = getContract();
		if (contract == null) return "";
//		if (!Primitives.MEASURE.equals(type())) return contract.getFormattedName();TODO
		PsiClass psiClass = (PsiClass) ReferenceManager.resolveContract(contract);
		if (psiClass == null) return contract.getFormattedName();
		return contract.getFormattedName() + "[" + extractFields(psiClass) + "]";

	}

	private String extractFields(PsiClass psiClass) {
		String fields = "";
		for (PsiField psiField : psiClass.getFields())
			if (psiField instanceof PsiEnumConstant) fields += ", " + psiField.getNameIdentifier().getText();
		return fields.isEmpty() ? "" : fields.substring(2);
	}

	public tara.language.model.NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public void container(tara.language.model.NodeContainer container) {
	}

	public Node destinyOfReference() {
		if (!isReference()) return null;
		TaraVariableType type = ((TaraVariable) this).getVariableType();
		if (type == null || type.getIdentifierReference() == null) return null;
		return ReferenceManager.resolveToNode(type.getIdentifierReference());
	}

	public void type(Primitive type) {
	}

	public int size() {
		return 0;
	}

	public void size(int tupleSize) {
	}

	public void contract(String contract) {
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
		if (!Primitive.WORD.equals(type())) return Collections.emptyList();
		Contract contract = getContract();
		if (contract == null || contract.getNode().getChildren(TokenSet.create(TaraTypes.LEFT_SQUARE)).length == 0)
			return findInWordClass();
		return contract.getIdentifierList().stream().map(TaraIdentifier::getText).collect(Collectors.toList());
	}

	private List<Object> findInWordClass() {
		List<Object> values = new ArrayList<>();
		Module module = ModuleProvider.getModuleOf(this);
		TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
		if (facet == null) return values;
		String wordClassName = facet.getConfiguration().getGeneratedDslName().toLowerCase() + ".words." + contract();
		PsiClass aClass = JavaPsiFacade.getInstance(this.getProject()).findClass(wordClassName, GlobalSearchScope.moduleScope(module));
		if (aClass == null) return values;
		for (PsiField field : aClass.getAllFields()) {
			if (field instanceof PsiEnumConstant)
				values.add(field.getNameIdentifier().getText());
		}

		return values;
	}

	public List<Object> defaultValues() {
		TaraValue value = ((TaraVariable) this).getValue();

		return value != null ? format(value.values()) : Collections.emptyList();
	}

	public List<Object> format(List<Object> values) {
		List<Object> objects = new ArrayList<>();
		for (Object v : values) {
			if (v instanceof Node && Primitive.WORD.equals(type())) objects.add(((Node) v).name());
			else objects.add(v);
		}
		return objects;
	}


	public String defaultExtension() {
		TaraMeasureValue measureValue = ((TaraVariable) this).getValue().getMeasureValue();
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
