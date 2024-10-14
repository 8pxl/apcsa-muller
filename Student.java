public class Student {
    private String name;
    private String lastName;
    private int age;
    
    public Student() {
        name = "keijay";
        lastName = "huang";
        age = 3;
    }

    public Student(String n,String a,int p) {
        name = n;
        lastName = a;
        age = p;
    }

    public String getFullName() {
        return (name + " " + lastName);
    }

    public void hasBirthday() {
        age ++;
    }

    public String getAgeGroup() {
        return(age <= 12 ? "child":"adult");
    }
}
