package befaster.solutions.CHL;

public class Product
{
	private final String sku;
	private final int price;
	private int quanityInBasket;
	
	/**
	 * Construct a new Product with a SKU and a price.
	 * 
	 * @param sku Product SKU;
	 * @param price Product price.
	 */
	public Product(final String sku, final int price)
	{
		this.sku = sku;
		this.price = price;
		this.quanityInBasket = 0;
	}

}

