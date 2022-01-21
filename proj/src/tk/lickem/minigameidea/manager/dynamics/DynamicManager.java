package tk.lickem.minigameidea.manager.dynamics;

import lombok.SneakyThrows;
import org.atteo.classindex.ClassIndex;
import tk.lickem.minigameidea.Plugin;
import tk.lickem.minigameidea.manager.dynamics.anno.Init;

import java.lang.reflect.Constructor;

public class DynamicManager {

    // Class registry using reflection
    // Pretty cool api ngl, makes my life a little easier

    public DynamicManager() {
    }

    public static void init() {
        ClassIndex.getAnnotated(Init.class, Plugin.class.getClassLoader()).forEach(DynamicManager::instance);
    }

    @SneakyThrows
    private static <T> T instance(Class<T> clazz) {
        Constructor<T> e = clazz.getDeclaredConstructor();
        e.setAccessible(true);
        return e.newInstance();
    }
}
