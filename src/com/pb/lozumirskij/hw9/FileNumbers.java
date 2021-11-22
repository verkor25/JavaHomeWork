package com.pb.lozumirskij.hw9;

import java.io.*;
import java.nio.file.*;
import java.util.Random;
import java.util.logging.Logger;

public class FileNumbers {

    public void createNumbersFile(String fileName) {
        int[][] arr = this.createRandom2DimensionalMassive(10, 10, 100);
        String data = this.convert2DMassiveToString(arr);

        String fullFileName = this.creatorTXTFile(fileName);

        this.writeStringToTXTFile(fullFileName, data);
    }

    public void createOddNumbersFile(String fileNameRead, String fileNameWrite) {
        String read = this.readTXTFile(fileNameRead);
        String read2 = read.replaceAll("\n", "").replaceAll(" ", ",");
        String[] arrRead = read2.replaceAll("\\[", "").replaceAll("]",
                "").replaceAll("\\s", "").split(",");
        StringBuilder builder = new StringBuilder();

        int counter = 0;
        int number = 0;
        for (String s : arrRead) {
            counter++;
            try {
                number = Integer.parseInt(s);
                if (number % 2 == 0) {
                    number = 0;
                }
                builder.append(number);
                builder.append(" ");
                if (counter == 10) {
                    builder.append("\n");
                    counter = 0;
                }
            } catch (NumberFormatException ex) {
                System.out.println("pars problem");
                ex.printStackTrace();
            }
        }
        String data = builder.toString();
        String fullFileName = this.creatorTXTFile(fileNameWrite);
        this.writeStringToTXTFile(fullFileName, data);
    }

    /**
     * create 2 dimensional array
     * @param size1 - size 1 dimension
     * @param size2 - size 2 dimension
     * @param randomBound - bound for random generate exclude bound number
     * @return 2 dimensional massive
     */
    public int[][] createRandom2DimensionalMassive(int size1, int size2, int randomBound) {
        Random rand = new Random();
        int[][] arr = new int [size1][size2];
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j <arr[i].length; j++){
                arr[i][j] = rand.nextInt(randomBound);
            }
        }
        return arr;
    }

    public String convert2DMassiveToString(int [][] arr){
        StringBuilder builder = new StringBuilder();

        for(int[] i: arr){
            for (int j: i) {
                builder.append(j);
                builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * create file in current path
     * @param fileName name of file
     * @return full fileName with path
     */
    public String creatorTXTFile(String fileName){
        Path currentPath = Paths.get("");
        String strPath = currentPath.toAbsolutePath().toString();
        String fullName = strPath+"\\" + fileName;

        File file = new File(fullName);

        try {
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullName;
    }

    public void writeStringToTXTFile(String fullFileName, String data){
        try (Writer writer = new FileWriter(fullFileName)){
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readTXTFile(String fullFileName){
        String line;
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fullFileName))){
            while((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Logger.getGlobal().info("Run file");

        FileNumbers fileNumbers = new FileNumbers();
        fileNumbers.createNumbersFile("numbers.txt");
        System.out.println("numbers in 1-st file");
        System.out.println(fileNumbers.readTXTFile("numbers.txt"));
        System.out.println("---------------------------------- \n");
        System.out.println("numbers in 2-nd file");
        fileNumbers.createOddNumbersFile("numbers.txt", "odd-numbers.txt");
        System.out.println(fileNumbers.readTXTFile("odd-numbers.txt"));

        }
}
