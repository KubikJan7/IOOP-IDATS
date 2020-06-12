/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Legol
 */
public class AbstrDoubleListTest {

    public AbstrDoubleListTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPocet method, of class AbstrDoubleList.
     */
    @Test
    public void testGetPocet() {
        System.out.println("getPocet");
        AbstrDoubleList<TestTrida> instance = new AbstrDoubleList<>();
        int expResult = 0;
        int result = instance.getPocet();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPocet2() {
        System.out.println("getPocet2");
        TestTrida data = new TestTrida(1);
        AbstrDoubleList<TestTrida> instance = new AbstrDoubleList<>();
        instance.vlozPrvni(data);
        int expResult = 1;
        int result = instance.getPocet();
        assertEquals(expResult, result);
    }

    /**
     * Test of zrus method, of class AbstrDoubleList.
     */
    @Test
    public void testGetPocet3() {
        System.out.println("getPocet3");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrDoubleList<TestTrida> instance = new AbstrDoubleList<>();
        instance.vlozPosledni(data1);
        instance.vlozPosledni(data2);
        instance.odeberPosledni();
        instance.odeberPosledni();
        instance.vlozPrvni(data3);
        instance.vlozPrvni(data4);
        int expResult = 2;
        int result = instance.getPocet();
        assertEquals(expResult, result);
    }

    @Test
    public void testZrus() {
        System.out.println("zrus");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data1);
        instance.vlozPosledni(data2);
        instance.zrus();
        int expResult = 0;
        int result = instance.getPocet();
        assertEquals(expResult, result);

    }

    /**
     * Test of jePrazdny method, of class AbstrDoubleList.
     */
    @Test
    public void testJePrazdny() {
        System.out.println("jePrazdny");
        AbstrDoubleList instance = new AbstrDoubleList();
        boolean expResult = true;
        boolean result = instance.jePrazdny();
        assertEquals(expResult, result);
    }

    @Test
    public void testJePrazdny2() {
        System.out.println("jePrazdny2");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(1);
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        boolean expResult = false;
        boolean result = instance.jePrazdny();
        assertEquals(expResult, result);
    }

    @Test
    public void testJePrazdny3() {
        System.out.println("jePrazdny3");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(1);
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        instance.odeberPrvni();
        instance.odeberPosledni();
        boolean expResult = true;
        boolean result = instance.jePrazdny();
        assertEquals(expResult, result);
    }

    @Test
    public void testJePrazdny4() {
        System.out.println("jePrazdny4");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(1);
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        instance.zrus();
        boolean expResult = true;
        boolean result = instance.jePrazdny();
        assertEquals(expResult, result);
    }

