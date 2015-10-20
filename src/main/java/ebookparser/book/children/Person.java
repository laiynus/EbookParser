package ebookparser.book.children;

/**
 * Class containing the data of the person, the author or translator
 */
public class Person {
    /**
     * Last name of the person
     */
    private String lastName;

    /**
     * First name of the person
     */
    private String firstName;

    /**
     * Middle name of the person
     */
    private String middleName;

    /**
     * Nick name of the person
     */
    private String nickName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * The class constructor, fills the fields with null values.
     */
    public Person() {
        lastName = null;
        firstName = null;
        middleName = null;
        nickName = null;
    }

    /**
     * The class constructor, fills the fields from parsing person
     * full name
     *
     * @param name - The person full name
     */
    public Person(String name) {
        name.trim();
        String[] nameParts = name.split("[\\s]+");
        int count = nameParts.length;
        if (count > 2) {
            this.firstName = nameParts[0];
            this.lastName = nameParts[count - 1];
            this.middleName = "";
            for (int index = 1; index < count - 1; index++) {
                this.middleName += nameParts[index];
            }
        } else if (count == 2) {
            this.firstName = nameParts[0];
            this.lastName = nameParts[count - 1];
        } else if (count == 1) {
            this.lastName = nameParts[0];
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}