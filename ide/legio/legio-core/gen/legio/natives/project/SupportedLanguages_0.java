package legio.natives.project;



/**#/Users/oroncal/workspace/tara/ide/legio/legio-core/src/legio/Main.tara#24#1**/
public class SupportedLanguages_0 implements tara.magritte.Expression<java.util.List<String>> {
	private legio.Project self;

	@Override
	public java.util.List<String> value() {
		return legio.Main.calculate(self);
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (legio.Project) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return legio.Project.class;
	}
}