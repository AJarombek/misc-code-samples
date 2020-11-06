/*
 * Annotation attached to classes that are thread safe.
 * Based on: https://jcip.net/annotations/doc/index.html
 * @author Andrew Jarombek
 * @since 11/5/2020
 */

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@interface ThreadSafe {}
