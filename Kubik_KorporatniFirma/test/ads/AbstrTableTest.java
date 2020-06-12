package ads;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstrTableTest {

    public AbstrTableTest() {
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
     * Test of zrus method, of class AbstrTable.
     */
    @Test
    public void testZrus() {
        System.out.println("zrus");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        AbstrTable instance = new AbstrTable();
        instance.vloz(1, data1);
        instance.vloz(2, data2);
        instance.zrus();
        boolean result = instance.jePrazdny();
        assertEquals(true, result);
    }

    /**
     * Test of jePrazdny method, of class AbstrTable.
     */
    @Test
    public void testJePrazdny() {
        System.out.println("jePrazdny");
        AbstrTable instance = new AbstrTable();
        instance.vloz("d", new TestTrida(1));
        boolean result = instance.jePrazdny();
        assertEquals(false, result);
    }

    /**
     * Test of najdi method, of class AbstrTable.
     */
    @Test(expected = NoSuchElementException.class)
    public void testNajdi1() throws NoSuchElementException{
        System.out.println("najdi1");
        TestTrida data = new TestTrida(1);
        AbstrTable<Integer,Integer> instance = new AbstrTable();
        instance.vloz(1, data.a);
        instance.najdi(5);
    }
    @Test
    public void testNajdi2() {
        System.out.println("najdi2");
        TestTrida data = new TestTrida(1);
        AbstrTable<Integer,Integer> instance = new AbstrTable();
        instance.vloz(1, data.a);
        assertEquals((Integer) data.a, instance.najdi(1));
    }
    @Test
    public void testNajdi3() {
        System.out.println("najdi3");
        TestTrida data1 = new TestTrida(5);
        TestTrida data2 = new TestTrida(1);
        AbstrTable<Integer,Integer> instance = new AbstrTable();
        instance.vloz(5, data2.a);
        instance.vloz(1, data1.a);
        assertEquals((Integer) data1.a, instance.najdi(1));
        assertEquals((Integer) data2.a, instance.najdi(5));
    }

    /**
     * Test of vloz method, of class AbstrTable.
     */
    @Test
    public void testVloz() {
        System.out.println("vloz");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTable<Integer,Integer> instance = new AbstrTable();
        instance.vloz(1, data1.a);
        instance.vloz(2, data2.a);
        instance.vloz(3, data3.a);
        instance.vloz(4, data4.a);
        assertEquals((Integer) data4.a, instance.najdi(4));
        assertEquals((Integer) data3.a, instance.najdi(3));
        assertEquals((Integer) data2.a, instance.najdi(2));
        assertEquals((Integer) data1.a, instance.najdi(1));
    }

    /**
     * Test of odeber method, of class AbstrTable.
     */
    @Test
    public void testOdeber() {
        System.out.println("odeber");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTable<Integer,Integer> instance = new AbstrTable<>();
        instance.vloz(1, data1.a);
        instance.vloz(2, data2.a);
        instance.vloz(3, data3.a);
        instance.vloz(4, data4.a);
        assertEquals((Integer) data4.a, instance.odeber(4));
        assertEquals((Integer) data3.a, instance.odeber(3));
        assertEquals((Integer) data2.a, instance.odeber(2));
        assertEquals((Integer) data1.a, instance.odeber(1));
        assertEquals(true, instance.jePrazdny());
    }

    /**
     * Test of iterator method, of class AbstrTable.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        TestTrida data1 = new TestTrida(1);
        TestTrida data2 = new TestTrida(2);
        TestTrida data3 = new TestTrida(3);
        TestTrida data4 = new TestTrida(4);
        AbstrTable<Integer,Integer> instance = new AbstrTable<>();
        instance.vloz(1, data1.a);
        instance.vloz(2, data2.a);
        instance.vloz(3, data3.a);
        instance.vloz(4, data4.a);
        Iterator itr = instance.iterator();
        assertEquals((Integer) data1.a, itr.next());
        assertEquals((Integer) data2.a, itr.next());
        assertEquals((Integer) data3.a, itr.next());
        assertEquals((Integer) data4.a, itr.next());
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
