package package_ha01;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class ModelCreation
{
	@Test
	public void createModel()
	{
		ClassModel model = new ClassModel("package_ha01");

		Clazz delivery = model.createClazz("DeliveryService");
		Clazz caterer = model.createClazz("Caterer");
		Clazz pizza = model.createClazz("PizzaShop");
		Clazz burger = model.createClazz("BurgerBude");
		Clazz doener = model.createClazz("DoenerLaden");
		
		caterer.withKidClazzes(pizza);
		caterer.withKidClazzes(burger);
		caterer.withKidClazzes(doener);

		delivery.withUniDirectional(caterer, "subcontractor", Cardinality.ONE);
		
		delivery.createMethod("deliver", new Parameter(DataType.STRING).with("foodNo"), new Parameter(DataType.STRING).with("addr"));
		caterer.createMethod("deliver", new Parameter(DataType.STRING).with("foodNo"), new Parameter(DataType.STRING).with("addr"));
		
		model.generate("src/main/java");
	}

}
