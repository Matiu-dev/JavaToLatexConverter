package convert;

import com.google.gson.Gson;
import model.TableObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ConvertFromJsonImpl implements ConvertFromJson{

        private Gson gson = new Gson();

        @Override
        public String loadJson(String path) {
                StringBuilder stringBuilder = new StringBuilder();
                try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
                        stream.forEach(s -> stringBuilder.append(s));
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return stringBuilder.toString();
        }

        @Override
        public TableObject[] convertToClass(String eventString) {
                return gson.fromJson(eventString, TableObject[].class);
        }

        @Override
        public void writeToFile(TableObject[] tableObject) {

                try {
                        String fileName = "test.tex";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                        StringBuilder text = new StringBuilder();
                        text.append("\\documentclass{article} \n");
                        text.append("\\begin{document}\n");
                        text.append("\\begin{center}\n");
                        text.append("\\begin{tabular}{c c c}\n");
                        text.append("Miasto 1 & Miasto 2 & odleglosc "+"\\\\"+ "\n");

                        for(TableObject to: tableObject){
                                text.append(to.getCitya() + "&" +
                                        to.getCityb() + "&" +
                                        to.getDistance() + "\\\\"+ "\n");
                        }

                        text.append("\\end{tabular}\n");
                        text.append("\\end{center}\n");
                        text.append("\\end{document}\n");

                        writer.write(text.toString());

                        writer.close();
                }catch (IOException e) {
                        System.err.println("Exception: " + e);
                }

        }
}
