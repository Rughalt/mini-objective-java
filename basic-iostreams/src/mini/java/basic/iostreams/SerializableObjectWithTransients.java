package mini.java.basic.iostreams;

import mini.java.basic.interfaces.test.IntegerRandomizer;
import mini.java.basic.interfaces.test.StringRandomizer;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SerializableObjectWithTransients implements Serializable {
    private String stringField;
    private Integer integerField;
    transient private List<String> stringList;

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public Integer getIntegerField() {
        return integerField;
    }

    public void setIntegerField(Integer integerField) {
        this.integerField = integerField;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public SerializableObjectWithTransients() {
        stringField = (new StringRandomizer()).next(10);
        stringList = (new StringRandomizer()).nextList(10,20);
        integerField = (new IntegerRandomizer()).next(100);
    }

    @Override
    public String toString() {
        return "SerializableObject{" +
                "stringField='" + stringField + '\'' +
                ", integerField=" + integerField +
                ", stringList=" + stringList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerializableObjectWithTransients that = (SerializableObjectWithTransients) o;
        return Objects.equals(stringField, that.stringField) &&
                Objects.equals(integerField, that.integerField) &&
                Objects.equals(stringList, that.stringList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stringField, integerField, stringList);
    }
}
