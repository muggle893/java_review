public class PrintLeapYear {
    public static void main(String[] args) {
        // 输出 1000 - 2000 之间所有的闰年
        int count = 0; // 用来记录打印的个数，打印20个闰年换1行
        for (int i = 1000; i <= 2000; i++) {
            if (isLeapYear(i)) {
                System.out.print(i + " ");
                count++;
                if (count == 20) {
                    System.out.println();
                    count = 0;
                }
            }
        }
    }

    public static boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }
}
