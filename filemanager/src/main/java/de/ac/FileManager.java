package de.ac;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public File createNewFile(String fileName, String path, boolean overrideFilesWithSameName, String fileSuffix) {
        path = !path.endsWith("/") ? path + "/" : path;
        fileSuffix = fileSuffix==null ? ".txt" : fileSuffix;
        fileSuffix = fileSuffix.startsWith(".") ? fileSuffix : "."+fileSuffix;
        File file = new File(path+fileName+fileSuffix);
        if (file.exists()) {
            if (overrideFilesWithSameName) {
                file.delete();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println("File Manager: Error while trying to overriding existing file.");
                }
            } else {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.err.println("File Manager: Error while creating file \"" + fileName + "\"");
                }
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("File Manager: Error while creating file \"" + fileName + "\"");
            }
        }
        return file;
    }


    public void deleteFile(String path) {
        File file = new File(path);
        try {
            file.delete();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteFile(File file) {
        try {
            file.delete();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public File getFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            return file;
        } else {
            System.err.println("File Manager: Can't find file at: " + path);
            return null;
        }
    }


    public File writeFile(String content, String filePath, boolean append) {
        try {
            FileWriter writer = new FileWriter(filePath, append);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath);
    }
    public File writeFile(String content, File file, boolean append) {
        try {
            FileWriter writer = new FileWriter(file, append);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public File writeFileNewLine(String content, String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath), true));
            writer.newLine();
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath);
    }
    public File writeFileNewLine(String content, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.newLine();
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public String getLastWrittenLineInFile(String filePath) {
        try {
            Scanner scanner = new Scanner(filePath);
            String s = scanner.nextLine();
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
            }
            scanner.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getLastWrittenLineInFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String s = scanner.nextLine();
            while (scanner.hasNextLine()) {
                s = scanner.nextLine();
            }
            scanner.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getFirstWrittenLineInFile(String filePath) {
        try {
            Scanner scanner = new Scanner(filePath);
            String s = scanner.nextLine();
            scanner.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getFirstWrittenLineInFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String s = scanner.nextLine();
            scanner.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
    protected String[] getFileContent(String filePath) {
        try {
            Scanner scanner = new Scanner(filePath);
            int i =0;
            while (scanner.hasNextLine()) {
                i++;
            }
            String[] s = new String[i];
            int ai = 0;
            while (scanner.hasNextLine()) {
                s[ai] = scanner.nextLine();
                ai++;
            }
            scanner.close();
            return s;
        } catch (Exception e) {
            System.err.println("File Manager: Can't find file at: " + filePath);
        }
        return new String[0];
    }
    protected String[] getFileContent(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int i =0;
            while (scanner.hasNextLine()) {
                i++;
            }
            String[] s = new String[i];
            int ai = 0;
            while (scanner.hasNextLine()) {
                s[ai] = scanner.nextLine();
                ai++;
            }
            scanner.close();
            return s;
        } catch (Exception e) {
            System.err.println("File Manager: Can't find file: " + file.getName());
        }
        return new String[0];
    }
     */


    public List<String> getFileContentAsList(String filePath) {
        try {
            List<String> l = new ArrayList<>();
            Scanner scanner = new Scanner(filePath);
            while (scanner.hasNextLine()) {
                l.add(scanner.nextLine());
            }
            scanner.close();
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getFileContentAsList(File file) {
        try {
            List<String> l = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                l.add(scanner.nextLine());
            }
            scanner.close();
            return l;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public File clearFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File Manager: Can't find file at: " + filePath);
        }
        return file;
    }
    public File clearFile(File file) {
        String filePath = file.getPath();
        if (file.exists()) {
            file.delete();
            try {
                File f = new File(filePath);
                f.createNewFile();
                return f;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("File Manager: Can't find file: " + file.getName());
        }
        return file;
    }


    public File deleteLastLineInFile(String filePath) {
        try {
            RandomAccessFile rndFile = new RandomAccessFile(filePath, "rw");
            rndFile.setLength(rndFile.length()-(getLastWrittenLineInFile(filePath).length()+2));
            rndFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(filePath);
    }
    public File deleteLastLineInFile(File file) {
        try {
            RandomAccessFile rndFile = new RandomAccessFile(file, "rw");
            rndFile.setLength(rndFile.length()-(getLastWrittenLineInFile(file).length()+2));
            rndFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public File overrideFileContent(String filePath, List<String> newFileContent) {
        try {
            File oldFile = new File(filePath);
            String path = oldFile.getPath();
            oldFile.delete();
            File newFile = new File(path);
            newFile.createNewFile();
            newFileContent.forEach(c -> {
                if (getFirstWrittenLineInFile(newFile) == null) writeFile(c, newFile, false);else writeFileNewLine(c, newFile);});
            return newFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(filePath);
    }
    public File overrideFileContent(File file, List<String> newFileContent) {
        try {
            File oldFile = file;
            String path = oldFile.getPath();
            oldFile.delete();
            File newFile = new File(path);
            newFile.createNewFile();
            newFileContent.forEach(c -> {
                if (getFirstWrittenLineInFile(newFile) == null) writeFile(c, newFile, false);else writeFileNewLine(c, newFile);});
            return newFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public File setOnlyRead(String filePath, boolean onlyRead) {
        try {
            File file = new File(filePath);
            file.setWritable(!onlyRead);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File(filePath);
    }
    public File setOnlyRead(File file, boolean onlyRead) {
        try {
            file.setWritable(!onlyRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public boolean doesFileExist(String filePath) {
        return new File(filePath).exists();
    }
    public boolean doesFileExist(File file) {
        return file.exists();
    }


    public void openFile(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openFile(String filePath) {
        try {
            Desktop.getDesktop().open(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getLinesInFile(File file) {
        return getFileContentAsList(file).size();
    }
    public int getLinesInFile(String filePath) {
        return getFileContentAsList(filePath).size();
    }


    public String getContentInLine(int line, File file) {
        return getFileContentAsList(file).get(line);
    }
    public String getContentInLine(int line, String filePath) {
        return getFileContentAsList(filePath).get(line);
    }


    public void writeInLine(int line, String content, File file, boolean append) {
        List<String> newContent = getFileContentAsList(file);
        if (append) {
            newContent.set(line, newContent.get(line) + content);
            overrideFileContent(file, newContent);
        } else {
            newContent.set(line, content);
            overrideFileContent(file, newContent);
        }
    }
    public void writeInLine(int line, String content, String filePath, boolean append) {
        List<String> newContent = getFileContentAsList(filePath);
        if (append) {
            newContent.set(line, newContent.get(line) + content);
            overrideFileContent(filePath, newContent);
        } else {
            newContent.set(line, content);
            overrideFileContent(filePath, newContent);
        }
    }
}
