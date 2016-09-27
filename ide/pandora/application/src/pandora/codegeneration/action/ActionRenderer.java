package pandora.codegeneration.action;

import org.siani.itrules.Template;
import pandora.helpers.Commons;
import pandora.type.TypeData;
import pandora.object.ObjectData;

import java.io.File;

import static cottons.utils.StringHelper.snakeCaseToCamelCase;

abstract class ActionRenderer {
	protected final File destiny;
	protected String packageName;

	ActionRenderer(File destiny, String packageName) {
		this.destiny = destiny;
		this.packageName = packageName;
	}

	protected Template template() {
		final Template template = ActionTemplate.create();
		template.add("ValidPackage", Commons::validPackage);
        template.add("SnakeCaseToCamelCase", value -> snakeCaseToCamelCase(value.toString()));
		return template;
	}

    boolean alreadyRendered(File destiny, String action) {
		return Commons.javaFile(destinyPackage(destiny), action + "Action").exists();
	}

	File destinyPackage(File destiny) {
		return new File(destiny, "actions");
	}

	String formatType(TypeData typeData) {
		return (typeData.is(ObjectData.class) ? (packageName + ".schemas.") : "") + typeData.type();
	}
}