    /**
     * Test of vlozPrvni method, of class AbstrDoubleList.
     */
    @Test(expected = NullPointerException.class)
    public void testVlozPrvni() throws NullPointerException {
        System.out.println("vlozPrvni");
        TestTrida data = null;
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testVlozPrvni2() {
        System.out.println("vlozPrvni2");
        TestTrida data = new TestTrida(2);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(1, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data);

    }

    @Test
    public void testVlozPrvni3() {
        System.out.println("vlozPrvni3");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(5);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        instance.vlozPosledni(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniPosledni(), data2);

    }

    @Test
    public void testVlozPrvni4() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("vlozPrvni4");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(5);
        TestTrida data3 = new TestTrida(9);
        TestTrida data4 = new TestTrida(20);
        TestTrida data5 = new TestTrida(42);
        TestTrida data6 = new TestTrida(7);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data3);
        assertEquals(instance.zpristupniPosledni(), data);
        assertEquals(instance.zpristupniPrvni(), data3);
        instance.vlozPrvni(data4);
        instance.vlozPrvni(data5);
        assertEquals(instance.zpristupniAktualni(), data3);
        instance.vlozPrvni(data6);
        assertEquals(instance.zpristupniPrvni(), data6);
        assertEquals(6, instance.getPocet());
        assertEquals(instance.odeberPosledni(), data);
        assertEquals(5, instance.getPocet());
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniPosledni(), data2);

    }

    /**
     * Test of vlozPosledni method, of class AbstrDoubleList.
     */
    @Test(expected = NullPointerException.class)
    public void testVlozPosledni() throws NullPointerException {
        System.out.println("vlozPosledni");
        TestTrida data = null;
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testVlozPosledni2() {
        System.out.println("vlozPosledni2");
        TestTrida data = new TestTrida(2);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        assertEquals(1, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data);
    }

    @Test
    public void testVlozPosledni3() {
        System.out.println("vlozPosledni3");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(5);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniPosledni(), data);
    }

    @Test
    public void testVlozPosledni4() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("vlozPrvni4");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(5);
        TestTrida data3 = new TestTrida(9);
        TestTrida data4 = new TestTrida(20);
        TestTrida data5 = new TestTrida(42);
        TestTrida data6 = new TestTrida(7);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.vlozPosledni(data2);
        instance.vlozPosledni(data3);
        assertEquals(instance.zpristupniPosledni(), data3);
        assertEquals(instance.zpristupniPrvni(), data);
        instance.vlozPosledni(data4);
        instance.vlozPosledni(data5);
        assertEquals(instance.zpristupniAktualni(), data);
        instance.vlozPosledni(data6);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(6, instance.getPocet());
        assertEquals(instance.odeberPrvni(), data);
        assertEquals(5, instance.getPocet());
        instance.vlozPosledni(data);
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniPosledni(), data);

    }

    /**
     * Test of vlozNaslednika method, of class AbstrDoubleList.
     *
     * @throws kolekce.IAbstrDoubleList.ListException
     */
    @Test(expected = NullPointerException.class)
    public void testVlozNaslednika() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozNaslednika");
        TestTrida data = null;
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozNaslednika(data);
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testVlozNaslednika2() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozNaslednika2");
        TestTrida data = new TestTrida(2);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozNaslednika(data);
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testVlozNaslednika3() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPosledni3");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        instance.vlozNaslednika(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data2);
    }

    @Test
    public void testVlozNaslednika4() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPosledni4");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        TestTrida data3 = new TestTrida(6);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        assertEquals(instance.zpristupniPrvni(), data2);
        instance.vlozNaslednika(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniNaslednika(), data3);
        assertEquals(instance.zpristupniPosledni(), data);
    }

    @Test
    public void testVlozNaslednika5() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPosledni5");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        TestTrida data3 = new TestTrida(6);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        assertEquals(instance.zpristupniPosledni(), data);
        instance.vlozNaslednika(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniNaslednika(), data);
        assertEquals(instance.zpristupniNaslednika(), data3);
        assertEquals(instance.zpristupniNaslednika(), data2);
        assertEquals(instance.zpristupniPosledni(), data3);
    }

    @Test
    public void testVlozNaslednika6() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozNaslednika6");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(5);
        TestTrida data3 = new TestTrida(9);
        TestTrida data4 = new TestTrida(20);
        TestTrida data5 = new TestTrida(42);
        TestTrida data6 = new TestTrida(7);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        instance.vlozNaslednika(data3);
        instance.vlozNaslednika(data2);
        assertEquals(instance.zpristupniPosledni(), data3);
        instance.vlozNaslednika(data4);
        assertEquals(instance.zpristupniPosledni(), data4);
        instance.vlozNaslednika(data6);
        instance.vlozNaslednika(data5);
        assertEquals(instance.zpristupniAktualni(), data4);
        assertEquals(instance.zpristupniNaslednika(), data5);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(6, instance.getPocet());
        assertEquals(instance.odeberPosledni(), data6);
        assertEquals(5, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data5);
        instance.vlozNaslednika(data6);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniPosledni(), data6);
    }

    /**
     * Test of vlozPredchudce method, of class AbstrDoubleList.
     *
     * @throws kolekce.IAbstrDoubleList.ListException
     */
    @Test(expected = NullPointerException.class)
    public void testVlozPredchudce() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPrechudce");
        TestTrida data = null;
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPredchudce(data);
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testVlozPredchudce2() throws IAbstrDoubleList.ListException {
        System.out.println("vlozPredchudce2");
        TestTrida data = new TestTrida(2);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPredchudce(data);
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testVlozPredchudce3() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPredchudce3");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        instance.vlozPredchudce(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data);
    }

    @Test
    public void testVlozPredchudce4() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPredchudce4");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        TestTrida data3 = new TestTrida(6);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        assertEquals(instance.zpristupniPosledni(), data);
        instance.vlozPredchudce(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniNaslednika(), data3);
        assertEquals(instance.zpristupniPosledni(), data);
    }

    @Test
    public void testVlozPredchudce5() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPrechudce5");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        TestTrida data3 = new TestTrida(6);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.vlozPrvni(data2);
        assertEquals(instance.zpristupniPrvni(), data2);
        instance.vlozPredchudce(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data3);
        assertEquals(instance.zpristupniPredchudce(), data);
        assertEquals(instance.zpristupniNaslednika(), data3);
        assertEquals(instance.zpristupniNaslednika(), data2);
        assertEquals(instance.zpristupniPosledni(), data);
    }

    @Test
    public void testVlozPredchudce6() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("vlozPrechudce6");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(5);
        TestTrida data3 = new TestTrida(9);
        TestTrida data4 = new TestTrida(20);
        TestTrida data5 = new TestTrida(42);
        TestTrida data6 = new TestTrida(7);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data6);
        assertEquals(instance.zpristupniPosledni(), data6);
        instance.vlozPredchudce(data4);
        instance.vlozPredchudce(data5);
        assertEquals(instance.zpristupniPrvni(), data4);
        instance.vlozPredchudce(data3);
        assertEquals(instance.zpristupniPrvni(), data3);
        instance.vlozPredchudce(data);
        instance.vlozPredchudce(data2);
        assertEquals(instance.zpristupniAktualni(), data3);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniNaslednika(), data2);
        instance.zpristupniPrvni();
        assertEquals(6, instance.getPocet());
        assertEquals(instance.odeberPrvni(), data);
        assertEquals(5, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        instance.vlozPredchudce(data);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniPosledni(), data6);
    }

    /**
     * Test of zpristupniAktualni method, of class AbstrDoubleList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testZpristupniAktualni() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("zpristupniAktualni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniAktualni();
        fail();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testZpristupniAktualni2() throws IAbstrDoubleList.ListException {
        System.out.println("zpristupniAktualni2");
        TestTrida data = new TestTrida(1);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        instance.zpristupniAktualni();
        fail();
    }

    @Test
    public void testZpristupniAktualni3() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("zpristupniAktualni3");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        instance.vlozPredchudce(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniAktualni(), data);
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniAktualni(), data2);
    }

    /**
     * Test of zpristupniPrvni method, of class AbstrDoubleList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testZpristupniPrvni() throws NoSuchElementException {
        System.out.println("zpristupniPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniPrvni();
        fail();
    }

    @Test
    public void testZpristupniPrvni2() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("zpristupniPrvni2");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        instance.vlozPredchudce(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
    }

    /**
     * Test of zpristupniPosledni method, of class AbstrDoubleList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testZpristupniPosledni() throws NoSuchElementException {
        System.out.println("zpristupniPosledni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.zpristupniPrvni();
        fail();
    }

    @Test
    public void testZpristupniPosledni2() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("zpristupniPosledni2");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPosledni(), data);
        instance.vlozPredchudce(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data);
    }

    /**
     * Test of zpristupniNaslednika method, of class AbstrDoubleList.
     *
     * @throws kolekce.IAbstrDoubleList.ListException
     */
    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testZpristupniNaslednika() throws IAbstrDoubleList.ListException {
        System.out.println("zpristupniNaslednika");
        TestTrida data = new TestTrida(1);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        instance.zpristupniAktualni();
        fail();
    }

    @Test
    public void testZpristupniNaslednika2() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("zpristupniNaslednika2");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniNaslednika(), data);
        instance.vlozPredchudce(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniNaslednika(), data);
        assertEquals(instance.zpristupniNaslednika(), data2);

    }

    /**
     * Test of zpristupniPredchudce method, of class AbstrDoubleList.
     */
    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testZpristupniPredchudce() throws IAbstrDoubleList.ListException {
        System.out.println("zpristupniPredchudce");
        TestTrida data = new TestTrida(1);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        instance.zpristupniAktualni();
        fail();
    }

    @Test
    public void testZpristupniPredchudce2() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("zpristupniPredchudce2");
        TestTrida data = new TestTrida(2);
        TestTrida data2 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPrvni(data);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.zpristupniPredchudce(), data);
        instance.vlozPredchudce(data2);
        assertEquals(2, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data2);
        assertEquals(instance.zpristupniPredchudce(), data);
        assertEquals(instance.zpristupniPredchudce(), data2);

    }

    /**
     * Test of odeberAktualni method, of class AbstrDoubleList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testOdeberAktualni() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("odeberAktualni");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberAktualni();
    }

    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testOdeberAktualni2() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("odeberAktualni2");
        TestTrida data = new TestTrida(1);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(data);
        instance.odeberAktualni();
    }

    @Test
    public void testOdeberAktualni3() throws IAbstrDoubleList.ListException {
        System.out.println("odeberAktualni3");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        instance.vlozPrvni(data);
        instance.zpristupniPrvni();
        assertEquals(data, instance.odeberAktualni());
    }

    @Test
    public void testOdeberAktualni4() throws IAbstrDoubleList.ListException {
        System.out.println("odeberAktualni4");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.odeberAktualni(), data);
        assertEquals(instance.zpristupniPrvni(), data2);

    }

    @Test
    public void testOdeberAktualni5() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("odeberAktualni5");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida data5 = new TestTrida(5);
        TestTrida data6 = new TestTrida(6);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(data6);
        instance.vlozNaslednika(data5);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        instance.vlozPredchudce(data4);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(6, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(instance.zpristupniPredchudce(), data3);
        assertEquals(instance.odeberAktualni(), data3);
        assertEquals(instance.zpristupniAktualni(), data);
        assertEquals(instance.zpristupniNaslednika(), data2);
        assertEquals(instance.zpristupniNaslednika(), data4);
        assertEquals(instance.odeberAktualni(), data4);
        assertEquals(instance.zpristupniAktualni(), data);
        assertEquals(instance.zpristupniNaslednika(), data2);
        assertEquals(instance.odeberAktualni(), data2);
        assertEquals(instance.zpristupniAktualni(), data);
        assertEquals(instance.zpristupniPredchudce(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        assertEquals(instance.zpristupniPredchudce(), data);
        assertEquals(instance.odeberAktualni(), data);
        assertEquals(instance.odeberAktualni(), data5);
        assertEquals(instance.odeberAktualni(), data6);
        assertEquals(0, instance.getPocet());

    }

    @Test
    public void testOdeberAktualni6() throws IAbstrDoubleList.ListException {
        System.out.println("odeberAktualni6");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        instance.vlozPosledni(data4);
        assertEquals(4, instance.getPocet());
        assertEquals(instance.zpristupniPosledni(), data4);
        assertEquals(instance.odeberAktualni(), data4);
        assertEquals(instance.zpristupniAktualni(), data);
        assertEquals(instance.zpristupniPredchudce(), data3);
        assertEquals(instance.zpristupniNaslednika(), data);
        instance.vlozPosledni(data4);
        assertEquals(instance.zpristupniPrvni(), data);
        assertEquals(instance.odeberAktualni(), data);
        assertEquals(instance.zpristupniAktualni(), data2);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(instance.zpristupniNaslednika(), data2);

    }

    /**
     * Test of odeberPrvni method, of class AbstrDoubleList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testOdeberPrvni() throws NoSuchElementException {
        System.out.println("odeberPrvni");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.odeberPrvni();
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testOdeberPrvni2() {
        System.out.println("odeberPrvni2");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.vlozPrvni(expResult);
        Object result = instance.odeberPrvni();
        assertEquals(expResult, result);
        assertEquals(0, instance.getPocet());
    }

    @Test
    public void testOdeberPrvni3() {
        System.out.println("odeberPrvni3");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.odeberPrvni(), data);

    }

    @Test
    public void testOdeberPrvni4() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("odeberPrvni4");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida data5 = new TestTrida(5);
        TestTrida data6 = new TestTrida(6);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(data6);
        instance.vlozNaslednika(data5);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        instance.vlozPredchudce(data4);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(6, instance.getPocet());
        assertEquals(instance.odeberPrvni(), data);
        assertEquals(instance.odeberPrvni(), data2);
        assertEquals(instance.odeberPrvni(), data3);
        assertEquals(instance.odeberPrvni(), data4);
        assertEquals(instance.odeberPrvni(), data5);
        assertEquals(instance.odeberPrvni(), data6);
        assertEquals(0, instance.getPocet());

    }

    /**
     * Test of odeberPosledni method, of class AbstrDoubleList.
     */
    @Test(expected = NoSuchElementException.class)
    public void testOdeberPosledni() throws NoSuchElementException {
        System.out.println("odeberPosledni");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.odeberPosledni();
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testOdeberPosledni2() {
        System.out.println("odeberPosledni2");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.vlozPrvni(expResult);
        Object result = instance.odeberPosledni();
        assertEquals(expResult, result);
        assertEquals(0, instance.getPocet());
    }

    @Test
    public void testOdeberPosledni3() {
        System.out.println("odeberPosledni3");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        assertEquals(3, instance.getPocet());
        assertEquals(instance.odeberPosledni(), data3);

    }

    @Test
    public void testOdeberPosledni4() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("odeberPosledni4");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida data5 = new TestTrida(5);
        TestTrida data6 = new TestTrida(6);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(data6);
        instance.vlozNaslednika(data5);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        instance.vlozPredchudce(data4);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(6, instance.getPocet());
        assertEquals(instance.odeberPosledni(), data6);
        assertEquals(instance.odeberPosledni(), data5);
        assertEquals(instance.odeberPosledni(), data4);
        assertEquals(instance.odeberPosledni(), data3);
        assertEquals(instance.odeberPosledni(), data2);
        assertEquals(instance.odeberPosledni(), data);
        assertEquals(0, instance.getPocet());
    }

    /**
     * Test of odeberNaslednika method, of class AbstrDoubleList.
     *
     * @throws kolekce.IAbstrDoubleList.ListException
     */
    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testOdeberNaslednika() throws IAbstrDoubleList.ListException {
        System.out.println("odeberNaslednika");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.vlozPosledni(expResult);
        instance.odeberNaslednika();
        assertEquals(1, instance.getPocet());
        fail();
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberNaslednika2() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("odeberNaslednika2");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberNaslednika();
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testOdeberNaslednika3() throws IAbstrDoubleList.ListException {
        System.out.println("odeberNaslednika3");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.vlozPrvni(expResult);
        instance.zpristupniPrvni();
        Object result = instance.odeberNaslednika();
        assertEquals(expResult, result);
        assertEquals(0, instance.getPocet());
    }

    @Test
    public void testOdeberNaslednika4() throws IAbstrDoubleList.ListException {
        System.out.println("odeberNaslednika4");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        assertEquals(3, instance.getPocet());
        instance.zpristupniPosledni();
        instance.zpristupniPredchudce();
        assertEquals(instance.odeberNaslednika(), data3);
    }

    @Test
    public void testOdeberNaslednika5() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("odeberNaslednika5");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida data5 = new TestTrida(5);
        TestTrida data6 = new TestTrida(6);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(data6);
        instance.vlozNaslednika(data5);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        instance.vlozPredchudce(data4);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(6, instance.getPocet());
        instance.zpristupniPosledni();
        assertEquals(instance.odeberNaslednika(), data);
        assertEquals(instance.odeberNaslednika(), data2);
        assertEquals(instance.odeberNaslednika(), data3);
        assertEquals(instance.odeberNaslednika(), data4);
        assertEquals(instance.odeberNaslednika(), data5);
        assertEquals(instance.odeberNaslednika(), data6);
        assertEquals(0, instance.getPocet());

    }

    /**
     * Test of odeberPredchudce method, of class AbstrDoubleList.
     */
    @Test(expected = IAbstrDoubleList.ListException.class)
    public void testOdeberPredchudce() throws IAbstrDoubleList.ListException {
        System.out.println("odeberPredchudce");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.vlozPosledni(expResult);
        instance.odeberPredchudce();
        assertEquals(1, instance.getPocet());
        fail();
    }

    @Test(expected = NoSuchElementException.class)
    public void testOdeberPredchudce2() throws NoSuchElementException, IAbstrDoubleList.ListException {
        System.out.println("odeberPredchudce2");
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.odeberPredchudce();
        assertEquals(0, instance.getPocet());
        fail();
    }

    @Test
    public void testOdeberPredchudce3() throws IAbstrDoubleList.ListException {
        System.out.println("odeberPredchudce3");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida expResult = new TestTrida(1);
        instance.vlozPrvni(expResult);
        instance.zpristupniPrvni();
        Object result = instance.odeberPredchudce();
        assertEquals(expResult, result);
        assertEquals(0, instance.getPocet());
    }

    @Test
    public void testOdeberPredchudce4() throws IAbstrDoubleList.ListException {
        System.out.println("odeberNaslednika4");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        assertEquals(3, instance.getPocet());
        instance.zpristupniPrvni();
        assertEquals(instance.odeberPredchudce(), data3);
        assertEquals(instance.zpristupniPosledni(), data2);
    }

    @Test
    public void testOdeberPredchudce5() throws NullPointerException, IAbstrDoubleList.ListException {
        System.out.println("odeberPredchudce5");
        AbstrDoubleList instance = new AbstrDoubleList();
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida data5 = new TestTrida(5);
        TestTrida data6 = new TestTrida(6);
        instance.vlozPrvni(data2);
        instance.vlozPrvni(data);
        instance.vlozPosledni(data3);
        instance.zpristupniPosledni();
        instance.vlozNaslednika(data6);
        instance.vlozNaslednika(data5);
        assertEquals(instance.zpristupniPosledni(), data6);
        assertEquals(instance.zpristupniPredchudce(), data5);
        instance.vlozPredchudce(data4);
        assertEquals(instance.zpristupniPredchudce(), data4);
        assertEquals(6, instance.getPocet());
        instance.zpristupniPrvni();
        assertEquals(instance.odeberPredchudce(), data6);
        assertEquals(instance.odeberPredchudce(), data5);
        assertEquals(instance.odeberPredchudce(), data4);
        assertEquals(instance.odeberPredchudce(), data3);
        assertEquals(instance.odeberPredchudce(), data2);
        assertEquals(instance.odeberPredchudce(), data);
        assertEquals(0, instance.getPocet());

    }

    /**
     * Test of iterator method, of class AbstrDoubleList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        AbstrDoubleList instance = new AbstrDoubleList();
        Iterator result = instance.iterator();
        assertNotNull(result);
    }

    @Test
    public void testIterator2() {
        System.out.println("iterator2");
        TestTrida data = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrDoubleList instance = new AbstrDoubleList();
        instance.vlozPosledni(new TestTrida(1));
        instance.vlozPosledni(new TestTrida(2));
        instance.vlozPosledni(new TestTrida(3));
        instance.vlozPosledni(new TestTrida(4));
        instance.iterator().next();
        instance.iterator().next();
        instance.iterator().next();
        instance.iterator().next();
        instance.iterator().next();
        instance.iterator().next();
    }

    static class TestTrida implements Serializable {

        public final int a;

        public TestTrida(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }
}
