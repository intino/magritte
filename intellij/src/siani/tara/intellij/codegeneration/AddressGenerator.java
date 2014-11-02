package siani.tara.intellij.codegeneration;

import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.RunResult;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import siani.tara.intellij.lang.psi.Concept;
import siani.tara.intellij.lang.psi.TaraElementFactory;
import siani.tara.intellij.lang.psi.impl.TaraBoxFileImpl;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class AddressGenerator {
	private final Collection<Concept> concepts;
	private final Project project;
	private final TaraBoxFileImpl file;

	public AddressGenerator(Collection<Concept> concepts) {
		this.concepts = concepts;
		project = concepts.iterator().next().getProject();
		file = concepts.iterator().next().getFile();
	}

	public void generate() {
		WriteCommandAction action = new WriteCommandAction(project, file) {
			@Override
			protected void run(@NotNull Result result) throws Throwable {
				for (Concept concept : concepts)
					if (concept.getAddress() == null)
						concept.addAddress(
							TaraElementFactory.getInstance(concept.getProject()).createAddress(new Random(new Date().getTime()).nextLong()));
			}
		};
		RunResult execute = action.execute();
	}
}
