package org.dieschnittstelle.jee.esa.basics;


import org.dieschnittstelle.jee.esa.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.jee.esa.basics.annotations.DisplayAs;
import org.dieschnittstelle.jee.esa.basics.annotations.StockItemProxyImpl;

import java.lang.reflect.Field;

import static org.dieschnittstelle.jee.esa.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			;
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

	/*
	 * UE BAS2 
	 */
	private static void showAttributes(Object consumable) {
		show("class is: " + consumable.getClass());
		Class objClass = consumable.getClass();
		String res = "{" + objClass.getSimpleName() + " ";
		Field[] fields = objClass.getDeclaredFields();
		for(int i = 0; i < fields.length; i++){
			fields[i].setAccessible(true);
			boolean succesfullyAccessField = false;
			try{
				if(fields[i].isAnnotationPresent(DisplayAs.class)){
				    DisplayAs annotation = fields[i].getAnnotation(DisplayAs.class);
				    res += annotation.value();
				}else{
					res += fields[i].getName();
				}
				res += ":" + fields[i].get(consumable).toString();
				succesfullyAccessField = true;
			}catch (IllegalAccessException iae){
				show("cannot access field '" + fields[i].getName() + "'");
			}
			if(succesfullyAccessField){
				if(i < (fields.length - 1))
					res += ", ";
			}
			fields[i].setAccessible(false);
		}
		res += "}";
		show(res);
	}

}
