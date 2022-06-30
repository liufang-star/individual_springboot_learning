package cn.seal.configuration;

/**
 * @Description: 印章圆圈类
 * @Author 刘芳
 * @Date: Created in 15:41 2022-06-29
 */
public class SealCircle {
    public SealCircle(Integer lineSize, Integer width,Integer height) {
        this.lineSize = lineSize;
        this.width = width;
        this.height = height;
    }

    /**
     * 线宽度
     */
    private Integer lineSize;
    /**
     * 半径
     */
    private Integer width;

    /**
     * 测试拉取数据是否成功
     */
    private Integer liufang;
    /**
     * 半径
     */
    private Integer height;

    public Integer getLineSize() {
        return lineSize;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
