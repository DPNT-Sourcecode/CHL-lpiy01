package befaster.solutions.CHL;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CheckliteSolutionTest
{
	private CheckliteSolution sut;
	
    @Before
    public void setUp()
    {
    	sut = new CheckliteSolution();
    }

	@Test
	public void testAAA()
	{
		System.out.println(sut.checklite("EEB"));
	}

}
