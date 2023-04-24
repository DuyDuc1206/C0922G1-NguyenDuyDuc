package com.example.be.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer idOrder;
    private Integer codeOrder;
    private String orderDate;
    private Boolean paymentStatus;
    @Column(columnDefinition = "bit default false")
    private Boolean flagDelete;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    private User uer;
}
