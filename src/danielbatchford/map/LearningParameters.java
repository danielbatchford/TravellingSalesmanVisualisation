package danielbatchford.map;


// Simulated annealing
interface LearningParameters {

    /* Represents the probability of picking a worse node at each step, with P(WorseSwap) = e^ [(newCost - oldCost) / temperature)
    Temperature is then updated for each step with temperature = temperature * ALPHA */

    double ALPHA = 0.99;
    double INITIAL_TEMPERATURE = 100;
}
