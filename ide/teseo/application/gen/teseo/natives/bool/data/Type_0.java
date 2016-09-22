package teseo.natives.bool.data;



/**#/Users/oroncal/workspace/tara/ide/teseo/application/src/teseo/Teseo.tara#31#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.bool.BoolData self;

	@Override
	public String value() {
		return "Boolean";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.bool.BoolData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.bool.BoolData.class;
	}
}