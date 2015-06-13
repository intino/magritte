package siani.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Node;
import siani.tara.intellij.lang.psi.impl.TaraModelImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileStructureViewElement extends PsiTreeElementBase<TaraModelImpl> {

	protected FileStructureViewElement(TaraModelImpl propertiesFile) {
		super(propertiesFile);
	}

	@NotNull
	public Collection<StructureViewTreeElement> getChildrenBase() {
		if (getElement() == null) return Collections.EMPTY_LIST;
		Collection<Node> nodes = getElement().getIncludes();
		Collection<StructureViewTreeElement> elements = new ArrayList<>(1);
		for (Node node : nodes) elements.add(new StructureViewElement(node));
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