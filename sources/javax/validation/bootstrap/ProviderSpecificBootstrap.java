package javax.validation.bootstrap;

import javax.validation.Configuration;
import javax.validation.ValidationProviderResolver;
/* loaded from: classes2.dex */
public interface ProviderSpecificBootstrap<T extends Configuration<T>> {
    T configure();

    ProviderSpecificBootstrap<T> providerResolver(ValidationProviderResolver validationProviderResolver);
}
