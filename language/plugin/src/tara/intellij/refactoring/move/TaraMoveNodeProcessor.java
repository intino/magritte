package tara.intellij.refactoring.move;

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
import org.jetbrains.annotations.NotNull;
import tara.intellij.MessageProvider;
import tara.intellij.lang.psi.Node;
import tara.intellij.lang.psi.TaraModel;
import tara.intellij.lang.psi.impl.TaraUtil;
import tara.intellij.refactoring.TaraRefactoringUtil;

import java.util.*;

public class TaraMoveNodeProcessor extends BaseRefactoringProcessor {
	public static final String REFACTORING_NAME = MessageProvider.message("refactoring.move.concept");

	private PsiNamedElement[] myElements;
	private String myDestination;

	public TaraMoveNodeProcessor(Project project, PsiNamedElement[] elements, String destination, boolean previewUsages) {
		super(project);
		assert elements.length > 0;
		myElements = elements.clone();
		myDestination = destination;
		setPreviewUsages(previewUsages);
	}

	private static void moveElement(@NotNull PsiNamedElement element, @NotNull Collection<UsageInfo> usages, @NotNull TaraModel destination) {
		final PsiFile file = element.getContainingFile();
//		TaraConceptRefactoringUtil.rememberNamedReferences(element);
		final PsiNamedElement newElement = addToFile(element, destination, usages);
		for (UsageInfo usage : usages) {
			final PsiElement usageElement = usage.getElement();
			if (usageElement != null) {
//				updateUsage(usageElement, element, newElement);
			}
		}
//		TaraConceptRefactoringUtil.restoreNamedReferences(newElement, element);
		// TODO: Remove extra empty lines after the removed element
		element.delete();
		if (file != null) optimizeImports(file);
	}

	private static PsiNamedElement addToFile(@NotNull PsiNamedElement element, @NotNull final TaraModel destination, @NotNull Collection<UsageInfo> usages) {
		List<PsiElement> topLevelAtDestination = new ArrayList<>();
		for (UsageInfo usage : usages) {
			final PsiElement e = usage.getElement();
//			if (e != null && ScopeUtil.getScopeOwner(e) == destination && getImportStatementByElement(e) == null) {
//				PsiElement topLevel = PsiTreeUtil.findFirstParent(e, new Condition<PsiElement>() {
//					public boolean defaultValue(PsiElement element) {
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

			});
			final PsiElement firstUsage = topLevelAtDestination.get(0);
			return (PsiNamedElement) destination.addBefore(element, firstUsage);
		}
	}


	private static void optimizeImports(@NotNull PsiFile file) {
//		new TaraImportOptimizer().processFile(file).run();
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
						final TaraModel destination = TaraUtil.getOrCreateFile(myDestination, myProject);
						CommonRefactoringUtil.checkReadOnlyStatus(myProject, destination);
						for (PsiNamedElement e : myElements) {
							// TODO: Check for resulting circular imports
							CommonRefactoringUtil.checkReadOnlyStatus(myProject, e);
							if (e instanceof Node && destination.getIncludes().iterator().next() != null) {//TODO
								throw new IncorrectOperationException(MessageProvider.message("refactoring.move.concept.error.destination.file.contains.concept.$0",
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