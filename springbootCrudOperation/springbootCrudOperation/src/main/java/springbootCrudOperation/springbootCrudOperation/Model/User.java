package springbootCrudOperation.springbootCrudOperation.Model;

import jakarta.persistence.*;


@Entity
@Table(name="user")
public class User {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String lastname;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public User(String username, String lastname, String emailid, long id) {
        this.username = username;
        this.lastname = lastname;
        this.emailid = emailid;
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailid='" + emailid + '\'' +
                ", id=" + id +
                '}';
    }

    @Column(nullable = false,unique = true)
    private String emailid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


}

