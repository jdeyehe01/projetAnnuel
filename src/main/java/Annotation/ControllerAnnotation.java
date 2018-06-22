package Annotation;


import Controller.ControllerApi;
import com.google.gson.Gson;
import java.lang.Object;
import java.io.IOException;
import java.lang.reflect.Field;


public class ControllerAnnotation {

    //Annotation Inject
public static void inject() {


    try {
        Class c = Class.forName("Controller.ControllerConf");
        Field[] tabField = c.getDeclaredFields();
        Object o = c.newInstance();

        for(Field f : tabField ){
            Inject i = f.getAnnotation(Inject.class);
            if (i != null && !isNullField(f,o)){
                Class type = f.getType();
                Object obj = type.newInstance();
                obj = "Jean";
                f.setAccessible(true);
                f.set(o,obj);

            }
        }

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    }


}

/*
    Annotation BeanFromDataBase

 */
public static void getBean(String url , Class clazz , Object objectTarget) throws IllegalAccessException, InstantiationException {
    try {
        Field[] tabField = clazz.getDeclaredFields();
        objectTarget = clazz.newInstance();
    for(Field field : tabField){
        BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);

        if(aBean != null){
            field.setAccessible(true);
            Class<?> fieldType = field.getType();
            String jsonObject = new ControllerApi().get(url);
            Object newObject = new Gson().fromJson(jsonObject,fieldType);

            field.set(objectTarget,newObject);
        }
    }
    } catch (IOException e) {
        e.printStackTrace();
    }
}



public static boolean isNullField(Field f, Object o){

    try {
        Object currentObjet = f.get(o);
        if(currentObjet != null){
            return true;
        }
        return false;
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }


    return false;
}


}
