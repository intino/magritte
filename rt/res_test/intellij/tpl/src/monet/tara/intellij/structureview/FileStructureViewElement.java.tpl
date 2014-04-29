package monet.::projectName::.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import monet.::projectName::.intellij.metamodel.psi.Definition;
import monet.::projectName::.intellij.metamodel.psi.impl.::projectProperName::FileImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class FileStructureViewElement extends PsiTreeElementBase<::projectProperName::FileImpl> {

	protected FileStructureViewElement(::projectProperName::FileImpl propertiesFile) {
		super(propertiesFile);
	}

	\@NotNull
	public Collection<StructureViewTreeElement> getChildrenBase() {
		Definition definition = getElement().getDefinition();
		Collection<StructureViewTreeElement> elements = new ArrayList<>(1);
		elements.add(new StructureViewElement(definition));
		return elements;
	}

	public String getPresentableText() {
		return getElement().getName();
	}

	\@NotNull
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				return FileStructureViewElement.this.getPresentableText();
			}

			public String getLocationString() {
				return null;
			}

			public Icon getIcon(boolean open) {
				return getElement().getIcon(0);
			}
		};
	}
}