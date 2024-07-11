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

