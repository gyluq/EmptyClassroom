package com.musen.EmptyClassRoom.utils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author GuYeLuo
 * @create 2022/9/3 17:19
 */
public class MapUtils {

    /**
     * map转换为对象
     *
     * @param source source
     * @param target target
     * @param <T>    返回值类型
     * @return 返回值
     * @throws Exception newInstance可能会抛出的异常
     */
    public static <T> T mapToObj(Map source, Class<T> target) throws Exception {
        Field[] fields = target.getDeclaredFields();
        T o = target.getDeclaredConstructor().newInstance();
        for (Field field : fields) {
            Object val;
            if ((val = source.get(field.getName())) != null) {
                field.setAccessible(true);
                field.set(o, val);
            }
        }
        return o;
    }
}
