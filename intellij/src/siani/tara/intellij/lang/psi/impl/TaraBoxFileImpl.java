package siani.tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.project.module.ModuleConfiguration;
import siani.tara.intellij.project.module.ModuleProvider;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class TaraBoxFileImpl extends PsiFileBase implements TaraBoxFile {

	public TaraBoxFileImpl(@NotNull FileViewProvider viewProvider) {
		super(viewProvider, TaraLanguage.INSTANCE);
	}

	@NotNull
	@Override
	public FileType getFileType() {
		return TaraFileType.INSTANCE;
	}

	@Override
	public String toString() {
		return "Tara File";
	}

	@NotNull
	public String getPresentableName() {
		return getName().substring(0, getName().lastIndexOf("."));
	}

	@Override
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			@Override
			public String getPresentableText() {
				return getName().substring(0, getName().lastIndexOf("."));
			}

			@Override
			public String getLocationString() {
				final PsiDirectory psiDirectory = getParent();
				if (psiDirectory != null) {
					return psiDirectory.getVirtualFile().getPresentableUrl();
				}
				return null;
			}

			@Override
			public Icon getIcon(final boolean open) {
				return TaraIcons.getIcon(TaraIcons.MODEL);
			}
		};
	}

	@Nullable
	@Override
	public Icon getIcon(int flags) {
		return TaraIcons.getIcon(TaraIcons.MODEL);
	}

	@NotNull
	@Override
	public Collection<Concept> getConcepts() {
		return TaraUtil.getRootConceptsOfFile(this);
	}


	@Override
	public String getDSL() {
		TaraDslDeclaration dslDeclaration = getDSLDeclaration();
		if (dslDeclaration == null) return null;
		return dslDeclaration.getHeaderReference().getText();
	}

	@Override
	@NotNull
	public Collection<Import> getImports() {
		TaraImports[] taraImports = PsiTreeUtil.getChildrenOfType(this, TaraImports.class);
		if (taraImports == null) return Collections.EMPTY_LIST;
		Import[] imports = PsiTreeUtil.getChildrenOfType(taraImports[0], Import.class);
		return imports != null ? Arrays.asList(imports) : Collections.EMPTY_LIST;
	}

	private void insertLineBreakBefore(final ASTNode anchorBefore) {
		getNode().addChild(ASTFactory.whitespace("\n"), anchorBefore);
	}

	private boolean haveToAddNewLine() {
		ASTNode lastChild = getNode().getLastChildNode();
		return lastChild != null && !lastChild.getText().endsWith("\n");
	}


	@Override
	@NotNull
	public PsiElement addConcept(@NotNull Concept concept) throws IncorrectOperationException {
		if (haveToAddNewLine()) insertLineBreakBefore(null);
		final TreeElement copy = ChangeUtil.copyToElement(concept.getPsiElement());
		getNode().addChild(copy);
		return copy.getPsi();
	}

	@Override
	public Concept addConcept(String identifier) {
		return (Concept) addConcept(TaraElementFactory.getInstance(getProject()).createConcept(identifier));
	}

	@Override
	public Import addImport(String reference) {
		TaraImports imports = TaraElementFactory.getInstance(getProject()).createImport(reference);
		return (Import) addImport(imports);
	}

	private PsiElement addImport(TaraImports imports) {
		final TreeElement copy = ChangeUtil.copyToElement(imports);
		TaraImports psi = (TaraImports) copy.getPsi();
		return this.getImports().isEmpty() ? addTaraImport(psi) : addImportToList(psi);
	}

	private TaraAnImport addTaraImport(TaraImports psi) {
		TaraAnImport anImport = ((TaraImports) this.addBefore(psi, findImportAnchor())).getAnImportList().get(0);
		anImport.add(TaraElementFactoryImpl.getInstance(psi.getProject()).createNewLine());
		return anImport;
	}

	private PsiElement findImportAnchor() {
		Iterator<Concept> iterator = this.getConcepts().iterator();
		if (iterator.hasNext()) return iterator.next();
		return this.getFirstChild();
	}

	private Import addImportToList(TaraImports psi) {
		TaraImports[] imports = PsiTreeUtil.getChildrenOfType(this, TaraImports.class);
		if (imports == null || imports.length == 0) return null;
		return (Import) imports[0].addBefore(psi.getAnImportList().get(0), imports[0].getAnImportList().get(0));
	}

	public void updateDSL() {
		ModuleConfiguration configuration = ModuleConfiguration.getInstance(ModuleProvider.getModuleOf(this));
		if (configuration == null) return;
		String metaLanguage = configuration.getMetamodelName();
		String metamodelName = metaLanguage == null || metaLanguage.isEmpty() ? null : metaLanguage;
		setDSL(metamodelName);
	}

	private void setDSL(String metamodelName) {
		TaraDslDeclaration dslDeclaration = getDSLDeclaration();
		if (dslDeclaration != null) dslDeclaration.delete();
		if (metamodelName != null && !metamodelName.isEmpty()) {
			TaraDslDeclaration dsl = TaraElementFactory.getInstance(getProject()).createDslDeclaration(metamodelName);
			final TreeElement copy = ChangeUtil.copyToElement(dsl);
			TaraDslDeclaration psi = (TaraDslDeclaration) copy.getPsi();
			this.addAfter(psi, getFirstChild());
		}
	}

	@Nullable
	public TaraDslDeclaration getDSLDeclaration() {
		TaraDslDeclaration[] childrenOfType = PsiTreeUtil.getChildrenOfType(this, TaraDslDeclaration.class);
		return childrenOfType != null && childrenOfType.length > 0 ? childrenOfType[0] : null;
	}
}