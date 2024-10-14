public class Book {
    private String name;
    private String author;
    private int pages;

    public Book() {
        name = "None";
        author = "None";
        pages = -1;
    }

    public Book(String n,String a,int p) {
        name = n;
        author = a;
        pages = p;
    }
    public String getSummary() {
        return ( "Title: " + name + ", Author: " + author + ", Pages: " + pages);
    }

    public void setAuthor(String newName) {
        author = newName;
    }

    public void doublePages() {
        pages *= 2;
    }

    public static void main(String [] args) {
        Book myBook = new Book("ym", "ym", 312);
        System.out.println(myBook.getSummary());
        myBook.setAuthor("me!");
        myBook.doublePages();
        System.out.println(myBook.getSummary());
    }
}

