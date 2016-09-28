package pandora.natives.bool.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#19#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.bool.BoolData self;

	@Override
	public String value() {
		return "Boolean";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.bool.BoolData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.bool.BoolData.class;
	}
}