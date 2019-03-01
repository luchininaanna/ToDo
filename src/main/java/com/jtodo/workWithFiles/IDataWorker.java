package com.jtodo.workWithFiles;

import com.jtodo.toDoObjects.IMainList;

import java.io.File;

public interface IDataWorker {
    IMainList convertDataFromFiles(File dir) throws Exception;

    void convertDataToFiles(IMainList mainList, File dir) throws Exception;

    void deleteDifferences(IMainList mainList, File dir) throws Exception;
}
