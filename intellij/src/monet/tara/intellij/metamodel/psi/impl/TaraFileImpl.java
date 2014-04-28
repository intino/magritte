package monet.tara.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import monet.tara.intellij.metamodel.TaraIcons;
import monet.tara.intellij.metamodel.TaraLanguage;
import monet.tara.intellij.metamodel.file.TaraFileType;
import monet.tara.intellij.metamodel.psi.*;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TaraFileImpl extends PsiFileBase implements TaraFile {

	public TaraFileImpl(@NotNull com.intellij.psi.FileViewProvider viewProvider) {
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
				return TaraIcons.CONCEPT_13;
			}
		};
	}

	@NotNull
	@Override
	public Concept getConcept() {
		return TaraUtil.getRootConceptOfFile(this);
	}

	@Override
	public TaraPacket getPackage() {
		return PsiTreeUtil.getChildrenOfType(PsiTreeUtil.getChildrenOfType(this, TaraHeader.class)[0], TaraPacket.class)[0];
	}

	@Override
	public Import[] getImports() {
		return PsiTreeUtil.getChildrenOfType(PsiTreeUtil.getChildrenOfType(this, TaraHeader.class)[0], Import.class);
	}


	@Override
	public Concept findConceptByKey(@NotNull @NonNls String key) {
		return TaraUtil.getConceptsOfFileByName(this, key).get(0);
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
		Import anImport = TaraElementFactory.getInstance(getProject()).createImport(reference);
		return (Import) addImport(anImport);
	}

	private PsiElement addImport(Import anImport) {
		final TreeElement copy = ChangeUtil.copyToElement(anImport);
		PsiTreeUtil.getChildrenOfType(this, TaraHeader.class)[0].add(copy.getPsi());
		return copy.getPsi();
	}
}