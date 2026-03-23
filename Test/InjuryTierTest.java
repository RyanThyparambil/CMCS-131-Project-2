import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InjuryTierTest {

    @Test
    void testEnumContainsExpectedValues() {
        assertNotNull(InjuryTier.valueOf("TIER1_MINOR"));
        assertNotNull(InjuryTier.valueOf("TIER2_FRACTURE"));
        assertNotNull(InjuryTier.valueOf("TIER3_SERIOUS"));
        assertNotNull(InjuryTier.valueOf("TIER4_CRITICAL"));
        assertNotNull(InjuryTier.valueOf("TIER5_TBI"));
    }

    @Test
    void testEnumCount() {
        assertEquals(5, InjuryTier.values().length);
    }
}
