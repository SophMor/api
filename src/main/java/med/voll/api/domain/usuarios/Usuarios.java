package med.voll.api.domain.usuarios;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name="Uuario")
@Table(name="usuarios")
@EqualsAndHashCode
public class Usuarios implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String clave;

    public Usuarios(Long id, String login, String clave) {
        this.id = id;
        this.login = login;
        this.clave = clave;
    }

    public Usuarios() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
return true;    }

    @Override
    public boolean isAccountNonLocked() {
return true;
        }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
    return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
