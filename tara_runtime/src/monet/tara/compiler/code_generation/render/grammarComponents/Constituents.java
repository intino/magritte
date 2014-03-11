package monet.tara.compiler.code_generation.render.grammarComponents;

import AST.*;

public class Constituents {
    private AST root;
    private ASTNode node;

    public Constituents(AST root, ASTNode node) {
        this.root = root;
        this.node = node;
    }

    public String getConstituentString(){
        String constituent = getStringConstituents(root.searchAncestry(node), "");
        constituent =  getStringConstituents(node, constituent);
        return constituent.substring(constituent.indexOf("|") + 1);
    }

    private String getTransformedAbsolutePath(ASTNode node){
        return node.getAbsolutePath().toLowerCase().replaceAll("\\.","_");
    }

    private String getStringConstituents(ASTNode node, String constituents){
        if (node != null){
            for (ASTNode child : node.getChildren())
                if (!child.isAbstract()) constituents = evaluateStringFormatConstituent(child, constituents);
        }
        return constituents;
    }

    private String evaluateStringFormatConstituent(ASTNode child, String constituents) {
        if (child.isPolymorphic()){
            for (ASTNode morphChild : child.getMorphs())
                if (! morphChild.isAbstract()) constituents = polymorphicEvaluatorFormat(morphChild, constituents);
        }
        else constituents = polymorphicEvaluatorFormat(child, constituents);
        return constituents;
    }

    private String polymorphicEvaluatorFormat(ASTNode morphChild, String constituents) {
        if (root.searchAncestry(morphChild) != null)constituents += " | " + ((!morphChild.getIdentifier().equals(""))?
                getTransformedAbsolutePath(morphChild) : getTransformedAbsolutePath(root.searchAncestry(morphChild)));
        else constituents += (morphChild.getIdentifier().equals(""))?
                "" : " | " + getTransformedAbsolutePath(morphChild) ;
        return constituents;
    }
}
