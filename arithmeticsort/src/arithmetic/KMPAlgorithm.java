package arithmetic;///**
// * @desc KMP算法
// * 基本介绍：
// * （1）暴力匹配算法
// *      1）如果当前字符匹配成功（即str1[i]=str2[i]），则i++,j++，继续匹配下一个字符
// *      2）如果失败，令i=i-(j-1)，j=0，相当于每次匹配失败时，i回溯，j被转为0
// *      3）用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费大量时间。（不可行）
// *      4）暴力匹配实现
// * （2）KMP算法介绍
// *      1）KMP是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置就经典算法。
// *      2）Knuth-Morris-Pratt字符串查找法，简称KMP。
// *      3）KMP算法就是利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共序列的长度，每次回溯时，通过next数组找到，
// *          前面匹配的位置，省去了大量的计算时间
// *      4）参考资料：https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
// * @Author xw
// * @Date 2019/9/27
// */
//public class KMPAlgorithm {
//    public static void main(String[] args) {
//        // 暴力匹配
//        String str1 = "ABCDE";
//        String str2 = "CD";
//        int index = violenceMatch(str1, str2);
//        if (index != -1) {
//            System.out.println("找到了，位置：" + index);
//        } else {
//            System.out.println("没有找到！");
//        }
//        // KMP算法介绍
//        // 字符串模板匹配值
//        str1 = "BBC ABCDAD ABCDABCDABDE";
//        str2 = "ABCDABD";
//        /*int[] next = kmpNext("ABCDABD");
//        System.out.println("next=" + Arrays.toString(next));*/
//        index = kmpMatch(str1, str2, kmpNext(str2));
//        if (index != -1) {
//            System.out.println("找到了，位置：" + index);
//        } else {
//            System.out.println("没有找到！");
//        }
//    }
//}

import java.util.Arrays;

public class KMPAlgorithm {

    public static void main(String[] args) {
        String a = "ABACABAD";
        String b = "BBC ABACABACABAD ABCDABDE";
        int result = kmp(b, a);

        //打印结果：和字符串获得匹配的位置
        System.out.println("resultPosion:" + result);
    }

    /**
     * KMP 匹配
     */
    public static int kmp(String str, String dest) {
        //1.首先计算出 部分匹配表
        int[] next = kmpnext(dest);

        System.out.println("next =" + Arrays.toString(next));
        //2.查找匹配位置
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 计算部分匹配表
     */
    public static int[] kmpnext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
