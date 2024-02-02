package javax.validation;

import java.util.List;
import javax.validation.spi.ValidationProvider;
/* loaded from: classes2.dex */
public interface ValidationProviderResolver {
    List<ValidationProvider<?>> getValidationProviders();
}
