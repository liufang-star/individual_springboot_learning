package test;

public class 分治算法 {
    public static void main(String[] args) {
        fz(4, 'A', 'B', 'C');
    }

    public static void fz(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "-->" + c);
        } else {
            //num >=2
            //最上边的盘移动到B，可能会移动C  A--->B
            fz(num - 1, a, c, b);
            //将移动完之后的盘，剩下A盘中下面的盘，将其移动到C盘，  A--->C
            System.out.println("第" + num + "个盘从" + a + "-->" + c);
            //将
            fz(num - 1, b, a, c);
        }
    }
}
