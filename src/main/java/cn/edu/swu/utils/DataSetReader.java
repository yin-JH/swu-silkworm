package cn.edu.swu.utils;

import cn.edu.swu.entity.Question;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataSetReader {
    /**
     * @methodName：read
     * @author: wusheng
     * @date:
     * @param：String resourceName
     * @return：List<Question>
     * @throws: IOException
     * @description: 这是伍胜老师提供的excel接口，现已废弃，转用MySql数据库的接口
     */
    public List<Question> read(String resourceName) throws IOException {
        List<Question> questions = new ArrayList<>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                int index = 1;
                String type = row.getCell(index++).getStringCellValue();
                String question = row.getCell(index++).getStringCellValue();
                String keyword1 = row.getCell(index++).getStringCellValue();
                String keyword2 = row.getCell(index++).getStringCellValue();
                String answer   = row.getCell(index++).getStringCellValue();
                String mediaType = row.getCell(index).getStringCellValue();

                Question q = new Question();
                q.setType(type);
                q.setQuestion(question);
                q.addKeyword(keyword1);
                q.addKeyword(keyword2);
                q.setAnswer(answer);
                q.setMediaType(mediaType);

                questions.add(q);
            }
        } catch (IOException ioe) {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return questions;
    }
}
