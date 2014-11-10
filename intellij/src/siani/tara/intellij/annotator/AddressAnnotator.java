package siani.tara.intellij.annotator;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraAddress;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;

import java.util.HashMap;
import java.util.Map;

public class AddressAnnotator extends TaraAnnotator {

	private static Map<String, String> addresses = new HashMap<>();

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (!TaraAddress.class.isInstance(element)) return;
		this.holder = holder;
		TaraAddress address = (TaraAddress) element;
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(address);
		if (concept == null) return;
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (model == null) annotateAndFix(element, new RemoveAddressFix(concept), "Address not available");
		String qualifiedName = concept.getQualifiedName();
		if (addresses.containsKey(address.getText())) {
			if (!addresses.get(address.getText()).equals(concept.getQualifiedName()))
				annotateAndFix(element, new RemoveAddressFix(concept), "Duplicated Address");
		} else addresses.put(address.getText(), qualifiedName);

	}
}
