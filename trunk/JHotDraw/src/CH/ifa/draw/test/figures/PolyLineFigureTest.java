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

import junit.framework.TestCase;
// JUnitDoclet begin import
import CH.ifa.draw.figures.ArrowTip;
import CH.ifa.draw.figures.PolyLineFigure;
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
 * TestCase PolyLineFigureTest is generated by
 * JUnitDoclet to hold the tests for PolyLineFigure.
 * @see CH.ifa.draw.figures.PolyLineFigure
 */
// JUnitDoclet end javadoc_class
public class PolyLineFigureTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private PolyLineFigure polylinefigure;
	// JUnitDoclet end class

	/**
	 * Constructor PolyLineFigureTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public PolyLineFigureTest(String name) {
		// JUnitDoclet begin method PolyLineFigureTest
		super(name);
		// JUnitDoclet end method PolyLineFigureTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public CH.ifa.draw.figures.PolyLineFigure createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new CH.ifa.draw.figures.PolyLineFigure();
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
		polylinefigure = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		polylinefigure = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method displayBox()
	/**
	 * Method testDisplayBox is testing displayBox
	 * @see CH.ifa.draw.figures.PolyLineFigure#displayBox()
	 */
	// JUnitDoclet end javadoc_method displayBox()
	public void testDisplayBox() throws Exception {
		// JUnitDoclet begin method displayBox
		// JUnitDoclet end method displayBox
	}

	// JUnitDoclet begin javadoc_method isEmpty()
	/**
	 * Method testIsEmpty is testing isEmpty
	 * @see CH.ifa.draw.figures.PolyLineFigure#isEmpty()
	 */
	// JUnitDoclet end javadoc_method isEmpty()
	public void testIsEmpty() throws Exception {
		// JUnitDoclet begin method isEmpty
		// JUnitDoclet end method isEmpty
	}

	// JUnitDoclet begin javadoc_method handles()
	/**
	 * Method testHandles is testing handles
	 * @see CH.ifa.draw.figures.PolyLineFigure#handles()
	 */
	// JUnitDoclet end javadoc_method handles()
	public void testHandles() throws Exception {
		// JUnitDoclet begin method handles
		// JUnitDoclet end method handles
	}

	// JUnitDoclet begin javadoc_method basicDisplayBox()
	/**
	 * Method testBasicDisplayBox is testing basicDisplayBox
	 * @see CH.ifa.draw.figures.PolyLineFigure#basicDisplayBox(java.awt.Point, java.awt.Point)
	 */
	// JUnitDoclet end javadoc_method basicDisplayBox()
	public void testBasicDisplayBox() throws Exception {
		// JUnitDoclet begin method basicDisplayBox
		// JUnitDoclet end method basicDisplayBox
	}

	// JUnitDoclet begin javadoc_method addPoint()
	/**
	 * Method testAddPoint is testing addPoint
	 * @see CH.ifa.draw.figures.PolyLineFigure#addPoint(int, int)
	 */
	// JUnitDoclet end javadoc_method addPoint()
	public void testAddPoint() throws Exception {
		// JUnitDoclet begin method addPoint
		// JUnitDoclet end method addPoint
	}

	// JUnitDoclet begin javadoc_method points()
	/**
	 * Method testPoints is testing points
	 * @see CH.ifa.draw.figures.PolyLineFigure#points()
	 */
	// JUnitDoclet end javadoc_method points()
	public void testPoints() throws Exception {
		// JUnitDoclet begin method points
		// JUnitDoclet end method points
	}

	// JUnitDoclet begin javadoc_method pointCount()
	/**
	 * Method testPointCount is testing pointCount
	 * @see CH.ifa.draw.figures.PolyLineFigure#pointCount()
	 */
	// JUnitDoclet end javadoc_method pointCount()
	public void testPointCount() throws Exception {
		// JUnitDoclet begin method pointCount
		// JUnitDoclet end method pointCount
	}

	// JUnitDoclet begin javadoc_method setPointAt()
	/**
	 * Method testSetPointAt is testing setPointAt
	 * @see CH.ifa.draw.figures.PolyLineFigure#setPointAt(java.awt.Point, int)
	 */
	// JUnitDoclet end javadoc_method setPointAt()
	public void testSetPointAt() throws Exception {
		// JUnitDoclet begin method setPointAt
		// JUnitDoclet end method setPointAt
	}

	// JUnitDoclet begin javadoc_method insertPointAt()
	/**
	 * Method testInsertPointAt is testing insertPointAt
	 * @see CH.ifa.draw.figures.PolyLineFigure#insertPointAt(java.awt.Point, int)
	 */
	// JUnitDoclet end javadoc_method insertPointAt()
	public void testInsertPointAt() throws Exception {
		// JUnitDoclet begin method insertPointAt
		// JUnitDoclet end method insertPointAt
	}

	// JUnitDoclet begin javadoc_method removePointAt()
	/**
	 * Method testRemovePointAt is testing removePointAt
	 * @see CH.ifa.draw.figures.PolyLineFigure#removePointAt(int)
	 */
	// JUnitDoclet end javadoc_method removePointAt()
	public void testRemovePointAt() throws Exception {
		// JUnitDoclet begin method removePointAt
		// JUnitDoclet end method removePointAt
	}

	// JUnitDoclet begin javadoc_method splitSegment()
	/**
	 * Method testSplitSegment is testing splitSegment
	 * @see CH.ifa.draw.figures.PolyLineFigure#splitSegment(int, int)
	 */
	// JUnitDoclet end javadoc_method splitSegment()
	public void testSplitSegment() throws Exception {
		// JUnitDoclet begin method splitSegment
		// JUnitDoclet end method splitSegment
	}

	// JUnitDoclet begin javadoc_method pointAt()
	/**
	 * Method testPointAt is testing pointAt
	 * @see CH.ifa.draw.figures.PolyLineFigure#pointAt(int)
	 */
	// JUnitDoclet end javadoc_method pointAt()
	public void testPointAt() throws Exception {
		// JUnitDoclet begin method pointAt
		// JUnitDoclet end method pointAt
	}

	// JUnitDoclet begin javadoc_method joinSegments()
	/**
	 * Method testJoinSegments is testing joinSegments
	 * @see CH.ifa.draw.figures.PolyLineFigure#joinSegments(int, int)
	 */
	// JUnitDoclet end javadoc_method joinSegments()
	public void testJoinSegments() throws Exception {
		// JUnitDoclet begin method joinSegments
		// JUnitDoclet end method joinSegments
	}

	// JUnitDoclet begin javadoc_method connectorAt()
	/**
	 * Method testConnectorAt is testing connectorAt
	 * @see CH.ifa.draw.figures.PolyLineFigure#connectorAt(int, int)
	 */
	// JUnitDoclet end javadoc_method connectorAt()
	public void testConnectorAt() throws Exception {
		// JUnitDoclet begin method connectorAt
		// JUnitDoclet end method connectorAt
	}

	// JUnitDoclet begin javadoc_method setStartDecoration()
	/**
	 * Method testSetGetStartDecoration is testing setStartDecoration
	 * and getStartDecoration together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.figures.PolyLineFigure#setStartDecoration(CH.ifa.draw.figures.LineDecoration)
	 * @see CH.ifa.draw.figures.PolyLineFigure#getStartDecoration()
	 */
	// JUnitDoclet end javadoc_method setStartDecoration()
	public void testSetGetStartDecoration() throws Exception {
		// JUnitDoclet begin method setStartDecoration getStartDecoration
		CH.ifa.draw.figures.LineDecoration[] tests = { new ArrowTip(), null };

		for (int i = 0; i < tests.length; i++) {
			polylinefigure.setStartDecoration(tests[i]);
			assertEquals(tests[i], polylinefigure.getStartDecoration());
		}
		// JUnitDoclet end method setStartDecoration getStartDecoration
	}

	// JUnitDoclet begin javadoc_method setEndDecoration()
	/**
	 * Method testSetGetEndDecoration is testing setEndDecoration
	 * and getEndDecoration together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.figures.PolyLineFigure#setEndDecoration(CH.ifa.draw.figures.LineDecoration)
	 * @see CH.ifa.draw.figures.PolyLineFigure#getEndDecoration()
	 */
	// JUnitDoclet end javadoc_method setEndDecoration()
	public void testSetGetEndDecoration() throws Exception {
		// JUnitDoclet begin method setEndDecoration getEndDecoration
		CH.ifa.draw.figures.LineDecoration[] tests = { new ArrowTip(), null };

		for (int i = 0; i < tests.length; i++) {
			polylinefigure.setEndDecoration(tests[i]);
			assertEquals(tests[i], polylinefigure.getEndDecoration());
		}
		// JUnitDoclet end method setEndDecoration getEndDecoration
	}

	// JUnitDoclet begin javadoc_method draw()
	/**
	 * Method testDraw is testing draw
	 * @see CH.ifa.draw.figures.PolyLineFigure#draw(java.awt.Graphics)
	 */
	// JUnitDoclet end javadoc_method draw()
	public void testDraw() throws Exception {
		// JUnitDoclet begin method draw
		// JUnitDoclet end method draw
	}

	// JUnitDoclet begin javadoc_method containsPoint()
	/**
	 * Method testContainsPoint is testing containsPoint
	 * @see CH.ifa.draw.figures.PolyLineFigure#containsPoint(int, int)
	 */
	// JUnitDoclet end javadoc_method containsPoint()
	public void testContainsPoint() throws Exception {
		// JUnitDoclet begin method containsPoint
		// JUnitDoclet end method containsPoint
	}

	// JUnitDoclet begin javadoc_method findSegment()
	/**
	 * Method testFindSegment is testing findSegment
	 * @see CH.ifa.draw.figures.PolyLineFigure#findSegment(int, int)
	 */
	// JUnitDoclet end javadoc_method findSegment()
	public void testFindSegment() throws Exception {
		// JUnitDoclet begin method findSegment
		// JUnitDoclet end method findSegment
	}

	// JUnitDoclet begin javadoc_method getAttribute()
	/**
	 * Method testGetAttribute is testing getAttribute
	 * @see CH.ifa.draw.figures.PolyLineFigure#getAttribute(java.lang.String)
	 */
	// JUnitDoclet end javadoc_method getAttribute()
	public void testGetAttribute() throws Exception {
		// JUnitDoclet begin method getAttribute
		// JUnitDoclet end method getAttribute
	}

	// JUnitDoclet begin javadoc_method setAttribute()
	/**
	 * Method testSetAttribute is testing setAttribute
	 * @see CH.ifa.draw.figures.PolyLineFigure#setAttribute(java.lang.String, java.lang.Object)
	 */
	// JUnitDoclet end javadoc_method setAttribute()
	public void testSetAttribute() throws Exception {
		// JUnitDoclet begin method setAttribute
		// JUnitDoclet end method setAttribute
	}

	// JUnitDoclet begin javadoc_method write()
	/**
	 * Method testWrite is testing write
	 * @see CH.ifa.draw.figures.PolyLineFigure#write(CH.ifa.draw.util.StorableOutput)
	 */
	// JUnitDoclet end javadoc_method write()
	public void testWrite() throws Exception {
		// JUnitDoclet begin method write
		// JUnitDoclet end method write
	}

	// JUnitDoclet begin javadoc_method read()
	/**
	 * Method testRead is testing read
	 * @see CH.ifa.draw.figures.PolyLineFigure#read(CH.ifa.draw.util.StorableInput)
	 */
	// JUnitDoclet end javadoc_method read()
	public void testRead() throws Exception {
		// JUnitDoclet begin method read
		// JUnitDoclet end method read
	}

	// JUnitDoclet begin javadoc_method locator()
	/**
	 * Method testLocator is testing locator
	 * @see CH.ifa.draw.figures.PolyLineFigure#locator(int)
	 */
	// JUnitDoclet end javadoc_method locator()
	public void testLocator() throws Exception {
		// JUnitDoclet begin method locator
		// JUnitDoclet end method locator
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
