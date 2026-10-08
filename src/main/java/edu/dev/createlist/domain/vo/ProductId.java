package edu.dev.createlist.domain.vo;


public record ProductId(
        String value
) {
    public ProductId {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("Produto não pode ser nulo ou branco");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ProductId(String value1))) return false;
        return value.equals(value1);
    }

    @Override
    public String toString() {
        return value;
    }
}
