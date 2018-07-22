package Annotation;


import Controller.ControllerApi;
import com.google.gson.Gson;
import java.lang.Object;
import java.io.IOException;
import java.lang.reflect.Field;


public class ControllerAnnotation {

    //Annotation Inject
/*
    Annotation BeanFromDataBase

 */
public static void getBean(String url , Class clazz , Object objectTarget) throws IllegalAccessException, InstantiationException {
    try {
        Field[] tabField = clazz.getDeclaredFields();
        objectTarget = clazz.newInstance();
    for(Field field : tabField){
        Annotation.BeanFromDataBase aBean = field.getAnnotation(Annotation.BeanFromDataBase.class);

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
