package com.bamboocloud.homework.pay.common.util.excel;

/**
 * <p>文件名称: ExcelHeader </p>
 * <p>文件描述: 标题头  </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/17
 */
public class ExcelHeader implements Comparable<ExcelHeader> {

    private String title;
    private int order;
    private String methodName;

    public ExcelHeader() {
    }

    public ExcelHeader(String title, int order, String methodName) {
        super();
        this.title = title;
        this.order = order;
        this.methodName = methodName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public int compareTo(ExcelHeader o) {
        return Integer.compare(this.order, o.order);
    }

    @Override
    public String toString() {
        return "ExcelHeader [title=" + title + ", order=" + order + ", methodName=" + methodName + "]";
    }

}
