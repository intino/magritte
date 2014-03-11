package monet.tara.compiler.code_generation.render.grammarComponents.types;

import AST.*;
import m1Generation.grammarComponents.Attributes;

public class ExplicitAttributes extends Attributes {

    public ExplicitAttributes(ASTNode node, AST root) {
        super(node, root);
    }

    @Override
    protected String getAttributes(ASTNode node) {
        String attributes="";
        for (int index=0;index<node.getVariables().size();index++)
            attributes += stringByEvaluationType(node, index);
        return attributes;
    }

    @Override
    protected String getParentAttributes(ASTNode node, AST root) {
        String attributes = "";
        ASTNode extendNode = root.searchAncestry(node);
        if (extendNode!=null){
            for (int index=0;index<extendNode.getVariables().size();index++)
                attributes+= stringByEvaluationParentType(node, extendNode, index);
        }
        return attributes;
    }

    @Override
    protected String stringByEvaluationType(ASTNode elementType, int index) {
        if (elementType.getVariables().get(index) instanceof ASTNode.Attribute){
            ASTNode.Attribute attribute = (ASTNode.Attribute) elementType.getVariables().get(index);
            if (elementType.getVariables().size() -1 == index)
                return setAttributeStringWithEndingToken(attribute,"");
            else
                return setAttributeStringWithEndingToken(attribute,"| ");
        }
        if (elementType.getVariables().get(index) instanceof ASTNode.Reference ){
            ASTNode.Reference attribute = (ASTNode.Reference) elementType.getVariables().get(index);
            if (elementType.getVariables().size() -1 == index)
                return setReferenceStringWithEndingToken(attribute,"");
            else
                return setReferenceStringWithEndingToken(attribute,"| ");
        }
        if (elementType.getVariables().get(index) instanceof ASTNode.Word ){
            ASTNode.Word attribute = (ASTNode.Word)elementType.getVariables().get(index);
            if (elementType.getVariables().size() -1 == index)
                return setWordStringWithEndingToken(attribute,"");
            else
                return setWordStringWithEndingToken(attribute,"| ");
        }
        return "";
    }
    @Override
    protected String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int index) {
        if (extendType.getVariables().get(index) instanceof ASTNode.Attribute ){
            ASTNode.Attribute attribute = (ASTNode.Attribute) extendType.getVariables().get(index);
            if (elementType.getVariables().size()==0 && extendType.getVariables().size() -1 == index)
                return setAttributeStringWithEndingToken(attribute,"");
            else
                return setAttributeStringWithEndingToken(attribute,"| ");
        }
        if (extendType.getVariables().get(index) instanceof ASTNode.Reference ){
            ASTNode.Reference attribute = (ASTNode.Reference) extendType.getVariables().get(index);
            if (elementType.getVariables().size()==0 && extendType.getVariables().size() -1 == index)
                return setReferenceStringWithEndingToken(attribute, "");
            else
                return setReferenceStringWithEndingToken(attribute, "| ");
        }
        if (elementType.getVariables().get(index) instanceof ASTNode.Word ){
            ASTNode.Word attribute = (ASTNode.Word)extendType.getVariables().get(index);
            if (elementType.getVariables().size()==0 && extendType.getVariables().size() -1 == index)
                return setWordStringWithEndingToken(attribute, "");
            else
                return setWordStringWithEndingToken(attribute, "| ");
        }
        return "";
    }


    private String setAttributeStringWithEndingToken(ASTNode.Attribute attribute, String token){
        if (attribute.isList()) return attribute.getName().toUpperCase()+ "_ATTRIBUTE EQUAL LEFT_SQUARE " + attribute.getPrimitiveType().toUpperCase()+"_VALUE (COMMA "+attribute.getPrimitiveType().toUpperCase()+"_VALUE)* RIGHT_SQUARE" + token;
        else return attribute.getName().toUpperCase()+ "_ATTRIBUTE EQUAL "+ attribute.getPrimitiveType().toUpperCase()+"_VALUE" + token;
    }
    private String setReferenceStringWithEndingToken(ASTNode.Reference attribute, String token){
        if (attribute.isList()) return attribute.getName().toUpperCase()+ "_ATTRIBUTE EQUAL LEFT_SQUARE identifier(COMMA identifier)* RIGHT_SQUARE" + token;
        else return attribute.getName().toUpperCase()+ "_ATTRIBUTE EQUAL IDENTIFIER" + token;
    }
    private String setWordStringWithEndingToken(ASTNode.Word attribute, String token){
        return attribute.getIdentifier().toUpperCase()+ "_ATTRIBUTE EQUAL "+ getStringOfWordList(attribute.getWordTypes()) + token;
    }
}
