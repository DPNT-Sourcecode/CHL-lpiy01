package befaster.solutions.CHL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import befaster.runner.SolutionNotImplementedException;

public class CheckliteSolution
{
	private final static int ILLEGAL_INPUT = -1;
	
	private HashMap<String, Product> priceTable = buildPriceTable();
	
	/**
	 * Build the Supermarket's price table.
	 * 
	 * @return Supermarket's price table.
	 */
	private HashMap<String, Product> buildPriceTable()
	{
		HashMap<String, Product> priceTable = new HashMap<String, Product>();
		final Product productA = new Product("A", 50);
		productA.addOfferToProduct(new OfferMultiBuyPrice(5, 200));
		productA.addOfferToProduct(new OfferMultiBuyPrice(3, 130));
		priceTable.put("A", productA);
		
		
		priceTable.put("B", new Product("B", 30));
		priceTable.put("C", new Product("C", 20));
		priceTable.put("D", new Product("D", 15));
		return priceTable;
	}
	
	/**
	 * Reset the quantities stored in the price table.
	 */
	private void resetQuantitiesInPriceTable()
	{
    	Iterator<Map.Entry<String, Product>> iterator = priceTable.entrySet().iterator();
    	while (iterator.hasNext())
    	{
    		Map.Entry<String, Product> entry = iterator.next();
    		entry.getValue().resetQuanityInBasket();
    	}
	}
	
	/**
	 * Calculate price of list of SKUs , or return -1 if any invalid characters.
	 */
    public Integer checklite(String skus)
    {
    	if (skus == null) return ILLEGAL_INPUT;
    	if (skus.length() == 0) return 0;
    	
    	resetQuantitiesInPriceTable();
    	
    	// Loop through the list of SKUS , and set quantities for each product.
    	for (int i = 0; i < skus.length(); i++)
    	{
    		final String sku = Character.toString(skus.charAt(i));
    		Product product = priceTable.get(sku);
    		if (product == null) return ILLEGAL_INPUT;
    		product.incrementQuanityInBasket();
    	}
    	
    	// Calculate grand total using quantities for each product.
    	int grandTotal = 0;
    	Iterator<Map.Entry<String, Product>> iterator = priceTable.entrySet().iterator();
    	while (iterator.hasNext())
    	{
    		Map.Entry<String, Product> entry = iterator.next();
    		grandTotal += entry.getValue().calculateTotalPrice();
    	}
    	return grandTotal;
    }
}

