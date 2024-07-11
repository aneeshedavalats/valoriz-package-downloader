# Valoriz Package Downloader CLI

The Valoriz Package Downloader (VPD) CLI allows Valoriz employees to download the required applications based on their project and role. This document provides detailed usage instructions for the CLI.

## Table of Contents
1. [Usage](#usage)
2. [Options](#options)
3. [Examples](#examples)

## Usage
vpd [-dhlV] [-dv] [-qa] [-qm] -p=\<project\>

## Options

- `-d, --download`: Download packages.
- `-dv, --developer`: Filter by developer.
- `-h, --help`: Show this help message and exit.
- `-l, --list`: List all available packages.
- `-p, --project=<project>`: Code of the project [amb = Ambient 360, gmg = GMG, mgb = Magrabi]. This is a required option.
- `-qa, --automation-tester`: Filter by automation tester.
- `-qm, --manual-tester`: Filter by manual tester.
- `-V, --version`: Print version information and exit.

## Examples

### List All Packages for a Project
To list all the applications required for the Ambient 360 project:

```sh
vpd -l -p amb
```
### List Packages for a Manual Tester in a Project
To list all the applications required for a manual tester in the Ambient 360 project:

```sh
vpd -l -p amb -qm
```

### Download Packages for a Manual Tester in a Project
To download all the applications required for a manual tester in the Ambient 360 project:

```sh
vpd -p amb -qm -d
```

### List Packages for a Developer in a Project
To list all the applications required for a developer in the GMG project:

```sh
vpd -l -p amb -dv
```

### Download Packages for an Automation Tester in a Project
To download all the applications required for an automation tester in the Magrabi project:

```sh
vpd -p mgb -qa -d
```

### Additional Information
For further assistance, use the help command:

```sh
vpd -h
```

For version information:

```sh
vpd -V
```

## Running the CLI
To run the CLI, do a Gradle build:

```sh
gradle build
```

In the IDE terminal, enter the following command followed by a `vpd` command. For example, to list all the applications required for the Ambient 360 project:

```sh
java -jar build/libs/vpd-cli-1.0.jar -l -p amb
```

### Alternative Method: Using a Batch File
1. After the Gradle build, copy the jar file from build/libs and paste it into a folder (e.g., ValorizPackageDownloader) outside the build directory.

2. Create a vpd.bat file in that folder and paste the below code:
```sh
@echo off

set JAR_PATH=./vpd-cli-1.0.jar
java -jar "%JAR_PATH%" %*
```

3. Now open CMD in that location and start running `vpd` commands. For example, to list all the applications required for the Ambient 360 project:
```sh
vpd -l -p amb
```