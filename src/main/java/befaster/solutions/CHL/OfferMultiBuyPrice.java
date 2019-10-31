package befaster.solutions.CHL;

/**
 * Class representing a "multiple buy price" offer , for example 5 for 200.
 */
public class OfferMultiBuyPrice implements Offer
{
	private final int offerQuantity;
	private final int offerPrice;
	
	/**
	 * Construct a "multiple buy price" offer.
	 * 
	 * @param offerQuantity Offer quantity.
	 * @param offerPrice Offer price.
	 */
	public OfferMultiBuyPrice(final int offerQuantity, final int offerPrice)
	{
		this.offerQuantity = offerQuantity;
		this.offerPrice = offerPrice;
	}
	
	public int getOfferQuantity()
	{
		return offerQuantity;
	}

	public int getOfferPrice()
	{
		return offerPrice;
	}
}
