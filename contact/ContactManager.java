package contact;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addContact(Contact contact) {
        contacts.add(contact);
        System.out.println("contact added successfully.");
    }

    public void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("no contact available.");
        } else {
            System.out.println("contacts:");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    public void deleteContact(String name) {
        boolean found = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                contacts.remove(i);
                found = true;
                System.out.println("contact delted succesfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("contact not found.");
        }
    }

    public void updateContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                System.out.print("etner new name: ");
                String newName = scanner.nextLine();
                System.out.print("enter new email: ");
                String newEmail = scanner.nextLine();
                System.out.print("Enter new phone number: ");
                String newPhoneNumber = scanner.nextLine();

                contacts.get(i).setName(newName);
                contacts.get(i).setEmail(newEmail);
                contacts.get(i).setPhoneNumber(newPhoneNumber);
                System.out.println("Contact updated successfully.");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    public void searchContact(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nContact Manager Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Delete Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Search Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    contactManager.addContact(new Contact(name, email, phoneNumber));
                    break;
                case 2:
                    contactManager.viewContacts();
                    break;
                case 3:
                    System.out.print("Enter name of contact to delete: ");
                    String deleteName = scanner.nextLine();
                    contactManager.deleteContact(deleteName);
                    break;
                case 4:
                    System.out.print("Enter name of contact to update: ");
                    String updateName = scanner.nextLine();
                    contactManager.updateContact(updateName);
                    break;
                case 5:
                    System.out.print("Enter name of contact to search: ");
                    String searchName = scanner.nextLine();
                    contactManager.searchContact(searchName);
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }
}
