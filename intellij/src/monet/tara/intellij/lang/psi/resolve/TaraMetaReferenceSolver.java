package monet.tara.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.tara.intellij.lang.TaraIcons;
import monet.tara.intellij.lang.TaraLanguage;
import monet.tara.intellij.lang.psi.Concept;
import monet.tara.intellij.lang.psi.MetaIdentifier;
import monet.tara.intellij.lang.psi.impl.TaraPsiImplUtil;
import monet.tara.lang.AbstractNode;
import monet.tara.lang.Modifiable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaraMetaReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public TaraMetaReferenceSolver(MetaIdentifier metaIdentifier, TextRange textRange) {
		super(metaIdentifier, textRange);
	}

	@NotNull
	@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		Concept contextOf = TaraPsiImplUtil.getContextOf(myElement);
		if (contextOf != null)
			results.add(new PsiElementResolveResult(contextOf));
		return results.toArray(new ResolveResult[results.size()]);
	}

	@Nullable
	@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
	}

	@NotNull
	@Override
	public Object[] getVariants() {
		List<String> concepts = new ArrayList<>();
		if (myElement instanceof MetaIdentifier) {
			Concept context = TaraPsiImplUtil.getContextOf(TaraPsiImplUtil.getContextOf(myElement));
			if (context != null) {
				AbstractNode node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(context.getType()).get(0);
				if (node != null) {
					addChildren(concepts, node);
					if (node.getParentName() != null) addInheritedConcepts(node.getParentName(), concepts);
					if (node.isCase()) addBaseConcepts(node.getBaseNode(), concepts);
				}
			}
		}
		return fillVariants(concepts);
	}

	private void addBaseConcepts(String baseConcept, List<String> concepts) {
		AbstractNode node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(baseConcept).get(0);
		for (AbstractNode children : node.getChildren())
			if (!children.isCase() && !children.isAbstract()) concepts.add(children.getIdentifier());
	}

	private void addInheritedConcepts(String extendFrom, List<String> concepts) {
		AbstractNode node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(extendFrom).get(0);
		for (AbstractNode children : node.getChildren())
			if (!children.isBase() && !children.isAbstract()) concepts.add(children.getIdentifier());
		if (node.getParentName() != null) addInheritedConcepts(node.getParentName(), concepts);
	}

	private void addChildren(List<String> concepts, AbstractNode node) {
		for (AbstractNode child : node.getChildren())
			if (!child.isBase() && !child.isAbstract())
				concepts.add(child.getIdentifier());
			else for (AbstractNode abstractNode : child.getChildren())
				if (abstractNode.isCase())
					concepts.add(abstractNode.getIdentifier());
	}

	@Modifiable(tag = "TaraMetaReferenceSolver.fillVariants")
	private Object[] fillVariants(List<String> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final String element : elements)
			if (element.length() != 0)
				variants.add(LookupElementBuilder.create(element).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13)).withTypeText("Tara"));
		return variants.toArray();
	}
}
