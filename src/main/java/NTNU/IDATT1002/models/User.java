package NTNU.IDATT1002.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id @NotBlank(message = "Username may not be blank")
    private String username;

    @Email
    @NotBlank(message = "Email may not be blank")
    private String email;

    @NotBlank(message = "Fist name may not be blank")
    private String firstName;

    @NotBlank(message = "Last name may not be blank")
    private String lastName;

    @NotBlank(message = "Calling code may not be blank")
    private String callingCode;

    @NotBlank(message = "Phone number may not be blank")
    private String phoneNumber;

    @Past(message = "Birth date must be in the past")
    private Date birthDate;
    
    private boolean isAdmin;
    private boolean isActive;


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ImageAlbum> imageAlbums = new ArrayList<>();


    public User() {
    }

    public User(String email, String username, String firstName, String lastName, String callingCode, String phoneNumber, Date birthDate) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.callingCode = callingCode;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.isAdmin = false;
        this.isActive = true;
        this.imageAlbums = imageAlbums;
    }

    public User(User user) {
        this(user.getEmail(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getCallingCode(),
                user.getPhoneNumber(),
                user.getBirthDate(),
                user.isAdmin(),
                user.isActive()
        );
    }

    public User(String email, String username, String firstName, String lastName, String callingCode, String phoneNumber, Date birthDate, boolean isAdmin, boolean isActive) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.callingCode = callingCode;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
        this.imageAlbums = imageAlbums;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCallingCode() {
        return callingCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * Add given image album.
     *
     * @param imageAlbum the image album to add
     */
    public void addImageAlbum(ImageAlbum imageAlbum) {
        imageAlbums.add(imageAlbum);
        imageAlbum.setUser(this);
    }

    /**
     * Remove given image album.
     *
     * @param imageAlbum the image album to remove
     */
    public void removeImageAlbum(ImageAlbum imageAlbum) {
        imageAlbums.remove(imageAlbum);
        imageAlbum.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }
}
