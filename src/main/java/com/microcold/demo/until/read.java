package com.microcold.demo.until;

import com.linuxense.javadbf.DBFReader;
import com.microcold.demo.entity.ProvinceInfo;
import com.microcold.demo.service.ProvinceInfoService;
import com.zaxxer.hikari.util.UtilityElf;
import com.linuxense.javadbf.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class read
{
    /**
     * 获取字段名
     * @param path
     * @param charsetName
     * @return
     * @throws IOException
     */

    public static String[][] getFieldName(String path, String charsetName) throws IOException
    {
        InputStream fis = new FileInputStream(path);
        DBFReader dbfReader = new DBFReader(new FileInputStream(path), Charset.forName(charsetName));
        int fieldCount = dbfReader.getFieldCount();//获取字段数量
        String[][] fieldName = new String[fieldCount][2];
        for (int i = 0; i < fieldCount; i++) {
            fieldName[i][1] = dbfReader.getField(i).getType().toString();
            fieldName[i][0]=dbfReader.getField(i).getName();
        }
        dbfReader.close();
        fis.close();
        return fieldName;
    }
    /**
     * 读dbf记录
     * @param path
     * @return
     * @throws IOException
     */
    public static List<Map<String, String>> readDbf(String path, String charsetName) throws IOException {
        List<Map<String, String>> rowList = new ArrayList<>();
        InputStream fis = new FileInputStream(path);
        DBFReader dbfReader = new DBFReader(new FileInputStream(path), Charset.forName(charsetName));
        Object[] rowValues;
        while ((rowValues = dbfReader.nextRecord()) != null) {
            Map<String, String> rowMap = new HashMap<String, String>();
            for (int i = 0; i < rowValues.length; i++) {
                rowMap.put(dbfReader.getField(i).getName(), String.valueOf(rowValues[3]).trim());
            }
		System.out.println(rowMap);
            rowList.add(rowMap);

        }
        dbfReader.close();
        fis.close();
        return rowList;

    }
    public static void readDBF(String path)

    {

        InputStream fis = null;

        try
        {
            //读取文件的输入流
            fis  = new FileInputStream(path);
            //根据输入流初始化一个DBFReader实例，用来读取DBF文件信息
            DBFReader reader = new DBFReader(fis);
            //调用DBFReader对实例方法得到path文件中字段的个数
            int fieldsCount = reader.getFieldCount();
            System.out.println("字段数:"+fieldsCount);
            //取出字段信息

            for( int i=0; i<fieldsCount; i++)
            {
                DBFField field = reader.getField(i);
                System.out.println(field.getName());

            }
            Object[] rowValues;
            //一条条取出path文件中记录
            while((rowValues = reader.nextRecord()) != null)
            {
                for( int i=0; i<rowValues.length; i++)
                {
                    System.out.println(rowValues[i]);

                }
            }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
                fis.close();
            }catch(Exception e){}
        }

    }

    public static void main(String[] args)throws IOException{
        String path="C:\\Users\\micro\\Desktop\\china region\\省界_region.dbf";
        String charset="GB2312";
        String[][] title = read.getFieldName(path, charset);
        ProvinceInfoService info=new ProvinceInfoService();
        //readDBF(path);
        for(int i=0;i<title.length;i++){
            for(int j=0;j<2;j++){
                System.out.println(title[i][j]);
            }

        }

        //List<Map<String,String>> result=read.readDbf(path,charset);
    }
}