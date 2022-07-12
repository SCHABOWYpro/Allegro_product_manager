package com.jaronet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

    private static String myOpis(String number, String name, String condition, String comm) {
        String opis = "";
        if (condition.equals("Nowy") | condition.equals("nowy") | condition.equals("Nowa")) {
            opis = '\u2022' + name + "\n"
                    + '\u2022' + "Numer części: " + number + "\n"
                    + '\u2022' + "Część nowa nie używana" + comm + "\n" + '\u2022' + "Producent OEM \n" + '\u2022' + "Na sprzedawany produkt wystawiamy paragon lub fakturę \n" + '\u2022' + "Paczki wysyłane są codziennie do godziny 13:00 \n"  + '\u2022' +
                    "Szukasz innych części do tego auta? Zapraszamy na nasze pozostałe aukcje!";;

        }
        else if(condition.equals("Uzywany")||condition.equals("uzywany")||condition.equals("Używany")||condition.equals("Używana")||condition.equals("używana")|| condition.equals("Uzywana")){
            opis = '\u2022'  + name  + "\n"
                    + '\u2022' + "Numer części: "  + number  + "\n"
                    + '\u2022' + "Część sprawna używana" + comm + "\n" + '\u2022' + "Producent OEM \n" + '\u2022' + "Na sprzedawany produkt wystawiamy paragon lub fakturę \n" + '\u2022' + "Paczki wysyłane są codziennie do godziny 13:00 \n"  + '\u2022' +
                    "Szukasz innych części do tego auta? Zapraszamy na nasze pozostałe aukcje!";
        }
        return opis;
    }

    private static String myOpis2(String number, String name, String condition, String comm) {
        String opis = "";
        if (condition.equals("Nowy") | condition.equals("nowy") | condition.equals("Nowa")) {
            opis = '\u2022' + name + "\n"
                    + '\u2022' + "Numer części: " + number + "\n"
                    + '\u2022' + "Część nowa nie używana, " + comm + "\n" + '\u2022' + "Producent OEM \n" + '\u2022' + "Na sprzedawany produkt wystawiamy paragon lub fakturę \n" + '\u2022' + "Paczki wysyłane są codziennie do godziny 13:00 \n"  + '\u2022' +
                    "Szukasz innych części do tego auta? Zapraszamy na nasze pozostałe aukcje!";;

        }
        else if(condition.equals("Uzywany")||condition.equals("uzywany")||condition.equals("Używany")||condition.equals("Używana")||condition.equals("używana")|| condition.equals("Uzywana")){
            opis = '\u2022'  + name  + "\n"
                    + '\u2022' + "Numer części: "  + number  + "\n"
                    + '\u2022' + "Część sprawna używana, " + comm + "\n" + '\u2022' + "Producent OEM \n" + '\u2022' + "Na sprzedawany produkt wystawiamy paragon lub fakturę \n" + '\u2022' + "Paczki wysyłane są codziennie do godziny 13:00 \n"  + '\u2022' +
                    "Szukasz innych części do tego auta? Zapraszamy na nasze pozostałe aukcje!";;
        }
        return opis;
    }



    private static String[] columns = {"Nr części","Tytuł","Komentarz", "Cena", "Stan", "Ilość", "Nr. katalogowy", "Regał","Opis"};
    static List<Part> parts = new ArrayList<>(10000);


    public static void main(String[] args) throws IOException {

        String path = "C:/Users/jarda/OneDrive/Pulpit/excel/tutaj_wklej.csv";
//        String path = "C:/Users/damia/Desktop/Java/tutaj_wklej.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            int i = 0;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(";");
                if (values.length == 7){
                    parts.add(i, new Part(values[0], values[1], values[3], values[4], values[2], values[5], values[6]," "));
                    i++;
                }
                else{
                    parts.add(i, new Part(values[0], values[1], values[3], values[4], values[2], values[5], values[6], values [7]));
                    i++;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Parts");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i=0; i< columns.length; i++){
            Cell cell= headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;

        for (Part part : parts){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(part.name + " "+ part.number);
            row.createCell(1).setCellValue(part.number);
//            row.createCell(2).setCellValue(part.name);
            row.createCell(2).setCellValue(part.price);
            row.createCell(3).setCellValue(part.condition);
//            row.createCell(5).setCellValue(part.amount);
//            row.createCell(6).setCellValue(part.catalog);
//            row.createCell(7).setCellValue(part.shelf);

            for (int j=0; j <= 1; j++){
                row.getCell(j).setCellStyle(headerCellStyle);
            }

            if (part.com.equals(" ")){
                row.createCell(4).setCellValue(myOpis(part.number, part.name, part.condition,part.spacja));

                System.out.println("dupa opis");
            }
            else{
                row.createCell(4).setCellValue(myOpis2(part.number,  part.name, part.condition, part.com));
                System.out.println("opis pełen");
            }
//            row.createCell(9).setCellValue(part.com);
        }

        for (int i=0; i< columns.length; i++){
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("C:/Users/jarda/OneDrive/Pulpit/excel/gotowe_opisy.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();




    }


}

