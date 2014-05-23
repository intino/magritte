package monet.::projectName::.intellij.lang.psi.impl;

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
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import monet.::projectName::.intellij.lang.file.::projectProperName::FileType;
import monet.::projectName::.intellij.lang.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class ::projectProperName::FileImpl extends PsiFileBase implements ::projectProperName::File {

	public ::projectProperName::FileImpl(\@NotNull com.intellij.psi.FileViewProvider viewProvider) {
		super(viewProvider, ::projectProperName::Language.INSTANCE);
	}

	\@NotNull
	\@Override
	public FileType getFileType() {
		return ::projectProperName::FileType.INSTANCE;
	}

	\@Override
	public String toString() {
		return "::projectProperName:: File";
	}

	public String getPresentableName() {
		return getName().split("\\\\.")[0];
	}

	\@Override
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			\@Override
			public String getPresentableText() {
				return getName().substring(0, getName().lastIndexOf("."));
			}

			\@Override
			public String getLocationString() {
				final PsiDirectory psiDirectory = getParent();
				if (psiDirectory != null) {
					return psiDirectory.getVirtualFile().getPresentableUrl();
				}
				return null;
			}

			\@Override
			public Icon getIcon(final boolean open) {
				return ::projectProperName::Icons.getIcon(::projectProperName::Icons.DEFINITION);
			}
		};
	}

	\@Nullable
	\@Override
	public Icon getIcon(int flags) {
		MetaIdentifier type = this.getDefinition().getSignature().getType();
		if (type != null) {
			Icon icon = ::projectProperName::Icons.getIcon(type.getText().toUpperCase());
			if (icon != null) return icon;
		}
		return ::projectProperName::Icons.getIcon(::projectProperName::Icons.DEFINITION);
	}

	\@Override
	public Definition getDefinition() {
		return ::projectProperName::Util.getRootDefinitionOfFile(this);
	}

	\@Override
	public ::projectProperName::Packet getPackage() {
		::projectProperName::Header[] header = PsiTreeUtil.getChildrenOfType(this, ::projectProperName::Header.class);
		if (header == null) return null;
		::projectProperName::Packet[] packet = PsiTreeUtil.getChildrenOfType(header[0], ::projectProperName::Packet.class);
		return packet != null ? packet[0] \: null;
	}

	\@Override
	public List<? extends Identifier> getPackageRoute() {
		return getPackage().getHeaderReference().getIdentifierList();
	}


	\@Override
	public Import[] getImports() {
		::projectProperName::Header[] header = PsiTreeUtil.getChildrenOfType(this, ::projectProperName::Header.class);
		return header != null ? PsiTreeUtil.getChildrenOfType(header[0], Import.class) \: null;
	}

	private void insertLineBreakBefore(final ASTNode anchorBefore) {
		getNode().addChild(ASTFactory.whitespace("\\n"), anchorBefore);
	}

	private boolean haveToAddNewLine() {
		ASTNode lastChild = getNode().getLastChildNode();
		return lastChild != null && !lastChild.getText().endsWith("\\n");
	}


	\@Override
	\@NotNull
	public PsiElement addDefinition(\@NotNull Definition definition) throws IncorrectOperationException {
		if (haveToAddNewLine()) insertLineBreakBefore(null);
		final TreeElement copy = ChangeUtil.copyToElement(definition.getPsiElement());
		getNode().addChild(copy);
		return copy.getPsi();
	}

	\@Override
	public Definition addDefinition(String identifier) {
		return (Definition) addDefinition(::projectProperName::ElementFactory.getInstance(getProject()).createDefinition(identifier));
	}

	\@Override
	public Import addImport(String reference) {
		Import anImport = ::projectProperName::ElementFactory.getInstance(getProject()).createImport(reference);
		return (Import) addImport(anImport);
	}

	private PsiElement addImport(Import anImport) {
		final TreeElement copy = ChangeUtil.copyToElement(anImport);
		PsiTreeUtil.getChildrenOfType(this, ::projectProperName::Header.class)[0].add(copy.getPsi());
		return copy.getPsi();
	}
}