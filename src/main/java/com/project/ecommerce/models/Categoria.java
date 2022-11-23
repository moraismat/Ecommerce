package com.project.ecommerce.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "categoria")
    private Set<Produto> produtos = new HashSet<Produto>();


    public Categoria() {
    }

    public Categoria(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Produto> getProdutos(){
        return produtos;
    }


    public Integer getId() {
        return id;
    }
}
