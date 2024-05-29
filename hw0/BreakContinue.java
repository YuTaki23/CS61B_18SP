public class BreakContinue {
    public static void windowPosSum(int[] a, int n){
        for (int i = 0; i < n; i++){
            if (a[i] < 0){
                continue;
            }
            for (int j = 0; j < n; j++){
                if (i + j + 1 > a.length - 1){
                    break;
                }
                a[i] += a[i + j + 1];
            }
        }
    }

    public static void main(String[] args){
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        System.out.println(java.util.Arrays.toString(a));
    }
}
