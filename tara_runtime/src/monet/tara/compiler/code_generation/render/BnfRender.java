package monet.tara.compiler.code_generation.render;

import AST.*;
import m1Generation.grammarComponents.*;
import m1Generation.grammarComponents.types.ExplicitAttributes;
import m1Generation.grammarComponents.types.ImplicitAttributes;
import org.monet.templation.CanvasLogger;
import org.monet.templation.Render;
import java.util.ArrayList;
import java.util.HashMap;

public class BnfRender extends Render {
    private String tplName;
    private String projectName;
    private AST root;
    private ArrayList<ASTNode> rootList = new ArrayList<>();
    public BnfRender(String projectName, String tplName, String path, AST root) {
        super(new Logger(), path);
        this.tplName = tplName;
        this.projectName = projectName;
        this.root=root;
    }

    @Override
    protected void init() {
        loadCanvas(tplName, true);
        addMark("projectNameFile", projectName);
        addMark("projectName",projectName.substring(0,1).toLowerCase() + projectName.substring(1));
        for (ASTNode node : root.getAstRootNodes()) goOver(node);
        generateListOfRootConcepts();
    }

    private static class Logger implements CanvasLogger {
        @Override
        public void debug(String message, Object... args) {
        }
    }

    private String getTransformedAbsolutePath(ASTNode node){
        return node.getAbsolutePath().toLowerCase().replaceAll("\\.","_");
    }

    private void goOver(ASTNode node){
            if (!node.isAbstract()) {
                generateMainRulesForConcepts(node);
                if (!node.isPolymorphic() && isExtendedRoot(node)) rootList.add(node);
            }
            for(ASTNode child : node.getChildren())
                if (!child.getIdentifier().equals("")) goOver(child);
    }

    private void generateListOfRootConcepts() {
        StringBuilder concepts = new StringBuilder();
        for (ASTNode node : rootList){
            HashMap<String, Object> localMap = new HashMap<>();
            localMap.put("identifier", getTransformedAbsolutePath(node));
            localMap.put("pipe", (rootList.indexOf(node)!= 0 && concepts.length() !=0 )? "|" : "");
            concepts.append(block("concept", localMap));
        }
        addMark("concepts", concepts.toString());
    }

    private void generateMainRulesForConcepts(ASTNode node){
        StringBuilder concepts = new StringBuilder();
        HashMap<String, Object> localMap = new HashMap<>();
        putMainComponentsToHashMap(node, localMap);
        putAttributesComponentsToHashMap(localMap,
                (new ImplicitAttributes(node,root)).getAttributesString(),
                (new ExplicitAttributes(node,root)).getAttributesString());
        putConstituentsToHashMap(localMap, (new Constituents(root,node)).getConstituentString());
        concepts.append(block("ruleConcept", localMap));
        addMark("rules", concepts.toString());
    }

    private boolean containsRoot(ASTNode.AnnotationType[] annotations){
        for (ASTNode.AnnotationType annotation : annotations)
            if (annotation.equals(ASTNode.AnnotationType.Root)) return true;
        return false;
    }

    private boolean isExtendedRoot(ASTNode node){
        if (node != null){
            if (containsRoot(node.getAnnotations())) return true;
            if (node.getParent() != null && node.isMorph()) return isExtendedRoot(node.getParent());
            if (root.searchAncestry(node) != null) return isExtendedRoot(root.searchAncestry(node));
        }
        return false;
    }

    private boolean hasExtendedCode(ASTNode node){
        if (node != null){
            if (node.hasCode()) return true;
            if (node.getParent() != null && node.isMorph()) return hasExtendedCode(node.getParent());
            if (root.searchAncestry(node) != null) return hasExtendedCode(root.searchAncestry(node));
        }
        return false;
    }

    private void putConstituentsToHashMap(HashMap<String, Object> localMap, String constituent) {
        localMap.put("identifierConstituent",(!constituent.equals(""))? " | "+localMap.get("identifier")+"Constituents":"");
        localMap.put("constituentRule",(!constituent.equals(""))? localMap.get("identifier")+"Constituents ::= "+constituent : "");
    }
    private void putAttributesComponentsToHashMap(HashMap<String, Object> localMap, String attributesImplicit, String attributesExplicit) {
        localMap.put("assignAttributeHeader",(! attributesImplicit.equals(""))?localMap.get("identifier")+"AttributesImplicit":"");
        localMap.put("typeValues",attributesImplicit);
        localMap.put("typeKey-Value",attributesExplicit);
        String attributeImplicitForm = localMap.get("identifier")+"AttributesImplicit ::= ASSIGN "+localMap.get("typeValues")+"\n";
        String attributeExplicit = localMap.get("identifier")+"AttributesExplicit ::= "+localMap.get("typeKey-Value");
        localMap.put("attributeRule",(!localMap.get("assignAttributeHeader").equals(""))? attributeExplicit + "\n" + attributeImplicitForm : "");
        localMap.put("identifierExplicitAttribute",(!localMap.get("assignAttributeHeader").equals(""))? "| " + localMap.get("identifier") + "AttributesExplicit":"");
    }
    private void putMainComponentsToHashMap(ASTNode advance, HashMap<String, Object> localMap) {
        localMap.put("identifier", getTransformedAbsolutePath(advance));
        localMap.put("code", hasExtendedCode(advance)?"CODE":"");
        localMap.put("projectName",projectName);
        localMap.put("lexicoIdentifier", root.getKeys(advance.getIdentifier()).get(0));
    }
}