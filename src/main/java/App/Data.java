package App;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Data {
    public static int TempID;
    public static String CSVDirectory =  "CSV data";
    public static UserFactory userFactory = new UserFactory();
    public static Map<Integer, User> Users = new HashMap<>();
    public static Map<Integer, User> Admins = new HashMap<>();
    public static Map<Integer, User> DeletedUsers = new HashMap<>();
    public static Map<Integer, User> PremiumUsers =new HashMap<>();

    public static Map<String, double[]> loadCSVFilesToHashMap(String directoryPath) throws IOException {
        Map<String, double[]> map = new HashMap<>();

        // Get the list of CSV files in the directory
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".csv"));

        if (files != null) {
            for (File file : files) {
                // Get the key from the file name (without the .csv extension)
                String key = file.getName().replace(".csv", "");

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    String lastLine = null;

                    // Read through the file to get the last line
                    while ((line = br.readLine()) != null) {
                        lastLine = line;
                    }

                    if (lastLine != null) {
                        // Split the last line by commas and convert to an int array
                        String[] stringValues = lastLine.split(",");
                        double[] intValues = new double[stringValues.length - 1];
                        for (int i = 0; i < stringValues.length - 1; i++) {
                            intValues[i] = Double.parseDouble(stringValues[i + 1]);
                        }

                        // Put the key and the int array into the hash map
                        map.put(key, intValues);
                    }
                }
            }
        }

        return map;
    }

    public static void printHashMap(Map<String, double[]> map) {
        for (Map.Entry<String, double[]> entry : map.entrySet()) {
            System.out.print("Key: " + entry.getKey() + ", Values: ");
            for (double value : entry.getValue()) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }




/*************************/
//    public final static String[] popularSymbols = new String[]{"AAPL", "GOOGL", "MSFT", "AMZN", "FB", "TESLA", "NVDA", "NFLX",
//            "BABA", "V", "JNJ", "JPM", "WMT", "PG"};

    public static Map<String, double[]> stockData;
    public static void initStockData() throws IOException {
        stockData=loadCSVFilesToHashMap(CSVDirectory);
    }

    public static void addSymbol(String symbol,double initialPrice){
        // min, max, opening, closing, initialPrice

         stockData.put(symbol, new double[]{initialPrice, initialPrice, initialPrice, initialPrice, initialPrice});
    }


    public static void exportToCSV(String directoryPath) throws IOException {
        // Get the current date
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // Ensure the directory exists, create if it doesn't
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (Map.Entry<String, double[]> entry : stockData.entrySet()) {
            String key = entry.getKey();
            double[] values = entry.getValue();

            String filePath = directoryPath + File.separator + key + ".csv";
            File file = new File(filePath);
            boolean fileExists = file.exists();

            // Create a CSV file with the key as the file name
            try (FileWriter writer = new FileWriter(filePath, true)) {
                if (fileExists) {
                    writer.write(System.lineSeparator()); // Start a new line if appending
                }
                // Write the current date as the first column
                writer.write(currentDate);

                // Write the values to the CSV file
                for (double value : values) {
                    writer.write("," + value);
                }

                // Remove the trailing comma
                writer.flush();
            } catch (IOException e) {
                System.err.println("Error writing to file " + filePath + ": " + e.getMessage());
            }
        }
    }


    public static boolean UsernameIsAvailable(String username){
        for (Map.Entry<Integer, User> set : Users.entrySet()) {
            if (Objects.equals(username, set.getValue().GetUsername())) {

                return false;
            }
        }
        return true;
    }

    public static boolean AdminNameIsAvailable(String username){
        for (Map.Entry<Integer, User> set : Admins.entrySet()){
            if (Objects.equals(username, set.getValue().GetUsername())){
                return false;
            }
        }

        return true;
    }


    public static User VerifyLogin(String username , String password){
            for (Map.Entry<Integer, User> set : Users.entrySet()) {
                if (Objects.equals(username, set.getValue().GetUsername()) && Objects.equals(password, set.getValue().getPassword())) {
                    return set.getValue();
                }
            }
        return null;
    }

    public static boolean Verify_AdminLogin(String username , String password){

        // System.out.println(Admins);
        for (Map.Entry<Integer, User> set : Admins.entrySet()) {
            if (Objects.equals(username, set.getValue().GetUsername()) && Objects.equals(password, set.getValue().getPassword())) {
                return true;
            }
        }
        return false;
    }

    public static User VerifyAdminLogin(String username , String password){

        // System.out.println(Admins);
        for (Map.Entry<Integer, User> set : Admins.entrySet()) {
            if (Objects.equals(username, set.getValue().GetUsername()) && Objects.equals(password, set.getValue().getPassword())) {
                return set.getValue();
            }
        }
        return null;
    }

    public static User isInUsers(String username){
        for (Map.Entry<Integer, User> set : Users.entrySet()) {
            if (Objects.equals(username, set.getValue().GetUsername())) {

                return set.getValue();
            }
        }
        return null;
    }


    public static void setUsers(String username , String password){
        User user = userFactory.GetUser(UserFactory.NORMAL, username, password);
        Users.put(user.getId(), user);

    }

}
