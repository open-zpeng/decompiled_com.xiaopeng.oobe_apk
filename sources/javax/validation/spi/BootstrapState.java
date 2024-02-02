package javax.validation.spi;

import javax.validation.ValidationProviderResolver;
/* loaded from: classes2.dex */
public interface BootstrapState {
    ValidationProviderResolver getDefaultValidationProviderResolver();

    ValidationProviderResolver getValidationProviderResolver();
}
