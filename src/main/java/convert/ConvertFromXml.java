package convert;

import jaxb.gen.TabelaKursow;
import model.TableObject;

import java.util.ArrayList;

public interface ConvertFromXml {

        ArrayList<TabelaKursow.Pozycja> getTable(String path);
        void writeToFile(ArrayList<TabelaKursow.Pozycja> pozycjaArrayList);
}
