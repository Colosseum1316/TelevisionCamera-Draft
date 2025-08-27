package colosseum.televisioncamera.checks;

import lombok.Getter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;

@Getter
public final class CheckManager {

    private final HashSet<Check> activeChecks;
    // TODO: ?
    public static ArrayList<Check> AVAILABLE_CHECKS;

    public CheckManager() {
        this.activeChecks = new HashSet<>();
    }

    public HashSet<Check> ACTIVE_CHECKS() {
        return activeChecks;
    }

    public static String getCheckSimpleName(Class<? extends Check> check) {
        return check.getSimpleName();
    }

    public static Class<? extends Check> getCheckBySimpleName(String simpleName) {
        ServiceLoader<Check> serviceLoader = ServiceLoader.load(Check.class);
        for (Object provider : serviceLoader) {
            Class<? extends Check> providerClass = provider.getClass().asSubclass(Check.class);
            if (simpleName.equals(providerClass.getSimpleName())) {
                return providerClass;
            }
        }
        return null;
    }

    public void enableCheck(Class<? extends Check> check) throws Exception {
        if (!this.isActiveCheckInstancePresent(check)) {
            Constructor<? extends Check> constructor = check.getConstructor();
            Check checkInstance = constructor.newInstance();
            this.activeChecks.add(checkInstance);
        }
    }

    public void disableCheck(Class<? extends Check> check) {
        Check instanceToRemove = null;
        for (Check checkInstance : this.activeChecks) {
            if (checkInstance.getClass().equals(check)) {
                instanceToRemove = checkInstance;
                break;
            }
        }
        if (instanceToRemove != null) {
            this.activeChecks.remove(instanceToRemove);
        }
    }

    public boolean isActiveCheckInstancePresent(Class<? extends Check> checkClass) {
        Iterator<Check> iterator = this.activeChecks.iterator();
        Check instance;
        do {
            if (!iterator.hasNext()) {
                return false;
            }
            instance = iterator.next();
        } while (!instance.getClass().equals(checkClass));
        return true;
    }
}
