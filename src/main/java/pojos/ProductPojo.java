package pojos;

public class ProductPojo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getExotic() {
        return exotic;
    }

    public void setExotic(Boolean exotic) {
        this.exotic = exotic;
    }



    private String name;
    private String type;
    private Boolean exotic;

    public ProductPojo(String name, String type, Boolean exotic) {
        this.name = name;
        this.type = type;
        this.exotic = exotic;
    }
}
