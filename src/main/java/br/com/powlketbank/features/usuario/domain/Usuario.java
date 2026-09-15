package br.com.powlketbank.features.usuario.domain;


import br.com.powlketbank.features.usuario.endereco.domain.Endereco;
import br.com.powlketbank.shared.services.Conversor.CpfCryptoConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(nullable = false, length = 60)
    private String senha;

    @Column(unique = true, nullable = false, length = 128)
    private String email;

    @Column(nullable = false, length = 128)
    private String nomeCompleto;

    @Embedded
    private Endereco endereco;

    @Column(unique = true, nullable = false, name = "cpf", length = 255)
    @Convert(converter = CpfCryptoConverter.class)
    private String cpf;

    public Usuario(String email, String senhaCriptografada){
        this.email = email;
        this.senha = senhaCriptografada;
    }

    @Override
    public @NonNull Collection<? extends GrantedAuthority> getAuthorities() {return List.of();}

    @Override
    public @Nullable String getPassword() {return senha;}

    @Override
    public @Nullable String getUsername() {return email;}
}
