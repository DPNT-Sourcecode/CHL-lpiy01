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
	 * +------+-------+------------------------+
	 * | Item | Price | Special offers         |
	 * +------+-------+------------------------+
	 * | A    | 50    | 3A for 130, 5A for 200 |
	 * | B    | 30    | 2B for 45              |
	 * | C    | 20    |                        |
	 * | D    | 15    |                        |
	 * | E    | 40    | 2E get one B free      |
	 * | F    | 10    | 2F get one F free      |
	 * | G    | 20    |                        |
	 * | H    | 10    | 5H for 45, 10H for 80  |
	 * | I    | 35    |                        |
	 * | J    | 60    |                        |
	 * | K    | 80    | 2K for 150             |
	 * | L    | 90    |                        |
	 * | M    | 15    |                        |
	 * | N    | 40    | 3N get one M free      |
	 * | O    | 10    |                        |
	 * | P    | 50    | 5P for 200             |
	 * | Q    | 30    | 3Q for 80              |
	 * | R    | 50    | 3R get one Q free      |
	 * | S    | 30    |                        |
	 * | T    | 20    |                        |
	 * | U    | 40    | 3U get one U free      |
	 * | V    | 50    | 2V for 90, 3V for 130  |
	 * | W    | 20    |                        |
	 * | X    | 90    |                        |
	 * | Y    | 10    |                        |
	 * | Z    | 50    |                        |
	 * +------+-------+------------------------+
	 * 
	 * @return Supermarket's price table.
	 */
	private HashMap<String, Product> buildPriceTable()
	{
		Product[] productTable = new Product[] {
			new Product("A", 50),  new Product("B", 30),  new Product("C", 20),  new Product("D", 15),  new Product("E", 40),
			new Product("F", 10),  new Product("G", 20),  new Product("H", 10),  new Product("I", 35),  new Product("J", 60),
			new Product("K", 80),  new Product("L", 90),  new Product("M", 15),  new Product("N", 40),  new Product("O", 10),
			new Product("P", 50),  new Product("Q", 30),  new Product("R", 50),  new Product("S", 30),  new Product("T", 20),
			new Product("U", 40),  new Product("V", 50),  new Product("W", 20),  new Product("X", 90),  new Product("Y", 10),
			new Product("Z", 50)
		};
		// Product A offers
		productTable[0].addOfferToProduct(new OfferMultiBuyPrice(5, 200));
		productTable[0].addOfferToProduct(new OfferMultiBuyPrice(3, 130));
		// Product B offers
		productTable[1].addOfferToProduct(new OfferMultiBuyPrice(2, 45));
		// Product E offers
		productTable[4].addOfferToProduct(new OfferBuyXGetYFree(2, productTable[1]));
		// Product F offers
		productTable[5].addOfferToProduct(new OfferBuyXGetYFree(2, productTable[5]));
		// Product H offers
		productTable[7].addOfferToProduct(new OfferMultiBuyPrice(10, 80));
		productTable[7].addOfferToProduct(new OfferMultiBuyPrice(5, 45));
		// Product K offers
		productTable[10].addOfferToProduct(new OfferMultiBuyPrice(2, 150));
		// Product N offers
		productTable[13].addOfferToProduct(new OfferBuyXGetYFree(3, productTable[12]));
		// Product P offers
		productTable[15].addOfferToProduct(new OfferMultiBuyPrice(5, 200));
		// Product Q offers
		productTable[16].addOfferToProduct(new OfferMultiBuyPrice(3, 80));
		// Product R offers
		productTable[17].addOfferToProduct(new OfferBuyXGetYFree(3, productTable[16]));
		// Product U offers
		productTable[20].addOfferToProduct(new OfferBuyXGetYFree(3, productTable[20]));
		// Product V offers
		productTable[21].addOfferToProduct(new OfferMultiBuyPrice(3, 130));
		productTable[21].addOfferToProduct(new OfferMultiBuyPrice(2, 90));
				
		HashMap<String, Product> priceTable = new HashMap<String, Product>();
		for (Product product : productTable)
		{
			priceTable.put(product.getSku(), product);
		}
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
    		entry.getValue().resetQuantityToBePaidFor();
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
    		product.incrementQuantityToBePaidFor();
    	}
    	
    	// Apply "buy quantity X , get product Y free" offers in each product.
    	Iterator<Map.Entry<String, Product>> iterator = priceTable.entrySet().iterator();
    	while (iterator.hasNext())
    	{
    		Map.Entry<String, Product> entry = iterator.next();
    		entry.getValue().applyOfferBuyXGetYFree();
    	}
    	
    	// Calculate grand total using quantities for each product.
    	int grandTotal = 0;
    	iterator = priceTable.entrySet().iterator();
    	while (iterator.hasNext())
    	{
    		Map.Entry<String, Product> entry = iterator.next();
    		grandTotal += entry.getValue().calculateTotalPrice();
    	}
    	return grandTotal;
    }
}


