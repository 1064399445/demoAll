package cn.jun.vo;

public class Permission {

    private String id;
    private String permissionId;
    private String permissionName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", permissionName='" + permissionName + '\'' +
                '}';
    }
}
