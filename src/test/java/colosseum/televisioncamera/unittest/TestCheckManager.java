package colosseum.televisioncamera.unittest;

import colosseum.televisioncamera.checks.Check;
import colosseum.televisioncamera.checks.CheckManager;
import colosseum.televisioncamera.checks.combat.FastBow;
import colosseum.televisioncamera.checks.combat.KillauraTypeA;
import colosseum.televisioncamera.checks.combat.KillauraTypeB;
import colosseum.televisioncamera.checks.combat.KillauraTypeC;
import colosseum.televisioncamera.checks.combat.KillauraTypeD;
import colosseum.televisioncamera.checks.combat.KillauraTypeE;
import colosseum.televisioncamera.checks.combat.KillauraTypeF;
import colosseum.televisioncamera.checks.move.Glide;
import colosseum.televisioncamera.checks.move.HeadRoll;
import colosseum.televisioncamera.checks.move.Phase;
import colosseum.televisioncamera.checks.move.Speed;
import colosseum.televisioncamera.checks.move.Timer;
import colosseum.televisioncamera.checks.move.Toggle;
import colosseum.televisioncamera.checks.player.BadPackets;
import colosseum.televisioncamera.checks.player.Scaffold;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

class TestCheckManager {
    private CheckManager checkManager;

    @BeforeEach
    void setup() {
        checkManager = new CheckManager();
    }

    private void assertCheckPresence(Consumer<Boolean> consumer) {
        consumer.accept(checkManager.isActiveCheckInstancePresent(FastBow.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(KillauraTypeA.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(KillauraTypeB.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(KillauraTypeC.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(KillauraTypeD.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(KillauraTypeE.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(KillauraTypeF.class));

        consumer.accept(checkManager.isActiveCheckInstancePresent(Glide.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(HeadRoll.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(Phase.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(Speed.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(Timer.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(Toggle.class));

        consumer.accept(checkManager.isActiveCheckInstancePresent(BadPackets.class));
        consumer.accept(checkManager.isActiveCheckInstancePresent(Scaffold.class));
    }

    private void assertCheckNotPresent() {
        assertCheckPresence(Assertions::assertFalse);
    }

    private void assertCheckPresent() {
        assertCheckPresence(Assertions::assertTrue);
    }

    private void applyCheck(Consumer<Class<? extends Check>> consumer) {
        consumer.accept(FastBow.class);
        consumer.accept(KillauraTypeA.class);
        consumer.accept(KillauraTypeB.class);
        consumer.accept(KillauraTypeC.class);
        consumer.accept(KillauraTypeD.class);
        consumer.accept(KillauraTypeE.class);
        consumer.accept(KillauraTypeF.class);

        consumer.accept(Glide.class);
        consumer.accept(HeadRoll.class);
        consumer.accept(Phase.class);
        consumer.accept(Speed.class);
        consumer.accept(Timer.class);
        consumer.accept(Toggle.class);

        consumer.accept(BadPackets.class);
        consumer.accept(Scaffold.class);
    }

    @Test
    void testGetCheckBySimpleName() {
        Assertions.assertEquals(FastBow.class, CheckManager.getCheckBySimpleName("FastBow"));
        Assertions.assertEquals(KillauraTypeA.class, CheckManager.getCheckBySimpleName("KillauraTypeA"));
        Assertions.assertEquals(KillauraTypeB.class, CheckManager.getCheckBySimpleName("KillauraTypeB"));
        Assertions.assertEquals(KillauraTypeC.class, CheckManager.getCheckBySimpleName("KillauraTypeC"));
        Assertions.assertEquals(KillauraTypeD.class, CheckManager.getCheckBySimpleName("KillauraTypeD"));
        Assertions.assertEquals(KillauraTypeE.class, CheckManager.getCheckBySimpleName("KillauraTypeE"));
        Assertions.assertEquals(KillauraTypeF.class, CheckManager.getCheckBySimpleName("KillauraTypeF"));

        Assertions.assertEquals(Glide.class, CheckManager.getCheckBySimpleName("Glide"));
        Assertions.assertEquals(HeadRoll.class, CheckManager.getCheckBySimpleName("HeadRoll"));
        Assertions.assertEquals(Phase.class, CheckManager.getCheckBySimpleName("Phase"));
        Assertions.assertEquals(Speed.class, CheckManager.getCheckBySimpleName("Speed"));
        Assertions.assertEquals(Timer.class, CheckManager.getCheckBySimpleName("Timer"));
        Assertions.assertEquals(Toggle.class, CheckManager.getCheckBySimpleName("Toggle"));

        Assertions.assertEquals(BadPackets.class, CheckManager.getCheckBySimpleName("BadPackets"));
        Assertions.assertEquals(Scaffold.class, CheckManager.getCheckBySimpleName("Scaffold"));
    }

    @Test
    void testEnableDisableCheck() {
        assertCheckNotPresent();
        applyCheck(v -> Assertions.assertDoesNotThrow(() -> checkManager.enableCheck(v)));
        assertCheckPresent();
        applyCheck(v -> Assertions.assertDoesNotThrow(() -> checkManager.disableCheck(v)));
        assertCheckNotPresent();
    }
}
