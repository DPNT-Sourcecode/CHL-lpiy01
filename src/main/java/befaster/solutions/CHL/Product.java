package befaster.solutions.CHL;

import java.util.ArrayList;
import java.util.List;

public class Product
{
	private final String sku;
	private final int standardPrice;
	private int quanityInBasket;
	
	private final List<Offer> offers = new ArrayList<Offer>();
	
	/**
	 * Construct a new Product with SKU and standard price.
	 * 
	 * @param sku Product SKU;
	 * @param standardPrice Standard price.
	 */
	public Product(final String sku, final int standardPrice)
	{
		this.sku = sku;
		this.standardPrice = standardPrice;
		this.quanityInBasket = 0;
	}
	
	/**
	 * Add an offer to this product.
	 */
	public void addOfferToProduct(final Offer offer)
	{
		offers.add(offer);
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
		//TODO: Offers to be applied before returning price.
		return quanityInBasket * standardPrice;

	}
}
