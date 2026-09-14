package br.com.powlketbank.features.usuario.domain;


import br.com.powlketbank.features.usuario.endereco.domain.Endereco;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "usuarios")
@Entity(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 100)
    private String usuario;

    @Column(nullable = false, length = 32)
    private String senha;

    @Column(nullable = false, length = 128)
    private String email;

    @Column(nullable = false, length = 128)
    private String nomeCompleto;

    @Embedded
    private Endereco endereco;

    public Usuario(String usuario, String senhaCriptografada){
        this.usuario = usuario;
        this.senha = senhaCriptografada;
    }

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {return List.of();}

    @Override
    public @Nullable String getPassword() {return senha;}

    @Override
    public @Nullable String getUsername() {return usuario;}
}
