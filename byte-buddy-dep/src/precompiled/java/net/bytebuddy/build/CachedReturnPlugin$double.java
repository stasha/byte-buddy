package net.bytebuddy.build;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import net.bytebuddy.asm.Advice;

/**
 * An advice class for caching a {@code double} value.
 */
@SuppressFBWarnings(value = "NM_CLASS_NAMING_CONVENTION", justification = "Name is chosen to optimize for simple lookup")
class CachedReturnPlugin$double {

    /**
     * A constructor that prohibits the instantiation of the class.
     */
    private CachedReturnPlugin$double() {
        throw new UnsupportedOperationException("This class is merely an advice template and should not be instantiated");
    }

    /**
     * The enter advice.
     *
     * @param cached The cached field's value.
     * @return {@code true} if a cached value exists.
     */
    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    protected static double enter(@CachedReturnPlugin.CacheField double cached) {
        return cached;
    }

    /**
     * The exit advice.
     *
     * @param returned The value that was returned by the method's execution or {@code 0} if it was not executed.
     * @param cached   The previously cached value or {@code 0} if no previous value exists.
     */
    @Advice.OnMethodExit
    @SuppressFBWarnings(value = {"UC_USELESS_VOID_METHOD", "DLS_DEAD_LOCAL_STORE"}, justification = "Advice method serves as a template")
    protected static void exit(@Advice.Return(readOnly = false) double returned, @CachedReturnPlugin.CacheField double cached) {
        if (returned == 0d) {
            returned = cached;
        } else {
            cached = returned;
        }
    }
}
