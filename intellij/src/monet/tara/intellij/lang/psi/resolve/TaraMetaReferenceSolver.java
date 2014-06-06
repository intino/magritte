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
import monet.tara.lang.Node;
import monet.tara.lang.NodeObject;
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
				NodeObject node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(context.getType()).get(0).getObject();
				if (node != null) {
					addChildren(concepts, node);
					if (node.isCase()) addBaseConcepts(node.getBaseNode(), concepts);
				}
			}
		}
		return fillVariants(concepts);
	}

	private void addBaseConcepts(String baseConcept, List<String> concepts) {
		Node node = TaraLanguage.getHeritage().getNodeNameLookUpTable().get(baseConcept).get(0);
		for (Node child : node.getInnerNodes())
			if (!child.getObject().isCase() && !child.getObject().isAbstract()) concepts.add(child.getName());
	}

	private void addChildren(List<String> concepts, NodeObject node) {
		for (NodeObject child : node.getChildren())
			if (!child.isBase() && !child.isAbstract())
				concepts.add(child.getName());
			else for (NodeObject nodeObject : child.getChildren())
				if (nodeObject.isCase())
					concepts.add(nodeObject.getName());
	}

	private Object[] fillVariants(List<String> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final String element : elements)
			if (element.length() != 0)
				variants.add(LookupElementBuilder.create(element).withIcon(TaraIcons.getIcon(TaraIcons.ICON_13)).withTypeText("Tara"));
		return variants.toArray();
	}
}
