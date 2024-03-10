package com.roadest.roadproredact;

import com.roadest.roadproredact.models.Road;
import com.roadest.roadproredact.models.Roads;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordExport {
    public static void exportToWord(String path, Roads roads, List<RoadCrest> roadCrests) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Отчет");
        titleRun.setBold(true);
        titleRun.setFontFamily("Times New Roman");
        titleRun.setFontSize(14);
        XWPFTable table = document.createTable();
        XWPFTableRow tableRowOne = table.getRow(0);
        XWPFRun tableRun = tableRowOne.getCell(0).getParagraphs().get(0).createRun();
        tableRun.setBold(true);
        tableRun.setText("Материал дорожного полотна");
        tableRun = tableRowOne.addNewTableCell().getParagraphs().get(0).createRun();
        tableRun.setBold(true);
        tableRun.setText("Длина");
        tableRun = tableRowOne.addNewTableCell().getParagraphs().get(0).createRun();
        tableRun.setBold(true);
        tableRun.setText("Количество полос");
        tableRun = tableRowOne.addNewTableCell().getParagraphs().get(0).createRun();
        tableRun.setBold(true);
        tableRun.setText("Знаки");
        for (Road road: roads.getRoads()){
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText(road.getType().toString());
            tableRow.getCell(1).setText(String.format("%.2f",road.getLength())+" м");
            tableRow.getCell(2).setText(road.getStripeList().getList().size() +" шт.");
            tableRow.getCell(3).setText(road.getStripeList().getSigns());
        }
        tableRowOne = table.createRow();
        tableRowOne.getCell(0).setText("Итого");
        tableRowOne.getCell(1).setText(String.format("%.2f",roads.getLength())+" м");
        tableRowOne.getCell(2).setText("");
        tableRowOne.getCell(3).setText(roads.getSignsCount()+" шт.");
        FileOutputStream out = new FileOutputStream(path);
        document.write(out);
        out.close();
        document.close();
    }
}
