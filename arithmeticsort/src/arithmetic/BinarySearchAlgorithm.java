package arithmetic;

/**
 * @author lf
 * @desc 二分查询 （非递归方式）
 * 案例：
 * {1,3,8,10,11,67,100},编程实现二分法查找，要求使用非递归方式完成。
 * @Date 2021
 */
public class BinarySearchAlgorithm {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8);
        if (index != -1) {
            System.out.println("找到了，下标为：" + index);
        } else {
            System.out.println("没有找到---");
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                //中间值（mid） > 需要查找的值（target） ---> 向左找
                right = mid - 1;
            } else {
                //中间值（mid） < 需要查找的值（target） ---> 向右找
                left = mid + 1;
            }
        }
        return -1;
    }
}
