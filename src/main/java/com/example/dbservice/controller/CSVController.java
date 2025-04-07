@RestController
@RequestMapping("/api")
public class CSVController {

    private static final String CSV_FILE_PATH = "data.csv";
    private static String[] cachedHeaderKeys = null;

    @PostMapping("/updateCsv")
    public ResponseEntity<String> updateCsv(@RequestBody Map<String, Object> requestBody) {
        boolean fileExists = Files.exists(Paths.get(CSV_FILE_PATH));

        try (FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {

            // If the CSV doesn't exist yet, write a header from the keys in the JSON
            if (!fileExists) {
                String[] header = requestBody.keySet().toArray(new String[0]);
                csvWriter.writeNext(header);
                cachedHeaderKeys = header;
            } else {
                if (cachedHeaderKeys == null) {
                    cachedHeaderKeys = readHeaderFromFile();
                }
            }

            // Build row in the same order as the header
            String[] row = new String[cachedHeaderKeys.length];
            for (int i = 0; i < cachedHeaderKeys.length; i++) {
                Object value = requestBody.getOrDefault(cachedHeaderKeys[i], "");
                row[i] = (value == null) ? "" : value.toString();
            }
            csvWriter.writeNext(row);

        } catch (IOException e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error writing to CSV file: " + e.getMessage());
        }

        return ResponseEntity.ok("CSV updated successfully!");
    }

    private String[] readHeaderFromFile() throws IOException {
        String firstLine = Files.lines(Paths.get(CSV_FILE_PATH))
                                .findFirst()
                                .orElse("");
        return firstLine.split(",");
    }
}
