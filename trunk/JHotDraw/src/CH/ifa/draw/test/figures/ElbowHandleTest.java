/*
 * @(#)Test.java
 *
 * Project:		JHotdraw - a GUI framework for technical drawings
 *				http://www.jhotdraw.org
 *				http://jhotdraw.sourceforge.net
 * Copyright:	� by the original author(s) and all contributors
 * License:		Lesser GNU Public License (LGPL)
 *				http://www.opensource.org/licenses/lgpl-license.html
 */
package CH.ifa.draw.test.figures;

// JUnitDoclet begin import
import CH.ifa.draw.figures.ElbowHandle;
import CH.ifa.draw.figures.LineConnection;
import CH.ifa.draw.test.JHDTestCase;
// JUnitDoclet end import

/*
 * Generated by JUnitDoclet, a tool provided by
 * ObjectFab GmbH under LGPL.
 * Please see www.junitdoclet.org, www.gnu.org
 * and www.objectfab.de for informations about
 * the tool, the licence and the authors.
 */

// JUnitDoclet begin javadoc_class
/**
 * TestCase ElbowHandleTest is generated by
 * JUnitDoclet to hold the tests for ElbowHandle.
 * @see CH.ifa.draw.figures.ElbowHandle
 */
// JUnitDoclet end javadoc_class
public class ElbowHandleTest
// JUnitDoclet begin extends_implements
extends JHDTestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private ElbowHandle elbowhandle;
	// JUnitDoclet end class

	/**
	 * Constructor ElbowHandleTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public ElbowHandleTest(String name) {
		// JUnitDoclet begin method ElbowHandleTest
		super(name);
		// JUnitDoclet end method ElbowHandleTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public CH.ifa.draw.figures.ElbowHandle createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		LineConnection lineConn = new LineConnection();
		//lineConn.connectStart();
		//lineConn.connectEnd();
		return new CH.ifa.draw.figures.ElbowHandle(lineConn, 1);
		// JUnitDoclet end method testcase.createInstance
	}

	/**
	 * Method setUp is overwriting the framework method to
	 * prepare an instance of this TestCase for a single test.
	 * It's called from the JUnit framework only.
	 */
	protected void setUp() throws Exception {
		// JUnitDoclet begin method testcase.setUp
		super.setUp();
		elbowhandle = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		elbowhandle = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method invokeStart()
	/**
	 * Method testInvokeStart is testing invokeStart
	 * @see CH.ifa.draw.figures.ElbowHandle#invokeStart(int, int, CH.ifa.draw.framework.DrawingView)
	 */
	// JUnitDoclet end javadoc_method invokeStart()
	public void testInvokeStart() throws Exception {
		// JUnitDoclet begin method invokeStart
		// JUnitDoclet end method invokeStart
	}

	// JUnitDoclet begin javadoc_method invokeStep()
	/**
	 * Method testInvokeStep is testing invokeStep
	 * @see CH.ifa.draw.figures.ElbowHandle#invokeStep(int, int, int, int, CH.ifa.draw.framework.DrawingView)
	 */
	// JUnitDoclet end javadoc_method invokeStep()
	public void testInvokeStep() throws Exception {
		// JUnitDoclet begin method invokeStep
		// JUnitDoclet end method invokeStep
	}

	// JUnitDoclet begin javadoc_method locate()
	/**
	 * Method testLocate is testing locate
	 * @see CH.ifa.draw.figures.ElbowHandle#locate()
	 */
	// JUnitDoclet end javadoc_method locate()
	public void testLocate() throws Exception {
		// JUnitDoclet begin method locate
		// JUnitDoclet end method locate
	}

	// JUnitDoclet begin javadoc_method draw()
	/**
	 * Method testDraw is testing draw
	 * @see CH.ifa.draw.figures.ElbowHandle#draw(java.awt.Graphics)
	 */
	// JUnitDoclet end javadoc_method draw()
	public void testDraw() throws Exception {
		// JUnitDoclet begin method draw
		// JUnitDoclet end method draw
	}

	// JUnitDoclet begin javadoc_method testVault
	/**
	 * JUnitDoclet moves marker to this method, if there is not match
	 * for them in the regenerated code and if the marker is not empty.
	 * This way, no test gets lost when regenerating after renaming.
	 * <b>Method testVault is supposed to be empty.</b>
	 */
	// JUnitDoclet end javadoc_method testVault
	public void testVault() throws Exception {
		// JUnitDoclet begin method testcase.testVault
		// JUnitDoclet end method testcase.testVault
	}

}
