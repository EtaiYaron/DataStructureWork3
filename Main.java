//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double[] results = HashingExperimentUtils.measureInsertionsProbing();
        for (double val : results)
            System.out.print("value is:" + val);
    }
}