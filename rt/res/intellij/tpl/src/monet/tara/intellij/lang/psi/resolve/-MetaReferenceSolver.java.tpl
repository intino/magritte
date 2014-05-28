package monet.::projectName::.intellij.lang.psi.resolve;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import monet.::projectName::.intellij.lang.::projectProperName::Icons;
import monet.::projectName::.intellij.lang.::projectProperName::Language;
import monet.::projectName::.intellij.lang.psi.Definition;
import monet.::projectName::.intellij.lang.psi.MetaIdentifier;
import monet.::projectName::.intellij.lang.psi.impl.::projectProperName::PsiImplUtil;
import monet.tara.lang.ASTNode;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ::projectProperName::MetaReferenceSolver extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

	public ::projectProperName::MetaReferenceSolver(MetaIdentifier metaIdentifier, TextRange textRange) {
		super(metaIdentifier, textRange);
	}

	\@NotNull
	\@Override
	public ResolveResult[] multiResolve(boolean incompleteCode) {
		List<ResolveResult> results = new ArrayList<>();
		Definition contextOf = ::projectProperName::PsiImplUtil.getContextOf(myElement);
		if (contextOf != null)
			results.add(new PsiElementResolveResult(contextOf));
		return results.toArray(new ResolveResult[results.size()]);
	}

	\@Nullable
	\@Override
	public PsiElement resolve() {
		ResolveResult[] resolveResults = multiResolve(false);
		return resolveResults.length == 1 ? resolveResults[0].getElement() \: null;
	}

	\@NotNull
	\@Override
	public Object[] getVariants() {
		List<String> definitions = new ArrayList<>();
		if (myElement instanceof MetaIdentifier) {
			Definition context = ::projectProperName::PsiImplUtil.getContextOf(::projectProperName::PsiImplUtil.getContextOf(myElement));
			if (context != null) {
				ASTNode node = ::projectProperName::Language.getHeritage().getNodeNameLookUpTable().get(context.getType()).get(0);
				if (node != null) {
					addChildren(definitions, node);
					if (node.getParentName() != null) addInheritedDefinitions(node.getParentName(), definitions);
					if (node.isCase()) addBaseDefinitions(node.getBaseNode(), definitions);
				}
			}
		}
		return fillVariants(definitions);
	}

	private void addBaseDefinitions(String baseDefinition, List<String> definitions) {
		ASTNode node = ::projectProperName::Language.getHeritage().getNodeNameLookUpTable().get(baseDefinition).get(0);
		for (ASTNode children \: node.getChildren())
			if (!children.isCase() && !children.isAbstract()) definitions.add(children.getIdentifier());
	}

	private void addInheritedDefinitions(String extendFrom, List<String> definitions) {
		ASTNode node = ::projectProperName::Language.getHeritage().getNodeNameLookUpTable().get(extendFrom).get(0);
		for (ASTNode children \: node.getChildren())
			if (!children.isBase() && !children.isAbstract()) definitions.add(children.getIdentifier());
		if (node.getParentName() != null) addInheritedDefinitions(node.getParentName(), definitions);
	}

	private void addChildren(List<String> definitions, ASTNode node) {
		for (ASTNode child \: node.getChildren())
			if (!child.isBase() && !child.isAbstract())
				definitions.add(child.getIdentifier());
			else for (ASTNode astNode \: child.getChildren())
				if (astNode.isCase())
					definitions.add(astNode.getIdentifier());
	}

	private Object[] fillVariants(List<String> elements) {
		List<LookupElement> variants = new ArrayList<>();
		for (final String element \: elements)
			if (element.length() != 0)
				variants.add(LookupElementBuilder.create(element).withIcon(monet.::projectName::.intellij.lang.::projectProperName::Icons.getIcon(::projectProperName::Icons.TARA)).withTypeText("::projectProperName::"));
		return variants.toArray();
	}
}
