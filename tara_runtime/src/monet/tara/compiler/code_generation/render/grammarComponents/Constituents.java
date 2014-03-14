package monet.tara.compiler.code_generation.render.grammarComponents;


import monet.tara.compiler.core.ast.AST;
import monet.tara.compiler.core.ast.ASTNode;

public class Constituents {
    private AST root;
    private ASTNode node;

    public Constituents(AST root, ASTNode node) {
        this.root = root;
        this.node = node;
    }

    public String getConstituentString(){
        String constituent = getStringConstituents(root.searchAncestry(node), "");
        if (node.isMorph()) constituent = getStringConstituentsPolymorphfic(node.getParent(), constituent);
        constituent =  getStringConstituents(node, constituent);
        return constituent.substring(constituent.indexOf("|") + 1);
    }

    private String getTransformedAbsolutePath(ASTNode node){
        return node.getAbsolutePath().toLowerCase().replaceAll("\\.","_");
    }

    private String getStringConstituentsPolymorphfic(ASTNode node, String constituents){
        if (node != null){
            for (ASTNode child : node.getChildren())
                if (!child.isAbstract() && !child.isMorph() || contains(this.node.getChildren(),node)) constituents = evaluateStringFormatConstituent(child, constituents);
        }
        return constituents;
    }

    private String getStringConstituents(ASTNode node, String constituents){
        if (node != null){
            for (ASTNode child : node.getChildren())
                if (!child.isAbstract()) constituents = evaluateStringFormatConstituent(child, constituents);
        }
        return constituents;
    }

    private String evaluateStringFormatConstituent(ASTNode node, String constituents) {
        if (node.isPolymorphic()){
            for (ASTNode morphChild : node.getMorphs())
                if (! morphChild.isAbstract()) constituents = evaluateFormat(morphChild, constituents);
        }
        else constituents = evaluateFormat(node, constituents);
        return constituents;
    }

    private String evaluateFormat(ASTNode node, String constituents) {
        ASTNode nodeAncestry = root.searchAncestry(node);
        if (nodeAncestry != null && !nodeAncestry.isPolymorphic())
            constituents += " | " + ((!node.getIdentifier().equals(""))?
                getTransformedAbsolutePath(node) : getTransformedAbsolutePath(nodeAncestry));
        else constituents += (node.getIdentifier().equals(""))?
                 "" : " | " + getTransformedAbsolutePath(node) ;
        return constituents;
    }

    private boolean contains(ASTNode[]nodes,ASTNode node){
        for (ASTNode child : nodes){
            ASTNode trulyNode = (child.getIdentifier().equals(""))? root.searchAncestry(child) : child;
            if (trulyNode == node) return true;
        }
        return false;
    }
}
