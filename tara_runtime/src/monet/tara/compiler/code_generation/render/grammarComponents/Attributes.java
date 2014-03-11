package monet.tara.compiler.code_generation.render.grammarComponents;

import AST.*;
import java.util.ArrayList;

public abstract class Attributes {

    private String attributesString;
    protected abstract String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int i);
    protected abstract String stringByEvaluationType(ASTNode elementType, int i);
    protected abstract String getParentAttributes(ASTNode node, AST root);
    protected abstract String getAttributes(ASTNode node);

    protected Attributes(ASTNode node, AST root) {
        this.attributesString = getParentAttributes(node, root) + getAttributes(node);
    }

    public String getAttributesString() {
        return attributesString;
    }

    protected String getStringOfWordList(ArrayList<String> wordTypes) {
        String list="(";
        for (String word : wordTypes){
            if (wordTypes.indexOf(word) != wordTypes.size()-1) list += setAttributeWithEndingToken(word,"| ");
            else list += setAttributeWithEndingToken(word,")");
        }
        return list;
    }

    private String setAttributeWithEndingToken(String word,String token){
        return (!word.contains("."))? word.toUpperCase()+"_WORD" + token
                : word.substring(word.lastIndexOf(".")+1).toUpperCase()+"_WORD" + token;
    }
}