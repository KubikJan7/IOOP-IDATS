package ads;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstrTreeTest {

    public AbstrTreeTest() {
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
     * Test of zrus method, of class AbstrTree.
     */
    @Test
    public void testZrus() {
        System.out.println("zrus");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.zrus();
        assertEquals(true, instance.jePrazdny());
        
    }

    /**
     * Test of jePrazdny method, of class AbstrTree.
     */
    @Test
    public void testJePrazdny() {
        System.out.println("jePrazdny");
        AbstrTree instance = new AbstrTree();
        boolean result = instance.jePrazdny();
        assertEquals(true, result);
    }

    /**
     * Test of mohutnost method, of class AbstrTree.
     */
    @Test
    public void testMohutnost() {
        System.out.println("mohutnost");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.vlozList(data3);
        assertEquals(3, instance.mohutnost());
        assertEquals(instance.odeberList(2),data3);
    }

    /**
     * Test of vlozKoren method, of class AbstrTree.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testVlozKoren1() throws UnsupportedOperationException{
        System.out.println("vlozKoren1");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.vlozKoren(data2);
    }
    @Test
    public void testVlozKoren2() {
        System.out.println("vlozKoren2");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.vlozList(data3);
        assertEquals(3, instance.mohutnost());
        assertEquals(instance.zpristupniKoren(),data1);
    }

    /**
     * Test of vlozList method, of class AbstrTree.
     */
    @Test
    public void testVlozList() {
        System.out.println("vlozList");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.zpristupniSyna(1);
        instance.vlozList(data3);
        instance.vlozList(data4);
        assertEquals(4, instance.mohutnost());
        assertEquals(instance.zpristupniKoren(),data1);
        assertEquals(instance.zpristupniSyna(1),data2);
        assertEquals(instance.zpristupniSyna(1),data3);
        assertEquals(instance.zpristupniOtce(),data2);
        assertEquals(instance.zpristupniSyna(2),data4);
    }

    /**
     * Test of odeberKoren method, of class AbstrTree.
     */
    @Test
    public void testOdeberKoren1() {
        System.out.println("odeberKoren1");
        TestTrida data1 = new TestTrida(1);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        assertEquals(1, instance.mohutnost());
        assertEquals(instance.odeberKoren(),data1);
        assertEquals(0,instance.mohutnost());
        assertEquals(instance.jePrazdny(),true);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testOdeberKoren2() throws UnsupportedOperationException{
        System.out.println("odeberKoren2");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        assertEquals(2, instance.mohutnost());
        instance.odeberKoren();
    }

    /**
     * Test of odeberList method, of class AbstrTree.
     */
    @Test
    public void testOdeberList() {
        System.out.println("odeberList");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida result;
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.zpristupniSyna(1);
        instance.vlozList(data3);
        instance.vlozList(data4);
        assertEquals(4, instance.mohutnost());
        assertEquals(instance.odeberList(2),data4);
        assertEquals(3, instance.mohutnost());
        assertEquals(data1,instance.zpristupniOtce());
        result = (TestTrida) instance.odeberList(1);
        assertEquals(result.a,data2.a);
        assertEquals(1, instance.mohutnost());
        assertEquals(instance.zpristupniKoren(),data1);
    }

    /**
     * Test of zpristupniKoren method, of class AbstrTree.
     */
    @Test
    public void testZpristupniKoren() {
        System.out.println("zpristupniKoren");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.zpristupniSyna(1);
        instance.vlozList(data3);
        instance.vlozList(data4);
        assertEquals(4, instance.mohutnost());
        assertEquals(instance.zpristupniKoren(),data1);
        assertEquals(instance.zpristupniSyna(1),data2);
        assertEquals(instance.zpristupniSyna(1),data3);
        assertEquals(instance.zpristupniOtce(),data2);
        assertEquals(instance.zpristupniSyna(2),data4);
    }

    /**
     * Test of zpristupniSyna method, of class AbstrTree.
     */
    @Test
    public void testZpristupniSyna() {
        System.out.println("zpristupniSyna");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.zpristupniSyna(1);
        instance.vlozList(data3);
        instance.vlozList(data4);
        assertEquals(4, instance.mohutnost());
        assertEquals(instance.zpristupniKoren(),data1);
        assertEquals(instance.zpristupniSyna(1),data2);
        assertEquals(instance.zpristupniSyna(1),data3);
        assertEquals(instance.zpristupniOtce(),data2);
        assertEquals(instance.zpristupniSyna(2),data4);
    }

    /**
     * Test of zpristupniOtce method, of class AbstrTree.
     */
    @Test
    public void testZpristupniOtce() {
        System.out.println("zpristupniOtce");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.zpristupniSyna(1);
        instance.vlozList(data3);
        instance.vlozList(data4);
        assertEquals(4, instance.mohutnost());
        assertEquals(instance.zpristupniKoren(),data1);
        assertEquals(instance.zpristupniSyna(1),data2);
        assertEquals(instance.zpristupniSyna(1),data3);
        assertEquals(instance.zpristupniOtce(),data2);
        assertEquals(instance.zpristupniSyna(2),data4);
    }

    /**
     * Test of iterator method, of class AbstrTree.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        TestTrida data5 = new TestTrida(5);
        TestTrida data6 = new TestTrida(6);
        TestTrida result;
        AbstrTree instance = new AbstrTree();
        instance.vlozKoren(data1);
        instance.zpristupniKoren();
        instance.vlozList(data2);
        instance.vlozList(data3);
        instance.zpristupniSyna(1);
        instance.vlozList(data4);
        instance.vlozList(data5);
        instance.zpristupniOtce();
        instance.zpristupniSyna(2);
        instance.vlozList(data6);
        Iterator<TestTrida> itr = instance.iterator();
        assertEquals(6, instance.mohutnost());
        assertEquals(itr.next().a,data1.a);
        assertEquals(itr.next().a,data2.a);
        assertEquals(itr.next().a,data4.a);
        assertEquals(itr.next().a,data5.a);
        assertEquals(itr.next().a,data3.a);
        assertEquals(itr.next().a,data6.a);
    }

    class TestTrida {

        public int a;

        public TestTrida(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }
    }
}
