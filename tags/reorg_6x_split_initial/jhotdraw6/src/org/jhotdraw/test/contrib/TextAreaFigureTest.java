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
package CH.ifa.draw.test.contrib;

import java.awt.Font;

import CH.ifa.draw.contrib.TextAreaFigure;

import junit.framework.TestCase;
// JUnitDoclet begin import
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
 * TestCase TextAreaFigureTest is generated by
 * JUnitDoclet to hold the tests for TextAreaFigure.
 * @see CH.ifa.draw.contrib.TextAreaFigure
 */
// JUnitDoclet end javadoc_class
public class TextAreaFigureTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private TextAreaFigure textareafigure;
	// JUnitDoclet end class

	/**
	 * Constructor TextAreaFigureTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public TextAreaFigureTest(String name) {
		// JUnitDoclet begin method TextAreaFigureTest
		super(name);
		// JUnitDoclet end method TextAreaFigureTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public CH.ifa.draw.contrib.TextAreaFigure createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new CH.ifa.draw.contrib.TextAreaFigure();
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
		textareafigure = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		textareafigure = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method setText()
	/**
	 * Method testSetGetText is testing setText
	 * and getText together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setText(java.lang.String)
	 * @see CH.ifa.draw.contrib.TextAreaFigure#getText()
	 */
	// JUnitDoclet end javadoc_method setText()
	public void testSetGetText() throws Exception {
		// JUnitDoclet begin method setText getText
		java.lang.String[] tests = { "", " ", "a", "A", "?", "?", "0123456789", "012345678901234567890", "\n", null };

		for (int i = 0; i < tests.length; i++) {
			textareafigure.setText(tests[i]);
			assertEquals(tests[i], textareafigure.getText());
		}
		// JUnitDoclet end method setText getText
	}

	// JUnitDoclet begin javadoc_method textDisplayBox()
	/**
	 * Method testTextDisplayBox is testing textDisplayBox
	 * @see CH.ifa.draw.contrib.TextAreaFigure#textDisplayBox()
	 */
	// JUnitDoclet end javadoc_method textDisplayBox()
	public void testTextDisplayBox() throws Exception {
		// JUnitDoclet begin method textDisplayBox
		// JUnitDoclet end method textDisplayBox
	}

	// JUnitDoclet begin javadoc_method createFont()
	/**
	 * Method testCreateFont is testing createFont
	 * @see CH.ifa.draw.contrib.TextAreaFigure#createFont()
	 */
	// JUnitDoclet end javadoc_method createFont()
	public void testCreateFont() throws Exception {
		// JUnitDoclet begin method createFont
		// JUnitDoclet end method createFont
	}

	// JUnitDoclet begin javadoc_method setReadOnly()
	/**
	 * Method testSetIsReadOnly is testing setReadOnly
	 * and isReadOnly together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setReadOnly(boolean)
	 * @see CH.ifa.draw.contrib.TextAreaFigure#isReadOnly()
	 */
	// JUnitDoclet end javadoc_method setReadOnly()
	public void testSetIsReadOnly() throws Exception {
		// JUnitDoclet begin method setReadOnly isReadOnly
		boolean[] tests = { true, false };

		for (int i = 0; i < tests.length; i++) {
			textareafigure.setReadOnly(tests[i]);
			assertEquals(tests[i], textareafigure.isReadOnly());
		}
		// JUnitDoclet end method setReadOnly isReadOnly
	}

	// JUnitDoclet begin javadoc_method acceptsTyping()
	/**
	 * Method testAcceptsTyping is testing acceptsTyping
	 * @see CH.ifa.draw.contrib.TextAreaFigure#acceptsTyping()
	 */
	// JUnitDoclet end javadoc_method acceptsTyping()
	public void testAcceptsTyping() throws Exception {
		// JUnitDoclet begin method acceptsTyping
		// JUnitDoclet end method acceptsTyping
	}

	// JUnitDoclet begin javadoc_method isTextDirty()
	/**
	 * Method testIsTextDirty is testing isTextDirty
	 * @see CH.ifa.draw.contrib.TextAreaFigure#isTextDirty()
	 */
	// JUnitDoclet end javadoc_method isTextDirty()
	public void testIsTextDirty() throws Exception {
		// JUnitDoclet begin method isTextDirty
		// JUnitDoclet end method isTextDirty
	}

	// JUnitDoclet begin javadoc_method setSizeDirty()
	/**
	 * Method testSetIsSizeDirty is testing setSizeDirty
	 * and isSizeDirty together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setSizeDirty(boolean)
	 * @see CH.ifa.draw.contrib.TextAreaFigure#isSizeDirty()
	 */
	// JUnitDoclet end javadoc_method setSizeDirty()
	public void testSetIsSizeDirty() throws Exception {
		// JUnitDoclet begin method setSizeDirty isSizeDirty
		boolean[] tests = { true, false };

		for (int i = 0; i < tests.length; i++) {
			textareafigure.setSizeDirty(tests[i]);
			assertEquals(tests[i], textareafigure.isSizeDirty());
		}
		// JUnitDoclet end method setSizeDirty isSizeDirty
	}

	// JUnitDoclet begin javadoc_method setFont()
	/**
	 * Method testSetGetFont is testing setFont
	 * and getFont together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setFont(java.awt.Font)
	 * @see CH.ifa.draw.contrib.TextAreaFigure#getFont()
	 */
	// JUnitDoclet end javadoc_method setFont()
	public void testSetGetFont() throws Exception {
		// JUnitDoclet begin method setFont getFont
		Font[] tests = { new Font("Helvetica", Font.PLAIN, 12)};

		for (int i = 0; i < tests.length; i++) {
			textareafigure.setFont(tests[i]);
			assertEquals(tests[i], textareafigure.getFont());
		}
		// JUnitDoclet end method setFont getFont
	}

	// JUnitDoclet begin method setNullFont()
	/**
	 * Test a null argument to setFont.		Expect an IllegalArgumentException
	 * 
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setFont(java.awt.Font)
	 */
	public void testSetNullFont() throws Exception {
		Font original = textareafigure.getFont();

		try {
			textareafigure.setFont(null);
			fail("IllegalArgumentException expected");
		}
		catch (IllegalArgumentException ok) {
			assertEquals("setFont(null) altered property value", original, textareafigure.getFont());
		}
	}
	// JUnitDoclet end method

	// JUnitDoclet begin javadoc_method overlayColumns()		
	/**
	 * Method testOverlayColumns is testing overlayColumns
	 * @see CH.ifa.draw.contrib.TextAreaFigure#overlayColumns()
	 */
	// JUnitDoclet end javadoc_method overlayColumns()
	public void testOverlayColumns() throws Exception {
		// JUnitDoclet begin method overlayColumns
		// JUnitDoclet end method overlayColumns
	}

	// JUnitDoclet begin javadoc_method basicDisplayBox()
	/**
	 * Method testBasicDisplayBox is testing basicDisplayBox
	 * @see CH.ifa.draw.contrib.TextAreaFigure#basicDisplayBox(java.awt.Point, java.awt.Point)
	 */
	// JUnitDoclet end javadoc_method basicDisplayBox()
	public void testBasicDisplayBox() throws Exception {
		// JUnitDoclet begin method basicDisplayBox
		// JUnitDoclet end method basicDisplayBox
	}

	// JUnitDoclet begin javadoc_method handles()
	/**
	 * Method testHandles is testing handles
	 * @see CH.ifa.draw.contrib.TextAreaFigure#handles()
	 */
	// JUnitDoclet end javadoc_method handles()
	public void testHandles() throws Exception {
		// JUnitDoclet begin method handles
		// JUnitDoclet end method handles
	}

	// JUnitDoclet begin javadoc_method displayBox()
	/**
	 * Method testDisplayBox is testing displayBox
	 * @see CH.ifa.draw.contrib.TextAreaFigure#displayBox()
	 */
	// JUnitDoclet end javadoc_method displayBox()
	public void testDisplayBox() throws Exception {
		// JUnitDoclet begin method displayBox
		// JUnitDoclet end method displayBox
	}

	// JUnitDoclet begin javadoc_method moveBy()
	/**
	 * Method testMoveBy is testing moveBy
	 * @see CH.ifa.draw.contrib.TextAreaFigure#moveBy(int, int)
	 */
	// JUnitDoclet end javadoc_method moveBy()
	public void testMoveBy() throws Exception {
		// JUnitDoclet begin method moveBy
		// JUnitDoclet end method moveBy
	}

	// JUnitDoclet begin javadoc_method drawBackground()
	/**
	 * Method testDrawBackground is testing drawBackground
	 * @see CH.ifa.draw.contrib.TextAreaFigure#drawBackground(java.awt.Graphics)
	 */
	// JUnitDoclet end javadoc_method drawBackground()
	public void testDrawBackground() throws Exception {
		// JUnitDoclet begin method drawBackground
		// JUnitDoclet end method drawBackground
	}

	// JUnitDoclet begin javadoc_method draw()
	/**
	 * Method testDraw is testing draw
	 * @see CH.ifa.draw.contrib.TextAreaFigure#draw(java.awt.Graphics)
	 */
	// JUnitDoclet end javadoc_method draw()
	public void testDraw() throws Exception {
		// JUnitDoclet begin method draw
		// JUnitDoclet end method draw
	}

	// JUnitDoclet begin javadoc_method drawFrame()
	/**
	 * Method testDrawFrame is testing drawFrame
	 * @see CH.ifa.draw.contrib.TextAreaFigure#drawFrame(java.awt.Graphics)
	 */
	// JUnitDoclet end javadoc_method drawFrame()
	public void testDrawFrame() throws Exception {
		// JUnitDoclet begin method drawFrame
		// JUnitDoclet end method drawFrame
	}

	// JUnitDoclet begin javadoc_method getAttribute()
	/**
	 * Method testGetAttribute is testing getAttribute
	 * @see CH.ifa.draw.contrib.TextAreaFigure#getAttribute(java.lang.String)
	 */
	// JUnitDoclet end javadoc_method getAttribute()
	public void testGetAttribute() throws Exception {
		// JUnitDoclet begin method getAttribute
		// JUnitDoclet end method getAttribute
	}

	// JUnitDoclet begin javadoc_method setAttribute()
	/**
	 * Method testSetAttribute is testing setAttribute
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setAttribute(java.lang.String, java.lang.Object)
	 */
	// JUnitDoclet end javadoc_method setAttribute()
	public void testSetAttribute() throws Exception {
		// JUnitDoclet begin method setAttribute
		// JUnitDoclet end method setAttribute
	}

	// JUnitDoclet begin javadoc_method write()
	/**
	 * Method testWrite is testing write
	 * @see CH.ifa.draw.contrib.TextAreaFigure#write(CH.ifa.draw.util.StorableOutput)
	 */
	// JUnitDoclet end javadoc_method write()
	public void testWrite() throws Exception {
		// JUnitDoclet begin method write
		// JUnitDoclet end method write
	}

	// JUnitDoclet begin javadoc_method read()
	/**
	 * Method testRead is testing read
	 * @see CH.ifa.draw.contrib.TextAreaFigure#read(CH.ifa.draw.util.StorableInput)
	 */
	// JUnitDoclet end javadoc_method read()
	public void testRead() throws Exception {
		// JUnitDoclet begin method read
		// JUnitDoclet end method read
	}

	// JUnitDoclet begin javadoc_method connect()
	/**
	 * Method testConnect is testing connect
	 * @see CH.ifa.draw.contrib.TextAreaFigure#connect(CH.ifa.draw.framework.Figure)
	 */
	// JUnitDoclet end javadoc_method connect()
	public void testConnect() throws Exception {
		// JUnitDoclet begin method connect
		// JUnitDoclet end method connect
	}

	// JUnitDoclet begin javadoc_method disconnect()
	/**
	 * Method testDisconnect is testing disconnect
	 * @see CH.ifa.draw.contrib.TextAreaFigure#disconnect(CH.ifa.draw.framework.Figure)
	 */
	// JUnitDoclet end javadoc_method disconnect()
	public void testDisconnect() throws Exception {
		// JUnitDoclet begin method disconnect
		// JUnitDoclet end method disconnect
	}

	// JUnitDoclet begin javadoc_method figureInvalidated()
	/**
	 * Method testFigureInvalidated is testing figureInvalidated
	 * @see CH.ifa.draw.contrib.TextAreaFigure#figureInvalidated(CH.ifa.draw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureInvalidated()
	public void testFigureInvalidated() throws Exception {
		// JUnitDoclet begin method figureInvalidated
		// JUnitDoclet end method figureInvalidated
	}

	// JUnitDoclet begin javadoc_method figureChanged()
	/**
	 * Method testFigureChanged is testing figureChanged
	 * @see CH.ifa.draw.contrib.TextAreaFigure#figureChanged(CH.ifa.draw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureChanged()
	public void testFigureChanged() throws Exception {
		// JUnitDoclet begin method figureChanged
		// JUnitDoclet end method figureChanged
	}

	// JUnitDoclet begin javadoc_method figureRemoved()
	/**
	 * Method testFigureRemoved is testing figureRemoved
	 * @see CH.ifa.draw.contrib.TextAreaFigure#figureRemoved(CH.ifa.draw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureRemoved()
	public void testFigureRemoved() throws Exception {
		// JUnitDoclet begin method figureRemoved
		// JUnitDoclet end method figureRemoved
	}

	// JUnitDoclet begin javadoc_method figureRequestRemove()
	/**
	 * Method testFigureRequestRemove is testing figureRequestRemove
	 * @see CH.ifa.draw.contrib.TextAreaFigure#figureRequestRemove(CH.ifa.draw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureRequestRemove()
	public void testFigureRequestRemove() throws Exception {
		// JUnitDoclet begin method figureRequestRemove
		// JUnitDoclet end method figureRequestRemove
	}

	// JUnitDoclet begin javadoc_method figureRequestUpdate()
	/**
	 * Method testFigureRequestUpdate is testing figureRequestUpdate
	 * @see CH.ifa.draw.contrib.TextAreaFigure#figureRequestUpdate(CH.ifa.draw.framework.FigureChangeEvent)
	 */
	// JUnitDoclet end javadoc_method figureRequestUpdate()
	public void testFigureRequestUpdate() throws Exception {
		// JUnitDoclet begin method figureRequestUpdate
		// JUnitDoclet end method figureRequestUpdate
	}

	// JUnitDoclet begin javadoc_method getTextColor()
	/**
	 * Method testGetTextColor is testing getTextColor
	 * @see CH.ifa.draw.contrib.TextAreaFigure#getTextColor()
	 */
	// JUnitDoclet end javadoc_method getTextColor()
	public void testGetTextColor() throws Exception {
		// JUnitDoclet begin method getTextColor
		// JUnitDoclet end method getTextColor
	}

	// JUnitDoclet begin javadoc_method isEmpty()
	/**
	 * Method testIsEmpty is testing isEmpty
	 * @see CH.ifa.draw.contrib.TextAreaFigure#isEmpty()
	 */
	// JUnitDoclet end javadoc_method isEmpty()
	public void testIsEmpty() throws Exception {
		// JUnitDoclet begin method isEmpty
		// JUnitDoclet end method isEmpty
	}

	// JUnitDoclet begin javadoc_method setFontDirty()
	/**
	 * Method testSetIsFontDirty is testing setFontDirty
	 * and isFontDirty together by setting some value
	 * and verifying it by reading.
	 * @see CH.ifa.draw.contrib.TextAreaFigure#setFontDirty(boolean)
	 * @see CH.ifa.draw.contrib.TextAreaFigure#isFontDirty()
	 */
	// JUnitDoclet end javadoc_method setFontDirty()
	public void testSetIsFontDirty() throws Exception {
		// JUnitDoclet begin method setFontDirty isFontDirty
		boolean[] tests = { true, false };

		for (int i = 0; i < tests.length; i++) {
			textareafigure.setFontDirty(tests[i]);
			assertEquals(tests[i], textareafigure.isFontDirty());
		}
		// JUnitDoclet end method setFontDirty isFontDirty
	}

	// JUnitDoclet begin javadoc_method getRepresentingFigure()
	/**
	 * Method testGetRepresentingFigure is testing getRepresentingFigure
	 * @see CH.ifa.draw.contrib.TextAreaFigure#getRepresentingFigure()
	 */
	// JUnitDoclet end javadoc_method getRepresentingFigure()
	public void testGetRepresentingFigure() throws Exception {
		// JUnitDoclet begin method getRepresentingFigure
		// JUnitDoclet end method getRepresentingFigure
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
