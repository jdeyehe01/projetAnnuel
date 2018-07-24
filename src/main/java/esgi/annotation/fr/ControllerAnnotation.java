package esgi.annotation.fr;


import esgi.controller.fr.ControllerApi;
import com.google.gson.Gson;

import java.lang.Object;
import java.io.IOException;
import java.lang.reflect.Field;


public class ControllerAnnotation {

    //Annotation Inject
/*
    Annotation BeanFromDataBase

 */
public Object getBean(String url , Class clazz , Class objectTargetClass) throws IllegalAccessException, InstantiationException {
    try {
        Field[] tabField = clazz.getDeclaredFields();



    for(Field field : tabField){
        BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);

        if(aBean != null && field.getType().getName().equals(objectTargetClass.getName()) ){

            Class<?> fieldType = field.getType();
            String jsonObject = new ControllerApi().get(url);
            Object newObject = new Gson().fromJson(jsonObject,fieldType);
            return newObject;
        }
    }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return objectTargetClass.newInstance();
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
