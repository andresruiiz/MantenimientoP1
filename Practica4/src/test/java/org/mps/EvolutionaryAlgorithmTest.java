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
    @DisplayName("Prueba constructor con argumentos correctos")
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
    @DisplayName("Prueba constructor con operador de selección nulo")
    public void testConstructorWithNullSelectionOperator() {
        // Arrange
        SelectionOperator selectionOperator = null;
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = new OnePointCrossover();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("Prueba constructor con operador de mutación nulo")
    public void testConstructorWithNullMutationOperator() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = null;
        CrossoverOperator crossoverOperator = new OnePointCrossover();

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("Prueba constructor con operador de cruce nulo")
    public void testConstructorWithNullCrossoverOperator() throws EvolutionaryAlgorithmException {
        // Arrange
        SelectionOperator selectionOperator = new TournamentSelection(2);
        MutationOperator mutationOperator = new SwapMutation();
        CrossoverOperator crossoverOperator = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator));
    }

    @Test
    @DisplayName("Prueba con tamaño del torneo igual a 0")
    public void testWithTournamentSizeZero() throws EvolutionaryAlgorithmException {
        // Arrange
        int tournamentSize = 0;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> new TournamentSelection(tournamentSize));
    }

    @Test
    @DisplayName("Prueba con una población nula")
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
    @DisplayName("Prueba con una población vacía")
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
    @DisplayName("Prueba con el primer elemento de la población nulo")
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
    @DisplayName("Prueba con una población con el primer individuo vacío")
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
    @DisplayName("Prueba con una población donde al menos un individuo sea nulo")
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
    @DisplayName("Prueba con una población de tamaño par")
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
    @DisplayName("Prueba con una población de tamaño impar")
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
    @DisplayName("Prueba con una población con un array vacío")
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
    @DisplayName("Prueba con una población de un solo individuo")
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
    @DisplayName("Prueba con una población con individuos de distinto tamaño")
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
    @DisplayName("Prueba SwapMutation con un individuo nulo")
    public void testMutateWithNullIndividual() throws EvolutionaryAlgorithmException {
        // Arrange
        MutationOperator mutationOperator = new SwapMutation();
        int[] individual = null;

        // Act & Assert
        assertThrows(EvolutionaryAlgorithmException.class, () -> mutationOperator.mutate(individual));
    }

    @Test
    @DisplayName("Prueba a crear un torneo con un tamaño de poblacion menor a este")
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