package com.example.ReservaTourBack.Tours;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ToursConfig {

    @Bean
    CommandLineRunner commandLineRunner(ToursRepository repository){
        return args -> {

            Tours mulataC = new Tours(
                    "Tour Isla Mulata",
                    "Descubre la joya tropical escondida en el Caribe colombiano con nuestro emocionante tour a la Isla Mulata en las Islas del Rosario. Ubicada a solo una hora en barco desde la vibrante ciudad de Cartagena, esta isla paradisíaca te ofrece un escape perfecto a aguas cristalinas y playas de arena blanca.\n" +
                            "Nuestro tour a la Isla Mulata incluye transporte en barco desde Cartagena, almuerzo tradicional caribeño y tiempo libre para explorar y disfrutar de las maravillas naturales que esta isla única tiene para ofrecer.",
                    "Cartagena de Indias",
                    65000,
                    "10:00 AM",
                    "Playa Bocagrande"
            );

            Tours centroC = new Tours(
                    "Centro Historico Tour",
                    "Al caminar por las estrechas calles adoquinadas del Centro Histórico, te maravillarás con la belleza \n" +
                            "arquitectónica de sus edificios coloniales, adornados con balcones floridos y fachadas coloridas que cuentan historias de siglos pasados.\n" +
                            "\nNuestro tour por el Centro Histórico de Cartagena ofrece una\n" +
                            "experiencia inigualable, con guías expertos que te llevarán en un viaje enriquecedor a través de la historia y la cultura de esta joya caribeña.",
                    "Cartagena de Indias",
                    35000,
                    "7:00 AM",
                    "Centro Uno"

            );

            Tours guatape = new Tours(
                    "Excursión por Guatapé",
                    "¿Quieres conocer el pueblo de la eterna primavera? \n" +
                            "Puedes hacerlo en esta excursión a Guatapé con paseo en barco. Además, te llevaremos a ver la Piedra del Peñol.\n" +
                            "\nNos acercaremos hasta la Piedra del Peñol, un monolito de 220 metros de altura.\n" +
                            "¡Desde la cima tendremos Guatapé a nuestros pies!",
                    "Medellin",
                    25000,
                    "10:30 AM",
                    "Iglesia de San José del Parque del Poblado."

            );



            repository.saveAll(List.of(mulataC,centroC,guatape));

        };
    }

}
