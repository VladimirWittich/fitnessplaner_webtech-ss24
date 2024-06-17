import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Konstruktor mit allen erforderlichen Parametern
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // Implementiere die erforderlichen Methoden der UserDetails-Schnittstelle
    // ...

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // Weitere Methoden der UserDetails-Schnittstelle implementieren
    // ...

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implementiere hier deine Logik für Kontoablauf
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implementiere hier deine Logik für gesperrte Konten
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implementiere hier deine Logik für abgelaufene Anmeldeinformationen
    }

    @Override
    public boolean isEnabled() {
        return true; // Implementiere hier deine Logik für aktivierte Benutzerkonten
    }
}
