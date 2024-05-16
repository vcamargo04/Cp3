package br.com.fiap.revisaoapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_clente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome")
    private String nome;
    // Exemplo de validador personalizado usando um padrão regex: @Pattern(regexp = "^[\\w\\-\\.\\+]+@([\\w-]+\\.)+[\\w-]{2,}$", message = "Email inválido")
    @Email(message = "Email inválido")
    @Column(name = "email")
    private String email;
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Column(name = "senha")
    private String senha;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
