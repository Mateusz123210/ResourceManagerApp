package com.example.resourcemanagerapp.validators;

import com.example.resourcemanagerapp.additionalTypes.ResourceType;
import com.example.resourcemanagerapp.exceptions.ApplicationException;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class ResourceApiParamsValidator {
    public static void validateAddResourceParameters(String name, Integer userId, ResourceType type, String metadata) {
        if(name == null || name.isEmpty()){
            throw new ApplicationException("Name was not given!");
        }
        if(!StringValidator.checkIfContainsOnlyLettersAndDigits(name)){
            throw new ApplicationException("Resource name should consists only of letters and digits!");
        }
        UserApiParamsValidator.validateUserId(userId);
        validateResourceType(type);
        if(metadata == null || metadata.isEmpty()){
            throw new ApplicationException("Metadata was not given!");
        }
        validateMetadata(metadata);
    }

    public static void validateUpdateResourceNameParameters(Integer id, String newName){
        validateResourceId(id);
        if(!StringValidator.checkIfContainsOnlyLettersAndDigits(newName)) {
            throw new ApplicationException("Resource name should consists only of letters and digits!");
        }
    }

    public static void validateResourceId(Integer id){
        if(id <= 0) {
            throw new ApplicationException("Given resource id is invalid!");
        }
    }

    public static void validateUpdateResourceMetadataParameters(Integer id, ResourceType type, String metadata){
        validateResourceId(id);
        validateResourceType(type);
        validateMetadata(metadata);
    }

    public static void validateResourceType(ResourceType type){
        if(type == null){
            throw new ApplicationException("Resource type was not given!");
        }
    }

    public static void validateMetadata(String metadata){
        try{
            new JSONObject(metadata);
        }catch(JSONException e) {
            throw new ApplicationException("Metadata is not a valid json!");
        }
    }
}
