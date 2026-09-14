FROM postgres:16-alpine

# Define as variáveis padrão do banco de dados
ENV POSTGRES_DB=powlketbank_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

# Expõe internamente a porta padrão do PostgreSQL
EXPOSE 5432