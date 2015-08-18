package tara.intellij.annotator.semanticanalizer;

import com.intellij.openapi.module.Module;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import tara.intellij.annotator.TaraAnnotator;
import tara.intellij.annotator.fix.CreateWordClassIntention;
import tara.intellij.lang.psi.Contract;
import tara.intellij.lang.psi.TaraAttributeType;
import tara.intellij.project.facet.TaraFacet;
import tara.intellij.project.module.ModuleProvider;
import tara.language.model.Primitives;
import tara.language.model.Variable;

import static tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class WordClassCreationAnalyzer extends TaraAnalyzer {

	private final String wordsPackage;
	private final Contract contract;
	private final TaraAttributeType attribute;

	public WordClassCreationAnalyzer(TaraAttributeType contract) {
		this.contract = contract.getContract();
		this.attribute = contract;
		final String generatedDslName = TaraFacet.getTaraFacetByModule(ModuleProvider.getModuleOf(contract)).getConfiguration().getGeneratedDslName();
		wordsPackage = generatedDslName.toLowerCase() + "." + "words";
	}

	@Override
	public void analyze() {
		if (!Variable.class.isInstance(attribute.getParent()) || !Primitives.WORD.equals(((Variable) attribute.getParent()).type()) || !((Variable) attribute.getParent()).allowedValues().isEmpty())
		return;
		String wordClassName = contract.getFormattedName();
		PsiClass aClass = JavaPsiFacade.getInstance(contract.getProject()).findClass(wordsPackage + "." + wordClassName, GlobalSearchScope.moduleScope(getModule()));
		if (aClass == null) error();
	}

	private void error() {
		results.put(contract,
			new TaraAnnotator.AnnotateAndFix(ERROR, "Word Not Found. Create it.",
				new CreateWordClassIntention(contract.getFormattedName(), wordsPackage.toLowerCase())));
	}

	private Module getModule() {
		return ModuleProvider.getModuleOf(contract.getContainingFile());
	}
}
