package cn.rainbow.westore.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bvin on 2016/8/18.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void add() throws Exception {
        assertEquals(mCalculator.add(0, 1), 1);
    }

    @Test
    public void sub() throws Exception {
        assertEquals(mCalculator.sub(5, 3), 2);
    }

    @Test
    public void mul() throws Exception {
        assertEquals(mCalculator.mul(5,3),15);
    }

    @Test
    public void div() throws Exception {
        assertEquals(mCalculator.div(10,5),2);
    }

}