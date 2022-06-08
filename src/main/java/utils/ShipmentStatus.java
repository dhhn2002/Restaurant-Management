package utils;

public enum ShipmentStatus {
    TORECIEVE("toreceive", "Đang giao hàng"),
    COMPLETED("completed", "Hoàn thành");
    private String id, name;

    ShipmentStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ShipmentStatus getById(String id) {
        for (ShipmentStatus e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return COMPLETED;
    }

    public static ShipmentStatus getByName(String name) {
        for (ShipmentStatus e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return COMPLETED;
    }
}
