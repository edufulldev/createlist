package edu.dev.createlist.domain.repository;

public interface ConfigPropertyRepository {

    String findByKey(String key);
}
