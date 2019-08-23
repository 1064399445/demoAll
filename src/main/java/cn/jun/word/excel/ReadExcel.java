package cn.jun.word.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {

    private static Map<String,String> projectMap = new HashMap<>();
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        getInitProjectMap();
        readexcel("G:/123.xlsx");
    }

    public static void readexcel(String path) throws Exception {
        File file = new File("G:/newExcel.xlsx");
        FileOutputStream fileOutputStream  = new FileOutputStream (file);
        //获取Excel对象
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(path));
        //创建excel对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        //获取一个Sheet对象
        XSSFSheet sheet = wb.getSheetAt(0);
        //创建sheet对象
        XSSFSheet createSheet = workbook.createSheet("sheet-0");
        XSSFRow excelRow = createSheet.createRow(0);
        for(int i=0;i<list.size();i++){
            XSSFCell excelCell = excelRow.createCell(i);
            excelCell.setCellValue(list.get(i));
        }
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        //获取Row对象
        Person person = null;
        int j = 1;
        for(int i=1;i<rowNum;i++){
            XSSFRow row = sheet.getRow(i);
            //日期
            Date date = (Date) getCellFormatValue(row.getCell(0));
            //工号
            String no = (String)getCellFormatValue(row.getCell(1));
            //姓名
            String name = (String)getCellFormatValue(row.getCell(2));
            //项目
            String project = (String)getCellFormatValue(row.getCell(3));
            //价格
            String price = (String)getCellFormatValue(row.getCell(4));
            if(person == null){
                person = new Person();
                Map<String,String> map = new HashMap<>();
                map.put(projectMap.get(project), price);
                person.setProject(map);
                person.setName(name);
                person.setNo(no);
                person.setDate(date);
            }else {
                if(name.equals(person.getName())){
                    Map<String,String> map = person.getProject();
                    map.put(projectMap.get(project), price);
                    person.setProject(map);
                }else {
                    //写入患者数据
                    XSSFRow createRow = createSheet.createRow(j++);
                    //写入名字
                    XSSFCell nameCell = createRow.createCell(0);
                    nameCell.setCellValue(person.getName());
                    for(Map.Entry entry : person.getProject().entrySet()){
                        XSSFCell mapCell = createRow.createCell(list.indexOf(entry.getKey()));
                        mapCell.setCellValue(entry.getValue().toString());
                    }
                    System.out.println("写入第" + j + "条数据");
                    //下个患者
                    person = new Person();
                    Map<String,String> map = new HashMap<>();
                    map.put(projectMap.get(project), price);
                    person.setProject(map);
                    person.setName(name);
                    person.setNo(no);
                    person.setDate(date);
                }
            }
        }
        try {
            workbook.write(fileOutputStream);
        }finally {
            workbook.close();
        }
    }

    /**
     * 根据Cell类型设置数据
     * @param cell
     * @return Object
     */
    private static Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {

            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {
                        // 如果是纯数字
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    private static void getInitProjectMap(){
        projectMap.put("床位费","床位费");
        projectMap.put("取暖费","取暖费");
        projectMap.put("西药费","西药费");
        projectMap.put("中成药","中成药");
        projectMap.put("中草药","中草药");
        projectMap.put("检查费","检查费");
        projectMap.put("电诊费","电诊费");
        projectMap.put("治疗费","治疗费");
        projectMap.put("手术费","手术费");
        projectMap.put("护理费","护理费");
        projectMap.put("化验费","化验费");
        projectMap.put("磁共振","磁共振");
        projectMap.put("CT费"  ,"CT费");
        projectMap.put("X光费" ,"X光费");
        projectMap.put("处置费","处置费");
        projectMap.put("注射费","注射费");
        projectMap.put("输氧费","输氧费");
        projectMap.put("输血费","输血费");
        projectMap.put("诊察费","诊察费");
        projectMap.put("接生费","接生费");
        projectMap.put("麻醉费","麻醉费");
        projectMap.put("材料"  ,"材料");
        projectMap.put("其它"  ,"其它");
        list.add("患者姓名");
        list.add("总费用");
        list.add("其它");
        list.add("材料");
        list.add("X光费");
        list.add("CT费");
        list.add("中成药");
        list.add("中草药");
        list.add("化验费");
        list.add("取暖费");
        list.add("处置费");
        list.add("床位费");
        list.add("手术费");
        list.add("护理费");
        list.add("接生费");
        list.add("检查费");
        list.add("治疗费");
        list.add("电诊费");
        list.add("磁共振");
        list.add("西药费");
        list.add("诊察费");
        list.add("输氧费");
        list.add("输血费");
        list.add("麻醉费");
        list.add("注射费");
    }

}
