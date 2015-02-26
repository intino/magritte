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
import siani.tara.intellij.MessageProvider;
import siani.tara.intellij.annotator.TaraAnnotator;
import siani.tara.intellij.annotator.imports.CreateConceptQuickFix;
import siani.tara.intellij.annotator.imports.ImportQuickFix;
import siani.tara.intellij.annotator.imports.RemoveImportFix;
import siani.tara.intellij.annotator.imports.TaraReferenceImporter;
import siani.tara.intellij.highlighting.TaraSyntaxHighlighter;
import siani.tara.intellij.lang.TaraLanguage;
import siani.tara.intellij.lang.psi.*;
import siani.tara.intellij.lang.psi.impl.ReferenceManager;
import siani.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.lang.Node;

import java.util.ArrayList;
import java.util.List;

import static siani.tara.intellij.annotator.TaraAnnotator.AnnotateAndFix.Level.ERROR;

public class ReferenceAnalyzer extends TaraAnalyzer {

	public static final String MESSAGE = MessageProvider.message("unreached.reference");
	private final Identifier identifier;

	public ReferenceAnalyzer(Identifier identifier) {
		this.identifier = identifier;
	}

	@Override
	public void analyze() {
		PsiElement reference = ReferenceManager.resolve(identifier);
		if (reference == null
			&& !checkAsMetaWord(TaraPsiImplUtil.getConceptContainerOf(identifier), identifier.getText())
			&& !isParameterName(identifier))
			if (identifier.getParent() instanceof IdentifierReference) {
				List<? extends Identifier> identifierList = ((IdentifierReference) identifier.getParent()).getIdentifierList();
				addImportAlternatives(identifierList.get(identifierList.size() - 1));
			} else
				results.put(identifier, new TaraAnnotator.AnnotateAndFix(ERROR, MESSAGE, TaraSyntaxHighlighter.UNRESOLVED_ACCESS,
					new RemoveImportFix((TaraPsiElement) identifier.getParent())));
	}

	private boolean isParameterName(PsiElement element) {
		return element.getParent() instanceof TaraExplicitParameter;
	}

	private boolean checkAsMetaWord(Concept concept, String wordName) {
		if (concept == null) return false;
		Node node = TaraUtil.findNode(concept, TaraLanguage.getMetaModel(identifier.getContainingFile()));
		if (node == null) return false;
		siani.tara.lang.Word[] words = node.getObject().getWords();
		if (words.length == 0) return false;
		for (siani.tara.lang.Word word : words)
			if (word.contains(wordName))
				return true;
		return true;
	}

	private void addImportAlternatives(Identifier element) {
		ArrayList<LocalQuickFix> fixes = new ArrayList<>();
		addImportFix(element, fixes);
		Concept concept = TaraPsiImplUtil.getConceptContainerOf(element);
		addCreateConceptFix(element, concept != null ? concept.getType() : "Concept", fixes);
		results.put(element, new TaraAnnotator.AnnotateAndFix(ERROR, MESSAGE, TaraSyntaxHighlighter.UNRESOLVED_ACCESS, createFixes(element, fixes)));
	}

	private IntentionAction[] createFixes(Identifier element, List<LocalQuickFix> fixes) {
		List<IntentionAction> actions = new ArrayList<>();
		for (LocalQuickFix fix : fixes)
			actions.add(createIntention(element, fix.getName(), fix));
		return actions.toArray(new IntentionAction[actions.size()]);
	}

	private void addCreateConceptFix(Identifier name, String type, List<LocalQuickFix> actions) {
		actions.add(new CreateConceptQuickFix(name.getText(), type, (TaraBoxFile) name.getContainingFile()));
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
		if (!(file instanceof TaraBoxFile)) return;
		List<ImportQuickFix> importFix = TaraReferenceImporter.proposeImportFix((IdentifierReference) node.getParent());
		for (ImportQuickFix importQuickFix : importFix) actions.add(importQuickFix);
	}
}
