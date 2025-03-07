import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Student tests for sorting algorithms.
 *
 * Redistribution of this file, in part or in whole,
 * is strictly prohibited.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class SortingStudentTests {
    private TeachingAssistant[] tas;
    private TeachingAssistant[] tasByName;
    private ComparatorPlus<TeachingAssistant> comp;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        tas = new TeachingAssistant[10];
        tas[0] = new TeachingAssistant("Afiq", 22, 4);
        tas[1] = new TeachingAssistant("David", 20, 2);
        tas[2] = new TeachingAssistant("Eric", 26, 2);
        tas[3] = new TeachingAssistant("Julia", 20, 3);
        tas[4] = new TeachingAssistant("Carey", 21, 3);
        tas[5] = new TeachingAssistant("Raymond", 20, 2);
        tas[6] = new TeachingAssistant("Saikrishna", 20, 3);
        tas[7] = new TeachingAssistant("Jonathan", 20, 3);
        tas[8] = new TeachingAssistant("Makoto", 19, 2);
        tas[9] = new TeachingAssistant("Cory", 21, 3);
        tasByName = new TeachingAssistant[10];
        tasByName[0] = tas[0];
        tasByName[1] = tas[4];
        tasByName[2] = tas[9];
        tasByName[3] = tas[1];
        tasByName[4] = tas[2];
        tasByName[5] = tas[7];
        tasByName[6] = tas[3];
        tasByName[7] = tas[8];
        tasByName[8] = tas[5];
        tasByName[9] = tas[6];

        comp = TeachingAssistant.getNameComparator();
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Sorting.bubbleSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 44);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 30);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        Sorting.selectionSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        Sorting.quickSort(tas, comp, new Random(0x600dc0de));
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 45);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(tas, comp);
        assertArrayEquals(tasByName, tas);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 48);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, 85, 3};
        int[] sortedArray = new int[] {3, 20, 28, 54, 58, 84, 85, 122};
        assertArrayEquals(sortedArray, Sorting.lsdRadixSort(unsortedArray));
    }

    @Test(timeout = TIMEOUT)
    public void testMsdRadixSort() {
        int[] unsortedArray = new int[] {54, 28, 58, 84, 20, 122, 85, 3};
        int[] sortedArray = new int[] {3, 20, 28, 54, 58, 84, 85, 122};
        assertArrayEquals(sortedArray, Sorting.msdRadixSort(unsortedArray));
    }

    /**
     * Class for testing proper sorting.
     */
    private static class TeachingAssistant {
        private String name;
        private int age;
        private int year;

        /**
         * Create a teaching assistant.
         *
         * @param name name of TA
         * @param age age of TA
         * @param year year at Tech of TA
         */
        public TeachingAssistant(String name, int age, int year) {
            this.name = name;
            this.age = age;
            this.year = year;
        }

        /**
         * Get the name of the teaching assistant.
         *
         * @return name of teaching assistant
         */
        public String getName() {
            return name;
        }

        /**
         * Get the age of the teaching assistant.
         *
         * @return age of teaching assistant
         */
        public int getAge() {
            return age;
        }

        /**
         * Get the year at Tech of the teaching assistant
         *
         * @return year at Tech of the teaching assistant
         */
        public int getYear() {
            return year;
        }

        /**
         * Set the name of the teaching assistant.
         *
         * @param name name of the teaching assistant
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Set the age of the teaching assistant.
         *
         * @param age age of the teaching assistant
         */
        public void setAge(int age) {
            this.age = age;
        }

        /**
         * Set the year at Tech of the teaching assistant.
         *
         * @param year year at Tech of the teaching assistant
         */
        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\tAge: " + age + "\tYear: " + year;
        }

        /**
         * Create a comparator that compares the names of the teaching
         * assistants.
         *
         * @return comparator that compares the names of the teaching assistants
         */
        public static ComparatorPlus<TeachingAssistant> getNameComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                        TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getName().compareTo(ta2.getName());
                }
            };
        }

        /**
         * Create a comparator that compares the age of each teaching assistant.
         *
         * @return comparator that compares the age of the teaching assistants.
         */
        public static ComparatorPlus<TeachingAssistant> getAgeComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                        TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getAge() - ta2.getAge();
                }
            };
        }

        /**
         * Create a comparator that compares the year at Tech of the teaching
         * assistants.
         *
         * @return comparator that compares the year at Tech of the teaching
         * assistants
         */
        public static ComparatorPlus<TeachingAssistant> getYearComparator() {
            return new ComparatorPlus<TeachingAssistant>() {
                @Override
                public int compare(TeachingAssistant ta1,
                        TeachingAssistant ta2) {
                    incrementCount();
                    return ta1.getYear() - ta2.getYear();
                }
            };
        }

    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }
    }
}
