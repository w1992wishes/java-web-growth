package me.w1992wishes.smart.framework;

import me.w1992wishes.smart.framework.helper.BeanHelper;
import me.w1992wishes.smart.framework.helper.ClassHelper;
import me.w1992wishes.smart.framework.helper.ControllerHelper;
import me.w1992wishes.smart.framework.helper.IocHelper;
import me.w1992wishes.smart.framework.util.ClassUtil;

/**
 * 加载相应的Helper类，第一次访问类时，就会加载static块，这里只是让加载更集中
 *
 * Created by w1992wishes
 * on 2017/12/22.
 */
public final class HelperLoader {

    public static void init(){
        Class<?> [] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> cls : classList){
            ClassUtil.loadClass(cls.getName(), false);
        }
    }

}
