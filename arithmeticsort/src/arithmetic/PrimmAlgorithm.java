package arithmetic;///**
// * @desc 普利姆算法
// * 应用案例：修路问题
// * <p>
// * 思路分析
// *  1.从<A>顶点开始处理=><A,G> 2
// *      A,C[7] A-G[2] A-B[5] =>
// *  2.<A,G>开始，将A和G顶点和他们相邻的还没有访问的顶面进行处理=> <A,G,B>
// *      A-C[7] A-B[5] G-B[3] G-F[6]
// *  3.<A,G,B>开始，将A,G,B顶点和他们相邻的还没有访问的顶面进行处理=> <A,G,B>
// *      A-C[7] G-E[4] G-F[6] B-D[9]
// *  ...
// *  4.{A,G,B,E,F,D} -> C // 第6次大循环，对应边<A,C>权值：7 => <A,G,B,E,F,D,C>
// * @Author xw
// * @Date 2019/10/4
// */
//public class PrimAlgorithm {
//    public static void main(String[] args) {
//        char[] data = {'A','B','C','D','E','F','G'};
//        int verxs = data.length;
//        // 邻接矩阵
//        int[][] weight = new int[][] {
//                {10000,5,7,10000,10000,10000,2},
//                {5,10000,10000,9,10000,10000,3},
//                {7,10000,10000,10000,8,10000,10000},
//                {10000,9,10000,10000,10000,4,10000},
//                {10000,10000,8,10000,10000,5,4},
//                {10000,10000,10000,4,5,10000,6},
//                {2,3,10000,10000,4,6,10000}
//        };
//        // 创建MGraph对象
//        MGraph graph = new MGraph(verxs);
//        // 创建最小树
//        MinTree minTree = new MinTree();
//        minTree.createGraph(graph, verxs, data, weight);
//        // 输出
//        minTree.showGraph(graph);
//        // 测试普利姆算法
//        minTree.prim(graph, 0);
//    }
//}

public class PrimmAlgorithm {

    public static void main(String[] args) {
        int block = Integer.MAX_VALUE;
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int nodes = data.length;
        int[][] weight = {
                {block, 5, 7, block, block, block, 2},
                {5, block, block, 9, block, block, 3},
                {7, block, block, block, 8, block, block},
                {block, 9, block, block, block, 4, block},
                {block, block, 8, block, block, 5, 4},
                {block, block, block, 4, 5, block, 6},
                {2, 3, block, block, 4, 6, block}};
        MyGraph myGraph = new MyGraph(nodes);
        MinTree minTree = new MinTree();
        minTree.createGraph(myGraph, nodes, data, weight);
        minTree.showGraph(myGraph);
        minTree.prim(myGraph, 1);
    }


    private static class MinTree {


        private void prim(MyGraph myGraph, int v) {
            /**
             * visited[] 默认值是0，表示没有访问过
             */
            int visited[] = new int[myGraph.nodes];
            //标记当前节点为已访问
            visited[v] = 1;
            //n1和n2标记两个顶点的下标
            int n1 = -1;
            int n2 = -1;
            int minWeight = Integer.MAX_VALUE;
            /**
             * 因为有myGraph.nodes个顶点，普利姆算法结束后，有myGraph.nodes条边
             */
            for (int k = 1; k < myGraph.nodes; k++) {
                /**
                 * 确定每一次生成的子图，和哪个节点距离最近
                 * 其中i节点表示访问过的节点
                 * j节点表示还没有访问过的节点
                 */
                for (int i = 0; i < myGraph.nodes; i++) {
                    for (int j = 0; j < myGraph.nodes; j++) {
                        if (visited[i] == 1 && visited[j] == 0 && myGraph.weight[i][j] < minWeight) {
                            minWeight = myGraph.weight[i][j];
                            n1 = i;
                            n2 = j;
                        }
                    }
                }
                /**
                 * 找到距离最小的边
                 * 并将当前这个节点标记为已经访问过
                 */
                System.out.println("边<" + myGraph.data[n1] + "," + myGraph.data[n2] + "> 权值：" + minWeight);
                visited[n2] = 1;
                minWeight = Integer.MAX_VALUE;
            }
        }

        private void createGraph(MyGraph myGraph, int nodes, char[] data, int[][] weight) {
            for (int i = 0; i < nodes; i++) {
                myGraph.data[i] = data[i];
                for (int j = 0; j < nodes; j++) {
                    myGraph.weight[i][j] = weight[i][j];
                }
            }
        }

        private void showGraph(MyGraph myGraph) {
            int nodes = myGraph.nodes;
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    System.out.print(myGraph.weight[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    static class MyGraph {
        int nodes;//表示图的节点个数
        char[] data;//存放节点数据
        int[][] weight;//存放边

        private MyGraph(int nodes) {
            this.nodes = nodes;
            this.data = new char[nodes];
            this.weight = new int[nodes][nodes];
        }
    }
}


