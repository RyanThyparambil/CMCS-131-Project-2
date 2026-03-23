import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {

    @Test
    void testRandomIntWithinRange() {
        for (int i = 0; i < 100; i++) {
            int value = Simulation.getRandomInt(5, 10);
            assertTrue(value >= 5 && value <= 10);
        }
    }

    @Test
    void testRandomIntMinEqualsMax() {
        int value = Simulation.getRandomInt(7, 7);
        assertEquals(7, value);
    }

    @Test
    void testTimeStartsAtZero() {
        Simulation sim = new Simulation();
        assertEquals(0, sim.getCurrentTime());
    }

    @Test
    void testTimeIncrementsCorrectly() {
        Simulation sim = new Simulation();

        sim.incrementTime();
        assertEquals(1, sim.getCurrentTime());

        sim.incrementTime();
        assertEquals(2, sim.getCurrentTime());
    }

    @Test
    void testTimeDoesNotGoNegative() {
        Simulation sim = new Simulation();
        assertTrue(sim.getCurrentTime() >= 0);
    }
}
