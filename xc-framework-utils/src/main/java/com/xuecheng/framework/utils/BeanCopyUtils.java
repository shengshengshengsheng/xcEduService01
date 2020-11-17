package com.xuecheng.framework.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * description:好房的BeanCopy工具类
 *
 * @author shengsheng
 * @date 2020/9/24 13:03
 */
public class BeanCopyUtils {
    /**
     * BeanCopy的方法
     * 该方法是对org.springframework.beans.BeanUtils.copyProperties(Object source, Object target, String... ignoreProperties)
     * 的封装，旨在解决beanCopy时的空值问题
     * @author shengsheng
     * @date 2020/9/24 13:06
     * @param source the source bean
     * @param target the target bean
     * @return void
    */
    public static void copyPropertiesIgnoreNull(Object source,Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * 获取所有字段为null的属性名
     * 用于BeanUtils.copyProperties()拷贝属性时忽略空值
     * @param source 需要拷贝的source对象
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
