package models;

import org.mindrot.jbcrypt.BCrypt;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * A contacts object that holds a First Name, Last Name, and Telephone Number.
 */
@Entity
public class User extends Model {

  @Id
  private long id;
  private String email;
  private String password;
  @OneToMany(mappedBy = "user")
  private List<Contact> contacts = new ArrayList<>();


  /**
   * Create a new User object.
   *
   * @param email The email address of the user.
   * @param password The password of the user.
   */
  public User(String email, String password) {

    this.email = email;
    this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
  }

  /**
   * The EBean ORM finder method for database queries on Contacts.
   *
   * @return The finder method for Contacts.
   */
  public static Finder<Long, User> find() {
    return new Finder<Long, User>(Long.class, User.class);
  }

  /**
   * Set the ID.
   *
   * @param id The ID.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Set the email.
   *
   * @param email The email address.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Set the password, encrypted.
   *
   * @param password The password.
   */
  public void setPassword(String password) {
    this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
  }

  /**
   * Set the associated Contact.
   *
   * @param contacts The Contact.
   */
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }

  /**
   * Returns the id value to the caller.
   *
   * @return id long value.
   */
  public long getId() {
    return id;
  }

  /**
   * Get the email address.
   *
   * @return String email address.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Get the encrypted password.
   * NOTE:  We do NOT return a decrypted password for security reasons.
   *
   * @return The encrypted password.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Get the associated Contacts.
   *
   * @return The associated Contacts.
   */
  public List<Contact> getContacts() {
    return contacts;
  }

  /**
   * Add a new contact to the list of contacts associated with this User.
   *
   * @param contact The Contact to Add.
   */
  public void addContact(Contact contact) {
    contacts.add(contact);
  }
}
