package monet.tara.intellij.structureview;

import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase;
import com.intellij.navigation.ItemPresentation;
import monet.tara.intellij.metamodel.psi.Concept;
import monet.tara.intellij.metamodel.psi.impl.TaraFileImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaraFileStructureViewElement extends PsiTreeElementBase<TaraFileImpl> {

	protected TaraFileStructureViewElement(TaraFileImpl propertiesFile) {
		super(propertiesFile);
	}

	@NotNull
	public Collection<StructureViewTreeElement> getChildrenBase() {
		List<? extends Concept> concepts = getElement().getConcepts();
		Collection<StructureViewTreeElement> elements = new ArrayList<>(concepts.size());
		for (Concept concept : concepts)
			elements.add(new TaraStructureViewElement(concept));
		return elements;
	}

	public String getPresentableText() {
		return getElement().getName();
	}

	@NotNull
	public ItemPresentation getPresentation() {
		return new ItemPresentation() {
			public String getPresentableText() {
				return TaraFileStructureViewElement.this.getPresentableText();
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