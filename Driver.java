import java.util.HashMap;
import java.util.Map;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		coffeeMachine cm = new coffeeMachine();
		cm.getStock();
		System.out.println("==========");
		cm.addStock("hot_water", 100);
		cm.addStock("sugar_syrup", 200);
		cm.getStock();
		System.out.println("==========");
		cm.makeBeverage("black_tea");
		System.out.println("==========");
		cm.getStock();
		System.out.println("==========");
		cm.makeBeverage("hot_coffee");
		System.out.println("==========");
		cm.makeBeverage("hot_coffee");
		System.out.println("==========");
		cm.makeBeverage("hot_coffee");
		System.out.println("==========");
		cm.makeBeverage("black_tea");
		System.out.println("==========");

	}

}

enum ingredient {
	hot_water,
	hot_milk,
	coffee_decoction,
	sugar_syrup,
	tea_leaves_syrup,
	green_tea_syrup
}

class coffeeMachine{
	Map<ingredient,Integer> ingredientMap;
	public coffeeMachine() {
		Map<ingredient,Integer> ingredientMap = new HashMap<ingredient,Integer>();
		ingredientMap.put(ingredient.hot_water, 500);
		ingredientMap.put(ingredient.hot_milk, 500);
		ingredientMap.put(ingredient.coffee_decoction, 100);
		ingredientMap.put(ingredient.sugar_syrup, 100);
		ingredientMap.put(ingredient.tea_leaves_syrup, 100);
		ingredientMap.put(ingredient.green_tea_syrup, 100);
		this.setIngredientMap(ingredientMap);
	}
	public Map<ingredient, Integer> getIngredientMap() {
		return ingredientMap;
	}

	public void setIngredientMap(Map<ingredient, Integer> ingredientMap) {
		this.ingredientMap = ingredientMap;
	}
	public void makeBeverage(String beverage){
		beverage bv =  new beverageMaker().getBeverage(beverage);
		bv.updateBaseMapAfterCompletion(this.ingredientMap);
	}
	public void addStock(String givenIngredient, int quantity){
		ingredient ing = ingredient.valueOf(givenIngredient);
		if(ingredientMap.containsKey(ing))ingredientMap.put(ing, ingredientMap.get(ing)+quantity);
		else ingredientMap.put(ing, quantity);
	}
	public void getStock(){
		for(ingredient key: ingredientMap.keySet()){
			System.out.println(key.name()+" "+ingredientMap.get(key));
		}
	}
}

class beverage{
	Map<ingredient,Integer> ingredientMap;

	public Map<ingredient, Integer> getIngredientMap() {
		return ingredientMap;
	}

	public void setIngredientMap(Map<ingredient, Integer> ingredientMap) {
		this.ingredientMap = ingredientMap;
	}
	
	public void updateBaseMapAfterCompletion(Map<ingredient, Integer> baseMap){
		boolean couldNotPrepare = false;
		for(ingredient key: ingredientMap.keySet()){
			if(!baseMap.containsKey(key)){
				couldNotPrepare = true;
				System.out.println("This ingredient is not present in the machine");
			}else if(baseMap.get(key) < ingredientMap.get(key)){
				couldNotPrepare = true;
				System.out.println("The ingredient "+key.name()+" is not present in sufficient amount");
			}
		}
		if(!couldNotPrepare) {
			System.out.println("Your beverage is being prepared");
		}
		else return;
		for(ingredient key: ingredientMap.keySet()){
			baseMap.put(key, baseMap.get(key)-ingredientMap.get(key));
		}
	}
}

class hot_tea extends beverage{
	public hot_tea(){
		Map<ingredient,Integer> ingredientMap = new HashMap<ingredient,Integer>();
		ingredientMap.put(ingredient.hot_water, 200);
		ingredientMap.put(ingredient.hot_milk, 100);
		ingredientMap.put(ingredient.sugar_syrup, 30);
		ingredientMap.put(ingredient.tea_leaves_syrup, 30);
		this.setIngredientMap(ingredientMap);
	}
}
class hot_coffee extends beverage{
	public hot_coffee(){
		Map<ingredient,Integer> ingredientMap = new HashMap<ingredient,Integer>();
		ingredientMap.put(ingredient.hot_water, 100);
		ingredientMap.put(ingredient.hot_milk, 400);
		ingredientMap.put(ingredient.coffee_decoction, 50);
		ingredientMap.put(ingredient.sugar_syrup, 30);
		this.setIngredientMap(ingredientMap);
	}
}
class black_tea extends beverage{
	public black_tea(){
		Map<ingredient,Integer> ingredientMap = new HashMap<ingredient,Integer>();
		ingredientMap.put(ingredient.hot_water, 300);
		ingredientMap.put(ingredient.sugar_syrup, 20);
		ingredientMap.put(ingredient.tea_leaves_syrup, 30);
		this.setIngredientMap(ingredientMap);
	}
}
class green_tea extends beverage{
	public green_tea(){
		Map<ingredient,Integer> ingredientMap = new HashMap<ingredient,Integer>();
		ingredientMap.put(ingredient.hot_water, 200);
		ingredientMap.put(ingredient.sugar_syrup, 20);
		ingredientMap.put(ingredient.green_tea_syrup, 30);
		this.setIngredientMap(ingredientMap);
	}
}
class beverageMaker{
	public beverage getBeverage(String s) {
		if(s.equals("hot_tea"))
			return new hot_tea();
		else if(s.equals("hot_coffee"))
			return new hot_coffee();
		else if(s.equals("black_tea"))
			return new black_tea();
		else
			return new green_tea();
	}
}

