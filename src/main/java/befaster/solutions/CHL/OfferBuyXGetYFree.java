package befaster.solutions.CHL;

/**
 * Class representing a "buy quantity X , get product Y free" offer.
 */
public class OfferBuyXGetYFree implements Offer
{
	private final int quantityNeeded;
	private final Product product;
	
	/**
	 * Construct a new "buy quantity X , get product Y free" offer.
	 * 
	 * @param quantityNeeded Quantity which must be purchased before this offer can be applied.
	 * @param quantityFree Quantity which can be free.
	 */
	public OfferBuyXGetYFree(final int quantityNeeded, final Product product)
	{
		this.quantityNeeded = quantityNeeded;
		this.product = product;
	}

	public int getQuantityNeeded()
	{
		return quantityNeeded;
	}
	
	public Product getProductFree()
	{
		return product;
	}
}


