public class Main {

        private static final String PATH_TO_JSON = System.getProperty("user.dir") +
                "/src/main/resources/table_object_example.json";

        private static final String PATH_TO_XML = System.getProperty("user.dir") +
                "/src/main/resources/pozycja.xml";

        public static void main(String[] args) {

                CreateFile createFile = new CreateFile();
                //createFile.createFileFromJson(PATH_TO_JSON);
                createFile.createFileFromXml(PATH_TO_XML);
        }
}
