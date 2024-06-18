package diet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
	Food food;

	private double calories;
	private double proteins;
	private double carbs;
	private double fat;
	/**
	 * Adds the given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	
	private String name;
	public Recipe(String name, Food food){ 
		this.name = name; 
		this.food = food;
	}

	HashMap<String, Double> ingredients = new HashMap<>(); 
	public Recipe addIngredient(String material, double quantity) {
		ingredients.put(material, quantity); 
		this.updateNutritionalValues();
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public double getCalories() {
		return this.calories;
	}
	

	@Override
	public double getProteins() {
		return this.proteins;
	}

	@Override
	public double getCarbs() {
		return this.carbs;
	}

	@Override
	public double getFat() {
		return this.fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}


	public void updateNutritionalValues(){ 
		double calories = 0.0; 
		double proteins = 0.0;
		double carbs = 0.0;
		double fat = 0.0;
		double quantitySum = 0.0;

		for(String material : this.ingredients.keySet()){ 
			double quantity = this.ingredients.get(material); 
			NutritionalElement element = this.food.getRawMaterial(material);

			calories += element.getCalories() *	quantity;
			proteins += element.getProteins() * quantity; 
			carbs += element.getCarbs() * quantity; 
			fat += element.getFat() * quantity; 
			quantitySum += quantity; 
		}

		this.calories = calories / quantitySum; 
		this.proteins = proteins / quantitySum; 
		this.carbs = carbs / quantitySum; 
		this.fat = fat / quantitySum;
	}

	@Override
	public String toString() {
		String result = "";
		
		List<String> sortedIngredient = ingredients.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue
		(Comparator.reverseOrder()).thenComparing(Map.Entry.<String, Double>comparingByKey(Comparator.reverseOrder()))).
		map(Map.Entry::getKey).toList();

		for(String ingredient : sortedIngredient){ 
			result += ingredient + "\n";
		}
		return result;
	}

	
	
	public void updateNuteritionalValues(){ 
		double calories = 0.0;
		double proteins = 0.0; 
		double carbs = 0.0; 
		double fat = 0.0;
		double quantitySum = 0.0;

		for (var material : ingredients.keySet()) {
			double quantity = ingredients.get(material);
			NutritionalElement element = this.food.getRawMaterial(material);
			
			calories += element.getCalories() * quantity; 
			proteins += element.getProteins() * quantity; 
			carbs += element.getCarbs() * quantity; 
			fat += element.getFat() * quantity; 
			quantitySum += quantity; 
		}

		// here we are deviding the whole amount of protein in our recipe by the whole amount of material in our 
		// recipe and we get "amount of protein per 100g" 
		this.calories = calories / quantitySum; 
		this.proteins = proteins / quantitySum; 
		this.carbs = carbs / quantitySum; 
		this.fat = fat / quantitySum; 
	}
	

	
}
