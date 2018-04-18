package com.bamboocloud.homework.pay.common.util.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.IntStream;

import static com.bamboocloud.homework.pay.common.Constants.GENERAL_EXCEPTION_MSG;

/**
 * <p>文件名称: ExcelUtil </p>
 * <p>文件描述:   </p>
 * <p>版权所有: 版权所有(C)2017 BambooCloud</p>
 * <p>公 司: 深圳竹云科技有限公司</p>
 *
 * @author : 文洁
 * 创建日期: 2018/4/17
 */
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    private static ExcelUtil excelUtil = new ExcelUtil();

    private ExcelUtil() {
    }

    public static ExcelUtil getInstance() {
        return excelUtil;
    }

    /**
     * 不通过excel模版的方式,直接将数据对象导入到Excel中
     *
     * @param outPath 文件输出路径
     * @param obs     数据列表，用于要输出的数据对象
     * @param clz     导入的对象，通过反射机制实现
     */
    public void exportObjToExcel(String outPath, List obs, Class clz) {
        try (FileOutputStream fos = new FileOutputStream(new File(outPath));
             Workbook workbook = new SXSSFWorkbook(1000)) {
            Sheet sheet = workbook.createSheet();
            sheet.setDefaultColumnWidth(20);
            List<ExcelHeader> excelHeaders = setExcelHeaders(clz, sheet);
            if (null != obs && !obs.isEmpty()) {
                for (int i = 0; i < obs.size(); i++) {
                    Object obj = obs.get(i);
                    Row dataRow = sheet.createRow(i + 1);
                    int j = 0;
                    while (j < excelHeaders.size()) {
                        ExcelHeader header = excelHeaders.get(j);
                        Cell cell = dataRow.createCell(j);
                        Method method = clz.getMethod(header.getMethodName());
                        setCellValue(workbook, obj, cell, method);
                        j++;
                    }
                }
            }
            workbook.write(fos);
        } catch (SecurityException | IllegalArgumentException | IOException e) {
            LOGGER.error(StringUtils.join(e.getClass().getName(), " : ", e.getMessage()));
        } catch (Exception e) {
            LOGGER.error(GENERAL_EXCEPTION_MSG, e.getMessage());
        }
    }

    /**
     * 设置单元格值
     */
    private void setCellValue(Workbook workbook, Object obj, Cell cell, Method method) {
        try {
            Object returnType = method.getReturnType();
            Object value = method.invoke(obj);
            String nullValue = "null";
            if (value == null || nullValue.equals(value)) {
                cell.setCellValue("");
                return;
            }
            if (Timestamp.class.equals(returnType)) {
                cell.setCellValue((Timestamp) value);
                setTimeTypeCellStyle(workbook, cell);
            } else if (Date.class.equals(returnType)) {
                cell.setCellValue((Date) value);
                setTimeTypeCellStyle(workbook, cell);
            } else if (java.sql.Date.class.equals(returnType)) {
                cell.setCellValue((java.sql.Date) value);
                setTimeTypeCellStyle(workbook, cell);
            } else if (BigDecimal.class.equals(returnType)) {
                BigDecimal bigDecimal = (BigDecimal) value;
                //不使用科学计数法表示
                cell.setCellValue(bigDecimal.stripTrailingZeros().toPlainString());
            } else if (Double.class.equals(returnType)) {
                cell.setCellValue(getDoubleString((Double) value));
            } else {
                cell.setCellValue(String.valueOf(value));
            }
        } catch (Exception e) {
            LOGGER.error(GENERAL_EXCEPTION_MSG, e.getMessage());
        }
    }

    /**
     * 设置时间格式的单元格style
     */
    private void setTimeTypeCellStyle(Workbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }

    /**
     * 设置表格标题行
     *
     * @param clz   对象
     * @param sheet 表单
     */
    private List<ExcelHeader> setExcelHeaders(Class clz, Sheet sheet) {
        Row row = sheet.createRow(0);
        //获取表头
        List<ExcelHeader> excelHeaders = getExcelHeader(clz);
        //表头排序
        Collections.sort(excelHeaders);
        //将表头插入到第一行
        IntStream.range(0, excelHeaders.size()).forEach(i -> {
            Cell cell = row.createCell(i);
            cell.setCellValue(excelHeaders.get(i).getTitle());
        });
        return excelHeaders;
    }


    /**
     * 通过反射，得到在对应类中的注解，该方法通过注解在getter上
     */
    private List<ExcelHeader> getExcelHeader(Class clz) {
        List<ExcelHeader> headers = new ArrayList<>();
        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            //只需要获取get有关的方法
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                //获取到get方法中对应的注解
                ExcelResources er = method.getAnnotation(ExcelResources.class);
                //当一个类中存在有些字段的get未添加annotation 就不进行添加
                if (er != null) {
                    headers.add(new ExcelHeader(er.title(), er.order(), methodName));
                }
            }
        }
        return headers;
    }

    /**
     * 获取Excel表单
     *
     * @param file       文件名
     * @param sheetIndex 表单序号
     * @return sheet
     */
    public XSSFSheet readExcelSheet(String file, int sheetIndex) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {
            return workbook.getSheetAt(sheetIndex);
        } catch (Exception e) {
            LOGGER.error("failed to read Excel sheet : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 得到所有行的指定列部分值
     *
     * @param file        文件
     * @param sheetIndex  表单序号
     * @param skipHeader  是否跳过表头
     * @param columnStart 列开始
     * @param columnEnd   列结束
     */
    public List<ArrayList<String>> readAllRowLimitColumns(String file, int sheetIndex,
                                                          boolean skipHeader,
                                                          int columnStart, int columnEnd) {
        if (columnStart < 0 || columnEnd < columnStart) {
            LOGGER.error("起始列数设置错误");
            return Collections.emptyList();
        }
        XSSFSheet spreadsheet = readExcelSheet(file, sheetIndex);
        if (spreadsheet != null) {
            List<ArrayList<String>> result = new ArrayList<>();
            Iterator<Row> rowIterator = spreadsheet.iterator();
            if (skipHeader) {
                rowIterator.next();
            }
            while (rowIterator.hasNext()) {
                ArrayList<String> cells = new ArrayList<>();
                XSSFRow row = (XSSFRow) rowIterator.next();
                for (int i = columnStart; i <= columnEnd; i++) {
                    Cell cell = row.getCell(i);
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            setNumericCellValue(cells, cell);
                            break;
                        case Cell.CELL_TYPE_STRING:
                            cells.add(cell.getStringCellValue());
                            break;
                        default:
                            cells.add("");
                            LOGGER.error("导入格式异常，只允许导入数字或文本格式");
                            break;
                    }
                }
                result.add(cells);
            }
            return result;
        }
        return Collections.emptyList();
    }

    /**
     * 单独设置数字类型单元格值
     *
     * @param cells 行结果集
     * @param cell  单元格
     */
    private void setNumericCellValue(ArrayList<String> cells, Cell cell) {
        String finalValue = getDoubleTypeCellValue(cell);
        cells.add(finalValue);
    }

    /**
     * 获取小数类型单元格的值，去掉多余的O
     *
     * @param cell 单元格
     */
    private String getDoubleTypeCellValue(Cell cell) {
        Double cellValue = cell.getNumericCellValue();
        Double doubleValue = cellValue;
        //去掉多余小数位.0
        return getDoubleString(doubleValue);
    }

    /**
     * 获取小数类型单元格的值，去掉多余的O
     * @param doubleValue double数值
     */
    private String getDoubleString(Double doubleValue) {
        Object inputValue;
        final String s = ".0";
        Long longValue = Math.round(doubleValue);
        String finalValue;
        if (Double.parseDouble(longValue + s) == doubleValue) {
            inputValue = longValue;
            DecimalFormat df = new DecimalFormat("#");
            finalValue = String.valueOf(df.format(inputValue));
        } else {
            finalValue = String.valueOf(doubleValue);
        }
        return finalValue;
    }

}
