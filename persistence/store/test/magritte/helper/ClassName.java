package magritte.helper;

import org.junit.Test;

/**
 * Created by josejuan on 25/5/15.
 */
public class ClassName {

    @Test
    public void testName() throws Exception {
        Class<?> shopModel = Class.forName("magritte.ShopModel");
        System.out.println(shopModel);

    }
}
