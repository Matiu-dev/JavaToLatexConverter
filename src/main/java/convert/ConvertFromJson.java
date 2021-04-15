package convert;


import model.TableObject;

public interface ConvertFromJson {
        String loadJson(String path);
        TableObject[] convertToClass(String eventString);
        void writeToFile(TableObject[] tableObject);
}
