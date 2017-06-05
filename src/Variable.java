/**
 * Created by ben on 01.06.2017.
 */
public class Variable {
    private String type, name, value;

    public Variable(String type, String name, String value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object b) {
        if (b == null) return false;
        if (b == this) return true;
        if(this.name.equals(((Variable)b).getName())) return true;
        return false;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void changeValue(String value) {
        this.value = value;
    }
}
