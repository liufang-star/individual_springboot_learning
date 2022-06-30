package arithmetic;///**
// * @desc 克鲁斯卡尔算法
// * 案例：公交车问题
// * 1. 某城市新增7个站点，A,B,C,D,E,F,G,现在需要修路7个站点连通
// * 2. 各个站点距离用连线表示，比如A-B距离12公里
// * 3. 问：如何修路保证各个站点都能连通，并且总的修建公路总里程最短
// * @Author xw
// * @Date 2019/10/8
// */
//public class KruskalAlgorithm {
//    private static final int INF = Integer.MAX_VALUE;
//    private char[] vertexs;
//    private int[][] matrix;
//    private int edgeNums; // 边的数量
//    public KruskalAlgorithm(char[] vertexs,int[][] matrix ) {
//        this.vertexs = vertexs;
//        this.matrix = matrix;
//        // 统计边
//        for (int i = 0; i < vertexs.length; i++) {
//            for (int j = i + 1; j < vertexs.length; j++) { // 每次少一条边，所以是i+1
//                if (this.matrix[i][j] != INF) {
//                    edgeNums++;
//                }
//            }
//        }
//    }
//    public static void main(String[] args) {
//        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//        int[][] matrix = {
//                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
//                /*A*/{ 0,   12, INF,  INF, INF, 16,  14 },
//                /*B*/{ 12,  0,   10,  INF, INF, 7,   INF},
//                /*C*/{ INF, 10,  0,   3,    5,  6,   INF },
//                /*D*/{ INF, INF, 3,   0,    4,  INF, INF },
//                /*E*/{ INF, INF, 5,   4,    0,  2,   8 },
//                /*F*/{ 16,  7,   6,   INF,  2,  0,   9 },
//                /*G*/{ 14,  INF, INF, INF,  8,  9,   0 }
//        };
//        // 创建KruskalCase对象实例
//        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm(vertexs, matrix);
//        //
//        kruskalAlgorithm.print();
//        kruskalAlgorithm.kruskal();
//    }
//}


import java.util.ArrayList;
import java.util.Collections;

public class KruskalAlgorithm {

    public static void main(String[] args) {
        int[][] edges = {
                {0, 1, 6},
                {0, 2, 1},
                {0, 3, 5},
                {2, 1, 5},
                {2, 3, 5},
                {2, 4, 5},
                {2, 5, 4},
                {1, 4, 3},
                {4, 5, 6},
                {5, 3, 2}
        };

        int n = 6;
        int[][] mstEdges = kruskal(n, edges);

        int totalCost = 0;
        System.out.println("Edges of MST: [node1, node2, cost]");
        //输出构树的边集
        for (int i = 0; i < mstEdges.length; i++) {
            int[] edge = mstEdges[i];
            for (int j = 0; j < edge.length; j++) {
                System.out.print(edge[j] + " ");
            }
            totalCost += edge[2];
            System.out.println();
        }

        System.out.println("Total cost of MST: " + totalCost);
    }

    public static int[][] kruskal(int n, int[][] edges) {
        /**
         * @Description: 克鲁斯卡尔算法求最小生成树
         * @Param: [n, edges] ==> [结点个数， 边集]
         * @return: int[] 构成最小生成树的边集
         * @Author: Aiven
         * @Date: 2019/6/27
         */
        int[] pres = new int[n]; //并查集
        int[] ranks = new int[n]; //结点的秩

        // 初始化：pres一开始设置每个元素的上一级是自己，ranks一开始设置每个元素的秩为0
        for (int i = 0; i < n; i++) {
            pres[i] = i;
            ranks[i] = 0;
        }

        //用自己定义的MyEdge类里面的compareTo排序，按边权排序
        ArrayList<MyEdge> edgesList = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            edgesList.add(new MyEdge(edges[i]));
        }
        // 边集从小到大排序
        Collections.sort(edgesList);

        int[][] mstEdges = new int[n - 1][3];
        int count = 0;
        for (int i = 0; i < edgesList.size(); i++) {
            int[] arr = edgesList.get(i).array;
            int a = arr[0], b = arr[1], c = arr[2];
            if (find(a, pres) != find(b, pres)) {
                unionSet(a, b, pres, ranks);
                mstEdges[count] = arr;
                count++;
            }
            if (count == n) {
                break;
            }
        }
        return mstEdges;
    }

    //并：合并两个集合，按秩合并
    public static void unionSet(int n1, int n2, int[] pres, int[] ranks) {
        int root1 = find(n1, pres);
        int root2 = find(n2, pres);
        //当两个元素不是同一组的时候才合并
        if (root1 != root2) {
            if (ranks[root1] < ranks[root2]) {
                pres[root1] = root2;
            } else {
                pres[root2] = root1;
                if (ranks[root1] == ranks[root2])
                    ranks[root1]++;
            }
        }
    }

    //查：查找元素的首级
    public static int find(int x, int[] pres) {
        int root = x;
        while (pres[root] != root)
            root = pres[root];

        //路径压缩
        int p = x;
        while (pres[p] != p) {
            int t = pres[p];
            pres[p] = root;
            p = t;
        }
        return root;
    }


}

// 边的排序类
class MyEdge implements Comparable {
    int[] array;

    MyEdge(int[] array) {
        this.array = array;
    }

    @Override
    public int compareTo(Object o) {
        o = (MyEdge) o;
        int[] arr = ((MyEdge) o).array;
        if (array[2] > arr[2]) {
            return 1;
        } else if (array[2] == arr[2]) {
            return 0;
        } else {
            return -1;
        }
    }
}

//   std output
//
//    Edges of MST: [node1, node2, cost]
//        0 2 1
//        5 3 2
//        1 4 3
//        2 5 4
//        2 1 5
//        0 0 0
//        Total cost of MST: 15

