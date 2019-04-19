package rescueRegister.bindingModels;

import javax.persistence.Column;

public class MountaineerBindingModel {
    private String name;
    private Integer age;
    private String gender;
    private String lastSeenDate;

    public MountaineerBindingModel() {
    }

    public MountaineerBindingModel(String name, Integer age, String gender, String lastSeenDate) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.lastSeenDate = lastSeenDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "lastSeenDate", nullable = false)
    public String getLastSeenDate() {
        return lastSeenDate;
    }

    public void setLastSeenDate(String lastSeenDate) {
        this.lastSeenDate = lastSeenDate;
    }
}
