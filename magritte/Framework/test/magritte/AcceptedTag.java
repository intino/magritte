package magritte;

import org.junit.Test;

import static magritte.Tag.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptedTag {

    @Test
    public void should_return_tags_for_annotations() throws Exception {
        assertThat(tags(0), is(new Tag[0]));
        assertThat(tags(1), is(new Tag[]{Case}));
        assertThat(tags(2), is(new Tag[]{Root}));
        assertThat(tags(3), is(new Tag[]{Case, Root}));
        assertThat(tags(8), is(new Tag[]{Abstract}));
        assertThat(tags(11), is(new Tag[]{Case,Root,Abstract}));
    }

    @Test
    public void should_return_annotation_for_tags() throws Exception {
        assertThat(flags(new Tag[0]),is(0));
        assertThat(flags(new Tag[]{Case}),is(1));
        assertThat(flags(new Tag[]{Root}),is(2));
        assertThat(flags(new Tag[]{Case, Root}),is(3));
        assertThat(flags(new Tag[]{Abstract}),is(8));
        assertThat(flags(new Tag[]{Case, Root, Abstract}),is(11));
    }
}
