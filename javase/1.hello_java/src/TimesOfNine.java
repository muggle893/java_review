
public class TimesOfNine {
    public static void main(String[] args) {
        int times = 0;
        for (int i = 1; i <= 100; i++) {
            int tmpNum = i;
            while (tmpNum != 0) {
                if (tmpNum % 10 == 9) {
                    times++;
                }
                tmpNum /= 10;
            }
        }
        System.out.println(times);
    }


}
