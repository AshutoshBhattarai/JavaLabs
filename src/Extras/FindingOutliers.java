package Extras;


public class FindingOutliers {
    public static void main(String[] args) {
        int[] arr = {160, 3, 1719, 19, 11, 13, -21};

        for(int i =0;i < arr.length;i++) {
            if(Math.abs(arr[i])%2 == 1) System.out.println(arr[i]);
        }
        System.out.println("Outlier is " + findOutliers(arr));

    }


    public static boolean isEven(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) count++;
        }
        if (count > arr.length-1)
            return true;
        return false;
    }

    public static int findOutliers(int[] arr) {
        boolean isEven = isEven(arr);
        int outlier = 0;
        for (int i = 0; i < arr.length; i++) {
            if (isEven) {
                if (Math.abs(arr[i]) % 2 == 1) outlier = arr[i];
            } else {
                if (Math.abs(arr[i]) % 2 == 0) outlier = arr[i];
            }

        }
        return outlier;

    }


}
