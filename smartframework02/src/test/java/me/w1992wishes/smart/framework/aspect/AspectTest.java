package me.w1992wishes.smart.framework.aspect;


import me.w1992wishes.smart.framework.HelperLoader;
import me.w1992wishes.smart.framework.helper.BeanHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by w1992wishes
 * on 2017/12/29.
 */
public class AspectTest {

    @Before
    public void init(){
        HelperLoader.init();
    }

    @Test
    public void testAop(){
        PersonController p = BeanHelper.getBean(PersonController.class);
        p.eat();
    }

}
