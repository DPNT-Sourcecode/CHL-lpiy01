package befaster.solutions.CHL;

import java.util.ArrayList;
import java.util.List;

public class Product
{
	private final String sku;
	private final int standardPrice;
	private int quantityToBePaidFor;
	private int quantityFree;
	
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
		resetQuantityToBePaidFor();
	}
	
	/**
	 * Reset quantity to be paid for.
	 */
	public void resetQuantityToBePaidFor()
	{
		quantityToBePaidFor = 0;
		quantityFree = 0;
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
	 * Increment quantity free due to offer.
	 */
	public void incrementQuantityFree()
	{
		quantityFree++;
	}
	
	/**
	 * Apply the "buy quantity X , get product Y free" offers to THIS product , which may update quantities in OTHER products.
	 */
	public void applyOfferBuyXGetYFree()
	{
		// Apply all "buy quantity X , get product Y free" offers
		for (Offer offer : offers)
		{
			if (offer instanceof OfferBuyXGetYFree)
			{
				final OfferBuyXGetYFree offerBuyXGetYFree = (OfferBuyXGetYFree)offer;
				final Product productToAllowFree = offerBuyXGetYFree.getProductFree();
				final int quantityNeeded = offerBuyXGetYFree.getQuantityNeeded();
				System.out.println("Got OfferBuyXGetYFree: quantityNeeded = " + quantityNeeded);
				System.out.println("Got OfferBuyXGetYFree: productToAllowFree = " + productToAllowFree.sku);
				int quantityPurchased = quantityToBePaidFor;
				while (quantityPurchased >= quantityNeeded)
				{
					productToAllowFree.incrementQuantityFree();
					quantityPurchased -= quantityNeeded;
				}
			}
		}
	}
	
	/**
	 * Calculate the total price for this product based on quantity in Basket , standard price and offers.
	 */
	public int calculateTotalPrice()
	{
		int remainingQuantityToBePaidFor = Math.max(0, quantityToBePaidFor - quantityFree);
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

