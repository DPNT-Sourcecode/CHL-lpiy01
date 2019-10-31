package befaster.solutions.CHL;

public class Product
{
	private final String sku;
	private final int standardPrice;
	private final int offerQuantity;
	private final int offerPrice;
	private int quanityInBasket;
	
	/**
	 * Construct a new Product with SKU , standard price , offer quantity and offer price.
	 * 
	 * @param sku Product SKU;
	 * @param standardPrice Standard price.
	 * @param offerQuantity Offer quantity , or zero if no special offers.
	 * @param offerPrice Offer price , or zero if no special offers.
	 */
	public Product(final String sku, final int standardPrice, final int offerQuantity, final int offerPrice)
	{
		this.sku = sku;
		this.standardPrice = standardPrice;
		this.offerQuantity = offerQuantity;
		this.offerPrice = offerPrice;
		this.quanityInBasket = 0;
	}
	
	/**
	 * Construct a new Product with SKU and standard price.
	 * 
	 * @param sku Product SKU;
	 * @param standardPrice Standard price.
	 */
	public Product(final String sku, final int standardPrice)
	{
		this(sku, standardPrice, 0, 0);
		this.quanityInBasket = 0;
	}
	
	/**
	 * Increment the quantity in Basket.
	 */
	public void incrementQuanityInBasket()
	{
		quanityInBasket++;
	}
	
	/**
	 * Reset quantity in Basket.
	 */
	public void resetQuanityInBasket()
	{
		quanityInBasket = 0;
	}
	
	/**
	 * Calculate the total price for this product based on quantity in Basket , standard price , offer quantity and offer price.
	 */
	public int calculateTotalPrice()
	{
		if (offerQuantity == 0 || offerPrice == 0)
		{
			return quanityInBasket * standardPrice;
		}
		else
		{
			final int numberOfSpecialOffers = quanityInBasket / offerQuantity;
			final int numberOfStandards = quanityInBasket % offerQuantity;
			return (numberOfSpecialOffers * offerPrice) + (numberOfStandards * standardPrice);
		}
	}
}
