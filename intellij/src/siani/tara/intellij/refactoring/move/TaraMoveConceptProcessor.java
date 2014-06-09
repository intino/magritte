package siani.tara.intellij.refactoring.move;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.refactoring.BaseRefactoringProcessor;
import com.intellij.refactoring.ui.UsageViewDescriptorAdapter;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.usageView.UsageInfo;
import com.intellij.usageView.UsageViewDescriptor;
import com.intellij.util.IncorrectOperationException;
import siani.tara.intellij.TaraBundle;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraFile;
import siani.tara.intellij.lang.psi.impl.TaraUtil;
import siani.tara.intellij.refactoring.TaraRefactoringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TaraMoveConceptProcessor extends BaseRefactoringProcessor {
	public static final String REFACTORING_NAME = TaraBundle.message("refactoring.move.concept");

	private PsiNamedElement[] myElements;
	private String myDestination;

	public TaraMoveConceptProcessor(Project project, PsiNamedElement[] elements, String destination, boolean previewUsages) {
		super(project);
		assert elements.length > 0;
		myElements = elements;
		myDestination = destination;
		setPreviewUsages(previewUsages);
	}

	private static void moveElement(@NotNull PsiNamedElement element, @NotNull Collection<UsageInfo> usages, @NotNull TaraFile destination) {
		final PsiFile file = element.getContainingFile();
//		TaraConceptRefactoringUtil.rememberNamedReferences(element);
		final PsiNamedElement newElement = addToFile(element, destination, usages);
		for (UsageInfo usage : usages) {
			final PsiElement usageElement = usage.getElement();
			if (usageElement != null) {
				updateUsage(usageElement, element, newElement);
			}
		}
//		TaraConceptRefactoringUtil.restoreNamedReferences(newElement, element);
		// TODO: Remove extra empty lines after the removed element
		element.delete();
		if (file != null) optimizeImports(file);
	}

	private static PsiNamedElement addToFile(@NotNull PsiNamedElement element, @NotNull final TaraFile destination, @NotNull Collection<UsageInfo> usages) {
		List<PsiElement> topLevelAtDestination = new ArrayList<PsiElement>();
		for (UsageInfo usage : usages) {
			final PsiElement e = usage.getElement();
//			if (e != null && ScopeUtil.getScopeOwner(e) == destination && getImportStatementByElement(e) == null) {
//				PsiElement topLevel = PsiTreeUtil.findFirstParent(e, new Condition<PsiElement>() {
//					public boolean value(PsiElement element) {
//						return element.getParent() == destination;
//					}
//				});
//				if (topLevel != null) {
//					topLevelAtDestination.add(topLevel);
//				}
//			}
		}
		if (topLevelAtDestination.isEmpty()) {
			return (PsiNamedElement) (destination.add(element));
		} else {
			Collections.sort(topLevelAtDestination, new Comparator<PsiElement>() {
				@Override
				public int compare(PsiElement e1, PsiElement e2) {
					return PsiUtilCore.compareElementsByPosition(e1, e2);
				}

				;
			});
			final PsiElement firstUsage = topLevelAtDestination.get(0);
			return (PsiNamedElement) destination.addBefore(element, firstUsage);
		}
	}

	private static void updateUsage(@NotNull PsiElement usage, @NotNull PsiNamedElement oldElement, @NotNull PsiNamedElement newElement) {
		// TODO: Respect the qualified import style
//		if (usage instanceof TaraQualifiedExpression) {
//			TaraQualifiedExpression expr = (TaraQualifiedExpression) usage;
//			if (oldElement instanceof TaraClass && TaraNames.INIT.equals(expr.getName())) {
//				return;
//			}
//			if (expr.isQualified()) {
//				final TaraElementGenerator generator = TaraElementGenerator.getInstance(expr.getProject());
//				final TaraExpression generated = generator.createExpressionFromText(LanguageLevel.forElement(expr), expr.getName());
//				final PsiElement newExpr = expr.replace(generated);
//				TaraClassRefactoringUtil.insertImport(newExpr, newElement, null, true);
//			}
//		}
//		if (usage instanceof TaraStringLiteralExpression) {
//			for (PsiReference ref : usage.getReferences()) {
//				if (ref.isReferenceTo(oldElement)) {
//					ref.bindToElement(newElement);
//				}
//			}
//		} else {
//			final TaraImportStatementBase importStmt = getImportStatementByElement(usage);
//			if (importStmt != null) {
//				TaraClassRefactoringUtil.updateImportOfElement(importStmt, newElement);
//			}
//			final PsiFile usageFile = usage.getContainingFile();
//			if (usageFile == oldElement.getContainingFile() && !PsiTreeUtil.isAncestor(oldElement, usage, false)) {
//				TaraClassRefactoringUtil.insertImport(oldElement, newElement);
//			}
//			if (resolvesToLocalStarImport(usage)) {
//				TaraClassRefactoringUtil.insertImport(usage, newElement);
//				if (usageFile != null) {
//					optimizeImports(usageFile);
//				}
//			}
//		}
	}

	private static void optimizeImports(@NotNull PsiFile file) {
//		new TaraImportOptimizer().processFile(file).run();
	}

	private static boolean resolvesToLocalStarImport(@NotNull PsiElement element) {
		final PsiReference ref = element.getReference();
		final List<PsiElement> resolvedElements = new ArrayList<>();
		if (ref instanceof PsiPolyVariantReference) {
			for (ResolveResult result : ((PsiPolyVariantReference) ref).multiResolve(false)) {
				resolvedElements.add(result.getElement());
			}
		} else if (ref != null) {
			resolvedElements.add(ref.resolve());
		}
		final PsiFile containingFile = element.getContainingFile();
		if (containingFile != null) {
//			for (PsiElement resolved : resolvedElements) {
//				if (resolved instanceof TaraStarImportElement && resolved.getContainingFile() == containingFile) {
//					return true;
//				}
//			}
		}
		return false;
	}

	private static void checkValidImportableFile(PsiElement anchor, VirtualFile file) {
//		final QualifiedName qName = QualifiedNameFinder.findShortestImportableQName(anchor, file);
//		if (!TaraConceptRefactoringUtil.isValidQualifiedName(qName)) {
//			throw new IncorrectOperationException(TaraBundle.message("refactoring.move.concept.error.cannot.use.module.name.$0", qName));
//		}
	}

	@NotNull
	@Override
	protected UsageViewDescriptor createUsageViewDescriptor(final UsageInfo[] usages) {
		return new UsageViewDescriptorAdapter() {
			@NotNull
			@Override
			public PsiElement[] getElements() {
				return myElements;
			}

			@Override
			public String getProcessedElementsHeader() {
				return REFACTORING_NAME;
			}
		};
	}

	@NotNull
	@Override
	protected UsageInfo[] findUsages() {
		final List<UsageInfo> usages = new ArrayList<>();
		for (PsiNamedElement element : myElements) {
			usages.addAll(TaraRefactoringUtil.findUsages(element, false));
		}
		return usages.toArray(new UsageInfo[usages.size()]);
	}

	@Override
	protected void performRefactoring(final UsageInfo[] usages) {
		CommandProcessor.getInstance().executeCommand(myElements[0].getProject(), new Runnable() {
			public void run() {
				ApplicationManager.getApplication().runWriteAction(new Runnable() {
					public void run() {
						final TaraFile destination = TaraUtil.getOrCreateFile(myDestination, myProject);
						CommonRefactoringUtil.checkReadOnlyStatus(myProject, destination);
						for (PsiNamedElement e : myElements) {
							// TODO: Check for resulting circular imports
							CommonRefactoringUtil.checkReadOnlyStatus(myProject, e);
							if (e instanceof Concept && destination.getConcept() != null) {
								throw new IncorrectOperationException(TaraBundle.message("refactoring.move.concept.error.destination.file.contains.concept.$0",
									e.getName()));
							}
							checkValidImportableFile(destination, e.getContainingFile().getVirtualFile());
							checkValidImportableFile(e, destination.getVirtualFile());
						}
						for (PsiNamedElement oldElement : myElements) {
							moveElement(oldElement, Arrays.asList(usages), destination);
						}
					}
				});
			}
		}, REFACTORING_NAME, null);
	}

	@NotNull
	@Override
	protected String getCommandName() {
		return REFACTORING_NAME;
	}
}