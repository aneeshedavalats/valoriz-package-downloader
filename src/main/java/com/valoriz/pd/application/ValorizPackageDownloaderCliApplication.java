package com.valoriz.pd.application;

import com.valoriz.pd.option.CliOption;
import picocli.CommandLine;

public class ValorizPackageDownloaderCliApplication {

    public static void main(String... args) {
        int exitCode = new CommandLine(new CliOption()).execute(args);
        System.exit(exitCode);
    }

}
