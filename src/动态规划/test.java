package 动态规划;

import sun.misc.Unsafe;

import java.io.Console;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {

    public class test1{}

    public static void main(String[] args) throws ReflectiveOperationException {

//        Class c = Object.class;
        //Constructor constructors = c.getDeclaredConstructor();
        //String modifiers = Modifier.toString(c.getModifiers());
        //System.out.println(modifiers);
//        Method[] declaredMethods = c.getDeclaredMethods();
//        for (Method m : declaredMethods) {
//            Class type = m.getReturnType();
//            String name = m.getName();
//            String modifiers = Modifier.toString(m.getModifiers());
//
//            System.out.println(modifiers + "=====" + type.getName() + "=====" + name);
//        }
//        System.out.println(c.getDeclaredConstructor());
        Method method = Object.class.getMethod("toString");
        Object o = new Object();
        System.out.println(method.invoke(o));

        List<String> list =new ArrayList<>();




    }
}
