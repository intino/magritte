package magritte;

public class AcceptedStash {
//    @Test
//    public void should_be_named_with_the_right_convention() throws Exception {
//        assertThat(createHouseStash().toString(), is("stash[H1:House]"));
//        assertThat(createRadiatorStash().toString(), is("stash[Instance:Radiator]"));
//    }
//
//
//    private Stash createHouseStash() {
//        return createStash(createRoot("H1", createType("House")));
//    }
//
//    public static Stash createRadiatorStash() {
//        Stash.Type thermal = createType("Thermal|XYZ");
//        Stash.Type electrical = createType("Electrical");
//        Stash.Type device = createType("Device");
//        Stash.Type appliance = createType("Appliance", device);
//        Stash.Type radiator = createType("Radiator", appliance);
//        return createStash(createRoot("Instance|ABC", radiator, electrical, thermal));
//    }
//
//
//    public static Stash createStash(Stash.Root root) {
//        Stash stash = new Stash();
//        stash.root = root;
//        return stash;
//    }
//
//    public static Stash.Type createType(String name) {
//        Stash.Type type = new Stash.Type ();
//        type.name = name;
//        return type;
//
//    }
//
//    public static Stash.Type createType(String name, Stash.Type parent) {
//        Stash.Type type = new Stash.Type ();
//        type.name = name;
//        type.parent = parent;
//        return type;
//
//    }
//
//    public static Stash.Root createRoot(String name, Stash.Type... types) {
//        Stash.Root result = new Stash.Root();
//        result.name = name;
//        result.types = types;
//        return result;
//    }
}
