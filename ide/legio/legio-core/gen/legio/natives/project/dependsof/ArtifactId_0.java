package legio.natives.project.dependsof;



/**#/Users/oroncal/workspace/tara/ide/legio/legio-core/src/Model.tara#9#2**/
public class ArtifactId_0 implements tara.magritte.Expression<String> {
	private legio.Project.DependsOf self;

	@Override
	public String value() {
		return "";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (legio.Project.DependsOf) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return legio.Project.DependsOf.class;
	}
}