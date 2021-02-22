package com.kazurayam.ks.browserwindow

import static org.junit.Assert.*

import org.openqa.selenium.Dimension as Dimension
import org.openqa.selenium.Point as Point

import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kazurayam.ks.browserwindow.TilingLayoutManager

@RunWith(JUnit4.class)
class TilingLayoutManagerTest {

	TilingLayoutManager lm
	int NUM_OF_WINDOWS = 4

	@Before
	void setup() {
		lm = new TilingLayoutManager.Builder().physicalScreenSize(new Dimension(1020, 820)).build()
	}

	@Test
	void test_getVirtualScreenSize() {
		Dimension virtualScreenSize = lm.getVirtualScreenSize()
		assertTrue(virtualScreenSize.width == 1000)
		assertTrue(virtualScreenSize.height == 800)
	}

	@Test
	void test_getBasePoint() {
		Point basePoint = lm.getBasePoint()
		assertTrue(basePoint.x == 10)
		assertTrue(basePoint.y == 10)
	}

	@Test
	void test_getWindowDimension() {
		Dimension tileDimension = lm.getWindowDimension(NUM_OF_WINDOWS, 0)
		assertTrue("expected width == 500", tileDimension.width == 500)
		assertTrue("expected height == 400", tileDimension.height == 400)
	}

	@Test
	void testWindow0() {
		Point basePoint = lm.getBasePoint()
		Point loc = lm.getWindowPosition(NUM_OF_WINDOWS, 0)
		Dimension dim = lm.getWindowDimension(NUM_OF_WINDOWS, 0)
		Point expectedLoc = new Point((int)basePoint.x, (int)basePoint.y)
		assertTrue("loc.x=${loc.x}, expected to be ${expectedLoc.x}", expectedLoc.x == loc.x)
		assertTrue("loc.y=${loc.y}, expected to be ${expectedLoc.y}", expectedLoc.y == loc.y)
		assertTrue("dim.width=${dim.width}, expected to be 500", 500 == dim.width)
		assertTrue("dim.height=${dim.height}, expected to be 400", 400 == dim.height)
	}

	@Test
	void testWindow1() {
		Point basePoint = lm.getBasePoint()
		Point loc = lm.getWindowPosition(NUM_OF_WINDOWS, 1)
		Dimension dim = lm.getWindowDimension(NUM_OF_WINDOWS, 1)
		Point expectedLoc = new Point((int)basePoint.x + (int)dim.width, (int)basePoint.y)
		assertTrue("loc.x=${loc.x}, expected to be ${expectedLoc.x}", expectedLoc.x == loc.x)
		assertTrue("loc.y=${loc.y}, expected to be ${expectedLoc.y}", expectedLoc.y == loc.y)
		assertTrue("dim.width=${dim.width}, expected to be 500", 500 == dim.width)
		assertTrue("dim.height=${dim.height}, expected to be 400", 400 == dim.height)
	}

	@Test
	void testWindow2() {
		Point basePoint = lm.getBasePoint()
		Point loc = lm.getWindowPosition(NUM_OF_WINDOWS, 2)
		Dimension dim = lm.getWindowDimension(NUM_OF_WINDOWS, 2)
		Point expectedLoc = new Point((int)basePoint.x, (int)basePoint.y + (int)dim.height)
		assertTrue("loc.x=${loc.x}, expected to be ${expectedLoc.x}", expectedLoc.x == loc.x)
		assertTrue("loc.y=${loc.y}, expected to be ${expectedLoc.y}", expectedLoc.y == loc.y)
		assertTrue("dim.width=${dim.width}, expected to be 500", 500 == dim.width)
		assertTrue("dim.height=${dim.height}, expected to be 400", 400 == dim.height)
	}
}