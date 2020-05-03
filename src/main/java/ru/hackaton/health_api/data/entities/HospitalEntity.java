package ru.hackaton.health_api.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.health_api.data.dto.HospitalDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "hospital", schema = "health_api")
public class HospitalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

//    @Column(name = "address_id", nullable = false)
//    private Integer addressId;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity addressEntity;

    @Column(name = "available", nullable = false)
    private boolean available;


    public HospitalDto convertToDto(){
        return new HospitalDto(id, addressEntity.getAddress(), available);
    }
}
