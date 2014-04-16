package com.dstevens.demonstration;

import org.junit.*;
import org.springframework.context.ApplicationContext;

public class FooRepositoryTest {

private static final ApplicationContext APP_CONFIG = ApplicationConfiguration.appConfig();
    
    private FooRepository fooRepository;

    @Before
    public void setUp() {
        fooRepository = APP_CONFIG.getBean(FooRepository.class);
    }
    
    @Test
    public void testSavePlayer() {
        Foo foo = new Foo("some id", "some name");
        Foo savedFoo = fooRepository.save(foo);
        fooRepository.delete(savedFoo);
    }    
}
