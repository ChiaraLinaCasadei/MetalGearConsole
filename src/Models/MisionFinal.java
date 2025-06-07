package Models;

import java.util.Random;
import java.util.Scanner;

public class MisionFinal extends Mision {

    private MetalGear rex = new MetalGear();
    private Snake snake = new Snake();

    @Override
    public Boolean Iniciar() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║ MISIÓN 3 - HANGAR DE METAL GEAR  ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Objetivo: Vencer a Metal Gear!!");

        System.out.println("¡Metal Gear REX aparece!");

        var enBatalla = true;
        var turnoSnake = true;
        Scanner scanner = new Scanner(System.in);

        rex.setVida(100);
        snake.setVida(100);

        System.out.println("SNAKE       " + snake.getVida() + "HP   |   " + rex.getVida() + "HP       REX");
        while (enBatalla) {

            if (turnoSnake) {
                System.out.println("Turno de Snake:");
                System.out.println("1 - Disparar misil          2 - Esquivar");
                System.out.println();
                Random random = new Random();
                String eleccion = scanner.nextLine();
                switch (eleccion) {
                    case "1":
                        snake.setDefensa(0);
                        snake.setAtaque(random.nextInt(10, 31));
                        System.out.println("¡Le diste a REX! (-" + snake.getAtaque() + " HP)");
                        var vidaRex = rex.getVida();
                        rex.setVida(vidaRex - snake.getAtaque());
                        System.out.println("Vida de REX: " + rex.getVida() + " HP.");
                        break;
                    case "2":
                        snake.setDefensa(random.nextInt(50, 101));
                        break;
                    default:
                        System.out.println("No elegiste una opción válida.");
                        continue;
                }
                turnoSnake = false;
            } else {
                //turno metal gear rex
                Random random = new Random();
                rex.setAtaque(random.nextInt(15, 41));

                var defensa = snake.getDefensa();
                if (defensa > 0) {
                    double porcentajeReduccion = defensa / 100.0;
                    int dañoFinal = (int) (rex.getAtaque() * (1 - porcentajeReduccion));
                    rex.setAtaque(dañoFinal);
                    System.out.println("¡Esquivaste! Daño reducido a " + rex.getAtaque() + " HP.");
                } else {
                    System.out.println("¡Metal Gear ataca con un Cañón Láser! (-" + rex.getAtaque() + " HP)");
                }
                var vidaSnake = snake.getVida();
                snake.setVida(vidaSnake - rex.getAtaque());
                System.out.println("Tu vida: " + snake.getVida() + " HP");
                turnoSnake = true;
            }

            if (EsDerrota()) {
                System.out.println("¡Has sido derrotado!");
                System.out.println("Mejor suerte la próxima.");
                System.out.println("Presiona cualquier tecla para volver al menú: ");
                scanner.nextLine();
                break;
            }

            if (EsVictoria()) {
                System.out.println("¡Misión cumplida!");
                var codigoMision = "MISSION3PASSED";
                System.out.println("Este es tu código para guardar tu progreso: " + codigoMision);
                System.out.println("Presiona cualquier tecla para volver al menú: ");
                scanner.nextLine();
                break;
            }

            //enBatalla = false;
        }

        return true;
    }

    @Override
    public Boolean EsVictoria() {

        return (snake.getVida() > 0 && rex.getVida() <= 0);

    }

    @Override
    public Boolean EsDerrota() {
        return snake.getVida() <= 0;
    }

}
