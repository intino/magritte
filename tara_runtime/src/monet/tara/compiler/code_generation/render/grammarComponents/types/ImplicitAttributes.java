package monet.tara.compiler.code_generation.render.grammarComponents.types;

import AST.*;
import m1Generation.grammarComponents.Attributes;

public class ImplicitAttributes extends Attributes {

    public ImplicitAttributes(ASTNode node, AST root) {
        super(node, root);
    }

    @Override
    protected String getAttributes(ASTNode node) {
        String attributes="";
        for (int index=0;index<node.getVariables().size();index++)
            attributes+= stringByEvaluationType(node, index);
        return attributes;
    }
    @Override
    protected String getParentAttributes(ASTNode node, AST root) {
        String attributes = "";
        ASTNode extendNode = root.searchAncestry(node);
        if (extendNode != null){
            for (int index=0;index<extendNode.getVariables().size();index++)
                attributes += stringByEvaluationParentType(node, extendNode, index);
        }
        return attributes;
    }

    @Override
    protected String stringByEvaluationParentType(ASTNode elementType, ASTNode extendType, int index) {
        if (extendType.getVariables().get(index) instanceof ASTNode.Attribute ){
            ASTNode.Attribute attribute = (ASTNode.Attribute) extendType.getVariables().get(index);
            if (elementType.getVariables().size()==0 && extendType.getVariables().size() -1 == index)
                return setAttributeStringWithEndingToken(attribute,"");
            else
                return setAttributeStringWithEndingToken(attribute," COMMA ");
        }
        if (extendType.getVariables().get(index) instanceof ASTNode.Reference ){
            ASTNode.Reference attribute = (ASTNode.Reference)extendType.getVariables().get(index);
            if (elementType.getVariables().size()==0 && extendType.getVariables().size() -1 == index)
                return setReferenceStringWithEndingToken(attribute,"");
            else
                return setReferenceStringWithEndingToken(attribute," COMMA ");
        }
        if (elementType.getVariables().get(index) instanceof ASTNode.Word ){
            ASTNode.Word attribute = (ASTNode.Word)extendType.getVariables().get(index);
            if (elementType.getVariables().size()==0 && extendType.getVariables().size() -1 == index) return setWordStringWithEndingToken(attribute,"");
            else return setWordStringWithEndingToken(attribute," COMMA ");
        }
        return "";
    }
    @Override
    protected String stringByEvaluationType(ASTNode elementType, int index){
        if (elementType.getVariables().get(index) instanceof ASTNode.Attribute ){
            ASTNode.Attribute attribute = (ASTNode.Attribute) elementType.getVariables().get(index);
            if (elementType.getVariables().size() -1 == index)
                return setAttributeStringWithEndingToken(attribute,"");
            else
                return setAttributeStringWithEndingToken(attribute," COMMA ");
        }
        if (elementType.getVariables().get(index) instanceof ASTNode.Reference ){
            ASTNode.Reference attribute = (ASTNode.Reference)elementType.getVariables().get(index);
            if (elementType.getVariables().size() -1 == index)
                return setReferenceStringWithEndingToken(attribute,"");
            else
                return setReferenceStringWithEndingToken(attribute," COMMA ");
        }
        if (elementType.getVariables().get(index) instanceof ASTNode.Word ){
            ASTNode.Word attribute = (ASTNode.Word)elementType.getVariables().get(index);
            if (elementType.getVariables().size() -1 == index) return setWordStringWithEndingToken(attribute,"");
            else return setWordStringWithEndingToken(attribute," COMMA ");
        }
        return "";
    }

    private String setAttributeStringWithEndingToken(ASTNode.Attribute attribute, String token){
        if (attribute.isList()) return "LEFT_SQUARE " + attribute.getPrimitiveType().toUpperCase()+"_VALUE (COMMA "+attribute.getPrimitiveType().toUpperCase()+"_VALUE)* RIGHT_SQUARE" + token;
        else return attribute.getPrimitiveType().toUpperCase()+"_VALUE" + token;
    }
    private String setReferenceStringWithEndingToken(ASTNode.Reference attribute, String token){
        if (attribute.isList())
            return "LEFT_SQUARE identifier(COMMA identifier)* RIGHT_SQUARE" + token;
        else
            return "IDENTIFIER" + token;
    }
    private String setWordStringWithEndingToken(ASTNode.Word attribute, String token){
        return getStringOfWordList(attribute.getWordTypes()) + token;
    }
}