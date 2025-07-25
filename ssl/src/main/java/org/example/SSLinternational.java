package org.example;

import java.io.FileOutputStream;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SSLinternational {

    private static final String FILE_NAME = "SSL_Certificates1.xlsx";

    public static void main(String[] args) {
        System.setProperty("javax.net.debug", "ssl,handshake");

        // List of domains to check
        List<String> domainList = Arrays.asList(
                //Predchamp International
//                "https://play.predchamp.io",
//                "https://tb.predchamp.io",
//                "https://blu.predchamp.io",
//                "https://1049.win.predchamp.io",
//                "https://121.set.predchamp.io",
//                "https://301.live.predchamp.io",
//                "https://lokmat.predchamp.io",
//                "https://201.go.predchamp.io",
//                "https://10.get.predchamp.io",
//                "https://play01.predchamp.io",
                "https://435.game.predchamp.io",
                // Predchamp Domestic
                "https://play.predchamp.com",
                "https://tb.predchamp.com",
                "https://blu.predchamp.com",
                "https://1049.win.predchamp.com",
                "https://999.set.predchamp.com",
                "https://109.live.predchamp.com",
                "https://lokmat.predchamp.com",
                "https://68.go.predchamp.com",
                "https://10.get.predchamp.com",
                "https://play01.predchamp.com",
                "https://101.game.predchamp.com",
                "https://paytm.predchamp.com",
                //Predchamp Admin Urls
                "https://admin2.predchamp.com",
                "https://api2.predchamp.io",
                //BrainyGamez
                "https://brainygamez.com",
                "https://play.brainygamez.com",
                "https://01.go.brainygamez.com",
                "https://tb.brainygamez.com",
                "https://static.brainygamez.com",
                "https://cdn.brainygamez.com",
                "https://staticrepo.brainygamez.com",
                "https://lokmat.brainygamez.co",
                "https://lokmat.brainygamez.com",
                //Brainygamez Admin panel Urls
                "https://admin2.brainygamez.com",
                "https://api2.brainygamez.com",
                //Qureka QuizBytes Urls
                "https://01.fun.qurekaquizbytes.com/open",
                "https://play.qurekaquizbytes.com/open",
                "https://test.qurekaquizbytes.com/open",
                "https://tb.qurekaquizbytes.com/open/intro",
                "https://01.fun.qurekaquizbytes.co/open",
                "https://play.qurekaquizbytes.co/open",
                "https://test.qurekaquizbytes.co",
                "https://tb.qurekaquizbytes.co",
                "https://www.qurekaquizbytes.com",
                "https://fecdn.qurekaquizbytes.com",
                "https://cdn.qurekaquizbytes.com",
                "https://play.quizbytes.co",
                "https://cdn.quizbytes.co",
                "https://static.quizbytes.co",
                //Quiz byte admin panel
                "https://admin2.qurekaquizbytes.co/admin/login",
                "https://api2.qurekaquizbytes.com/admin/login",
                //BrainyBuster
                "https://brainygamez.co",
                "http://tb.brainygamez.co",
                "https://play.brainygamez.co",
                "https://01.go.brainygamez.co",
                "https://static.brainygamez.co",
                "https://tb2cdn.brainygamez.co",
                //BrainyBuster Admin urls
                "https://admin2.brainygamez.co",
                "https://api2.brainygamez.co",
                //Shiffo
                "https://shifoo.io",
                "https://play.shifoo.io",
                //Shifoo Admin Panel urls
                "https://admin20.shifoo.io",
                "https://api20.shifoo.io",
                "https://cdn.shifoo.io",
                //Quizzers
                "https://play.quizzers.co",
                "https://quizzers.co",
                //finmatrix
                "https://play.finmatrixs.com",
                "https://01.game.finmatrixs.com",
                "https://static.finmatrixs.com",
                "https://cdn.finmatrixs.com",
                //finmatrix admin urls
                "https://admin20.finmatrixs.com",
                //viralshots
                "https://get.viralshots.co",
                "https://play01.viralshots.co",
                "https://read.viralshots.co",
                "https://tb.viralshots.co",
                "https://test.viralshots.co",
                "https://01.win.viralshots.co",
                "https://play.viralshots.co",
                "https://viralshots.in",
                "https://play.viralshots.in",
                //viralshots admin
                "https://admin.viralshots.co",
                "https://admin2.viralshots.co",
                "https://api2.viralshots.co",
                "https://api2.theviralshots.com",
                "https://api20.viralshots.in",
                "https://api.viralshots.co",
                "https://admin20.viralshots.in",
                //frodoh
                "https://test.frodoh.studio",
                "https://truereach.frodoh.studio",
                "https://frodoh.qureka.me",
                //qureka me urls
                "https://qurekalite.me",
                "https://01.game.qurekalite.me",
                "https://adsway.qureka.me",
                //qureka mee admin panel
                "https://dashboard.adx.qureka.me",
                "https://connect.qureka.me",
                "https://dashboard.qureka.me",
                "https://newdashboard.qureka.me",
                "https://cricearn.qureka.me",
                "https://feapps.qureka.me",
                "https://singular.qureka.me",
                //"https://frodoh.qureka.me",
                "https://skool.qureka.me",
                //Instant Reel Urls / TG9 Urls / Game Critique Urls
                "https://instantreeldownloadr.com",
                "https://cdn.instantreeldownloadr.com",
                "https://static.instantreeldownloadr.com",
                "https://tg9.in",
                "https://play.gamescritique.com",
                //SpaceWalker
                "https://play.starsshines.com"
        );

        List<String> certificateFoundLogs = new ArrayList<>();
        List<String> certificateNotFoundLogs = new ArrayList<>();

        // Iterate over each URL in the domain list
        for (String domain : domainList) {
            try {
                printSSLCertificateDetails(domain, certificateFoundLogs, certificateNotFoundLogs);
            } catch (SSLHandshakeException e) {
                certificateNotFoundLogs.add("Certificate not found for domain: " + domain);
            } catch (Exception e) {
                System.err.println("Error processing domain: " + domain);
                e.printStackTrace();
            }
        }

        // Write results to an Excel file
        writeToExcel(certificateFoundLogs, certificateNotFoundLogs);
    }

    public static void printSSLCertificateDetails(String urlString, List<String> certificateFoundLogs, List<String> certificateNotFoundLogs) throws Exception {
        HttpsURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setConnectTimeout(10000); // 10 seconds
            conn.setReadTimeout(10000);    // 10 seconds
            conn.connect();

            for (java.security.cert.Certificate cert : conn.getServerCertificates()) {
                if (cert instanceof X509Certificate) {
                    X509Certificate x509Cert = (X509Certificate) cert;

                    LocalDate expiryDate = x509Cert.getNotAfter().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    long daysRemaining = ChronoUnit.DAYS.between(currentDate, expiryDate);

                    certificateFoundLogs.add(String.format(
                            "Domain: %s\nCertificate Issue Date: %s\nCertificate Expiry Date: %s\nDays Remaining: %d",
                            urlString, x509Cert.getNotBefore(), x509Cert.getNotAfter(), daysRemaining));
                    return; // Exit after logging details of the first certificate found
                }
            }

            certificateNotFoundLogs.add("Certificate not found for domain: " + urlString);

        } catch (SSLHandshakeException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Error retrieving SSL certificate for domain: " + urlString, e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static void writeToExcel(List<String> certificateFoundLogs, List<String> certificateNotFoundLogs) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("SSL Certificates");

        int rownum = 0;

        // Write header row
        Row headerRow = sheet.createRow(rownum++);
        headerRow.createCell(0).setCellValue("Domain");
        headerRow.createCell(1).setCellValue("Issue Date");
        headerRow.createCell(2).setCellValue("Expiry Date");
        headerRow.createCell(3).setCellValue("Days Remaining"); // New column for remaining days
        headerRow.createCell(4).setCellValue("Remarks");

        // Write certificate found logs
        for (String log : certificateFoundLogs) {
            String[] parts = log.split("\n");
            String domain = parts[0].replace("Domain: ", "").trim();
            String issueDate = parts[1].replace("Certificate Issue Date: ", "").trim();
            String expiryDate = parts[2].replace("Certificate Expiry Date: ", "").trim();
            String daysRemaining = parts[3].replace("Days Remaining: ", "").trim();

            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(domain);
            row.createCell(1).setCellValue(issueDate);
            row.createCell(2).setCellValue(expiryDate);
            row.createCell(3).setCellValue(daysRemaining);
            row.createCell(4).setCellValue("Certificate Found");
            // Highlight: Add space and print "Brainygamez" after "paytm.predchamp.com"
            //https://435.game.predchamp.io
            if (domain.equals("https://435.game.predchamp.io")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Predchamp Domestic"); // Print "Brainygamez"
            }//https://paytm.predchamp.com
            if (domain.equals("https://paytm.predchamp.com")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Predchamp Admin Urls"); // Print "Brainygamez"
            }
            if (domain.equals("https://api2.predchamp.io")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Brainygamez (Domestic + International)"); // Print "Brainygamez"
            } //"https://lokmat.brainygamez.com"
            if (domain.equals("https://lokmat.brainygamez.com")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Brainygamez Admin panel Urls"); // Print "Brainygamez"
            }//"https://api2.brainygamez.com",
            if (domain.equals("https://api2.brainygamez.com")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Qureka QuizBytes Urls)"); // Print "Brainygamez"
            }https://static.quizbytes.co
            if (domain.equals("https://static.quizbytes.co")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Qureka QuizBytes Admin Panel Urls)"); // Print "Brainygamez"
            }//https://tb2cdn.brainygamez.co
            if (domain.equals("https://api2.qurekaquizbytes.com/admin/login")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("BrainyBusters Urls"); // Print "Brainygamez"
            }//https://tb2cdn.brainygamez.co
            if (domain.equals("https://tb2cdn.brainygamez.co")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("BrainyBusters Admin Panel Urls"); // Print "Brainygamez"
            }// https://api2.brainygamez.co
            if (domain.equals("https://api2.brainygamez.co")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Shifoo (Domestic + International)"); // Print "Brainygamez"
            }//https://play.shifoo.io"
            if (domain.equals("https://play.shifoo.io")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Shifoo Admin Panel Urls"); // Print "Brainygamez"
            }//https://cdn.shifoo.io
            if (domain.equals("https://cdn.shifoo.io")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Quizzers Urls"); // Print "Brainygamez"
            }//https://quizzers.co
            if (domain.equals("https://quizzers.co")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Finmatrixs (Domestic+International)"); // Print "Brainygamez"
            }//https://admin20.finmatrixs.com
            if (domain.equals("https://admin20.finmatrixs.com")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("ViralShots (Domestic+International)"); // Print "Brainygamez"
            }//https://play.viralshots.in
            if (domain.equals("https://play.viralshots.in")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("ViralShots Admin Panel Urls"); // Print "Brainygamez"
            }//https://admin20.viralshots.in
            if (domain.equals("https://admin20.viralshots.in")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Frodoh Admin Panel Urls"); // Print "Brainygamez"
            }//https://frodoh.qureka.me
            if (domain.equals("https://frodoh.qureka.me")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Qureka me urls"); // Print "Brainygamez"
            }//https://adsway.qureka.me
            if (domain.equals("https://adsway.qureka.me")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Qureka me Admin Panel Urls"); // Print "Brainygamez"
            }//https://skool.qureka.me
            if (domain.equals("https://skool.qureka.me")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("Instant Reel Urls / TG9 Urls / Game Critique Urls"); // Print "Brainygamez"
            }//https://play.gamescritique.com
            if (domain.equals("https://play.gamescritique.com")) {
                rownum++; // Add a blank row space
                Row blankRow = sheet.createRow(rownum++);
                blankRow.createCell(0).setCellValue(" "); // Blank space

                Row brainygamezRow = sheet.createRow(rownum++);
                brainygamezRow.createCell(0).setCellValue("SpaceWalker Url"); // Print "Brainygamez"
            }
        }

        // Write certificate not found logs
        for (String log : certificateNotFoundLogs) {
            Row row = sheet.createRow(rownum++);
            row.createCell(0).setCellValue(log.replace("Certificate not found for domain: ", "").trim());
            row.createCell(4).setCellValue("Certificate Not Found");
        }

        // Write to Excel file
        try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
            workbook.write(outputStream);
            System.out.println("Excel file has been generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
