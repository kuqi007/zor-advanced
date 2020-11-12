package com.zor.advanced.spring.ioc.demo.context;

import com.zor.advanced.spring.ioc.demo.annotation.IocResource;
import com.zor.advanced.spring.ioc.demo.annotation.IocService;
import com.zor.advanced.spring.ioc.demo.util.ClassUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现一个简单的IOC
 * 参考 https://blog.csdn.net/qq_18603599/article/details/80725330
 *
 * @author zqq
 * @date 2020/11/12
 */
public class SpringContext {

    ConcurrentHashMap<String, Object> initBean = null;

    private final String path;


    public SpringContext(String path) {
        this.path = path;
    }

    /**
     * 根据beanId获取相应的bean
     */
    public Object getBean(String beanId) throws Exception {
        List<Class> classes = findAnnotationService();
        if (classes.isEmpty()) {
            throw new RuntimeException("no found anything bean is useding initial...");
        }
        initBean = initBean(classes);
        if (initBean == null || initBean.isEmpty()) {
            throw new RuntimeException("initial bean is empty or null");
        }
        Object object = initBean.get(beanId);
        if (object == null) {
            throw new RuntimeException("找不到id为" + beanId + "的bean");
        }

        //初始化属性的依赖
        initAttribute(object);

        return object;

    }

    /**
     * 初始化依赖的属性
     *
     * @param object
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private void initAttribute(Object object) throws Exception {
        //获取object的所有类型
        Class<?> classInfo = object.getClass();
        //获取所有的属性字段
        Field[] fields = classInfo.getDeclaredFields();
        //遍历所有字段
        for (Field field : fields) {
            //查找字段上有依赖的注解
            boolean flag = field.isAnnotationPresent(IocResource.class);
            if (flag) {
                IocResource iocResource = field.getAnnotation(IocResource.class);
                if (iocResource != null) {
                    // 获取属性的 bean id
                    // TODO spring真实是取该变量名称嘛？如果变量名称不和实现类保持一致（首字母不一样）会有什么问题？
                    String beanId = field.getName();
                    // 获取对应的object
                    Object attrObject = getBean(beanId);
                    if (attrObject != null) {
                        // 访问私有字段
                        field.setAccessible(true);
                        // 赋值，将object替换成实例化以后的对象
                        field.set(object, attrObject);
                    }
                }
            }
        }
    }

    /**
     * 初始化bean
     *
     * @param classes
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public ConcurrentHashMap<String, Object> initBean(List<Class> classes) throws IllegalAccessException, InstantiationException {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        String beanId = "";
        for (Class clazz : classes) {
            Object object = clazz.newInstance();
            IocService annotation = (IocService) clazz.getDeclaredAnnotation(IocService.class);
            if (annotation != null) {
                // 如果定义了name属性 以实现的name属性为主否则以默认的规则为主
                String value = annotation.name();
                if (!value.equals("")) {
                    beanId = value;
                } else {
                    // 首字母变成小写
                    beanId = toLowerCaseFirstOne(clazz.getSimpleName());
                }
            }

            //存储值
            map.put(beanId, object);
        }
        return map;
    }


    /**
     * 查找包路径下面所有添加注解的类 @IocService
     *
     * @return
     * @throws Exception
     */
    private List<Class> findAnnotationService() {
        if (path == null || path.equals("")) {
            throw new RuntimeException("scan package address is null or empty..");
        }
        //获取包下面所有的类
        List<Class<?>> classes = ClassUtil.getClasses(path);
        if (classes.size() == 0) {
            throw new RuntimeException("not found service is added annoation for @iocservice");
        }
        List<Class> annotationClasses = new ArrayList<>();
        for (Class clazz : classes) {
            //通过反射机制 查找增加了注解的类
            IocService iocService = (IocService) clazz.getDeclaredAnnotation(IocService.class);
            if (iocService != null) {
                annotationClasses.add(clazz);
            }
        }
        return annotationClasses;
    }


    /**
     * 首字母转换为小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }


}
