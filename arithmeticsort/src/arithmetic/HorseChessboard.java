package arithmetic;

/**
 * @desc 马踏棋盘算法
 * @Author xw
 * @Date 2019/10/8
 */
//public class HorseChessboard {
//    private static int X; // 棋盘的列数
//    private static int Y; // 棋盘的行数
//    //创建一个数组，标记棋盘的各个位置是否被访问过
//    private static boolean visited[];
//    //使用一个属性，标记是否棋盘的所有位置都被访问
//    private static boolean finished; // 如果为true,表示成功
//    public static void main(String[] args) {
//        System.out.println("骑士周游算法，开始运行~~");
//        //测试骑士周游算法是否正确
//        X = 8;
//        Y = 8;
//        int row = 1; //马儿初始位置的行，从1开始编号
//        int column = 1; //马儿初始位置的列，从1开始编号
//        //创建棋盘
//        int[][] chessboard = new int[X][Y];
//        visited = new boolean[X * Y];//初始值都是false
//        //测试一下耗时
//        long start = System.currentTimeMillis();
//        traversalChessboard(chessboard, row - 1, column - 1, 1);
//        long end = System.currentTimeMillis();
//        System.out.println("共耗时: " + (end - start) + " 毫秒");
//        //输出棋盘的最后情况
//        for(int[] rows : chessboard) {
//            for(int step: rows) {
//                System.out.print(step + "\t");
//            }
//            System.out.println();
//        }
//    }
//}


import java.awt.Point;
import java.util.ArrayList;

public class HorseChessboard {
    private static int X; // 表示列
    private static int Y; // 表示行
    private static boolean visited[]; // 是否被访问
    private static boolean finished; // 是否全部完成

    // 进行行走
    public static void traversal(int[][] arr, int row, int col, int step) {
        arr[row][col] = step;
        visited[row * X + col] = true;// 初始位置标记为已访问
        // 获取下一步集合
        ArrayList<Point> ps = next(new Point(col, row));
        // 遍历集合
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            // 判断该点是否访问过
            if (!visited[p.y * X + p.x]) { // 没有访问过
                traversal(arr, p.y, p.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            arr[row][col] = 0;
            visited[row * X + col] = false;
        } else {
            finished = true;
        }
    }

    // 根据当前位置计算还有哪些位置可以走
    public static ArrayList<Point> next(Point cutPoint) {
        ArrayList<Point> ps = new ArrayList<Point>();
        Point p1 = new Point();
        // 判断是否可以走下一个位置
        if ((p1.x = cutPoint.x - 2) >= 0 && (p1.y = cutPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x - 1) >= 0 && (p1.y = cutPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x + 1) < X && (p1.y = cutPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x + 2) < X && (p1.y = cutPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x + 2) < X && (p1.y = cutPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x + 1) < X && (p1.y = cutPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x - 1) >= 0 && (p1.y = cutPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        if ((p1.x = cutPoint.x - 2) >= 0 && (p1.y = cutPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;
        int col = 1;
        int[][] arr = new int[X][Y];
        visited = new boolean[X * Y];
        System.out.println("开始");
        long start = System.currentTimeMillis();
        traversal(arr, row - 1, col - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时 = " + (end - start) + " 毫秒");
        for (int[] rows : arr) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
        System.out.println("结束");
    }
}
