package esgi.annotation.fr;


import esgi.controller.fr.AlertMessage;
import esgi.controller.fr.ControllerApi;
import com.google.gson.Gson;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.lang.Object;
import java.io.IOException;
import java.lang.reflect.Field;


public class ControllerAnnotation {


/*
    Pour l'annotation BeanFromDataBase
    Cette annotation nous permet d'initialiser notre objet avec les informations en provenances de la base de données

 */

    public Object getBean(String url, Class clazz, Class objectTargetClass) throws IllegalAccessException, InstantiationException {
        try {
            Field[] tabField = clazz.getDeclaredFields();
            for (Field field : tabField) {
                BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);

                if (aBean != null && field.getType().getName().equals(objectTargetClass.getName())) {

                    Class<?> fieldType = field.getType();
                    String jsonObject = new ControllerApi().get(url);
                    Object newObject = new Gson().fromJson(jsonObject, fieldType);
                    return newObject;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectTargetClass.newInstance();
    }
    
    /*
        Destiné à l'annotation NumberValue
        Qui nous permet de vérifier qu'un champ est un nombre ou non

     */

    public boolean isNumber(Class clazz, TextField txtField) {

        Field[] tabField = clazz.getDeclaredFields();
        for (Field field : tabField) {
            BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);
            try {
                TextField textF = (TextField) field.get(txtField);
                return textF.getText().matches("[0-9]+(.[0-9])*");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /*
        Destiné à l'annotation StringValue
        Qui nous permet de vérifier qu'un champ est une chaine de caractere ou non


     */

    public boolean isString(Class<?> clazz, TextField tf) {

        Field[] tabField = clazz.getDeclaredFields();
        for (Field field : tabField) {
            BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);

            try {
                TextField textF = (TextField) field.get(tf);
                return textF.getText().matches("[A-Za-z-_]+");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


    public boolean verifyPassword(Class<?> clazz, TextField textField) {

        Field[] tabField = clazz.getDeclaredFields();
        for (Field field : tabField) {
            BeanFromDataBase aBean = field.getAnnotation(BeanFromDataBase.class);

            try {
                TextField textF = (TextField) field.get(textField);
                return textF.getText().matches("[A-Za-z-_]+");

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return false;
    }


}
