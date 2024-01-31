package week6.java.cogip.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import week6.java.cogip.entities.User;

import java.util.Collection;
import java.util.List;

public class SecurityUser  implements UserDetails {

    private final User user;

    public SecurityUser(User user){
        this.user = user;
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
        return List.of(authority);
    }
}
