package pandora.natives.real.data;



/**#/Users/jevora/Documents/repositories/tara-framework/ide/pandora/application/src/pandora/DataTypes.tara#13#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private pandora.real.RealData self;

	@Override
	public String value() {
		return "Double";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (pandora.real.RealData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return pandora.real.RealData.class;
	}
}