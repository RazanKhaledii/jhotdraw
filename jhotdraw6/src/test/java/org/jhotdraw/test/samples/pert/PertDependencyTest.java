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
package org.jhotdraw.test.samples.pert;

import org.jhotdraw.samples.pert.PertDependency;
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
 * TestCase PertDependencyTest is generated by
 * JUnitDoclet to hold the tests for PertDependency.
 * @see org.jhotdraw.samples.pert.PertDependency
 */
// JUnitDoclet end javadoc_class
public class PertDependencyTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
	// JUnitDoclet begin class
	// instance variables, helper methods, ... put them in this marker
	private PertDependency pertdependency;
	// JUnitDoclet end class

	/**
	 * Constructor PertDependencyTest is
	 * basically calling the inherited constructor to
	 * initiate the TestCase for use by the Framework.
	 */
	public PertDependencyTest(String name) {
		// JUnitDoclet begin method PertDependencyTest
		super(name);
		// JUnitDoclet end method PertDependencyTest
	}

	/**
	 * Factory method for instances of the class to be tested.
	 */
	public PertDependency createInstance() throws Exception {
		// JUnitDoclet begin method testcase.createInstance
		return new PertDependency();
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
		pertdependency = createInstance();
		// JUnitDoclet end method testcase.setUp
	}

	/**
	 * Method tearDown is overwriting the framework method to
	 * clean up after each single test of this TestCase.
	 * It's called from the JUnit framework only.
	 */
	protected void tearDown() throws Exception {
		// JUnitDoclet begin method testcase.tearDown
		pertdependency = null;
		super.tearDown();
		// JUnitDoclet end method testcase.tearDown
	}

	// JUnitDoclet begin javadoc_method handleConnect()
	/**
	 * Method testHandleConnect is testing handleConnect
	 * @see org.jhotdraw.samples.pert.PertDependency#handleConnect(org.jhotdraw.framework.Figure, org.jhotdraw.framework.Figure)
	 */
	// JUnitDoclet end javadoc_method handleConnect()
	public void testHandleConnect() throws Exception {
		// JUnitDoclet begin method handleConnect
		// JUnitDoclet end method handleConnect
	}

	// JUnitDoclet begin javadoc_method handleDisconnect()
	/**
	 * Method testHandleDisconnect is testing handleDisconnect
	 * @see org.jhotdraw.samples.pert.PertDependency#handleDisconnect(org.jhotdraw.framework.Figure, org.jhotdraw.framework.Figure)
	 */
	// JUnitDoclet end javadoc_method handleDisconnect()
	public void testHandleDisconnect() throws Exception {
		// JUnitDoclet begin method handleDisconnect
		// JUnitDoclet end method handleDisconnect
	}

	// JUnitDoclet begin javadoc_method canConnect()
	/**
	 * Method testCanConnect is testing canConnect
	 * @see org.jhotdraw.samples.pert.PertDependency#canConnect(org.jhotdraw.framework.Figure, org.jhotdraw.framework.Figure)
	 */
	// JUnitDoclet end javadoc_method canConnect()
	public void testCanConnect() throws Exception {
		// JUnitDoclet begin method canConnect
		// JUnitDoclet end method canConnect
	}

	// JUnitDoclet begin javadoc_method handles()
	/**
	 * Method testHandles is testing handles
	 * @see org.jhotdraw.samples.pert.PertDependency#handles()
	 */
	// JUnitDoclet end javadoc_method handles()
	public void testHandles() throws Exception {
		// JUnitDoclet begin method handles
		// JUnitDoclet end method handles
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
