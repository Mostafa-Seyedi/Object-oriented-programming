package diet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {

	double calories = 0.0; 
	double proteins = 0.0;
	double carbs = 0.0;
	double fat = 0.0;
	double quantitySum = 0.0;
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	private String name; 
	Food food;

	public Menu(String name, Food food) {
		this.name = name;
		this.food = food; 
	}

	HashMap<String, Double> recipes = new HashMap<>(); 
    public Menu addRecipe(String recipe, double quantity) {
		recipes.put(recipe, quantity);
		this.updateNutritionalValues();
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	List<String> products = new ArrayList<>(); 
    public Menu addProduct(String product) {
		products.add(product); 
		this.updateNutritionalValues();
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return this.calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return this.proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return this.carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return this.fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return false;
	}

	public void updateNutritionalValues(){ 
		double calories = 0.0; 
		double proteins = 0.0;
		double carbs = 0.0;
		double fat = 0.0;
		double quantitySum = 0.0;

		for(String recipe : this.recipes.keySet()){ 
			double quantity = this.recipes.get(recipe); 
			NutritionalElement element = this.food.getRecipe(recipe);

			calories += element.getCalories();
			proteins += element.getProteins(); 
			carbs += element.getCarbs(); 
			fat += element.getFat(); 
			quantitySum += quantity; 
		}
		
		double per100g = quantitySum / 100;

		calories = calories * per100g; 
		proteins = proteins * per100g; 
		carbs = carbs * per100g; 
		fat = fat * per100g; 

		for(String product : this.products){ 
			NutritionalElement element = this.food.getProduct(product);

			calories += element.getCalories();
			proteins += element.getProteins(); 
			carbs += element.getCarbs(); 
			fat += element.getFat();  
		}
		

		this.calories = calories; 
		this.proteins = proteins; 
		this.carbs = carbs;
		this.fat = fat;

	}
}