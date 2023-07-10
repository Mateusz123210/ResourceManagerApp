package com.example.resourcemanagerapp.additionalTypes;

public class EnumChecker {

    public static MyUserType containsUserType(String userType) {
        for (MyUserType m : MyUserType.values()) {
            if (m.name().equals(userType)) {
                return m;
            }
        }
        return null;
    }

    public static MyResourceType containsResourceType(String resourceType) {
        for (MyResourceType m : MyResourceType.values()) {
            if (m.name().equals(resourceType)) {
                return m;
            }
        }
        return null;
    }
}
