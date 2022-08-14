package database.tables;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class DoctorLoginData {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "login")
    private int login;
    @Basic
    @Column(name = "password")
    private String password;

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorLoginData that = (DoctorLoginData) o;

        if (login != that.login) return false;
        if (!Objects.equals(password, that.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = login;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
