package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.module.Module;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.*;

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

	public Rule getRule() {
		TaraRuleContainer attributeType = ((TaraVariable) this).getRuleContainer();
		if (attributeType == null) return null;
		return attributeType.getRule();
	}

	public tara.lang.model.Rule rule() {
		final TaraRuleContainer container = ((TaraVariable) this).getRuleContainer();
		return container != null ? container.getRule() : null;
	}

	public void rule(tara.lang.model.Rule rule) {
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

	private String extractFields(PsiClass psiClass) {
		String fields = "";
		for (PsiField psiField : psiClass.getFields())
			if (psiField instanceof PsiEnumConstant) fields += ", " + psiField.getNameIdentifier().getText();
		return fields.isEmpty() ? "" : fields.substring(2);
	}

	public NodeContainer container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public void container(NodeContainer container) {
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

	private List<Object> findInWordClass() {
		List<Object> values = new ArrayList<>();
		Module module = ModuleProvider.getModuleOf(this);
		TaraFacet facet = TaraFacet.getTaraFacetByModule(module);
		if (facet == null) return values;
		String wordClassName = facet.getConfiguration().getGeneratedDslName().toLowerCase() + ".words." + rule();
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
		TaraMetric metric = ((TaraVariable) this).getValue().getMetric();
		return metric != null ? metric.getText() : "";
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
