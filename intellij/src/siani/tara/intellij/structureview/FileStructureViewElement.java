package siani.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;

public class FileStructureViewElement extends PsiTreeElementBase<TaraFileImpl> {

	protected FileStructureViewElement(TaraFileImpl propertiesFile) {
		super(propertiesFile);
	}

	@NotNull
	public Collection<StructureViewTreeElement> getChildrenBase() {
		Concept concept = getElement().getConcepts()[0]; //TODO
		Collection<StructureViewTreeElement> elements = new ArrayList<>(1);
		elements.add(new StructureViewElement(concept));
		return elements;
	}

	public String getPresentableText() {
		return getElement().getName();
	}

	@NotNull
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