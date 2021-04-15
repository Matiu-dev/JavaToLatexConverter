import convert.ConvertFromJsonImpl;
import convert.ConvertFromXmlImpl;
import jaxb.gen.TabelaKursow;
import model.TableObject;

import java.util.ArrayList;

public class CreateFile {

        private ConvertFromJsonImpl convertFromJson;
        private ConvertFromXmlImpl convertFromXml;
        private TableObject[] tableObject;


        public CreateFile() {
                this.convertFromJson = new ConvertFromJsonImpl();
                this.convertFromXml = new ConvertFromXmlImpl();
        }

        public void createFileFromJson(final String path) {
                String eventString = convertFromJson.loadJson(path);
                tableObject = convertFromJson.convertToClass(eventString);

                try {
                        convertFromJson.writeToFile(tableObject);
                }catch (Exception e){
                        System.out.println(e.getMessage());
                }

        }

        public void createFileFromXml(final String path) {
                ArrayList<TabelaKursow.Pozycja> tabelaKursow = convertFromXml.getTable(path);
                convertFromXml.writeToFile(tabelaKursow);

                for(TabelaKursow.Pozycja p: tabelaKursow) {
                        System.out.println("nazwa_waluty: " + p.getNazwaWaluty());
                        System.out.println("przelicznik: " + p.getPrzelicznik());
                        System.out.println("kod_waluty: " + p.getKodWaluty());
                        System.out.println("kurs_sredni: " + p.getKursSredni());
                }
        }

        public void createFileFromTxtFile() {
                //To-Do
        }
}
