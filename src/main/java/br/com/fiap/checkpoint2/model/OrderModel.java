// 3 e 4 - Criando a classe OrderModel e o pacote model

package br.com.fiap.checkpoint2.model;

// 14, 17.
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

// 10.
@Entity
// 11.
@Getter
@Setter
// 12.
@NoArgsConstructor
// 13.
@AllArgsConstructor
public class OrderModel {

    // 16.
    @GeneratedValue
    // 15.
    @Id
    // 5.
    private long id;
    // 6.
    private String clientName;
    // 7.
    private LocalDate orderDate;
    // 8.
    private BigDecimal totalValue;

    // 9.
    public void prePersist(){
        // 9.
        if(this.orderDate == null) {
            this.orderDate = LocalDate.now();
        }


    }

}
