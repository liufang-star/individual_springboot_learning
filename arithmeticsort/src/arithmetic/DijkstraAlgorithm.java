package arithmetic;
// * @desc 迪杰斯特拉算法
// * 案例：最短路径问题
// * 1. 战争时期，胜利乡有7个村庄(A,B,C,D,E,F,G)，现在有6个邮差，从G点出发，需要分别把邮件分别送到A,B,C,D,E,F 六个村庄
// * 2. 各个村庄的距离用边线表示（权），比如A-B距离5公里
// * 3. 问：如何计算最短距离
// *
// * @Author xw
// * @Date 2019/10/8
// */
//public class DijkstraAlgorithm {
//    public static void main(String[] args) {
//        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//        int[][] matrix = new int[vertex.length][vertex.length];
//        final int N = 65535;
//        matrix[0] = new int[]{N,5,7,N,N,N,2};
//        matrix[1] = new int[]{5,N,N,9,N,N,3};
//        matrix[2] = new int[]{7,N,N,N,8,N,N};
//        matrix[3] = new int[]{N,9,N,N,N,4,N};
//        matrix[4] = new int[]{N,N,8,N,N,5,4};
//        matrix[5] = new int[]{N,N,N,4,5,N,6};
//        matrix[6] = new int[]{2,3,N,N,4,6,N};
//        // 创建Graph对象
//        Graph graph = new Graph(vertex, matrix);
//        graph.showGraph();
//        // 测试迪杰斯特拉算法
//        graph.dsj(6); // G
//        graph.showDijkstra();
//    }
//}

import java.util.Arrays;

/**
 * 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int INF = 1000;//表示不可以连接

        matrix[0] = new int[]{INF, 5, 7, INF, INF, INF, 2};
        matrix[1] = new int[]{5, INF, INF, 9, INF, INF, 3};
        matrix[2] = new int[]{7, INF, INF, INF, 8, INF, INF};
        matrix[3] = new int[]{INF, 9, INF, INF, INF, 4, INF};
        matrix[4] = new int[]{INF, INF, 8, INF, INF, 5, 4};
        matrix[5] = new int[]{INF, INF, INF, 4, 5, INF, 6};
        matrix[6] = new int[]{2, 3, INF, INF, 4, 6, INF};

        //创建Graph
        Graph graph = new Graph(vertex, matrix);
        //打印一下，观察邻接矩阵是否OK
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dijkstra(6);
        graph.showDijkstra();

    }

}

class Graph {
    //所有顶点
    private char[] vertexs;
    //邻接矩阵
    private int[][] matrix;
    //
    private VisitedVertex visitedVertex;

    /**
     * 构造器
     *
     * @param vertexs
     * @param matrix
     */
    public Graph(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    /**
     * 打印图
     */
    public void showGraph() {
        for (int[] line : this.matrix) {
            System.out.println(Arrays.toString(line));
        }
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index 出发顶点
     */
    public void dijkstra(int index) {
        //获取已访问顶点实例
        this.visitedVertex = new VisitedVertex(index, this.vertexs.length);
        //访问出发顶点index后且处理index顶点的周围顶点
        updateAroundOfIndex(index);

        //为什么遍历次数是1~this.vertexs.length 因为最终边数为：顶点数-1
        for (int i = 1; i < this.vertexs.length; i++) {
            //选择新的未访问顶点并且是与出发顶点最近的  “访问这个新顶点”
            index = this.visitedVertex.selectUnVisitedIndex();
            //访问这个新顶点后，处理它的周围顶点
            updateAroundOfIndex(index);
        }

    }

    public void showDijkstra() {
        this.visitedVertex.show();
    }

    /**
     * 当找到下一个要访问的顶点index时，更新index顶点到周围顶点的距离和标记周围顶点的前驱顶点为index
     *
     * @param index
     */
    public void updateAroundOfIndex(int index) {
        int dis = 0;
        //matrix[index]就是index到它周围顶点的距离
        for (int aroundIndex = 0; aroundIndex < this.matrix[index].length; aroundIndex++) {
            //dis=出发顶点到index的距离+index到周围顶点aroundIndex的距离
            dis = this.visitedVertex.getDis(index) + this.matrix[index][aroundIndex];
            //如果index的周围顶点aroundIndex未访问过且dis距离小于出发顶点到aroundIndex顶点的最短距离
            if (!this.visitedVertex.isVisited(aroundIndex) && dis < this.visitedVertex.getDis(aroundIndex)) {
                //则更新出发顶点到i顶点的最短距离为dis
                this.visitedVertex.updateDis(aroundIndex, dis);
                //则更新i顶点的前驱顶点为index
                this.visitedVertex.updatePre(aroundIndex, index);
            }

        }

    }

}

//记录访问顶点的集合
class VisitedVertex {

    //记录访问过的顶点 1已访问，0未访问
    public int[] already_visited;
    //每个顶点下标表示为前一个顶点的下标
    public int[] pre_visited;
    /**
     * 出发顶点到每个顶点（下标）的最短距离，比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，
     * 求的最短距离就会存放到sort_dis
     */
    public int[] sort_dis;
    //定义INF为最远距离
    private static final int INF = 1000;

    /**
     * 构造器：
     *
     * @param index 出发顶点下标
     * @param len   顶点个数
     */
    public VisitedVertex(int index, int len) {
        this.already_visited = new int[len];
        this.pre_visited = new int[len];
        this.sort_dis = new int[len];

        //初始化数最短距离组：出发顶点到所有顶点的距离都为无限远（初始化）
        Arrays.fill(this.sort_dis, INF);
        //将出发顶点标记为已访问
        this.already_visited[index] = 1;
        //出发顶点到自身的最短距离当然为0
        this.sort_dis[index] = 0;
    }

    /**
     * 判断下标为index顶点是否被访问过
     *
     * @param index
     * @return
     */
    public boolean isVisited(int index) {
        return this.already_visited[index] == 1;
    }

    /**
     * 返回出发顶点到下标index顶点的最短距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return this.sort_dis[index];
    }

    /**
     * 返回访问到下标index顶点时的前一个顶点
     *
     * @param index
     * @return
     */
    public int getPre(int index) {
        return this.pre_visited[index];
    }

    /**
     * 更新当index顶点被访问时，前一个被访问顶点为pre
     *
     * @param index
     * @param pre
     */
    public void updatePre(int index, int pre) {
        this.pre_visited[index] = pre;
    }

    /**
     * 更新出发顶点到index顶点的最短距离为dis
     *
     * @param index
     * @param dis
     */
    public void updateDis(int index, int dis) {
        this.sort_dis[index] = dis;
    }

    /**
     * 选择里出发点最近且未访问过的顶点的下标
     *
     * @return
     */
    public int selectUnVisitedIndex() {
        int minDis = INF;
        int index = 0;
        for (int i = 0; i < this.already_visited.length; i++) {
            if (this.already_visited[i] == 0 && this.sort_dis[i] < minDis) {
                minDis = this.sort_dis[i];
                index = i;
            }
        }
        //找到下一个要访问的顶点了，并且“访问它”
        this.already_visited[index] = 1;
        return index;
    }

    /**
     * 输出最后的三个结果
     */
    public void show() {
        System.out.println("############################");
        for (int i : this.already_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : this.pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int index = 0;
        for (int i : this.sort_dis) {
            if (i != INF) {
                System.out.print(vertex[index] + "[" + i + "] ");
            } else {
                System.out.println("N");
            }
            index++;
        }
    }
}
