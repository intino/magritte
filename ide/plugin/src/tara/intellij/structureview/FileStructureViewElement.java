package tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import tara.intellij.lang.psi.impl.TaraModelImpl;
import tara.lang.model.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileStructureViewElement extends PsiTreeElementBase<TaraModelImpl> {

	protected FileStructureViewElement(TaraModelImpl taraModel) {
		super(taraModel);
	}

	@NotNull
	public Collection<StructureViewTreeElement> getChildrenBase() {
		if (getElement() == null) return Collections.EMPTY_LIST;
		List<Node> nodes = getElement().components();
		List<StructureViewTreeElement> elements = new ArrayList<>(1);
		elements.addAll(nodes.stream().map(StructureViewElement::new).collect(Collectors.toList()));
		return elements;
	}

	public String getPresentableText() {
		return getElement().getPresentableName();
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