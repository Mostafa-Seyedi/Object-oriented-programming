package diet;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {

	/**
	 * Define a new raw material.
	 * The nutritional values are specified for a conventional 100g quantity
	 * @param name unique name of the raw material
	 * @param calories calories per 100g
	 * @param proteins proteins per 100g
	 * @param carbs carbs per 100g
	 * @param fat fats per 100g
	 */
	HashMap<String, NutritionalElement> rawMaterials = new HashMap<>(); 
	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat) {
		RawMaterial rawMaterial = new RawMaterial(name, calories, proteins, carbs, fat); 
		rawMaterials.put(name, rawMaterial); 
	}



	/**
	 * Retrieves the collection of all defined raw materials
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials() {
		return rawMaterials.values().stream().sorted(Comparator.comparing(NutritionalElement::getName)).toList(); 
	}

	/**
	 * Retrieves a specific raw material, given its name
	 * @param name  name of the raw material
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRawMaterial(String name) {
		return rawMaterials.get(name);
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * @param name unique name of the product
	 * @param calories calories for a product unit
	 * @param proteins proteins for a product unit
	 * @param carbs carbs for a product unit
	 * @param fat fats for a product unit
	 */
	HashMap<String, NutritionalElement> preProduct = new HashMap<>(); 
	public void defineProduct(String name, double calories, double proteins, double carbs, double fat) {
		Products product = new Products(name, calories, proteins, carbs, fat); 
		preProduct.put(name, product); 
	}

	/**
	 * Retrieves the collection of all defined products
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products() {
		return preProduct.values().stream().sorted(Comparator.comparing(NutritionalElement::getName)).toList();
	}

	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name) {
		return preProduct.get(name);
	}

	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * @return the newly created Recipe object
	 */
	HashMap<String, NutritionalElement> recipes = new HashMap<>(); 
	public Recipe createRecipe(String name) {
		var recipe = new Recipe(name, this);
		recipes.put(name, recipe);
		return recipe;
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes() {
		return recipes.values().stream().sorted(Comparator.comparing(NutritionalElement::getName)).toList();
	}

	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name) {
		return recipes.get(name);
	}

	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu menu = new Menu(name, this);
		return menu;
	}
}