package siani.tara.intellij.lang.psi.impl;

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
import siani.tara.intellij.lang.TaraIcons;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.file.TaraFileType;
import siani.tara.intellij.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

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
		return getName().split("\\.")[0];
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
				return TaraIcons.getIcon(TaraIcons.CONCEPT);
			}
		};
	}

	@Nullable
	@Override
	public Icon getIcon(int flags) {
		if (getConcept() != null) {
			MetaIdentifier type = this.getConcept().getSignature().getType();
			if (type != null) {
				Icon icon = TaraIcons.getIcon(type.getText().toUpperCase());
				if (icon != null) return icon;
			}
		}
		return TaraIcons.getIcon(TaraIcons.CONCEPT);
	}

	@Override
	public Concept getConcept() {
		return TaraUtil.getRootConceptOfFile(this);
	}

	@Override
	public TaraPacket getPackage() {
		TaraHeader[] header = PsiTreeUtil.getChildrenOfType(this, TaraHeader.class);
		if (header == null) return null;
		TaraPacket[] packet = PsiTreeUtil.getChildrenOfType(header[0], TaraPacket.class);
		return packet != null ? packet[0] : null;
	}

	@Override
	public void setPackage(String path) {
		TaraElementFactory factory = TaraElementFactory.getInstance(this.getProject());
		PsiElement replace = this.getPackage().replace(factory.createPackage(path));
//		if (getImports() != null)
//			if (!replace.getNextSibling().getFirstChild().getNextSibling().getNode().getElementType().equals(TaraTypes.NEWLINE))
//				replace.getNextSibling().addAfter(factory.createNewLine(), replace.getNextSibling());
	}

	@Override
	public List<? extends Identifier> getPackageRoute() {
		return getPackage().getHeaderReference().getIdentifierList();
	}

	@Override
	public Import[] getImports() {
		TaraHeader[] header = PsiTreeUtil.getChildrenOfType(this, TaraHeader.class);
		return header != null ? PsiTreeUtil.getChildrenOfType(header[0], Import.class) : null;
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
		PsiElement psi = copy.getPsi();
		PsiTreeUtil.getChildrenOfType(this, TaraHeader.class)[0].add(psi);
		TaraPacket aPackage = getPackage();
//		if (!aPackage.getNextSibling().getFirstChild().getNextSibling().getNode().getElementType().equals(TaraTypes.NEWLINE))
//			aPackage.getNextSibling().addAfter(TaraElementFactory.getInstance(this.getProject()).createNewLine(), aPackage.getNextSibling());
		return psi;
	}
}