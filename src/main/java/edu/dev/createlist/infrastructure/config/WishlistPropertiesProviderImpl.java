package edu.dev.createlist.infrastructure.config;

import edu.dev.createlist.application.config.WishlistPropertiesProvider;
import edu.dev.createlist.domain.repository.ConfigPropertyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class WishlistPropertiesProviderImpl implements WishlistPropertiesProvider {

    private static final Logger LOGGER = LogManager.getLogger(WishlistPropertiesProviderImpl.class);

    private final ConfigPropertyRepository configRepo;

    public WishlistPropertiesProviderImpl(ConfigPropertyRepository configRepo) {
        this.configRepo = configRepo;
    }

    @Override
    public int getMaxProducts() {
        LOGGER.info("encontrando o valor do proximo produto no configuração do repositorio");
        String  value = configRepo.findByKey("wishlist.maxProducts");
        if(value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException _) {
                LOGGER.warn("valor encontrado para maxProduct {}", value);
                // criar log adequado e retornar valor padrao
            }
        }
        return 6;
    }
}
