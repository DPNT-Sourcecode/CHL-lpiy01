package befaster.solutions.CHL;

import java.util.ArrayList;
import java.util.List;

public class Product
{
	private final String sku;
	private final int standardPrice;
	private int quantityToBePaidFor;
	
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
		this.quantityToBePaidFor = 0;
	}
	
	/**
	 * Add an offer to this product.
	 */
	public void addOfferToProduct(final Offer offer)
	{
		offers.add(offer);
	}
	
	/**
	 * Increment quantity to be paid for.
	 */
	public void incrementQuantityToBePaidFor()
	{
		quantityToBePaidFor++;
	}
	
	/**
	 * Decrement quantity to be paid for due to offer.
	 */
	public void decrementQuantityToBePaidFor()
	{
		if (quantityToBePaidFor > 0)
		{
			quantityToBePaidFor--;
		}
	}
	
	/**
	 * Reset quantity to be paid for.
	 */
	public void resetQuantityToBePaidFor()
	{
		quantityToBePaidFor = 0;
	}
	
	/**
	 * Calculate the total price for this product based on quantity in Basket , standard price and offers.
	 */
	public int calculateTotalPrice()
	{
		int remainingQuantityToBePaidFor = quantityToBePaidFor;
		
		// Apply all "buy quantity X , get product Y free" offers
		for (Offer offer : offers)
		{
			if (offer instanceof OfferBuyXGetYFree)
			{
				final OfferBuyXGetYFree offerBuyXGetYFree = (OfferBuyXGetYFree)offer;
				final int quantityNeeded = offerBuyXGetYFree.getQuantityNeeded();
				if (remainingQuantityToBePaidFor > quantityNeeded)
				{
					int maxQuantityFree = offerBuyXGetYFree.getQuantityFree();
					while (maxQuantityFree > 0 && remainingQuantityToBePaidFor > 0)
					{
						maxQuantityFree--;
						remainingQuantityToBePaidFor--;
					}
				}
			}
		}
		
		int priceToBePaid = 0;
		
		// Apply all "multiple buy price" offers
		for (Offer offer : offers)
		{
			if (offer instanceof OfferMultiBuyPrice)
			{
				final OfferMultiBuyPrice offerMultiBuyPrice = (OfferMultiBuyPrice)offer;
				int offerQuantity = offerMultiBuyPrice.getOfferQuantity();
				int offerPrice = offerMultiBuyPrice.getOfferPrice();
				while (remainingQuantityToBePaidFor >= offerQuantity)
				{
					remainingQuantityToBePaidFor -= offerQuantity;
					priceToBePaid += offerPrice;
				}
			}
		}
		
		priceToBePaid += (remainingQuantityToBePaidFor * standardPrice);

		return priceToBePaid;
	}
}
