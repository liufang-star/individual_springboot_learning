package test;

public class 二分查找非递归 {

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 6, 78, 100};
        int index = erfen(arr, 1);
        if (index != -1) {
            System.out.println("找到了..." + index);
        } else {
            System.out.println("没找到..." + index);
        }
    }

    private static int erfen(int arr[], int targ) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mind = (left + right) / 2;
            if (arr[mind] == targ) {
                return mind;
            } else if (arr[mind] > targ) {  //左边找
                right = mind - 1;
            } else {
                left = mind + 1;
            }
        }
        return -1;
    }
}
