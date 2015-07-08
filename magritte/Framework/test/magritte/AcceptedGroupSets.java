package magritte;

public class AcceptedGroupSets {

    private enum AstronomicalBody {
        Satellite, Planet, Star
    }

//    @Test
//    public void should_return_empty_groups_when_created() throws Exception {
//        GroupSet<AstronomicalBody, String> set = new GroupSet<>();
//        assertEquals(0, set.groups());
//        assertTrue(set.group(AstronomicalBody.Satellite).isEmpty());
//        assertTrue(set.group(AstronomicalBody.Star).isEmpty());
//        assertTrue(set.group(AstronomicalBody.Planet).isEmpty());
//    }
//
//    @Test
//    public void should_provide_to_string() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//        assertEquals("#Satellite, moon, europe, #Planet, earth, mars, neptune, #Star, sun, rigel", set.toString());
//    }
//
//    @Test
//    public void should_add_items_in_a_group() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//        assertEquals(2, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(2, set.group(AstronomicalBody.Star).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//        assertEquals("moon", set.group(AstronomicalBody.Satellite).get(0));
//        assertEquals("europe", set.group(AstronomicalBody.Satellite).get(1));
//        assertEquals(null, set.group(AstronomicalBody.Satellite).get(2));
//    }

//    @Test
//    public void should_remove_items_in_a_group() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//
//        set.group(AstronomicalBody.Satellite).remove("europe");
//        assertEquals(1, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(2, set.group(AstronomicalBody.Star).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//        assertFalse(set.group(AstronomicalBody.Satellite).isEmpty());
//        assertEquals("rigel", set.group(AstronomicalBody.Star).get(1));
//        assertEquals(1, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(2, set.group(AstronomicalBody.Star).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//
//        set.group(AstronomicalBody.Satellite).remove("moon");
//        assertTrue(set.group(AstronomicalBody.Satellite).isEmpty());
//        assertEquals("earth", set.group(AstronomicalBody.Planet).get(0));
//        assertEquals("mars", set.group(AstronomicalBody.Planet).get(1));
//        assertEquals("neptune", set.group(AstronomicalBody.Planet).get(2));
//        assertEquals("rigel", set.group(AstronomicalBody.Star).get(1));
//
//        set.group(AstronomicalBody.Star).remove("sun");
//        assertEquals(0, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//        assertEquals("earth", set.group(AstronomicalBody.Planet).get(0));
//        assertEquals("mars", set.group(AstronomicalBody.Planet).get(1));
//        assertEquals("neptune", set.group(AstronomicalBody.Planet).get(2));
//        assertEquals(1, set.group(AstronomicalBody.Star).size());
//        assertEquals("rigel", set.group(AstronomicalBody.Star).get(0));
//
//        set.group(AstronomicalBody.Star).remove("rigel");
//        assertEquals(0, set.group(AstronomicalBody.Satellite).size());
//        assertTrue(set.group(AstronomicalBody.Satellite).isEmpty());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//        assertEquals("earth", set.group(AstronomicalBody.Planet).get(0));
//        assertEquals("mars", set.group(AstronomicalBody.Planet).get(1));
//        assertEquals("neptune", set.group(AstronomicalBody.Planet).get(2));
//        assertEquals(0, set.group(AstronomicalBody.Star).size());
//    }

//    @Test
//    public void should_index_items_in_each_group() throws Exception {
//        GroupSet<AstronomicalBody, String> set = new GroupSet<>();
//        set.group(AstronomicalBody.Satellite).add("moon");
//        set.group(AstronomicalBody.Satellite).add("europe");
//        set.group(AstronomicalBody.Planet).add("europe");
//        set.group(AstronomicalBody.Planet).add("moon");
//
//        assertEquals(0, set.group(AstronomicalBody.Planet).indexOf("europe"));
//        assertEquals(1, set.group(AstronomicalBody.Satellite).indexOf("europe"));
//        assertEquals(-1, set.group(AstronomicalBody.Star).indexOf("europe"));
//    }
//
//    @Test
//    public void should_not_remove_an_item_when_it_is_in_other_group() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//
//        set.group(AstronomicalBody.Star).remove("moon");
//        assertEquals(2, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(2, set.group(AstronomicalBody.Star).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//    }
//
//    @Test
//    public void should_not_add_an_item_if_already_exists() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//
//        set.group(AstronomicalBody.Satellite).add("moon");
//        assertEquals(2, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(2, set.group(AstronomicalBody.Star).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//    }


//    @Test
//    public void should_remove_item_from_its_group_when_exists_in_two_groups() throws Exception {
//        GroupSet<AstronomicalBody, String> set = new GroupSet<>();
//        set.group(AstronomicalBody.Satellite).add("moon");
//        set.group(AstronomicalBody.Satellite).add("europe");
//        set.group(AstronomicalBody.Planet).add("moon");
//        set.group(AstronomicalBody.Planet).add("europe");
//
//        set.group(AstronomicalBody.Planet).remove("moon");
//        set.group(AstronomicalBody.Planet).remove("europe");
//        assertEquals(2, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(0, set.group(AstronomicalBody.Planet).size());
//    }

//    @Test
//    public void should_iterate_a_group() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//        Iterator<String> iterator = set.group(AstronomicalBody.Satellite).iterator();
//        Iterator<String> iterator2 = set.group(AstronomicalBody.Star).iterator();
//        assertTrue(iterator.hasNext());
//        assertEquals("moon", iterator.next());
//        assertTrue(iterator.hasNext());
//        assertEquals("europe", iterator.next());
//        assertFalse(iterator.hasNext());
//        assertEquals(null, iterator.next());
//
//        assertTrue(iterator2.hasNext());
//        assertEquals("sun", iterator2.next());
//        assertTrue(iterator2.hasNext());
//        assertEquals("rigel", iterator2.next());
//        assertFalse(iterator2.hasNext());
//        assertEquals(null, iterator2.next());
//    }

//    @Test
//    public void should_remove_an_item_when_iterating() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//        Iterator<String> iterator = set.group(AstronomicalBody.Satellite).iterator();
//        iterator.next();
//        iterator.remove();
//        iterator.remove();
//        iterator.remove();
//        assertEquals(1, set.group(AstronomicalBody.Satellite).size());
//        assertEquals(2, set.group(AstronomicalBody.Star).size());
//        assertEquals(3, set.group(AstronomicalBody.Planet).size());
//    }
//
//    @Test
//    public void should_iterate_with_forEach() throws Exception {
//        GroupSet<AstronomicalBody, String> set = createSet();
//        List<String> result = new ArrayList<>();
//        set.group(AstronomicalBody.Planet).forEach(result::add);
//        assertEquals(3, result.size());
//    }

//    private GroupSet<AstronomicalBody, String> createSet() {
//        GroupSet<AstronomicalBody, String> set = new GroupSet<>();
//        set.group(AstronomicalBody.Satellite).add("moon");
//        set.group(AstronomicalBody.Satellite).add("europe");
//        set.group(AstronomicalBody.Planet).add("earth");
//        set.group(AstronomicalBody.Planet).add("mars");
//        set.group(AstronomicalBody.Planet).add("neptune");
//        set.group(AstronomicalBody.Star).add("sun");
//        set.group(AstronomicalBody.Star).add("rigel");
//        return set;
//    }
}
