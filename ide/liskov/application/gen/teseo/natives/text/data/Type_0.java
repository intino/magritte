package teseo.natives.text.data;



/**#/Users/oroncal/workspace/teseo/application/src/teseo/Teseo.tara#34#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.text.TextData self;

	@Override
	public String value() {
		return "String";
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.text.TextData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.text.TextData.class;
	}
}