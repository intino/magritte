package siani.tara.intellij.annotator.semanticanalizer;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInspection.CommonProblemDescriptorImpl;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ex.ProblemDescriptorImpl;
import com.intellij.codeInspection.ex.QuickFixWrapper;
import com.intellij.lang.injection.InjectedLanguageManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import siani.tara.Language;
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.imports.CreateNodeQuickFix;
import siani.tara.intellij.annotator.imports.ImportQuickFix;
import siani.tara.intellij.annotator.imports.TaraReferenceImporter;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.semantic.Allow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ReferenceAnalyzer extends TaraAnalyzer {

	public static final String MESSAGE = MessageProvider.message("unreached.reference");
	private final IdentifierReference reference;

	public ReferenceAnalyzer(IdentifierReference reference) {
		this.reference = reference;
	}

	@Override
	public void analyze() {
		if (isWordValueParameter(TaraPsiImplUtil.getContainerNodeOf(reference), reference.getText())) return;
		PsiElement destiny = ReferenceManager.resolve(reference);
		if (destiny == null)
			addImportAlternatives(reference.getIdentifierList().get(reference.getIdentifierList().size() - 1));
	}

	private boolean isWordValueParameter(Node node, String wordName) {
		Parameter parameter = asParameter();
		if (node == null || wordName.contains(".") || parameter == null) return false;
		Language language = TaraLanguage.getLanguage(node.getFile());
		Collection<Allow> allows = language.allows(node.resolve().getFullType());
		if (allows == null) return false;
		for (Allow allow : allows) {
			if (allow instanceof Allow.Parameter) {
				Allow.Parameter allowParameter = (Allow.Parameter) allow;
				if (allowParameter.name().equals(parameter.getExplicitName()) || allowParameter.position() == parameter.getIndexInParent())
					return true;
			}
		}
		return false;
	}

	private Parameter asParameter() {
		PsiElement element = reference.getParent();
		while (element != null && !(element instanceof PsiFile)) {
			if (element instanceof Parameter) return (Parameter) element;
			element = element.getParent();
		}
		return null;
	}

	private void addImportAlternatives(Identifier element) {
		ArrayList<LocalQuickFix> fixes = new ArrayList<>();
		addImportFix(element, fixes);
		Node node = TaraPsiImplUtil.getContainerNodeOf(element);
		addCreateNodeFix(element, node != null ? node.getType() : "Concept", fixes);
		results.put(element, new TaraAnnotator.AnnotateAndFix(ERROR, MESSAGE, TaraSyntaxHighlighter.UNRESOLVED_ACCESS, createFixes(element, fixes)));
	}

	private IntentionAction[] createFixes(Identifier element, List<LocalQuickFix> fixes) {
		List<IntentionAction> actions = new ArrayList<>();
		for (LocalQuickFix fix : fixes)
			actions.add(createIntention(element, fix.getName(), fix));
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private void addCreateNodeFix(Identifier name, String type, List<LocalQuickFix> actions) {
		actions.add(new CreateNodeQuickFix(name.getText(), type, (TaraModel) name.getContainingFile()));
	}

	private IntentionAction createIntention(PsiElement node, String message, LocalQuickFix fix) {
		return createIntention(node, node.getTextRange(), message, fix);
	}

	private IntentionAction createIntention(PsiElement node, TextRange range, String message, LocalQuickFix fix) {
		LocalQuickFix[] quickFixes = {fix};
		CommonProblemDescriptorImpl descr = new ProblemDescriptorImpl(node, node, message,
			quickFixes, ProblemHighlightType.GENERIC_ERROR_OR_WARNING, true, range, true);
		return QuickFixWrapper.wrap((ProblemDescriptor) descr, 0);
	}

	private void addImportFix(Identifier node, List<LocalQuickFix> actions) {
		final PsiFile file = InjectedLanguageManager.getInstance(node.getProject()).getTopLevelFile(node);
		if (!(file instanceof TaraModel)) return;
		List<ImportQuickFix> importFix = TaraReferenceImporter.proposeImportFix((IdentifierReference) node.getParent());
		for (ImportQuickFix importQuickFix : importFix) actions.add(importQuickFix);
	}
}
