package com.jtodo.workWithFiles;

import com.jtodo.status.*;
import com.jtodo.toDoObjects.*;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DataWorker implements IDataWorker {
    private static final String IN_PROCESS = "In process";
    private static final String COMPLETED = "Completed";

    public IStatus defineStatus(String statusStr) {
        IStatus status = null;
        switch (statusStr) {
            case IN_PROCESS:
                status = new InProcess();
                break;
            case COMPLETED:
                status = new Completed();
                break;
        }

        return status;
    }

    @Override
    public IMainList convertDataFromFiles(File dir) throws Exception {
        IMainList list = new MainList();

        File[] files = dir.listFiles();
        assert files != null;

        for (File file : files) {
            String fileName = file.getName();
            String listName = fileName.split("\\.")[0];
            IToDoList newList = new ToDoList(listName);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line;
            line = reader.readLine();
            while (line != null) {
                JSONObject json = new JSONObject(line);
                String name = json.getString("name");
                String status = json.getString("status");

                IStatus dealStatus;
                dealStatus = defineStatus(status);
                IDeal newDeal = new Deal(name, dealStatus);
                newList.addDeal(newDeal);

                line = reader.readLine();
            }
            list.addList(newList);
            reader.close();
        }

        return list;
    }

    @Override
    public void convertDataToFiles(IMainList mainList, File dir) throws Exception {
        List<IToDoList> lists = mainList.getLists();

        deleteDifferences(mainList, dir);

        String catalogPath = dir.toString() + "\\";
        for (IToDoList list : lists) {
            String pathStr = catalogPath + list.getName() + ".txt";

            File newFile = new File(pathStr);
            if(!newFile.exists()) {
                if (!newFile.createNewFile() || !newFile.setExecutable(true))
                {
                    throw new Exception("Creation file failed");
                }
            }
            FileWriter writer = new FileWriter(pathStr);

            List<IDeal> deals = list.getDeals();
            for (IDeal deal : deals) {
                JSONObject json = new JSONObject();
                json.put("name", deal.getName());
                json.put("status", deal.getStatus());
                writer.write(json.toString());
                writer.append('\n');
            }
            writer.flush();
            writer.close();
        }
    }

    private List<String> getFileNamesFromList(List<IToDoList> lists) {
        List<String> fileNames = new ArrayList<>();
        for (IToDoList list : lists) {
            fileNames.add(list.getName());
        }
        return fileNames;
    }

    public void deleteDifferences(IMainList mainList, File dir) throws  Exception {
        IMainList filesList = convertDataFromFiles(dir);
        if (!mainList.equals(filesList)) {
            List<IToDoList> mainLists = mainList.getLists();
            List<IToDoList> loadedLists = filesList.getLists();

            List<String> mainFileNames = getFileNamesFromList(mainLists);
            List<String> fileNames = getFileNamesFromList(loadedLists);

            for (String fileName : fileNames) {
                if (!mainFileNames.contains(fileName)) {
                    fileName = dir.toString() + "\\" + fileName + ".txt";
                    File file = new File(fileName);
                    if (!file.delete()) { throw new Exception("Deletion failed."); }
                }
            }
        }
    }
}