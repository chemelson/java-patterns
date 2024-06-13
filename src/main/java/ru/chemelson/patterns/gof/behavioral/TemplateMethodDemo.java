package ru.chemelson.patterns.gof.behavioral;

public class TemplateMethodDemo {

    public static void main(String[] args) {
        var csvReport = new CSVReportGenerator();
        csvReport.generateReport();
    }

    // Abstract class
    static abstract class ReportGenerator {
        protected abstract String collectData();
        protected abstract String processData(String data);
        protected abstract String formatReport(String processedData);

        private void printReport(String report) {
            System.out.println(report);
        }

        public void generateReport() {
            var data = collectData();
            var processedData = processData(data);
            var report = formatReport(processedData);
            printReport(report);
        }
    }

    // Concrete implementor
    static class CSVReportGenerator extends ReportGenerator {
        protected String collectData() {
            return "CSV Data";
        }

        protected String processData(String data) {
            return "Processed " + data;
        }

        protected String formatReport(String processedData) {
            return "CSV Report: " + processedData;
        }
    }

}
