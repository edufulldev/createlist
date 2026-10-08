package edu.dev.createlist.application.config;

import edu.dev.createlist.application.useCase.WishlistUseCase;
import edu.dev.createlist.application.useCase.WishlistUseCaseImpl;
import edu.dev.createlist.domain.repository.WishlistRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WishlistConfig {

    @Bean
    public WishlistUseCase wishlistUseCase(WishlistRepository wishlistRepository, WishlistPropertiesProvider wishlistPropertiesProvider) {
        return new WishlistUseCaseImpl(wishlistRepository, wishlistPropertiesProvider);
    }
}
