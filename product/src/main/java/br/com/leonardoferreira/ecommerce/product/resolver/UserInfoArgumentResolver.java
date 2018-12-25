package br.com.leonardoferreira.ecommerce.product.resolver;

import br.com.leonardoferreira.ecommerce.product.client.UserClient;
import br.com.leonardoferreira.ecommerce.product.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserClient userClient;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(UserInfo.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("X-Token");
        return token == null ? null : userClient.findByToken(token);
    }
}
