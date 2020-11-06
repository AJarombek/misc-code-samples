/*
 * Annotation attached to fields and methods that can only be accessed when holding a lock.
 * Based on: https://jcip.net/annotations/doc/index.html
 * @author Andrew Jarombek
 * @since 11/5/2020
 */

import java.lang.annotation.*;

@Documented
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface GuardedBy {}
