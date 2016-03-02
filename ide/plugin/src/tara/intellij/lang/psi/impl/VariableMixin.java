package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiEnumConstant;
import com.intellij.psi.PsiField;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.codeinsight.languageinjection.helpers.Format;
import tara.intellij.lang.psi.Rule;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.Valued;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.lang.model.*;
import tara.lang.model.rules.Size;

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
			ASTNode node = variable.getIdentifier().getNode().copyElement();
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
		return this.getRule() != null ? RuleFactory.createRule((TaraVariable) this) : null;
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
		return size().max() > 1;
	}

	public void size(Size size) {
	}

	public Size size() {
		final TaraSizeRange sizeRange = ((TaraVariable) this).getSizeRange();
		if (sizeRange == null) return new Size(1, 1);
		if (sizeRange.getSize() == null) return new Size(1, Integer.MAX_VALUE);
		return parseRange(sizeRange.getSize());
	}

	private Size parseRange(TaraSize size) {
		final TaraListRange range = size.getListRange();
		if (range != null)
			return new Size(Integer.parseInt(range.getChildren()[0].getText()), Integer.parseInt(range.getChildren()[range.getChildren().length - 1].getText()));
		final int minMax = Integer.parseInt(size.getText());
		return new Size(minMax, minMax);
	}

	public boolean isOverriden() {
		return TaraUtil.getOverriddenVariable((Variable) this) != null;
	}

	public List<Tag> flags() {
		List<Tag> tags = new ArrayList<>();
		tags.addAll(inheritedFlags);
		if (((TaraVariable) this).getFlags() != null)
			tags.addAll(((TaraVariable) this).getFlags().getFlagList().stream().
					map(f -> Tag.valueOf(Format.firstUpperCase().format(f.getText()).toString())).collect(Collectors.toList()));
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

	public String qualifiedNameCleaned() {
		return container().qualifiedNameCleaned() + "$" + name();
	}

	public boolean isTerminal() {
		return flags().contains(Tag.Terminal);
	}

	public boolean isTerminalInstance() {
		return flags().contains(Tag.Instance);
	}

	public boolean isFinal() {
		return flags().contains(Tag.Final);
	}

	public boolean isPrivate() {
		return flags().contains(Tag.Private);
	}

	public boolean isInherited() {
		return false;
	}

	public List<Object> values() {
		Value value = ((Valued) this).getValue();
		Value bodyValue = ((TaraVariable) this).getBodyValue();
		if (value == null && bodyValue == null) return Collections.emptyList();
		else if (bodyValue != null) return Value.makeUp(bodyValue.values(), type(), this);
		else return Value.makeUp(value.values(), type(), this);
	}

	public String defaultMetric() {
		TaraMetric metric = ((TaraVariable) this).getValue().getMetric();
		return metric != null ? metric.getText() : "";
	}

	public void defaultMetric(String defaultExtension) {
	}

	public void overriden(boolean overriden) {

	}

	public String getUID() {
		return null;
	}

	public String anchor() {
		final TaraAnchor anchor = ((TaraVariable) this).getAnchor();
		return anchor != null ? anchor.getText() : "";
	}

	public void anchor(String anchor) {
		final TaraAnchor psiAnchor = TaraElementFactory.getInstance(getProject()).createAnchor(anchor);
		PsiElement psi = ChangeUtil.copyToElement(psiAnchor).getPsi();
		this.getNode().addChild(ASTFactory.whitespace(" "));
		this.add(psi);
	}

	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}

	@Override
	public String toString() {
		return type() + " " + name();
	}
}
