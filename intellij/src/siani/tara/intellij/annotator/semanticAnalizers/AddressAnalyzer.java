package siani.tara.intellij.annotator.semanticAnalizers;

import siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix;
import siani.tara.intellij.annotator.fix.RemoveAddressFix;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraAddress;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.lang.Model;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class AddressAnalyzer extends TaraAnalyzer {

	TaraAddress address;

	public AddressAnalyzer(TaraAddress address) {
		this.address = address;
	}

	@Override
	public void analyze() {
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(address);
		if (concept == null) return;
		Model model = TaraLanguage.getMetaModel(concept.getFile());
		if (hasErrors = model != null)
			results.put(address, new AnnotateAndFix(ERROR, "Address not available", new RemoveAddressFix(concept)));
	}
}
