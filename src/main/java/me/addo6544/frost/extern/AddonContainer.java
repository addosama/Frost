package me.addo6544.frost.extern;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class AddonContainer {
    URLClassLoader urlClassLoader = null;
    Class<?> mainClass = null;
    String path;

    public static String id;

    public AddonContainer(String path){
        this.path = path;
    }

    public boolean load(){
        boolean result = false;
        try {
            //通过URLClassLoader加载外部jar
            urlClassLoader = new URLClassLoader(new URL[]{new URL(path)});
            //获取外部jar里面的具体类对象
            mainClass = urlClassLoader.loadClass("FrostExtern");
            //创建对象实例
            Object instance = mainClass.newInstance();
            //获取实例当中的方法名为show，参数只有一个且类型为string的public方法
            Method initMethod = mainClass.getMethod("init");
            //传入实例以及方法参数信息执行这个方法
            result = (boolean) initMethod.invoke(instance);

            Method getID = mainClass.getMethod("getID");
            id = (String) getID.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean unload(){
        //卸载关闭外部jar
        try {
            urlClassLoader.close();
            return true;
        } catch (IOException e) {
            System.out.println("关闭外部jar:"+id+"失败："+e.getMessage());
            return false;
        }
    }
}
