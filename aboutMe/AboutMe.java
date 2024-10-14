public class AboutMe {
    private String school;
    private String name;
    int age;

    public AboutMe(String school, String name, int age) {
        this.school = school;
        this.name = name;
        this.age = age;
    }
    public String myName() {
        return(this.name);
    }
    public String mySchool() {
        return(this.school);
    }
    public int myAge() {
        return(this.age);
    }

    public static void main(String[] args) {
        AboutMe me = new AboutMe("beckman", "keijay", 16);
        System.out.println("My name is " + me.myName() + ", and I "
                + "attend " + me.mySchool() + ". I am " + me.myAge()
                + " years old.");
    }
}
