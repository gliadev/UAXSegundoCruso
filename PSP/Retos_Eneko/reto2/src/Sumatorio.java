public class Sumatorio extends Thread {
    private int num;
    private double total = 0.0;

    public Sumatorio (int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void run() {
        if (num % 2 == 0) {
            num = num - 1;
        }

        for (int i = num; i >= 1; i = i -2) {
            total += i;
        }
    }
}
