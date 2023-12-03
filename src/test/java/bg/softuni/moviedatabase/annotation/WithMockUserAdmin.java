package bg.softuni.moviedatabase.annotation;

import org.springframework.security.test.context.support.WithMockUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @interface WithMockUserAdmin {
    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username = "dido956", password = "123456", roles = {"ADMIN"})
    public @interface WithMockAdminUser {
    }
}
