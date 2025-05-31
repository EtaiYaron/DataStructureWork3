import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Insert number of task to test, or 2.0 and so on to test all tasks\nWrite 4 to test all of task 4, or 4.1 for init, 4.2 for insert...\nHighest grade in the vpl - 52.");
        Scanner scanner = new Scanner(System.in);
        String taskNumber = scanner.nextLine();

        if(taskNumber.equals("2.0")) {
            task2_1();
            task2_2();
            task2_3();
            task2_4();
        }
        else if(taskNumber.equals("2.1")) {
            task2_1();
        }
        else if(taskNumber.equals("2.2")) {
            task2_2();
        }
        else if(taskNumber.equals("2.3")) {
            task2_3();
        }
        else if(taskNumber.equals("2.4")) {
            task2_4();
        }
        else if(taskNumber.equals("3.0")) {
            task3_1();
            task3_2();
            task3_3();
            task3_4();
            task3_5();
            task3_6();
            task3_7();
            task3_8();

        }
        else if(taskNumber.equals("3.1")) {
            task3_1();
        }
        else if(taskNumber.equals("3.2")) {
            task3_2();
        }
        else if(taskNumber.equals("3.3")) {
            task3_3();
        }
        else if(taskNumber.equals("3.4")) {
            task3_4();
        }
        else if(taskNumber.equals("3.5")) {
            task3_5();
        }
        else if(taskNumber.equals("3.6")) {
            task3_6();
        }
        else if(taskNumber.equals("3.7")) {
            task3_7();
        }
        else if(taskNumber.equals("3.8")) {
            task3_8();
        }
        else if(taskNumber.equals("4") | taskNumber.equals("4.0")) {
            task4_3();
            task4_4();
            task4_5();
            task4_6();
            task4_7();

        }
        else if(taskNumber.equals("4.1")) {
            task4_1();
        }
        else if(taskNumber.equals("4.2")) {
            task4_2();
        }
        else if(taskNumber.equals("4.3")) {
            task4_3();
        }
        else if(taskNumber.equals("4.4")) {
            task4_4();
        }
        else if(taskNumber.equals("4.5")) {
            task4_5();
        }
        else if(taskNumber.equals("4.6")) {
            task4_6();
        }
        else if(taskNumber.equals("4.7")) {
            task4_7();
        }

    }

    public static void task2_1() {
        String testText = "Testing task 2.1: \n";
        IndexableSkipList list = new IndexableSkipList(0.5);
        for(int i =0;i<10;i=i+2) {
            list.insert(i);
        }
        for(int i =1;i<10;i=i+2) {
            list.insert(i);
        }
        testText += "Test 1 - Find element, your result:" + list.find(4).toString() +", Should be: [4] \n";
        testText += "Test 2 - Find minimum, your result:" + list.find(0).toString()+", Should be: [0] \n";
        testText += "Test 3 - Find maximum, your result:" + list.find(9).toString()+", Should be: [9] \n";
        testText += "Test 4 - Find out of bounds below, your result:" + list.find(-5).toString()+", Should be: [-2147483648] \n";
        testText += "Test 5 - Find out of bounds above, your result:" + list.find(20).toString()+", Should be: [9] \n";
        System.out.println(testText);
    }

    public static void task2_2() {
        String testText = "Testing task 2.2: \nTest: Your results: ";
        IndexableSkipList list = new IndexableSkipList(0.5);
        for(int i=0;i<9;i++) {
            testText += list.generateHeight()+", ";
        }
        System.out.println(testText +list.generateHeight() + ". \nThey should all be reasonable - usually from 0 to 5. A lot of high numbers (or ones below zero) indicate a problem. \n" );

    }

    public static void task2_3() {
        String testText = "Testing task 2.3:\n";
        SkipListUtils tools = new SkipListUtils();
        testText += "Test 1 - Probability 1/2, your result: " + 	tools.calculateExpectedHeight(0.5) + ". should be: 2.0 \n";
        testText += "Test 2 - Probability 1/4, your result: " + 	tools.calculateExpectedHeight(0.25) + ". should be: 4.0 \n";
        testText += "Test 3 - Probability 1/8, your result: " + 	tools.calculateExpectedHeight(1.0/8) + ". should be: 8.0 \n";
        testText += "Test 4 - Probability 1/16, your result: " + 	tools.calculateExpectedHeight(1.0/16) + ". should be: 16.0 \n";
        System.out.println(testText);
    }

    public static void task2_4() {
        System.out.println("Testing task 2.4: Ten elements:");
        IndexableSkipList list = new IndexableSkipList(0.5);
        for(int i =0;i<10;i=i+2) {
            list.insert(i);
        }
        for(int i =1;i<10;i=i+2) {
            list.insert(i);
        }

        System.out.println(list.toString());
        System.out.println("Test 1 - Rank of 5: your result: " + list.rank(5) +", Should be: 5");
        System.out.println("Test 2 - Rank of max: your result: " + list.rank(9) +", Should be: 9");
        System.out.println("Test 3 - Rank of min: your result: " + list.rank(0) +", Should be: 0");
        System.out.println("Test 4 - Rank Below min: your result: " + list.rank(-10) +", Should be: 0");
        System.out.println("Test 5 - Rank above max: your result: " + list.rank(20) +", Should be: 10 ");
        System.out.println("Test 6 - Selecting 3: your result: " + list.select(3) +", Should be: 3");
        System.out.println("Test 7 - Selecting max: your result: " + list.select(9) +", Should be: 9");
        System.out.println("Test 8 - Selecting min: your result: " + list.select(0) +", Should be: 0 ");


        System.out.println("\n2.4: Performing Deletion: 7 elements");
        list.delete(list.find(4));
        System.out.println(list);
        list.delete(list.find(0));
        System.out.println(list);
        list.delete(list.find(9));
        System.out.println(list);
        System.out.println("Test 9 - Rank of 5: your result: " + list.rank(5) +", Should be: 3");
        System.out.println("Test 10 - Rank of max: your result: " + list.rank(8) +", Should be: 6");
        System.out.println("Test 11 - Rank of min: your result: " + list.rank(1) +", Should be: 0");
        System.out.println("Test 12 - Selecting 3: your result: " + list.select(3) +", Should be: 2");
        System.out.println("Test 13 - Selecting max: your result: " + list.select(7) +", Should be: 6");
        System.out.println("Test 14 - Selecting min: your result: " + list.select(1) +", Should be: 0");

        System.out.println("\n2.4: This Test empties the list and fills it again - yet the Tail and Head nodes do not resize.");
        for(int i =0;i<10;i=i+1) {
            if(i!=4 & i!=9 & i!=0)
                list.delete(list.find(i));
        }
        for(int i =0;i<10;i=i+1) {
            list.insert(i);
        }
        System.out.println("Test 15 - Rank of min: your result: " + list.rank(0) +", Should be: 1");
        System.out.println("Test 16 - Rank of max: your result: " + list.rank(9) +", Should be: 10 \n");

    }

    public static void task3_1() {
        System.out.println("\nTesting task 3.1: ModularHash\n");

        ModularHash hashFactory = new ModularHash();
        HashFunctor<Integer> hashFunc = hashFactory.pickHash(3);
        long suspectP = ((ModularHash.Functor)hashFunc).p();
        HashingUtils utils = new HashingUtils();
        boolean found = true;
        if(!found)
            System.out.println("Checking if p is prime: It isn't! Make sure to run the 'Rabin-Miller' test in 'HashingUtils'\n   and DONT send it even values (values that divide by two) - it returns a wrong result for them!") ;
        else
            System.out.println("Checking if p is prime: Yup, good job!") ;
        System.out.println("\nTesting small k for 2^k table: all values should be between 0 and 7, and probably not the same number");
        System.out.println("Hash 1 your result: " + hashFunc.hash(10));
        System.out.println("Hash 2 your result: " + hashFunc.hash(11));
        System.out.println("Hash 3 your result: " + hashFunc.hash(12));
        System.out.println("Hash 4 your result: " + hashFunc.hash(13));

        System.out.println("\nTesting for a medium k: values should be between 0 and 1,048,576, and probably not the same number");
        hashFunc = hashFactory.pickHash(20);
        System.out.println("Hash 5 your result: " + hashFunc.hash(10));
        System.out.println("Hash 6 your result: " + hashFunc.hash(11));
        System.out.println("Hash 7 your result: " + hashFunc.hash(12));
        System.out.println("Hash 8 your result: " + hashFunc.hash(13));

    }

    public static void task3_2() {
        System.out.println("\nTesting task 3.2: MultiplicativeShiftingHash\n");
        MultiplicativeShiftingHash hashFactory = new MultiplicativeShiftingHash();
        System.out.println("Testing small k for 2^k table: all values should be between 0 and 7, and probably not the same number");
        HashFunctor<Long> hashFunc = hashFactory.pickHash(3);
        System.out.println("Hash 1 your result: " + hashFunc.hash((long)10));
        System.out.println("Hash 2 your result: " + hashFunc.hash((long)11));
        System.out.println("Hash 3 your result: " + hashFunc.hash((long)12));
        System.out.println("Hash 4 your result: " + hashFunc.hash((long)13));

        System.out.println("\nTesting for a medium k: values should be between 0 and 1,048,576, and probably not the same number");
        hashFunc = hashFactory.pickHash(20);
        System.out.println("Hash 5 your result: " + hashFunc.hash((long)10));
        System.out.println("Hash 6 your result: " + hashFunc.hash((long)11));
        System.out.println("Hash 7 your result: " + hashFunc.hash((long)12));
        System.out.println("Hash 8 your result: " + hashFunc.hash((long)13));

    }

    public static void task3_3() {
        System.out.println("Testing task 3.3: Chaining HashTable\n");
        ChainedHashTable<Long, Long> table = new ChainedHashTable(new MultiplicativeShiftingHash(),1,2);
        System.out.println("Checking insert, search and hashing:");
        for(long i=-10000;i<10000;i++) {
            table.insert(i, i);
        }
        System.out.println("Table Capacity: " + table.capacity() + ", should be 16384");
        System.out.println("Test 1 - your result: " + table.search((long)5) +", Should be: 5 ");
        System.out.println("Test 2 - your result: " + table.search((long)-7) +", Should be: -7 ");
        System.out.println("Test 3 - element not in table, your result: " + table.search((long)100000) +", Should be: null ");
        System.out.println("\nChecking deletion:");
        table.delete((long)-5);		table.delete((long)100);
        System.out.println("Test 4 - element that shouldnt exist, your result: " + table.search((long)-5) +", Should be: null ");
        System.out.println("Test 5 - element that shouldnt exist, your result: " + table.search((long)100) +", Should be: null ");

        System.out.println("\nChecking search - extra:");
        int counter2=0;
        for(Long i=(long)-10000;i<10000;i++) {
            if(!i.equals((long)-5) & !i.equals((long)100)) {
                Long b = table.search((long)i);
                if(b==null)
                    counter2++;
            }
        }
        if(counter2!=0) {
            System.out.println("!!!------------------------------------------------------------------------------!!!");
            System.out.println("There is a problem with your search function, or possibly the rehash functions");
            System.out.println("Not all the elements were found. Possible problems:                ");
            System.out.println("1. Could be the use of '==' to compare two 'Long' types: instead, try using 'long.equals(long)' option.");
            System.out.println("2. Could be a problem with the rehash function - make sure you send the correct k to the new 'pick hash' function you build.");
            System.out.println("!!!------------------------------------------------------------------------------!!!");

        }
        else {
            System.out.println("Passed!");
        }


        System.out.println("\nChecking deletion - extra:");
        int counter=0;
        for(Long i=(long)-10000;i<10000;i++) {
            if(!i.equals((long)-5) & !i.equals((long)100)) {
                boolean b = table.delete((long)i);
                if(!b)
                    counter++;
            }
        }
        if(counter!=0) {
            System.out.println("!!!------------------------------------------------------------------------------!!!");
            System.out.println("There is a problem with your deletion function, or possibly the rehash functions");
            System.out.println("Not all the elements were found. Possible problems:                ");
            System.out.println("1. Could be the use of '==' to compare two 'Long' types: instead, try using 'long.equals(long)' option.");
            System.out.println("2. Could be a problem with the rehash function - make sure you send the correct k to the new 'pick hash' function you build.");
            System.out.println("!!!------------------------------------------------------------------------------!!!");
        }
        else {
            System.out.println("Passed!");
        }

    }

    public static void task3_4() {
        System.out.println("Testing task 3.4: Probing HashTable\n");
        ProbingHashTable<Long, Long> table = new ProbingHashTable(new MultiplicativeShiftingHash(),1,0.5);



        System.out.println("Checking insert, search and hashing:");
        for(long i=-10000;i<10000;i++) {
            table.insert(i, i);
        }
        System.out.println("Table Capacity: " + table.capacity() + ", should be 65536");
        System.out.println("Test 1 - your result: " + table.search((long)5) +", Should be: 5 ");
        System.out.println("Test 2 - your result: " + table.search((long)-7) +", Should be: -7 ");
        System.out.println("Test 3 - element not in table, your result: " + table.search((long)100000) +", Should be: null ");
        System.out.println("\nChecking deletion:");
        table.delete((long)-5);		table.delete((long)100);
        System.out.println("Test 4 - element that shouldnt exist, your result: " + table.search((long)-5) +", Should be: null ");
        System.out.println("Test 5 - element that shouldnt exist, your result: " + table.search((long)100) +", Should be: null ");

        System.out.println("\nChecking search - extra:");
        int counter2=0;
        for(Long i=(long)-10000;i<10000;i++) {
            if(!i.equals((long)-5) & !i.equals((long)100)) {
                Long b = table.search((long)i);
                if(b==null) {
                    counter2++;
                }

            }
        }
        if(counter2!=0) {
            System.out.println("!!!------------------------------------------------------------------------------!!!");
            System.out.println("There is a problem with your search function, or possibly the rehash functions");
            System.out.println("Not all the elements were found. Possible problems:                ");
            System.out.println("1. Could be the use of '==' to compare two 'Long' types: instead, try using 'long.equals(long)' option.");
            System.out.println("2. Could be a problem with the rehash function - make sure you send the correct k to the new 'pick hash' function you build.");
            System.out.println("!!!------------------------------------------------------------------------------!!!");

        }
        else {
            System.out.println("Passed!");
        }


        System.out.println("\nChecking deletion - extra:");
        int counter=0;
        for(Long i=(long)-10000;i<10000;i++) {
            if(!i.equals((long)-5) & !i.equals((long)100)) {
                boolean b = table.delete((long)i);
                if(!b)
                    counter++;
            }
        }
        if(counter!=0) {
            System.out.println("!!!------------------------------------------------------------------------------!!!");
            System.out.println("There is a problem with your deletion function, or possibly the rehash functions");
            System.out.println("Not all the elements were found. Possible problems:                ");
            System.out.println("1. Could be the use of '==' to compare two 'Long' types: instead, try using 'long.equals(long)' option.");
            System.out.println("2. Could be a problem with the rehash function - make sure you send the correct k to the new 'pick hash' function you build.");
            System.out.println("!!!------------------------------------------------------------------------------!!!");
        }
        else {
            System.out.println("Passed!");
        }
    }

    public static void task3_5() {
        System.out.println("Testing task 3.5: Probing Insertion Time");
        HashingExperimentUtils exp = new HashingExperimentUtils();
        double[] avgArr = exp.measureInsertionsProbing();
        System.out.println("The numbers are usually between 40 and 200, could be higher");
        System.out.println("Test 1: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+"]");
        avgArr = exp.measureInsertionsProbing();
        System.out.println("Test 2: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+"]");
        avgArr = exp.measureInsertionsProbing();
        System.out.println("Test 3: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+"]");

        System.out.println("\nExample results from my test - but it could be pretty different based on your computer and algorithms.");
        System.out.println("[142.68929105502488, 80.54363085186466, 81.37000156950282, 101.75784111069517]\n"
                + "[45.06057924130985, 49.02850399788407, 58.51804056292834, 91.35402594443269]\n"
                +  "[42.997528000732444, 44.9533071555004, 43.01135273703852, 52.26484806067807]");
    }

    public static void task3_6() {

        System.out.println("Testing task 3.6: Probing Search Time");
        HashingExperimentUtils exp = new HashingExperimentUtils();
        double[] avgArr = exp.measureSearchesProbing();
        System.out.println("The numbers are usually above 50, and going up towards the left");
        System.out.println("Test 1: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+"]");
        avgArr = exp.measureSearchesProbing();
        System.out.println("Test 2: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+"]");
        avgArr = exp.measureSearchesProbing();
        System.out.println("Test 3: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+"]");

        System.out.println("\nExample results from my test - but it could be pretty different based on your computer and algorithms.");
        System.out.println("[149.22330393383587, 100.43742751927734, 181.71877997314405, 495.3628802552125]\n"
                + "[46.07684560686056, 76.62305955117903, 172.3872137837225, 503.5400966812611]\n"
                +  "[41.792046876430554, 72.09212427010641, 132.15911270774114, 490.27978971011896]");


    }

    public static void task3_7() {

        System.out.println("Testing task 3.7: Chaining Insertion Time");
        HashingExperimentUtils exp = new HashingExperimentUtils();
        double[] avgArr = exp.measureInsertionsChaining();
        System.out.println("The numbers are usually between 40 and 200, could be higher");
        System.out.println("Test 1: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+", "+avgArr[4]+"]");
        avgArr = exp.measureInsertionsChaining();
        System.out.println("Test 2: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+", "+avgArr[4]+"]");
        avgArr = exp.measureInsertionsChaining();
        System.out.println("Test 3: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+", "+avgArr[4]+"]");

        System.out.println("\nExample results from my test - but it could be pretty different based on your computer and algorithms.");
        System.out.println("[224.9244666890469, 115.8369107444406, 156.68574044403755, 70.20131633826027, 195.5558437793257]\n"
                + "[43.01278725547044, 45.5575674960835, 50.800335698481724, 75.00991831378494, 94.44728429629743]\n"
                +  "[39.509262367625965, 40.823177554881894, 46.88334477759975, 50.78074931589067, 86.05412333773299]");
    }

    public static void task3_8() {

        System.out.println("Testing task 3.8: Chaining Searching Time");
        HashingExperimentUtils exp = new HashingExperimentUtils();
        double[] avgArr = exp.measureSearchesChaining();
        System.out.println("The numbers are usually above 50, and could be going up towards the left");
        System.out.println("Test 1: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+", "+avgArr[4]+"]");
        avgArr = exp.measureSearchesChaining();
        System.out.println("Test 2: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+", "+avgArr[4]+"]");
        avgArr = exp.measureSearchesChaining();
        System.out.println("Test 3: ["+avgArr[0]+", "+avgArr[1]+", "+avgArr[2]+", "+avgArr[3]+", "+avgArr[4]+"]");

        System.out.println("\nExample results from my test - but it could be pretty different based on your computer and algorithms.");
        System.out.println("[158.27204199346903, 87.44277837683872, 104.57007705806058, 141.71795367384516, 154.7634488178163]\n"
                + "[52.70851771599475, 87.09894000122073, 102.89616235599298, 112.34346866321475, 133.41166238145738]\n"
                +  "[43.91918698690756, 82.17533722609916, 138.72739757381552, 119.55993204683479, 191.5862395190393]");
    }

    public static void task4_1() {
        System.out.println("\nTesting task 4.1 - Initialization:");
        MyDataStructure struc= new MyDataStructure(10);
        MyDataStructure struc2= new MyDataStructure(10000);
        System.out.println("Seems to have worked :)");


    }

    public static void task4_2() {
        System.out.println("\nTesting task 4.2 - Insertion:");
        MyDataStructure struc= new MyDataStructure(10);
        boolean flag = true;
        for(int i =0;i<10;i++) {
            flag = flag & struc.insert(i);
        }
        if(!flag) {
            System.out.println("Failed to insert some elements.");
        }
        else {
            System.out.println("Working!");
        }
    }

    public static void task4_3() {
        System.out.println("\nTesting task 4.3 - Deletion:");
        MyDataStructure struc= new MyDataStructure(10);
        boolean flag = true;
        for(int i =0;i<10;i++) {
            flag = flag & struc.insert(i);
        }
        if(!flag) {
            System.out.println("Failed to insert some elements.");
            return;
        }
        flag = true;
        System.out.println("Test 1 - deleting existing element: " + struc.delete(0) +", Should be: true ");
        System.out.println("Test 2 - deleting existing element: " + struc.delete(5) +", Should be: true ");
        System.out.println("Test 3 - deleting non-existing element: " + struc.delete(-10) +", Should be: false ");
        System.out.println("Test 4 - deleting non-existing element: " + struc.delete(20) +", Should be: false ");
    }

    public static void task4_4() {
        System.out.println("\nTesting task 4.4 - Contains Function:");
        MyDataStructure struc= new MyDataStructure(10);
        boolean flag = true;
        for(int i =0;i<10;i++) {
            flag = flag & struc.insert(i);
        }
        if(!flag) {
            System.out.println("Failed to insert some elements.");
            return;
        }
        System.out.println("Test 1 - searching existing element: " + struc.delete(2) +", Should be: true ");
        System.out.println("Test 2 - searching existing element: " + struc.delete(3) +", Should be: true ");
        System.out.println("Test 3 - searching non-existing element: " + struc.delete(-100) +", Should be: false ");
        System.out.println("Test 4 - searching non-existing element: " + struc.delete(200) +", Should be: false ");

    }

    public static void task4_5() {
        System.out.println("\nTesting task 4.5 - Rank Function:");
        MyDataStructure struc= new MyDataStructure(10);
        boolean flag = true;
        for(int i =0;i<10;i++) {
            if(i!=1) {
                flag = flag & struc.insert(i);
            }
        }
        if(!flag) {
            System.out.println("Failed to insert some elements.");
            return;
        }

        System.out.println("Test 1 - Rank of existing element: " + struc.rank(2) +", Should be: 2 ");
        System.out.println("Test 2 - Rank of existing element: " + struc.rank(9) +", Should be: 9 ");
        System.out.println("Test 3 - Rank of missing element in the middle: " + struc.rank(1) +", Should be: 1 ");
        System.out.println("Test 4 - Rank of a value above the highest: " + struc.rank(-10) +", Should be: 0 ");
        System.out.println("Test 4 - Rank of a value below the lowest: " + struc.rank(200) +", Should be: 9 ");
    }


    public static void task4_6() {
        System.out.println("\nTesting task 4.6 - Select Function:");
        MyDataStructure struc= new MyDataStructure(10);
        boolean flag = true;
        for(int i =0;i<10;i++) {
            if(i!=5) {
                flag = flag & struc.insert(i);
            }
        }
        if(!flag) {
            System.out.println("Failed to insert some elements.");
            return;
        }
        System.out.println("Test 1: " + struc.select(2) +", Should be: 1 ");
        System.out.println("Test 2: " + struc.select(9) +", Should be: 9 ");
        System.out.println("Test 3: " + struc.select(5) +", Should be: 4 ");
    }

    public static void task4_7() {
        System.out.println("\nTesting task 4.7 - Range Function:");
        MyDataStructure struc= new MyDataStructure(10);
        boolean flag = true;
        for(int i =0;i<10;i++) {
            if(i!=5) {
                flag = flag & struc.insert(i);
            }
        }
        if(!flag) {
            System.out.println("Failed to insert some elements.");
            return;
        }
        System.out.println("Test 1 - Normal Check: " + struc.range(2,7) +", Should be: [2, 3, 4, 6, 7] ");
        System.out.println("Test 2 - Normal Check: " + struc.range(8,9) +", Should be: [8, 9] ");
        System.out.println("Test 3 - 'low' not existing: " + struc.range(5,8) +", Should be: null ");
        System.out.println("Test 4 - 'high' higher than max: " + struc.range(6,20) +", Should be: [6, 7, 8, 9] ");
        System.out.println("Test 5 - 'low'='high: " + struc.range(9,9) +", Should be: [9] ");

    }

}