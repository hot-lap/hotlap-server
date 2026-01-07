package com.oops.domain.auth.resolver;

import com.oops.application.auth.AuthService;
import com.oops.domain.auth.model.AuthConstants;
import com.oops.domain.auth.model.AuthUser;
import com.oops.domain.auth.model.AuthUserToken;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final AuthService authService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthUser.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        var httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        var accessToken = httpServletRequest.getHeader(AuthConstants.AUTH_TOKEN_KEY);

        if (accessToken == null) {
            if (parameter.isOptional()) {
                return null;
            }

            accessToken = "";
        }

        var token = new AuthUserToken(accessToken);

        return authService.resolveAuthUser(token);
    }
}
