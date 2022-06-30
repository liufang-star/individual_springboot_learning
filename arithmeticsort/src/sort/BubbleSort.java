package sort;

/**
 * @author 刘芳
 * @Description:<p>冒泡排序算法实现</p>
 * @time 2021-11-8
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 2, 8, 4, 7};//创建数组
        System.out.println("排序前");
        showArr(arr);//打印显示排序前
        //循环实现冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }

            }
        }
        System.out.println("排序后");
        showArr(arr);
    }

    //打印方法
    private static void showArr(int[] arr) {
        //增强for循环打印
        for (int a : arr) {
            System.out.print(a + "\t");

        }
        System.out.println();

    }

}


