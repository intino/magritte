package teseo.natives.object.data;



/**#/Users/oroncal/workspace/tara/ide/liskov/application/src/teseo/Teseo.tara#46#2**/
public class Type_0 implements tara.magritte.Expression<String> {
	private teseo.object.ObjectData self;

	@Override
	public String value() {
		return teseo.Teseo.schemaName(self);
	}

	@Override
	public void self(tara.magritte.Layer context) {
		self = (teseo.object.ObjectData) context;
	}

	@Override
	public Class<? extends tara.magritte.Layer> selfClass() {
		return teseo.object.ObjectData.class;
	}
}