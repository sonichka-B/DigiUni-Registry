package repository.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.nio.file.Files;
import java.nio.file.Path;

public class JsonStorageManager {
    private static final Path  FILE_PATH = Path.of("DigiUni_Registry.json");
    private final ObjectMapper mp;

    public JsonStorageManager(){
        mp = new ObjectMapper();
        mp.registerModule(new JavaTimeModule()); //to work with time/data
        mp.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); //for needed format for data (2026-04-10)
        mp.enable(SerializationFeature.INDENT_OUTPUT); //for formatted file
    }

    public void saveData(UniversityStorage data){
        try{
            String json = mp.writeValueAsString(data);
            Files.writeString(FILE_PATH, json);
            System.out.println("Saved data to " + FILE_PATH);
        }catch(Exception e){
            System.out.println("Failed to save"+e.getMessage());
        }
    }

    public UniversityStorage loadData(){
        try{
            if(Files.exists(FILE_PATH) && Files.size(FILE_PATH)>0){
                String json = Files.readString(FILE_PATH);
                System.out.println("Loaded data from " + FILE_PATH);
                UniversityStorage data = mp.readValue(json, UniversityStorage.class);
                return data;
            }
        }catch (Exception e){
            System.out.println("Failed to load data from " + FILE_PATH);
        }
        return null;
    }
}
