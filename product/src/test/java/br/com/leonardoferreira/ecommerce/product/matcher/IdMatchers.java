package br.com.leonardoferreira.ecommerce.product.matcher;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class IdMatchers extends BaseMatcher<Long> {

    private Long expected;

    public static IdMatchers is(final Long expected) {
        return new IdMatchers(expected);
    }

    @Override
    public boolean matches(final Object item) {
        return item != null && expected.equals(Long.parseLong(String.valueOf(item)));
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("value should is equal to ").appendText(String.valueOf(expected));
    }
}
