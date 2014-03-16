package monet.::projectName::.intellij.metamodel.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTFactory;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.ChangeUtil;
import com.intellij.psi.impl.source.tree.TreeElement;
import com.intellij.util.IncorrectOperationException;
import monet.::projectName::.intellij.metamodel.::projectProperName::Icons;
import monet.::projectName::.intellij.metamodel.::projectProperName::Language;
import monet.::projectName::.intellij.metamodel.file.::projectProperName::FileType;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::ElementFactory;
import monet.::projectName::.intellij.metamodel.psi.::projectProperName::File;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

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

	\@Override
	public Icon getIcon(int flags) {
		return super.getIcon(flags);
	}

	public String getPresentableName(){
		return getName().substring(0, getName().lastIndexOf("."));
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
				return ::projectProperName::Icons.CONCEPT_13;
			}
		};
	}

	\@NotNull
	\@Override
	public List<Definition> getDefinitions() {
		return ::projectProperName::Util.getDefinitionsOfFile(this);
	}

	\@Override
	public Definition findDefinitionByKey(\@NotNull \@NonNls String key) {
		return ::projectProperName::Util.getDefinitionsOfFileByName(this, key).get(0);
	}

	private ASTNode getDefinitionList() {
		return null;
	}

	private void insertLineBreakBefore(final ASTNode anchorBefore) {
		getDefinitionList().addChild(ASTFactory.whitespace("\\n"), anchorBefore);
	}

	private boolean haveToAddNewLine() {
		ASTNode lastChild = getDefinitionList().getLastChildNode();
		return lastChild != null && !lastChild.getText().endsWith("\\n");
	}


	\@Override
	\@NotNull
	public PsiElement addDefinition(\@NotNull Definition definition) throws IncorrectOperationException {
		if (haveToAddNewLine()) {
			insertLineBreakBefore(null);
		}
		final TreeElement copy = ChangeUtil.copyToElement(definition.getPsiElement());
		getDefinitionList().addChild(copy);
		return copy.getPsi();
	}

	\@Override
	public Definition addDefinition(String identifier) {
		return (Definition) addDefinition(::projectProperName::ElementFactory.getInstance(getProject()).createDefinition(identifier));
	}
}