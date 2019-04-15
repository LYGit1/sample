package com.springboot.sample.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Map和Object互转
 * 利用JAVA的反射机制
 */
public class MOUtil {

    /**
     * map转对象
     * @param map
     * @param beanClass
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object MapToObject(Map<String,Object> map,Class beanClass) throws IllegalAccessException, InstantiationException {
        if(null == map)
            return null;
        Object object = beanClass.newInstance();
        //利用反射获取对象属性
        Field[] fields = beanClass.getDeclaredFields();
        for(Field field:fields){
            //判断是否是静态属性或者常量，如果是，则不赋值
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod))
                continue;
            //给对象赋值
            field.setAccessible(true);//如果是私有属性，授权
            if(field.getType().isInstance(map.get(field.getName()))){
                field.set(object,map.get(field.getName()));
            }else{
                if(Long.class.isInstance(map.get(field.getName())) ||
                    Integer.class.isInstance(map.get(field.getName())) ||
                    Byte.class.isInstance(map.get(field.getName()))){
                    if("java.lang.Integer".equals(field.getType().getName()))
                        field.set(object,Integer.valueOf(map.get(field.getName()).toString()));
                    if("java.lang.Long".equals(field.getType().getName()))
                        field.set(object,Long.valueOf(map.get(field.getName()).toString()));
                    if("java.lang.Byte".equals(field.getType().getName()))
                        field.set(object,Byte.valueOf(map.get(field.getName()).toString()));
                }else if(Double.class.isInstance(map.get(field.getName())) ||
                    Float.class.isInstance(map.get(field.getName()))){
                    if("java.lang.Double".equals(field.getType().getName()))
                        field.set(object,Double.valueOf(map.get(field.getName()).toString()));
                    if("java.lang.Float".equals(field.getType().getName()))
                        field.set(object,Float.valueOf(map.get(field.getName()).toString()));
                }else{
                    throw new IllegalArgumentException("非数字类型");
                }
            }
        }
        return object;
    }

    /**
     * 对象转map
     * @param object
     * @return
     */
    public static Map<String,Object> objectToMap(Object object) throws IllegalAccessException {
        if(null == object)
            return null;
        Map<String,Object> map = new HashMap<String,Object>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            map.put(field.getName(),field.get(object));
        }
        return map;
    }
}
