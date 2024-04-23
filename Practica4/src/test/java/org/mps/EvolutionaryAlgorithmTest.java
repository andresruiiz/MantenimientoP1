/*
 * @author 1: Nicoló Melley
 * @author 2: Andrés Ruiz Sánchez
 */

package org.mps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.OnePointCrossover;
import org.mps.mutation.MutationOperator;
import org.mps.mutation.SwapMutation;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;

import static org.junit.jupiter.api.Assertions.*;

public class EvolutionaryAlgorithmTest {

    @Test
    @DisplayName("Cuando se llama al constructor con argumentos válidos, entonces se crea una instancia de EvolutionaryAlgorithm")
    public void testConstructorWithValidArguments() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();

        // Act
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Assert
        assertNotNull(ea);
    }

    @Test
    @DisplayName("Cuando se llama al constructor con un operador de selección nulo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testConstructorWithNullSelectionOperator() {
        // Arrange
        SelectionOperator selectionOperator = null;
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("Cuando se llama al constructor con un operador de mutación nulo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testConstructorWithNullMutationOperator() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = null;
        CrossoverOperator crossoverOperator = new OnePointCrossover();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("Cuando se llama al constructor con un operador de cruce nulo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testConstructorWithNullCrossoverOperator() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("Cuando se crea un TournamentSelection con un tamaño de torneo no válido, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testWithTournamentSizeZero() throws EvolutionaryAlgorithmException {
        // Arrange
        int tournamentSize = 0;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new TournamentSelection(tournamentSize));
    }

    @Test
    @DisplayName("Cuando se optimiza una población nula, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithNullPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = null;
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población vacía, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithEmptyPopulation() throws EvolutionaryAlgorithmException {
        // Arrange
        int[][] population = {};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        
        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población con el primer individuo nulo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithFirstIndividualNull() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {null, {1, 2, 3}};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población con el primer individuo vacío, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithFirstIndividualEmpty() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{}, {1, 2, 3}};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población con el último individuo nulo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithNullIndividualInPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, null};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población de tamaño par, entonces se devuelve una población de individuos")
    public void testOptimizeWithEvenSizedPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act
        int[][] result = ea.optimize(population);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Cuando se optimiza una población de tamaño impar, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithOddSizedPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SelectionOperator selectionOperator = new TournamentSelection(population.length);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población con un individuo vacío, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithEmptyIndividual() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {}};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población con un único individuo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithSingleIndividualPopulation() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }

    @Test
    @DisplayName("Cuando se optimiza una población con individuos de diferente tamaño, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testOptimizeWithIndividualsOfDifferentSizes() throws EvolutionaryAlgorithmException{
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5}};
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(population));
    }


    //Esta es la unica manera de probar que la excepcion se lanza ya que si un individuo es nulo por el metodo optimize no se llega a llamar al metodo mutate.
    @Test
    @DisplayName("Cuando se llama al método mutate con un individuo nulo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testMutateWithNullIndividual() throws EvolutionaryAlgorithmException {
        // Arrange
        MutationOperator mutationOperator = new SwapMutation();
        int[] individual = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> mutationOperator.mutate(individual));
    }

    @Test
    @DisplayName("Cuando se crea un torneo con un tamaño de población menor que el tamaño del torneo, entonces se lanza una excepción de tipo EvolutionaryAlgorithmException")
    public void testTournamentSelectionWithPopulationSizeLessThanTournamentSize() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(5);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();
        EvolutionaryAlgorithm ea = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        int[] population = {1, 2, 3};

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> ea.optimize(new int[][]{population}));
    }

    

}