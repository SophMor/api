package med.voll.api.domain.repository;

import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.persistence.MedicoTable;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<MedicoTable,Long> {


    @Query("select m.nombre, m.email, m.documento,m.especialidad from MedicoTable m ORDER BY m.nombre,m.documento asc LIMIT 10")
    List<MedicoTable> findMedicoAndOrder();

   Page<MedicoTable> findByActiveTrue(Pageable pageable);

   @Query("""
            select m from MedicoTable m
            where m.active = true 
            AND m.especialidad = :especialidad
           and m.id not in(
           SELECT c.medico.id from ConsultaTable c where c.fecha = :fecha
           )
            order by rand()
            limit 1
           """)
    MedicoTable elegirMedicoAleatorioEnFecha(Especialidad especialidad, LocalDateTime fecha);

   @Query("""
select m.active from MedicoTable m where m.id = :idMedico
""")
    Boolean findActiveById(Long idMedico);
}

