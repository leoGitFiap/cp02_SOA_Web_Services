// 3 e 4 - Criando a classe OrderModel e o pacote model

package br.com.fiap.checkpoint2.model;

// 14, 17, 19.
import java.time.LocalDate;

import jakarta.persistence.*;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

// 10.
@Entity
// 11.
@Getter
@Setter
// 12.
@NoArgsConstructor
// 13.
@AllArgsConstructor
// 62.
@Table(name = "Pedidos")
public class OrderModel {
    
    // 15.
    @Id
    // 16.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 5.
    private Long id;

    // 18.
    @NotEmpty(message = "O nome do cliente é obrigatório")
    // 6.
    private String clientName;

    // 7.
    private LocalDate orderDate;
    
    // 21.
    @Positive
    // 20.
    @DecimalMin (value = "0.0", message = "O valor total do pedido não pode ser negativo.")
    // 8.
    private BigDecimal totalValue;

    // 22.
    @PrePersist
    // 9.
    public void prePersist(){
        // 9.
        if(this.orderDate == null) {
            this.orderDate = LocalDate.now();
        }


    }

}
