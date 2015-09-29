package tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiClass;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.CreateMeasureClassIntention;
import tara.intellij.lang.psi.Contract;
import tara.intellij.lang.psi.TaraAttributeType;
import tara.intellij.lang.psi.resolve.ReferenceManager;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Primitives;
import tara.language.model.Variable;

import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class MetricClassCreationAnalyzer extends TaraAnalyzer {

	private static final Logger LOG = Logger.getInstance(MetricClassCreationAnalyzer.class.getName());

	private final String metricsPackage;
	private final Contract contract;
	private final TaraAttributeType attribute;

	public MetricClassCreationAnalyzer(TaraAttributeType contract) {
		this.contract = contract.getContract();
		this.attribute = contract;
		final TaraFacet facet = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(contract));
		if (facet != null) {
			final String generatedDslName = facet.getConfiguration().getGeneratedDslName();
			metricsPackage = generatedDslName.toLowerCase() + "." + "metrics";
		} else metricsPackage = "";
	}

	@Override
	public void analyze() {
		if (!Variable.class.isInstance(attribute.getParent()) || !Primitives.MEASURE.equals(((Variable) attribute.getParent()).type()))
			return;
		PsiClass psiClass = (PsiClass) ReferenceManager.resolveContract(contract);
		if (psiClass == null) error();
	}

	private void error() {
		results.put(contract, new TaraAnnotator.AnnotateAndFix(ERROR, "Metric Not Found. Create it.", new CreateMeasureClassIntention(contract.getFormattedName(), metricsPackage.toLowerCase())));
	}
}
