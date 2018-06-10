package annotation;


import Controller.ControllerApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Field;


public class ControllerAnnoation {

    //Annotation Inject
public static void inject() {


    try {
        Class<?> c = Class.forName("Controller.ControllerConf");
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
public static void getLastBean(Class <? extends Object> clazzObject) throws IllegalAccessException, InstantiationException {
    try {
    Field[] tabField = clazzObject.getDeclaredFields();
    Object o = null;
    Object object = clazzObject.newInstance();
    for(Field field : tabField){
        BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);

        if(aBean != null){
            field.setAccessible(true);
            String url = aBean.url();
            Class<?> className = aBean.className();


            String jsonObject = new ControllerApi().get(url);

             o = new Gson().fromJson(jsonObject,className);


            field.set(object,o);



        }
    }
    } catch (IOException e) {
        e.printStackTrace();
    }
}



public static boolean isNullField(Field f, Object o){

    try {
        System.out.println(o.toString());
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
