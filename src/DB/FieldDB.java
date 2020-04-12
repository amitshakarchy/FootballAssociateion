package DB;

import AssociationAssets.Field;

import java.util.HashMap;

public class FieldDB {
    HashMap<String, Field> allFields;

    public FieldDB() {
        this.allFields = new HashMap<>();
    }
    public void addField(Field newField,String fieldName){
        allFields.put(fieldName,newField);
    }
    public void removeField(String fieldName){
        this.allFields.remove(fieldName);
    }
}
