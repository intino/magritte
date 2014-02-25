package monet.tara.intellij.project;

import org.jetbrains.annotations.NonNls;

public interface TaraTemplates {

	@NonNls
	String CONCEPT = "Concept";

	String CONCEPT_TF="'' ${AUTHOR} \n" +
		"'' ${DATE}\n" +
		"'\n" +
		"Concept as ${NAME}";
}
