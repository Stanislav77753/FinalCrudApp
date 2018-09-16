package main.java.com.airtickets.repository;

import main.java.com.airtickets.exceptions.FileEmptyException;
import main.java.com.airtickets.model.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface Repository<T, ID> {
    void save(T t);
    void delete(T t);
    void update(T t);
    T getById(ID id) throws FileEmptyException;

    default List<String> getAll(File fileName) throws FileEmptyException{
        List<String> entityList = new ArrayList<>();
        Long count = 0L;
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String entityString;
            do{
                entityString = in.readLine();
                if(entityString == null && count == 0){
                    throw new FileEmptyException("\u001B[31m" + "Database " + "\"" + fileName + "\"" + " is empty!");
                }else if(entityString != null){
                    entityList.add(entityString);
                }
                count++;
            }while(entityString != null);
        } catch (FileNotFoundException e) {
            Logger.printLog("File " + "\"" + fileName + "\"" + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entityList;
    }

    default Long getId(File fileName) throws FileEmptyException {
        Long id = 0L;
        String str;
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
            do{
                str = in.readLine();
                if(str == null && id == 0L){
                    throw new FileEmptyException("\u001B[31m" + "Database " + "\"" + fileName + "\"" + " is empty!");
                }
                else if(str != null){
                    id++;
                }
            }while(str != null);
        } catch (FileNotFoundException e) {
            Logger.printLog("File " + "\"" + fileName + "\"" + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }
}
