package pandora.natives.integer.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#16#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.integer.IntegerData self;

	@Override
	public String value() {
		return "Integer";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.integer.IntegerData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.integer.IntegerData.class;
	}
}