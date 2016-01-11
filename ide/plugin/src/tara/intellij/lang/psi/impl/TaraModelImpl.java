package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiInvalidElementAccessException;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.Language;
import tara.Resolver;
import tara.intellij.lang.TaraIcons;
import tara.intellij.lang.TaraLanguage;
import tara.intellij.lang.file.TaraFileType;
import tara.intellij.lang.psi.*;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.facet.TaraFacetConfiguration;
import tara.intellij.project.module.ModuleProvider;
import tara.lang.model.Node;
import tara.lang.model.Variable;
import tara.lang.model.rules.CompositionRule;
import tara.lang.model.rules.Size;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class TaraModelImpl extends PsiFileBase implements TaraModel {

	public TaraModelImpl(@NotNull FileViewProvider viewProvider) {
		super(viewProvider, TaraLanguage.INSTANCE);
	}

	@NotNull
	public FileType getFileType() {
		return TaraFileType.INSTANCE;
	}

	public String toString() {
		return getPresentableName();
	}

	@NotNull
	public String getPresentableName() {
		return getName().contains(".") ? getName().substring(0, getName().lastIndexOf(".")) : getName();
	}

	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				return getName().substring(0, getName().lastIndexOf("."));
			}

			public String getLocationString() {
				final PsiDirectory psiDirectory = getParent();
				if (psiDirectory != null) {
					return psiDirectory.getVirtualFile().getPresentableUrl();
				}
				return null;
			}

			public Icon getIcon(final boolean open) {
				return TaraIcons.MODEL;
			}
		};
	}

	@Nullable
	public Icon getIcon(int flags) {
		return TaraIcons.MODEL;
	}

	public Node container() {
		return null;
	}

	@Override
	public List<String> uses() {
		return getImports().stream().map(anImport -> anImport.getHeaderReference().toString()).collect(Collectors.toList());
	}

	@NotNull
	@Override
	public List<Node> components() {
		return TaraUtil.getMainNodesOfFile(this);
	}

	public Node addNode(String identifier) {
		return (Node) addNode(TaraElementFactory.getInstance(getProject()).createNode(identifier));
	}

	public Import addImport(String reference) {
		TaraImports imports = TaraElementFactory.getInstance(getProject()).createImport(reference);
		return (Import) addImport(imports);
	}

	private PsiElement addImport(TaraImports imports) {
		final TreeElement copy = ChangeUtil.copyToElement(imports);
		TaraImports psi = (TaraImports) copy.getPsi();
		return this.getImports().isEmpty() ? addTaraImport(psi) : (PsiElement) addImportToList(psi);
	}

	private TaraAnImport addTaraImport(TaraImports psi) {
		TaraAnImport anImport = ((TaraImports) this.addBefore(psi, findImportAnchor())).getAnImportList().get(0);
		anImport.add(TaraElementFactoryImpl.getInstance(psi.getProject()).createNewLine());
		return anImport;
	}

	private PsiElement findImportAnchor() {
		for (PsiElement psiElement : this.getChildren())
			if (psiElement.getNode().getElementType().equals(TaraTypes.NEWLINE)) return psiElement.getNextSibling();
		return this.getLastChild();
	}

	private Import addImportToList(TaraImports psi) {
		TaraImports[] imports = PsiTreeUtil.getChildrenOfType(this, TaraImports.class);
		if (imports == null || imports.length == 0) return null;
		return (Import) imports[0].addBefore(psi.getAnImportList().get(0), imports[0].getAnImportList().get(0));
	}

	public void updateDSL() {
		TaraFacet facet = TaraFacet.of(ModuleProvider.getModuleOf(this));
		if (facet == null) return;
		TaraFacetConfiguration configuration = facet.getConfiguration();
		String dsl = configuration.getDsl();
		setDSL(dsl == null || dsl.isEmpty() ? null : dsl);
	}

	public void updateDSL(String dsl) {
		setDSL(dsl == null || dsl.isEmpty() ? null : dsl);
	}

	private void setDSL(String dslName) {
		TaraDslDeclaration dslDeclaration = getDSLDeclaration();
		if (dslName != null && !dslName.isEmpty()) {
			TaraDslDeclaration dsl = TaraElementFactory.getInstance(getProject()).createDslDeclaration(dslName);
			final TreeElement copy = ChangeUtil.copyToElement(dsl);
			TaraDslDeclaration psi = (TaraDslDeclaration) copy.getPsi();
			if (dslDeclaration != null) dslDeclaration.replace(psi);
			else this.addBefore(psi, getFirstChild());
		}
	}

	@Nullable
	public TaraDslDeclaration getDSLDeclaration() {
		TaraDslDeclaration[] childrenOfType = PsiTreeUtil.getChildrenOfType(this, TaraDslDeclaration.class);
		return childrenOfType != null && childrenOfType.length > 0 ? childrenOfType[0] : null;
	}


	public TaraModelImpl getFile() throws PsiInvalidElementAccessException {
		return this;
	}

	public List<Variable> variables() {
		return Collections.EMPTY_LIST;
	}

	public String qualifiedName() {
		return "";
	}

	@Override
	public String qualifiedNameCleaned() {
		return "";
	}

	@Override
	public String doc() {
		return "";
	}

	public String getDSL() {
		TaraDslDeclaration dslDeclaration = getDSLDeclaration();
		if (dslDeclaration == null) return null;
		return dslDeclaration.getHeaderReference().getText();
	}

	@NotNull
	public List<Import> getImports() {
		TaraImports[] taraImports = PsiTreeUtil.getChildrenOfType(this, TaraImports.class);
		if (taraImports == null) return Collections.EMPTY_LIST;
		Import[] imports = PsiTreeUtil.getChildrenOfType(taraImports[0], TaraAnImport.class);
		return imports != null ? unmodifiableList(Arrays.asList(imports)) : Collections.EMPTY_LIST;
	}

	private void insertLineBreakBefore(final ASTNode anchorBefore) {
		getNode().addChild(ASTFactory.whitespace("\n"), anchorBefore);
	}

	private boolean haveToAddNewLine() {
		ASTNode lastChild = getNode().getLastChildNode();
		return lastChild != null && !lastChild.getText().endsWith("\n");
	}


	@NotNull
	public PsiElement addNode(@NotNull Node node) throws IncorrectOperationException {
		if (haveToAddNewLine()) insertLineBreakBefore(null);
		final TreeElement copy = ChangeUtil.copyToElement((PsiElement) node);
		getNode().addChild(copy);
		return copy.getPsi();
	}

	public String type() {
		return "";
	}

	@Override
	public CompositionRule ruleOf(Node component) {
		final List<Node> components = components();
		final TaraNode node = (TaraNode) components.get(components.indexOf(component));
		if (node.getSignature().getRuleContainerList().isEmpty()) return Size.MULTIPLE;
		final TaraRuleContainer taraRuleContainer = node.getSignature().getRuleContainerList().get(0);
		return taraRuleContainer == null ? Size.MULTIPLE : createSize(taraRuleContainer.getRule());
	}

	private CompositionRule createSize(TaraRule rule) {
		final TaraRange range = rule.getRange();
		if (!rule.isLambda() || range == null) return Size.MULTIPLE;
		return new Size(min(range), max(range));
	}

	private int min(TaraRange range) {
		final PsiElement psiElement = range.getFirstChild();
		if (psiElement.getNode().getElementType().equals(TaraTypes.STAR)) return Integer.MIN_VALUE;
		return Integer.parseInt(psiElement.getText());
	}

	private int max(TaraRange range) {
		final PsiElement psiElement = range.getLastChild();
		if (psiElement.getNode().getElementType().equals(TaraTypes.STAR)) return Integer.MAX_VALUE;
		return Integer.parseInt(psiElement.getText());
	}

	public <T extends Node> boolean contains(T node) {
		return components().contains(node);
	}


	public String simpleType() {
		return "";
	}


	public void name(String name) {
	}

	@NotNull
	public Node resolve() {
		Language language = TaraUtil.getLanguage(this.getOriginalElement());
		if (language == null) return this;
		new Resolver(language).resolve(this);
		return this;
	}

	@Override
	public String file() {
		return this.getContainingFile().getVirtualFile().getPath();
	}
}