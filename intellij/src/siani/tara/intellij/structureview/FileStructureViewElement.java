package siani.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileStructureViewElement extends PsiTreeElementBase<TaraBoxFileImpl> {

	protected FileStructureViewElement(TaraBoxFileImpl propertiesFile) {
		super(propertiesFile);
	}

	@NotNull
	public Collection<StructureViewTreeElement> getChildrenBase() {
		if (getElement() == null) return Collections.EMPTY_LIST;
		Concept[] concepts = getElement().getConcepts();
		Collection<StructureViewTreeElement> elements = new ArrayList<>(1);
		for (Concept concept : concepts) elements.add(new StructureViewElement(concept));
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