package sample.com.projetAnnuel.controller;

import java.lang.reflect.Field;
import java.text.Annotation;
import java.util.ArrayList;

import sample.com.projetAnnuel.annotation.PropertyLabel;

public class Controller {
	
	
	public ArrayList<Field> getFields(Class<?> c) {
		
		Field[] fields = c.getDeclaredFields();
		ArrayList<Field> arrayListField = new ArrayList<Field>();
		
		for(Field f : fields) {
			
			if(f.isAnnotationPresent(PropertyLabel.class)) {
				arrayListField.add(f);
			}
			
		}
		return arrayListField;
	}
}
