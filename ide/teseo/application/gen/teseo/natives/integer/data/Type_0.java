package teseo.natives.integer.data;



/**#/Users/oroncal/workspace/tara/ide/teseo/application/src/teseo/Teseo.tara#28#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.integer.IntegerData self;

	@Override
	public String value() {
		return "Integer";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.integer.IntegerData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.integer.IntegerData.class;
	}
}