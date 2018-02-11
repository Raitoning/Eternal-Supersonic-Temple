package yal;

import java.io.File;

public class TestYal {

    public TestYal() {}

    public static void main(String[] args) {


        TestYal test = new TestYal();
        test.testFiles("test");
    }

    public void testFiles(String path) {

        String[] arg = new String[1];

        File folder = new File(path);

        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {

                arg[0] = folder.getPath() + "/" + listOfFiles[i].getName();

                Yal.main(arg);

                try {

                    Thread.sleep(100);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

            } else if (listOfFiles[i].isDirectory()) {

                testFiles(listOfFiles[i].getPath());
            }
        }
    }
}
