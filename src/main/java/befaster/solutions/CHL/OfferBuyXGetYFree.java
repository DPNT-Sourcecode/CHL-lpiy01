package befaster.solutions.CHL;

/**
 * Class representing a "buy X get product Y free" offer.
 */
public class OfferBuyXGetYFree implements Offer
{
	private final int quantityNeeded;
	private final int quantityFree;
	
	/**
	 * Construct a new "buy X get product Y free" offer.
	 * 
	 * @param quantityNeeded Quantity which must be purchased before this offer can be applied.
	 * @param quantityFree Quantity which can be free.
	 */
	public OfferBuyXGetYFree(final int quantityNeeded, final int quantityFree)
	{
		this.quantityNeeded = quantityNeeded;
		this.quantityFree = quantityFree;
	}

	public int getQuantityNeeded()
	{
		return quantityNeeded;
	}
	
	public int getQuantityFree()
	{
		return quantityFree;
	}
}

