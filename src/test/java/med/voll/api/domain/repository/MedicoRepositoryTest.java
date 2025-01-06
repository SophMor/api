package med.voll.api.domain.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
/*
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")*/
class MedicoRepositoryTest {
    @Test
    @DisplayName("Debería devolver médico cuando el médico buscado está disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
    }
/*
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("Debería devolver médico cuando el médico buscado está disponible en esa fecha")
    @Transactional
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        // Arrange
        var lunesSiguienteALas10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registrarMedico("Medico1", "medico@gmail.com", "324822", Especialidad.CARDIOLOGIA);

        // Act
        var medicoLibre = medicoRepository.elegirMedicoAleatorioEnFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);

        // Assert
        assertThat(medicoLibre).isNotNull();
        assertThat(medicoLibre).isEqualTo(medico);
    }
    private MedicoTable registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new MedicoTable(
                new DatosMedico(
                        null, // ID autogenerado
                        nombre,
                        email,
                        documento,
                        especialidad,
                        datosDireccion() // Dirección asociada al médico
                )
        );
        em.persist(medico); // Persistimos el médico en la base de datos
        return medico; // Devolvemos el objeto para usarlo en la prueba
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle x",
                "distrito y",
                "ciudad z",
                "123",
                "1"
        );
    }
*/
}