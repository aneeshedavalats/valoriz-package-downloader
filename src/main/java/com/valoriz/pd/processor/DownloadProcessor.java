package com.valoriz.pd.processor;

import com.valoriz.pd.enums.Headers;
import com.valoriz.pd.enums.Status;
import com.valoriz.pd.model.Application;
import com.valoriz.pd.model.Url;
import com.valoriz.pd.utils.Constants;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DownloadProcessor {

    private DownloadProcessor() {
    }

    public static void processor(boolean isForDeveloper, boolean isForQm, boolean isForQa, String project) {
        createDirectory();
        filter(Application.applicationManager(), isForDeveloper, isForQm, isForQa, project);
    }

    public static void filter(List<Application> applications, boolean isForDeveloper, boolean isForQm, boolean isForQa,
            String project) {
        if ((isForDeveloper && isForQa) || (isForDeveloper && isForQm)) {
            System.exit(0);
        } else if (isForDeveloper) {
            download(applications.stream().filter(applicationList -> Boolean.TRUE.equals(
                    applicationList.isForDeveloper()) && applicationList.getProjects().contains(project)).toList());
        } else if (isForQa) {
            download(applications.stream().filter(applicationList -> Boolean.TRUE.equals(
                    applicationList.isForQa()) && applicationList.getProjects().contains(project)).toList());
        } else if (isForQm) {
            download(applications.stream().filter(applicationList -> Boolean.TRUE.equals(
                    applicationList.isForQm()) && applicationList.getProjects().contains(project)).toList());
        } else {
            System.exit(0);
        }
    }

    public static void download(List<Application> applications) {
        System.out.printf(Constants.DOWNLOAD_HEADER_STRING_FORMAT, Headers.APPLICATION, Headers.STATUS,
                Headers.PERCENTAGE);
        System.out.printf(Constants.DOWNLOAD_LIST_STRING_FORMAT + Constants.LINE_FEED,
                Constants.DOWNLOAD_HEADER_UNDERLINE, Constants.DOWNLOAD_HEADER_UNDERLINE,
                Constants.DOWNLOAD_HEADER_UNDERLINE);

        for (Application application : applications) {
            String url = createURL(application.getName(), application.getVersion());
            String fileName = createFileName(application.getName(), application.getVersion(), application.getFormat());

            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                    FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {

                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long totalBytesRead = 0;
                long fileSize = new URL(url).openConnection().getContentLengthLong();
                int lastPercentage = 0;

                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    totalBytesRead += bytesRead;
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    int percentage = (int) ((totalBytesRead * 100) / fileSize);
                    if (percentage != lastPercentage) {
                        System.out.printf(Constants.CARRIAGE_RETURN + Constants.DOWNLOAD_LIST_STRING_FORMAT,
                                application.getName(), Status.DOWNLOADING, percentage + Status.DOWNLOADING.getValue());
                        lastPercentage = percentage;
                    }
                }

                System.out.printf(
                        Constants.CARRIAGE_RETURN + Constants.DOWNLOAD_LIST_STRING_FORMAT + Constants.LINE_FEED,
                        application.getName(), Status.DOWNLOADED, Status.DOWNLOADED.getValue());
            } catch (IOException fileNotFoundException) {
                System.out.printf(
                        Constants.CARRIAGE_RETURN + Constants.DOWNLOAD_LIST_STRING_FORMAT + Constants.LINE_FEED,
                        application.getName(), Status.FAILED, Status.FAILED.getValue());
            }
        }
    }

    private static void createDirectory() {
        Path path = Paths.get(Constants.DOWNLOAD_DIRECTORY);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Failed to create applications folder");
            }
        }
    }

    private static String createURL(String applicationName, String version) {
        return Url.getUrl(applicationName, version);
    }

    private static String createFileName(String applicationName, String version, String format) {
        return Constants.DOWNLOAD_DIRECTORY + Constants.FORWARD_SLASH + applicationName + Constants.ADDITION_SIGN + version + format;
    }
}
