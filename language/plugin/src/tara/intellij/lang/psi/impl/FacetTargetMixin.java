package tara.intellij.lang.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tara.intellij.lang.psi.*;
import tara.intellij.lang.psi.resolve.ReferenceManager;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class FacetTargetMixin extends ASTWrapperPsiElement {

	public FacetTargetMixin(ASTNode node) {
		super(node);
	}

	public List<String> constraints() {
		TaraConstraint with = ((TaraFacetTarget) this).getConstraint();
		if (with == null) return Collections.EMPTY_LIST;
		return with.getIdentifierReferenceList().stream().map(IdentifierReference::getText).collect(Collectors.toList());
	}

	@NotNull
	public List<Node> components() {
		return unmodifiableList(TaraUtil.getInnerNodesOf((FacetTarget) this));
	}

	@NotNull
	public List<Variable> variables() {
		Body body = ((TaraFacetTarget) this).getBody();
		return (body == null) ? Collections.EMPTY_LIST : Collections.unmodifiableList(body.getVariableList());
	}

	public String getQualifiedName() {
		final String target = target();
		return container().getQualifiedName() + "." + container().getName() + "_" + getTargetName(target);
	}

	@NotNull
	private String getTargetName(String target) {
		return target.contains(".") ? target.substring(0, target.lastIndexOf(".")) : target;
	}

	public Node container() {
		return TaraPsiImplUtil.getContainerNodeOf(this);
	}

	public String target() {
		TaraIdentifierReference identifierReference = ((TaraFacetTarget) this).getIdentifierReference();
		return identifierReference == null ? "" : identifierReference.getText();
	}

	public Node targetNode() {
		return ReferenceManager.resolveToNode(((TaraFacetTarget) this).getIdentifierReference());
	}

	public <T extends tara.language.model.Node> void targetNode(T destiny) {
	}

	public void target(String destiny) {

	}

	public void constraints(List<String> constraints) {

	}

	@Nullable
	public TaraConstraint getConstraint() {
		return findChildByClass(TaraConstraint.class);
	}

	@Nullable
	public TaraIdentifierReference getIdentifierReference() {
		return findChildByClass(TaraIdentifierReference.class);
	}


	public void add(tara.intellij.lang.psi.Node... nodes) {

	}

	public void add(int pos, tara.intellij.lang.psi.Node... nodes) {

	}

	public String type() {
		return null;
	}

	public void add(tara.language.model.Node... nodes) {

	}

	public <T extends tara.language.model.Node> void add(int pos, T... nodes) {

	}

	public tara.intellij.lang.psi.Node components(String name) {
		return null;
	}

	public <T extends tara.language.model.Node> boolean contains(T node) {
		return false;
	}

	public <T extends tara.language.model.Node> boolean remove(T node) {
		return false;
	}

	public void moveToTheTop() {

	}

	public List<? extends tara.intellij.lang.psi.Node> siblings() {
		return null;
	}

	public <T extends tara.language.model.Variable> void add(T... variables) {

	}

	public <T extends tara.language.model.Variable> void add(int pos, T... variables) {

	}

	public void container(tara.language.model.NodeContainer container) {

	}

	public String qualifiedName() {
		return null;
	}

	public String doc() {
		return null;
	}

	public void addDoc(String doc) {

	}

	public <T extends tara.intellij.lang.psi.Variable> void add(T... variables) {

	}

	public void add(int pos, tara.intellij.lang.psi.Variable... variables) {

	}

	public void container(tara.intellij.lang.psi.NodeContainer container) {

	}

	public String file() {
		return null;
	}

	public void file(String file) {

	}

	public int line() {
		return 0;
	}

	public void line(int line) {

	}

	public String toString() {
		return getQualifiedName();
	}
}
