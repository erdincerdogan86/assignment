package tr.ee.util.mailsender;

import java.io.Serializable;

public class DataInput implements Serializable {

private String name;
private String surname;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public DataInput(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
