
public class MainStuff{

    public static void main(String[]Args)
    {

        /**
         * I created 2 TestAnimals and one will sleep for twice as long as the
         * other. So that means the 150 will awaken 2 times compared to the 300's
         * one time. Play around with it and input different numbers to get different
         * results. It just shows how each object is an individual thread, running
         * simultaneously.
         */
        TestAnimal B1 = new TestAnimal(300);
        TestAnimal B2 = new TestAnimal(150);
    }
}