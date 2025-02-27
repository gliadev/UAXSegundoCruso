public class Main {
    public static void main(String[] args) {
        Sumatorio sum1 = new Sumatorio(23);
        Sumatorio sum2 = new Sumatorio(13);
        Sumatorio sum3 = new Sumatorio(15);
        sum1.start();
        sum2.start();
        sum3.start();
        try {
            sum1.join();
            sum2.join();
            sum3.join();
        } catch (InterruptedException err) {
            System.err.printf("Error: %s\n", err.getMessage());
        }
        double res = sum1.getTotal() + sum2.getTotal() + sum3.getTotal();
        double formula = res / 8;
        System.out.printf("El resultado es: %f\n", formula);
    }
}