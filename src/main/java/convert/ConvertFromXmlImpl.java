package convert;

import jaxb.gen.TabelaKursow;
import model.TableObject;

import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;

public class ConvertFromXmlImpl implements ConvertFromXml {

        @Override
        public ArrayList<TabelaKursow.Pozycja> getTable(String path) {

                ArrayList<TabelaKursow.Pozycja> pozycje = new ArrayList<>();

                try {
                        JAXBContext jc = JAXBContext.newInstance(TabelaKursow.class);
                        TabelaKursow kursy = (TabelaKursow) jc.createUnmarshaller()
                                .unmarshal(new FileReader(path));

                        for(TabelaKursow.Pozycja tk: kursy.getPozycja()){
                                pozycje.add(tk);
                        }

                } catch(JAXBException e) {
                        System.out.println("error jaxbe: " + e.getMessage());
                } catch (IOException e) {
                        System.out.println("error ioexception: " + e.getMessage());
                }

                return pozycje;
        }

        @Override
        public void writeToFile(ArrayList<TabelaKursow.Pozycja> pozycjaArrayList) {
                try {
                        String fileName = "testXml.tex";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

                        StringBuilder text = new StringBuilder();
                        text.append("\\documentclass{article}\n");


                        text.append("\\usepackage[T1]{fontenc}\n");
                        text.append("\\usepackage[polish]{babel}\n");
                        text.append("\\usepackage[utf8]{inputenc}\n");
                        text.append("\\usepackage{lmodern}");
                        text.append("\\selectlanguage{polish}");

                        text.append("\\usepackage{longtable}");


                        text.append("\\begin{document}\n");
                        text.append("\\begin{center}\n");
                        //start table
                        text.append("\\begin{longtable}{|l|l|l|l|}\n");
                        text.append("\\caption{Kursy walut.} \\label{tab:long} \\\\ \n");

                        text.append("\\hline \\multicolumn{1}{|c|}{\\textbf{nazwa waluty}} & \\multicolumn{1}{c|}{\\textbf{przelicznik}} & \\multicolumn{1}{c|}{\\textbf{kod waluty}} & \\multicolumn{1}{c|}{\\textbf{kurs sredni}} \\\\ \\hline \n");
                        text.append("\\endfirsthead\n");
                        text.append("\\multicolumn{3}{c}%\n");
                        text.append("{{\\bfseries \\tablename\\ \\thetable{} -- continued from previous page}} \\\\ \n");
                        text.append("\\hline \\multicolumn{1}{|c|}{\\textbf{nazwa waluty}} & \\multicolumn{1}{c|}{\\textbf{przelicznik}} & \\multicolumn{1}{c|}{\\textbf{kod waluty}} & \\multicolumn{1}{c|}{\\textbf{kurs sredni}} \\\\ \\hline \n");
                        text.append("\\endhead\n");
                        text.append("\\hline \\multicolumn{3}{|r|}{{Continued on next page}} \\\\ \\hline\n");
                        text.append("\\endfoot\n");
                        text.append("\\hline \\hline\n");
                        text.append("\\endlastfoot\n");

                        for(TabelaKursow.Pozycja p: pozycjaArrayList) {
                                text.append( p.getNazwaWaluty().toString() + " & " + p.getPrzelicznik().toString() + " & "+ p.getKodWaluty().toString() + " & " + p.getKursSredni() +  " \\\\ \n");
                        }

                        text.append("\\end{longtable}\n");
                        text.append("\\end{center}\n");
                        text.append("\\end{document}\n");

                        writer.write(text.toString());

                        writer.close();
                }catch (IOException e) {
                        System.err.println("Exception: " + e);
                }
        }
}
