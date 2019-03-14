package pl.com.app.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author JNartowicz
 */
@Entity
@Table(name = "authority")
public class Authority extends Common implements GrantedAuthority {

    @Column(name = "authority")
    @NotNull
    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

}
