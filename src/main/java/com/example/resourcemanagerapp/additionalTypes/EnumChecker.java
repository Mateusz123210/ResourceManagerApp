package com.example.resourcemanagerapp.additionalTypes;

public class EnumChecker {

    public static UserType containsUserType(String userType) {
        for (UserType m : UserType.values()) {
            if (m.name().equals(userType)) {
                return m;
            }
        }
        return null;
    }

    public static ResourceType containsResourceType(String resourceType) {
        for (ResourceType m : ResourceType.values()) {
            if (m.name().equals(resourceType)) {
                return m;
            }
        }
        return null;
    }
}
