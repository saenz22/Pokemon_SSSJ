package Pokemon_MVC;
import Pokemon_MVC.modelo.Pokemon;

public class App {
    public static void main(String[] args) throws Exception {
        Pokemon Poke1 = Pokemon.instanciarPokemon("Pikachu");
        Poke1.entrada();
    }
}