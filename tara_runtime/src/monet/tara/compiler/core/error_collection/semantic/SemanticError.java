package monet.tara.compiler.core.error_collection.semantic;


import monet.tara.compiler.core.ast.ASTNode;

import java.util.ArrayList;

public class SemanticError {

	private ArrayList<Error> duplicateIdentifiers = new ArrayList<>();
	private ArrayList<Error> duplicateAnnotations = new ArrayList<>();
	private ArrayList<Error> undefinedReferences = new ArrayList<>();
	private ArrayList<Error> invalidHeritage = new ArrayList<>();
	private ArrayList<Error> wrongAnnotations = new ArrayList<>();
	private ArrayList<Error> polymorphicChildless = new ArrayList<>();
	private ArrayList<Error> morphsWithoutParents = new ArrayList<>();
	private ArrayList<Warning> unusedConcepts = new ArrayList<>();

	public boolean isEmpty() {
		return (duplicateIdentifiers.isEmpty() && duplicateAnnotations.isEmpty() && undefinedReferences.isEmpty()
			&& invalidHeritage.isEmpty() && wrongAnnotations.isEmpty() && polymorphicChildless.isEmpty()
			&& morphsWithoutParents.isEmpty() && unusedConcepts.isEmpty());
	}

	public Error[] getDuplicateIdentifiers() {
		return duplicateIdentifiers.toArray(new Error[duplicateIdentifiers.size()]);
	}

	public Error[] getDuplicateAnnotations() {
		return duplicateAnnotations.toArray(new Error[duplicateAnnotations.size()]);
	}

	public Error[] getUndefinedReferences() {
		return undefinedReferences.toArray(new Error[undefinedReferences.size()]);
	}

	public Error[] getInvalidHeritage() {
		return invalidHeritage.toArray(new Error[invalidHeritage.size()]);
	}

	public Error[] getWrongAnnotations() {
		return wrongAnnotations.toArray(new Error[wrongAnnotations.size()]);
	}

	public Error[] getPolymorphicChildless() {
		return polymorphicChildless.toArray(new Error[polymorphicChildless.size()]);
	}

	public Error[] getMorphsWithoutParents() {
		return morphsWithoutParents.toArray(new Error[morphsWithoutParents.size()]);
	}

	public Warning[] getUnusedConcepts() {
		return unusedConcepts.toArray(new Warning[unusedConcepts.size()]);
	}

	public void addDuplicateIdentifier(Error identifier) {
		if (!duplicateIdentifiers.contains(identifier))
			duplicateIdentifiers.add(identifier);
	}

	public void addDuplicateAnnotation(Error annotation) {
		if (!duplicateAnnotations.contains(annotation))
			duplicateAnnotations.add(annotation);
	}

	public void addUndefinedReference(Error reference) {
		if (!undefinedReferences.contains(reference))
			undefinedReferences.add(reference);
	}

	public void addInvalidHeritage(Error concept) {
		if (!invalidHeritage.contains(concept))
			invalidHeritage.add(concept);
	}

	public void addWrongAnnotation(Error annotation) {
		if (!wrongAnnotations.contains(annotation))
			wrongAnnotations.add(annotation);
	}

	public void addPolymorphicChildless(Error polymorphicConcept) {
		if (!polymorphicChildless.contains(polymorphicConcept))
			polymorphicChildless.add(polymorphicConcept);
	}

	public void addMorphWithoutParents(Error morphConcept) {
		if (!morphsWithoutParents.contains(morphConcept))
			morphsWithoutParents.add(morphConcept);
	}

	public void addConceptUnused(Warning concept) {
		if (!unusedConcepts.contains(concept))
			unusedConcepts.add(concept);
	}

	public Error[] getErrors() {
		ArrayList<Error> errors = new ArrayList<>();
		errors.addAll(duplicateIdentifiers);
		errors.addAll(duplicateAnnotations);
		errors.addAll(undefinedReferences);
		errors.addAll(invalidHeritage);
		errors.addAll(wrongAnnotations);
		errors.addAll(morphsWithoutParents);
		return errors.toArray(new Error[errors.size()]);
	}

	public static class Error extends GeneralError {
		public Error(String name, String message, ASTNode node) {
			super(name, message, node);
		}
	}

	public static class Warning extends GeneralError {
		public Warning(String name, String message, ASTNode node) {
			super(name, message, node);
		}
	}

	public static class GeneralError {
		String name;
		ASTNode node;
		String message;

		public GeneralError(String name, String message, ASTNode node) {
			this.name = name;
			this.message = message;
			this.node = node;
		}

		public String getName() {
			return name;
		}

		public ASTNode getNode() {
			return node;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			GeneralError that = (GeneralError) o;

			if (name != null ? !name.equals(that.name) : that.name != null) return false;
			if (node != null ? !node.getAbsolutePath().equals(that.node.getAbsolutePath()) : that.node != null)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = name != null ? name.hashCode() : 0;
			result = 31 * result + (node != null ? node.hashCode() : 0);
			return result;
		}
	}
}