package befaster.solutions.CHL;

import java.util.HashMap;

import befaster.runner.SolutionNotImplementedException;

public class CheckliteSolution
{
	private HashMap<String, Product> priceTable = buildPriceTable();
	
	private HashMap<String, Product> buildPriceTable()
	{
		HashMap<String, Product> priceTable = new HashMap<String, Product>();
		priceTable.put("A", new Product("A", 50));
		priceTable.put("B", new Product("B", 30));
		priceTable.put("C", new Product("C", 20));
		priceTable.put("D", new Product("D", 15));
		return priceTable;
	}
	
	
	
    public Integer checklite(String skus)
    {
    	System.out.println(skus);
    	return 0;
    }
}

